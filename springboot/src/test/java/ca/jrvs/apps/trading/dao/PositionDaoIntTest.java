package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.model.domain.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfig.class})
@Sql({"classpath:schema.sql"})
public class PositionDaoIntTest {

    @Autowired
    private AccountDao accountDao;
    @Autowired
    private TraderDao traderDao;
    @Autowired
    private SecurityOrderDao securityOrderDao;
    @Autowired
    private QuoteDao quoteDao;
    @Autowired
    private PositionDao positionDao;

    private Account savedAccount;
    private Trader savedTrader;
    private Quote savedQuote;
    private SecurityOrder savedOrder1;
    private SecurityOrder savedOrder2;

    @Before
    public void setUp() {
        savedTrader = new Trader();
        savedTrader.setCountry("People's Republic of China");
        savedTrader.setDob(new Date(1926, Calendar.AUGUST, 17));
        savedTrader.setEmail("zemin.jiang@prc.gov");
        savedTrader.setFirstName("Zemin");
        savedTrader.setLastName("Jiang");
        savedTrader = traderDao.save(savedTrader);

        savedAccount = new Account();
        savedAccount.setTraderId(savedTrader.getId());
        savedAccount.setAmount(100d);
        savedAccount = accountDao.save(savedAccount);

        savedQuote = new Quote();
        savedQuote.setTicker("AAPL");
        savedQuote.setLastPrice(99.8);
        savedQuote.setAskPrice(99.7);
        savedQuote.setAskSize(10);
        savedQuote.setBidPrice(99.9);
        savedQuote.setBidSize(10);
        savedQuote = quoteDao.save(savedQuote);

        savedOrder1 = new SecurityOrder();
        savedOrder1.setAccountId(savedAccount.getId());
        savedOrder1.setTicker("AAPL");
        savedOrder1.setPrice(100d);
        savedOrder1.setSize(100);
        savedOrder1.setStatus("FILLED");
        savedOrder1.setNotes("No notes.");
        savedOrder1 = securityOrderDao.save(savedOrder1);

        savedOrder2 = new SecurityOrder();
        savedOrder2.setAccountId(savedAccount.getId());
        savedOrder2.setTicker("AAPL");
        savedOrder2.setPrice(100.2d);
        savedOrder2.setSize(300);
        savedOrder2.setStatus("FILLED");
        savedOrder2.setNotes("No notes.");
        savedOrder2 = securityOrderDao.save(savedOrder2);
    }

    @After
    public void cleanUp() {
        securityOrderDao.deleteAll();
        quoteDao.deleteById(savedQuote.getId());
        accountDao.deleteById(savedAccount.getId());
        traderDao.deleteById(savedTrader.getId());
    }

    @Test
    public void findByIdAndTicker() {
        Position position = positionDao.findByIdAndTicker(savedAccount.getId(), "AAPL").get();
        assertEquals(400, position.getPosition());
    }

    @Test
    public void existsByIdAndTicker() {
        assertTrue(positionDao.existsByIdAndTicker(savedAccount.getId(), "AAPL"));
        assertFalse(positionDao.existsByIdAndTicker(savedAccount.getId(), "MSFT"));
        assertFalse(positionDao.existsByIdAndTicker(savedAccount.getId() + 1, "AAPL"));
    }

    @Test
    public void findAll() {
        List<Position> positions = (List<Position>) positionDao.findAll();
        assertEquals(1, positions.size());
    }

    @Test
    public void count() {
        assertEquals(1, positionDao.count());
    }

    @Test
    public void findAllPositionsByAccountId() {
        List<Position> positions = (List<Position>) positionDao
                .findAllPositionsByAccountId(savedAccount.getId());
        assertEquals(1, positions.size());
    }
}