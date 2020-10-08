package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.model.domain.Trader;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfig.class})
@Sql({"classpath:schema.sql"})
public class TraderDaoIntTest {

    @Autowired
    private TraderDao traderDao;
    private Trader savedTrader;

    @Before
    public void insertOne() {
        savedTrader = new Trader();
        savedTrader.setCountry("People's Republic of China");
        savedTrader.setDob(new Date(1926, Calendar.AUGUST, 17));
        savedTrader.setEmail("zemin.jiang@prc.gov");
        savedTrader.setFirstName("Zemin");
        savedTrader.setLastName("Jiang");
        savedTrader = traderDao.save(savedTrader);
    }

    @After
    public void cleanUp() {
        traderDao.deleteAll();
    }

    @Test
    public void saveAll() {
        List<Trader> traders = new ArrayList<>();
        Trader trader1 = new Trader();
        Trader trader2 = new Trader();
        trader1.setLastName("Trump");
        trader1.setFirstName("Donale");
        trader1.setDob(new Date(1999,9,9));
        trader1.setCountry("United States");
        trader1.setEmail("donald.trump@wh.gov");
        trader2.setLastName("Trudeau");
        trader2.setFirstName("Justin");
        trader2.setDob(new Date(1988,8,8));
        trader2.setCountry("Canada");
        trader2.setEmail("justin.trudeau@gov.ca");
        traders.add(trader1);
        traders.add(trader2);
        traderDao.saveAll(traders);
        List<Trader> actual = (List<Trader>) traderDao.findAll();
        assertEquals(3, actual.size());
        assertEquals("Trump", traderDao.findById(trader1.getId()).get().getLastName());
        assertEquals("Trudeau", traderDao.findById(trader2.getId()).get().getLastName());
    }

    @Test
    public void findAllById() {
        Trader trader1 = new Trader();
        trader1.setLastName("Trump");
        trader1.setFirstName("Donale");
        trader1.setDob(new Date(1999,9,9));
        trader1.setCountry("United States");
        trader1.setEmail("donald.trump@wh.gov");
        traderDao.save(trader1);
        List<Integer> ids = new ArrayList<>();
        ids.add(savedTrader.getId());
        ids.add(trader1.getId());
        List<Trader> actual = (List<Trader>) traderDao.findAllById(ids);
        assertEquals(2, actual.size());
        assertEquals(trader1.getId(), traderDao.findById(trader1.getId()).get().getId());
    }

    @Test
    public void existsById() {
        assertTrue(traderDao.existsById(savedTrader.getId()));
    }

    @Test
    public void deleteById() {
        Trader trader1 = new Trader();
        trader1.setLastName("Trump");
        trader1.setFirstName("Donale");
        trader1.setDob(new Date(1999,9,9));
        trader1.setCountry("United States");
        trader1.setEmail("donald.trump@wh.gov");
        traderDao.save(trader1);
        List<Trader> afterSaving = (List<Trader>) traderDao.findAll();
        assertEquals(2, afterSaving.size());
        traderDao.deleteById(trader1.getId());
        List<Trader> afterDeleting = (List<Trader>) traderDao.findAll();
        assertEquals(1, afterDeleting.size());

        // now we are supposed to be unable to find trader1 in table trader.
        Optional<Trader> deleted = traderDao.findById(trader1.getId());
        assertFalse(deleted.isPresent());
    }
}