package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import ca.jrvs.apps.trading.model.domain.Quote;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfig.class})
@Sql({"classpath:schema.sql"})
public class QuoteServiceIntTest {

    @Autowired
    private QuoteService quoteService;
    @Autowired
    private QuoteDao quoteDao;

    @Before
    public void setUp() {
        quoteDao.deleteAll();
    }

    @Test
    public void findIexQuoteByTicker() {
        // test happy path.
        IexQuote apple = quoteService.findIexQuoteByTicker("AAPL");
        assertEquals("AAPL", apple.getSymbol());
        IexQuote fakeStock;

        // test sad path.
        try {
            fakeStock = quoteService.findIexQuoteByTicker("FAKESTOCK");
            fail();
        } catch (IllegalArgumentException ex) {
            assertTrue(true);
        } catch (Exception ex) {
            fail();
        }
    }

    @Test
    public void updateMarketData() {
        int originalSize = quoteService.findAllQuotes().size();
        List<Quote> updatedQuotes = quoteService.updateMarketData();
        assertEquals(originalSize, updatedQuotes.size());
    }

    @Test
    public void buildQuoteFromIexQuote() {
        IexQuote apple = quoteService.findIexQuoteByTicker("AAPL");
        Quote quote = QuoteService.buildQuoteFromIexQuote(apple);
        assertEquals(quote.getId(), apple.getSymbol());
        assertEquals(java.util.Optional.ofNullable(quote.getLastPrice()), Optional.of(apple.getLatestPrice()));
    }

    @Test
    public void saveQuotes() {
        List<String> tickers = new ArrayList<>();
        tickers.add("AAPL");
        tickers.add("MSFT");
        tickers.add("FB");
        List<Quote> savedQuotes = quoteService.saveQuotes(tickers);
        List<Quote> retrievedQuotes = quoteService.findAllQuotes();
        assertEquals(savedQuotes.size(), retrievedQuotes.size());
    }

    @Test
    public void saveQuote() {
        IexQuote microsoft = quoteService.findIexQuoteByTicker("MSFT");
        Quote microsoftQuote = QuoteService.buildQuoteFromIexQuote(microsoft);
        Quote savedQuote = quoteService.saveQuote(microsoftQuote);
        Quote retrievedQuote = quoteService.findAllQuotes().get(0);
        assertEquals(savedQuote.getTicker(), retrievedQuote.getTicker());
    }

    @Test
    public void testSaveQuote() {
        // happy path.
        Quote savedQuote = quoteService.saveQuote("TSLA");
        Quote retrievedQuote = quoteService.findAllQuotes().get(0);
        assertEquals(savedQuote.getTicker(), retrievedQuote.getTicker());

        // sad path.
        try {
            quoteService.saveQuote("TSLAAAA");
            fail();
        } catch (IllegalArgumentException ex) {
            assertTrue(true);
        }
    }

    @Test
    public void findAllQuotes() {
        quoteService.saveQuote("MSFT");
        quoteService.saveQuote("AAPL");
        quoteService.saveQuote("FB");
        assertEquals(3, quoteService.findAllQuotes().size());
    }
}