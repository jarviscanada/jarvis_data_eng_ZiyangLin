package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.model.domain.Account;
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
public class AccountDaoIntTest {

    @Autowired
    private AccountDao accountDao;
    @Autowired
    private TraderDao traderDao;

    private Account savedAccount;
    private Trader savedTrader;

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
    }

    @After
    public void cleanUp() {
        accountDao.deleteById(savedAccount.getId());
        traderDao.deleteById(savedTrader.getId());
    }

    @Test
    public void updateOne() {
        assertTrue(accountDao.findById(savedAccount.getId()).isPresent());
        assertEquals(100, (int) accountDao.findById(savedAccount.getId()).get().getAmount());
        savedAccount.setAmount(1000d);
        accountDao.save(savedAccount);
        assertEquals(1000, (int) accountDao.findById(savedAccount.getId()).get().getAmount());
    }

    @Test
    public void findAccountByTraderId() {
        Account anotherAccount = new Account();
        anotherAccount.setTraderId(savedTrader.getId());
        anotherAccount.setAmount(100d);
        accountDao.save(anotherAccount);
        List<Account> accounts = (List<Account>) accountDao.findAccountByTraderId(savedTrader.getId());
        assertEquals(2, accounts.size());
    }

}