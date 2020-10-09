package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Quote;
import ca.jrvs.apps.trading.model.domain.SecurityOrder;
import ca.jrvs.apps.trading.model.domain.Trader;
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
public class SecurityOrderDaoIntTest {

    @Autowired
    private AccountDao accountDao;
    @Autowired
    private TraderDao traderDao;
    @Autowired
    private SecurityOrderDao securityOrderDao;
    @Autowired
    private QuoteDao quoteDao;

    private Account savedAccount;
    private Trader savedTrader;
    private Quote savedQuote;
    private SecurityOrder savedOrder;

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

        savedOrder = new SecurityOrder();
        savedOrder.setAccountId(savedAccount.getId());
        savedOrder.setTicker("AAPL");
        savedOrder.setPrice(100d);
        savedOrder.setSize(100);
        savedOrder.setStatus("FILLED");
        savedOrder.setNotes("No notes.");
        savedOrder = securityOrderDao.save(savedOrder);
    }

    @After
    public void cleanUp() {
        securityOrderDao.deleteAll();
        quoteDao.deleteById(savedQuote.getId());
        accountDao.deleteById(savedAccount.getId());
        traderDao.deleteById(savedTrader.getId());
    }

    @Test
    public void updateOne() {
        // first test the old security order is still in the table.
        assertTrue(securityOrderDao.findById(savedOrder.getId()).isPresent());
        assertEquals("AAPL", securityOrderDao.findById(savedOrder.getId()).get().getTicker());

        // update savedOrder and persist into the table.
        savedOrder.setTicker("AAPL");
        savedOrder.setPrice(100.2);
        savedOrder.setSize(500);
        savedOrder.setStatus("Open");
        savedOrder.setNotes("No notes.");
        securityOrderDao.updateOne(savedOrder);

        // test updated.
        SecurityOrder actual = securityOrderDao.findById(savedOrder.getId()).get();
        assertEquals(500, actual.getSize());
    }

    @Test
    public void findAllOrdersById() {
        SecurityOrder newOrder = new SecurityOrder();
        newOrder.setAccountId(savedAccount.getId());
        newOrder.setTicker("AAPL");
        newOrder.setPrice(100.2);
        newOrder.setSize(500);
        newOrder.setStatus("Open");
        newOrder.setNotes("No notes.");
        securityOrderDao.save(newOrder);

        List<SecurityOrder> orders = (List<SecurityOrder>) securityOrderDao
                .findAllOrdersById(savedAccount.getId());
        assertEquals(2, orders.size());
    }
}