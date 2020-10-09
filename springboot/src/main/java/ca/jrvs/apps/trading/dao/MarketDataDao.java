package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.config.MarketDataConfig;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import ca.jrvs.apps.trading.util.JsonUtil;
import com.google.common.collect.Iterables;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.apache.http.conn.HttpClientConnectionManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * MarketDataDao is responsible for getting quotes data from IEX
 * Responsible for endpoint "GET /quote/iex/ticker/{ticker}"
 */
@Repository
public class MarketDataDao implements CrudRepository<IexQuote, String> {

    private static final String IEX_BATCH_PATH = "/stock/market/batch?symbols=%s&types=quote&token=";
    private final String IEX_BATCH_URL;

    private static final Logger logger = LoggerFactory.getLogger(MarketDataDao.class);
    private final HttpClientConnectionManager httpClientConnectionManager;

    @Autowired
    public MarketDataDao(HttpClientConnectionManager httpClientConnectionManager, MarketDataConfig config) {
        this.httpClientConnectionManager = httpClientConnectionManager;
        IEX_BATCH_URL = config.getHost() + IEX_BATCH_PATH + config.getToken();
    }

    /**
     * Get an IexQuote (findAllById() as helper method)
     *
     * @param ticker: targeted ticker
     * @throws DataRetrievalFailureException if the HTTP request failed.
     * @return the quote for the input ticker
     */
    @Override
    public Optional<IexQuote> findById(String ticker) {
        List<IexQuote> quotes = findAllById(Collections.singletonList(ticker));

        if (quotes.size() == 0) {
            return Optional.empty();
        } else if (quotes.size() == 1) {
            return Optional.of(quotes.get(0));
        } else {
            throw new DataRetrievalFailureException("error: unexpected number of quotes.");
        }
    }

    /**
     *
     * @param tickers a list of ticker
     * @return a list of IexQuote objects
     * @throws DataRetrievalFailureException if HTTP request failed.
     */
    @Override
    public List<IexQuote> findAllById(Iterable<String> tickers) {
        if (Iterables.size(tickers) == 0) {
            throw new IllegalArgumentException("error: given list of tickers is empty.");
        } else {
            List<IexQuote> quotes = new ArrayList<>();
            String url = String.format(IEX_BATCH_URL, String.join(",", tickers));
            Optional<String> getResponse = executeHttpGet(url);
            if (getResponse.isPresent()) {
                String response = getResponse.get();
                JSONObject jsonObject = new JSONObject(response);
                for (String ticker : tickers) {
                    if (jsonObject.has(ticker)) {
                        try {
                            String quote = jsonObject.getJSONObject(ticker).getJSONObject("quote").toString();
                            quotes.add(JsonUtil.toObjectFromJson(quote, IexQuote.class));
                        } catch (IOException ex) {
                            throw new RuntimeException("error: unable to convert JSON String to IexQuote objects.");
                        }
                    }
                }
            } else {
                // if getResponse is an Optional.empty(), that means no valid tickers given in the request URI.
                throw new IllegalArgumentException("error: none of the input tickers are valid.");
            }
            return quotes;
        }
    }

    /**
     * Execute a GET and return http entity/body as a String.
     * @param url resource url
     * @return http response body or Optional.empty() for 404 response
     * @throws DataRetrievalFailureException if HTTP failed or status code is unexpected
     */
    private Optional<String> executeHttpGet(String url) {
        HttpClient httpClient = getHttpClient();
        HttpGet getRequest = new HttpGet(url);
        logger.debug(getRequest.getURI().toString());
        HttpResponse getResponse;
        // obtain HTTP response JSON from the provided url.
        try {
            getResponse = httpClient.execute(getRequest);
        } catch (IOException ex) {
            throw new RuntimeException("error: url is not valid");
        }

        // interpret the obtained HTTP response.
        int status = getResponse.getStatusLine().getStatusCode();
        try {
            if (status == 404) {
                return Optional.empty();
            } else if (status != 200) {
                throw new DataRetrievalFailureException("error: unexpected HTTP status code (not 200 or 404).");
            } else {
                return Optional.of(EntityUtils.toString(getResponse.getEntity()));
            }
        } catch (IOException e) {
            throw new RuntimeException("error: unable to convert response JSON to String.");
        }
    }

    /**
     * Borrow a HTTP client from the HttpClientConnectionManager
     * @return a HttpClient object
     */
    private CloseableHttpClient getHttpClient() {
        return HttpClients.custom()
                .setConnectionManager(httpClientConnectionManager)
                .setConnectionManagerShared(true)
                .build();
    }

    @Override
    public <S extends IexQuote> S save(S s) {
        throw new UnsupportedOperationException("error: method not implemented.");
    }

    @Override
    public <S extends IexQuote> Iterable<S> saveAll(Iterable<S> iterable) {
        throw new UnsupportedOperationException("error: method not implemented.");
    }

    @Override
    public boolean existsById(String s) {
        throw new UnsupportedOperationException("error: method not implemented.");
    }

    @Override
    public Iterable<IexQuote> findAll() {
        throw new UnsupportedOperationException("error: method not implemented.");
    }

    @Override
    public long count() {
        throw new UnsupportedOperationException("error: method not implemented.");
    }

    @Override
    public void deleteById(String s) {
        throw new UnsupportedOperationException("error: method not implemented.");
    }

    @Override
    public void delete(IexQuote iexQuote) {
        throw new UnsupportedOperationException("error: method not implemented.");
    }

    @Override
    public void deleteAll(Iterable<? extends IexQuote> iterable) {
        throw new UnsupportedOperationException("error: method not implemented.");
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("error: method not implemented.");
    }

    public HttpClientConnectionManager getHttpClientConnectionManager() {
        return httpClientConnectionManager;
    }
}
