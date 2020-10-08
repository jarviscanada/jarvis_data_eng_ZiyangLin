package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.model.domain.Quote;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class QuoteDaoIntTest {

    static final Logger logger = LoggerFactory.getLogger(QuoteDaoIntTest.class);

    @Autowired
    private QuoteDao quoteDao;
    private final Quote savedQuote = new Quote();

    @Before
    public void insertOne() {
        savedQuote.setAskPrice(10d);
        savedQuote.setAskSize(10);
        savedQuote.setBidPrice(10.2d);
        savedQuote.setBidSize(10);
        savedQuote.setTicker("AAPL");
        savedQuote.setLastPrice(10.1d);
        logger.debug(String.valueOf(savedQuote));
        quoteDao.save(savedQuote);
    }

    @After
    public void deleteOne() {
        quoteDao.deleteById(savedQuote.getId());
    }

    @Test
    public void save() {
        Quote quote = new Quote();
        quote.setLastPrice(5.1d);
        quote.setAskPrice(5d);
        quote.setAskSize(10);
        quote.setBidPrice(5.2d);
        quote.setBidSize(10);
        quote.setTicker("TSLA");
        quoteDao.save(quote);
        Optional<Quote> getSaved = quoteDao.findById("TSLA");
        assertTrue(getSaved.isPresent());
        assertEquals(quote, getSaved.get());
    }

    @Test
    public void saveAll() {
        Quote quote1 = new Quote();
        quote1.setLastPrice(5.1d);
        quote1.setAskPrice(5d);
        quote1.setAskSize(10);
        quote1.setBidPrice(5.2d);
        quote1.setBidSize(10);
        quote1.setTicker("TSLA");
        Quote quote2 = new Quote();
        quote2.setLastPrice(50.1d);
        quote2.setAskPrice(50d);
        quote2.setAskSize(10);
        quote2.setBidPrice(50.2d);
        quote2.setBidSize(10);
        quote2.setTicker("MSFT");
        List<Quote> quotes = new ArrayList<>();
        quotes.add(quote1);
        quotes.add(quote2);
        quoteDao.saveAll(quotes);
        List<Quote> result = (List<Quote>) quoteDao.findAll();
        assertEquals(3, result.size());
        assertEquals("TSLA", result.get(1).getId());
        assertEquals("MSFT", result.get(2).getId());
    }

    @Test
    public void findById() {
        Optional<Quote> quote = quoteDao.findById("AAPL");
        assertTrue(quote.isPresent());
        assertEquals("AAPL", quote.get().getId());
    }

    @Test
    public void existsById() {
        boolean existence1 = quoteDao.existsById("AAPL");
        boolean existence2 = quoteDao.existsById("TSLA");
        assertTrue(existence1);
        assertFalse(existence2);
    }

    @Test
    public void findAll() {
        Quote quote = new Quote();
        quote.setLastPrice(5.1d);
        quote.setAskPrice(5d);
        quote.setAskSize(10);
        quote.setBidPrice(5.2d);
        quote.setBidSize(10);
        quote.setTicker("TSLA");
        quoteDao.save(quote);
        List<Quote> allQuotes = (List<Quote>) quoteDao.findAll();
        assertEquals(2, allQuotes.size());
        assertEquals("AAPL", allQuotes.get(0).getId());
    }

    @Test
    public void count() {
        Quote quote = new Quote();
        quote.setLastPrice(5.1d);
        quote.setAskPrice(5d);
        quote.setAskSize(10);
        quote.setBidPrice(5.2d);
        quote.setBidSize(10);
        quote.setTicker("TSLA");
        quoteDao.save(quote);
        assertEquals(2, quoteDao.count());
    }

    @Test
    public void deleteById() {
        quoteDao.deleteById("AAPL");
        assertEquals(0, quoteDao.count());
    }

    @Test
    public void testDeleteAll() {
        Quote quote1 = new Quote();
        quote1.setLastPrice(5.1d);
        quote1.setAskPrice(5d);
        quote1.setAskSize(10);
        quote1.setBidPrice(5.2d);
        quote1.setBidSize(10);
        quote1.setTicker("TSLA");
        Quote quote2 = new Quote();
        quote2.setLastPrice(50.1d);
        quote2.setAskPrice(50d);
        quote2.setAskSize(10);
        quote2.setBidPrice(50.2d);
        quote2.setBidSize(10);
        quote2.setTicker("MSFT");
        List<Quote> quotes = new ArrayList<>();
        quotes.add(quote1);
        quotes.add(quote2);
        quoteDao.saveAll(quotes);
        quoteDao.deleteAll();
        assertEquals(0, quoteDao.count());
    }
}