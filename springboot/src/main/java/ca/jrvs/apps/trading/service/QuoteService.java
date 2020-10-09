package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.MarketDataDao;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import ca.jrvs.apps.trading.model.domain.Quote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class QuoteService {

    private static final Logger logger = LoggerFactory.getLogger(QuoteService.class);

    private final QuoteDao quoteDao;
    private final MarketDataDao marketDataDao;

    @Autowired
    public QuoteService(QuoteDao quoteDao, MarketDataDao marketDataDao) {
        this.quoteDao = quoteDao;
        this.marketDataDao = marketDataDao;
    }

    /**
     * Find an IexQuote by ticker.
     *
     * @param ticker targeted ticker
     * @return corresponding IexQuote object
     */
    public IexQuote findIexQuoteByTicker(String ticker) {
        return marketDataDao.findById(ticker)
                .orElseThrow(() -> new IllegalArgumentException("error: input ticker [" + ticker + "] is invalid."));
    }

    /**
     * Update quote table against IEX source
     *  - get all quotes from the database
     *  - foreach ticker get IexQuote
     *  - convert IexQuote to quote entity
     *  - persist quote to database
     *
     * @throws // ca.jrvs.apps.trading.dao.ResourceNotFoundException if ticker is not found from IEX
     * @throws org.springframework.dao.DataAccessException if unable to retreive data
     * @throws IllegalArgumentException for invalid input
     */
    public List<Quote> updateMarketData() {
        List<Quote> allQuotes = findAllQuotes();
        for (Quote quote : allQuotes) {
            String ticker = quote.getTicker();
            IexQuote iexQuote = findIexQuoteByTicker(ticker);
            Quote updatedQuote = buildQuoteFromIexQuote(iexQuote);
            saveQuote(updatedQuote);
        }

        return findAllQuotes();
    }

    /**
     * Helper method for updateMarketData(). Map a IexQuote to a Quote entity.
     * Note: 'iexQuote.getLatestPrice() == null' if the stock market is closed.
     * Make sure set a default value for number field(s).
     *
     * @param iexQuote IexQuote object
     * @return converted Quote object
     */
    protected static Quote buildQuoteFromIexQuote(IexQuote iexQuote) {
        Quote quote = new Quote();
        quote.setTicker(iexQuote.getSymbol());
        quote.setBidPrice(iexQuote.getIexBidPrice());
        quote.setBidSize((int) iexQuote.getIexBidSize());
        quote.setAskSize((int) iexQuote.getIexAskSize());
        quote.setAskPrice(iexQuote.getIexAskPrice());
        if (iexQuote.getLatestPrice() == 0.0) {
            // if stock market closed, iexQuote.getLatestPrice() == null, set last price to be default -1.
            // note that Java double cannot be null, if it is not set, it will automatically set to 0.0
            quote.setLastPrice(-1d);
        } else {
            quote.setLastPrice(iexQuote.getLatestPrice());
        }
        return quote;
    }

    /**
     * Validate (against IEX) and save given tickers to quote table.
     *
     *  - get iexQuote(s)
     *  - convert each iexQuote to Quote entity
     *  - persist the quote to database
     */
    public List<Quote> saveQuotes(List<String> tickers) {
        List<Quote> quotes = new ArrayList<>();
        for (String ticker : tickers) {
            quotes.add(saveQuote(ticker));
        }

        return quotes;
    }

    /**
     * Helper method for saveQuotes().
     */
    public Quote saveQuote(String ticker) {
        Quote quote = buildQuoteFromIexQuote(findIexQuoteByTicker(ticker));
        return saveQuote(quote);
    }

    /**
     * Update a given quote to quote table without validation.
     * @param quote Quote entity
     */
    public Quote saveQuote(Quote quote) {
        return quoteDao.save(quote);
    }

    /**
     * Find all quotes from the quote table
     * @return a list of Quote entities
     */
    public List<Quote> findAllQuotes() {
        return (List<Quote>) quoteDao.findAll();
    }
}
