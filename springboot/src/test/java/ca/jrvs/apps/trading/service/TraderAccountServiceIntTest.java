package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.dao.AccountDao;
import ca.jrvs.apps.trading.dao.TraderDao;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Trader;
import ca.jrvs.apps.trading.view.TraderAccountView;
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

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfig.class})
@Sql({"classpath:schema.sql"})
public class TraderAccountServiceIntTest {

    private TraderAccountView savedView;
    @Autowired
    private TraderAccountService traderAccountService;
    @Autowired
    private TraderDao traderDao;
    @Autowired
    private AccountDao accountDao;

    @Before
    public void setUp() {
        Trader trader = new Trader();
        trader.setCountry("People's Republic of China");
        trader.setDob(new Date(1926, Calendar.AUGUST, 17));
        trader.setEmail("zemin.jiang@prc.gov");
        trader.setFirstName("Zemin");
        trader.setLastName("Jiang");
        savedView = traderAccountService.createTraderAndAccount(trader);
    }

    @After
    public void cleanUp() {
        accountDao.deleteById(savedView.getAccount().getId());
        traderDao.deleteById(savedView.getTrader().getId());
    }

    @Test
    public void superTest() {
        // first test deposit()
        try {
            traderAccountService.deposit(savedView.getTrader().getId(), -20000d);
            fail();
        } catch (IllegalArgumentException ex) {
            assertTrue(true);
        }
        try {
            traderAccountService.deposit(savedView.getTrader().getId() + 1000, 20000d);
            fail();
        } catch (IllegalArgumentException ex) {
            assertTrue(true);
        }

        // deposit into saved trader, then expect an Exception when deleting
        traderAccountService.deposit(savedView.getTrader().getId(), 20000d);
        try {
            traderAccountService.deleteTraderById(savedView.getTrader().getId());
            fail();
        } catch (IllegalArgumentException ex) {
            assertTrue(true);
        }

        // now test withdraw()
        try {
            traderAccountService.withdraw(savedView.getTrader().getId(), -30000d);
            fail();
        } catch (IllegalArgumentException ex) {
            assertTrue(true);
        }
        try {
            traderAccountService.withdraw(savedView.getTrader().getId(), 30000d);
            fail();
        } catch (IllegalArgumentException ex) {
            assertTrue(true);
        }

        // now withdraw exactly 20000.0 so that the trader's account has balance 0.
        traderAccountService.withdraw(savedView.getTrader().getId(), 20000d);
        traderAccountService.deleteTraderById(savedView.getTrader().getId());
    }

}