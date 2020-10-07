package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.config.MarketDataConfig;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class MarketDataDaoIntTest {

    private MarketDataDao dao;
    static final Logger logger = LoggerFactory.getLogger(MarketDataDaoIntTest.class);

    @Before
    public void init() {
        // token: pk_5405cd82c58e4781b23ebf0b9adb1d24
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(50);
        cm.setDefaultMaxPerRoute(50);
        MarketDataConfig marketDataConfig = new MarketDataConfig();
        marketDataConfig.setHost("https://cloud.iexapis.com/v1");
        marketDataConfig.setToken(System.getenv("IEX_PUB_TOKEN"));

        dao = new MarketDataDao(cm, marketDataConfig);
    }

    @Test
    public void testFindById() {
        IexQuote iexQuote = dao.findById("AAPL").get();
        assertEquals("AAPL", iexQuote.getSymbol());
    }

    @Test
    public void testFindAllById() {
        // happy path
        List<IexQuote> quotes = dao.findAllById(Arrays.asList("AAPL", "MSSSFT", "FB"));
        assertEquals(2, quotes.size());
        assertEquals("AAPL", quotes.get(0).getSymbol());
        System.out.println(quotes.get(0).getSymbol());

        // sad path
        try {
            dao.findAllById(Arrays.asList("AAAPL", "MSSSSFT"));
            fail();
        } catch (IllegalArgumentException ex) {
            assertTrue(true);
        } catch (Exception ex) {
            fail();
        }
    }
}