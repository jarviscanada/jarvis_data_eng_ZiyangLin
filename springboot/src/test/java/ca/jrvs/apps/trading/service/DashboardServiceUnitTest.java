package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.AccountDao;
import ca.jrvs.apps.trading.dao.PositionDao;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.dao.TraderDao;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Position;
import ca.jrvs.apps.trading.model.domain.Quote;
import ca.jrvs.apps.trading.model.domain.Trader;
import ca.jrvs.apps.trading.view.PortfolioView;
import ca.jrvs.apps.trading.view.TraderAccountView;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DashboardServiceUnitTest {

    @Mock
    private AccountDao accountDao;
    @Mock
    private TraderDao traderDao;
    @Mock
    private QuoteDao quoteDao;
    @Mock
    private PositionDao positionDao;

    @InjectMocks
    private DashboardService dashboardService;

    @Before
    public void setUp() {
        Trader mockTrader = new Trader();
        mockTrader.setLastName("One");
        mockTrader.setFirstName("Two");
        mockTrader.setId(1);
        when(traderDao.findById(1)).thenReturn(Optional.of(mockTrader));
        when(traderDao.findById(2)).thenReturn(Optional.empty());

        Account mockAccount = new Account();
        mockAccount.setTraderId(1);
        mockAccount.setAmount(10000);
        mockAccount.setId(1);
        List<Account> account = new ArrayList<>();
        account.add(mockAccount);
        when(accountDao.findAccountByTraderId(any())).thenReturn(account);

        Quote quote1 = new Quote();
        Quote quote2 = new Quote();
        quote1.setTicker("AAPL");
        quote2.setTicker("MSFT");
        quote1.setBidSize(20);
        quote1.setAskSize(20);
        quote2.setBidSize(20);
        quote2.setAskSize(20);
        quote1.setBidPrice(100d);
        quote2.setBidPrice(200d);
        quote1.setAskPrice(100d);
        quote2.setAskPrice(200d);
        quote1.setLastPrice(100d);
        quote2.setLastPrice(200d);
        when(quoteDao.findById("AAPL")).thenReturn(Optional.of(quote1));
        when(quoteDao.findById("MSFT")).thenReturn(Optional.of(quote2));

        Position mockPosition1 = new Position();
        Position mockPosition2 = new Position();
        mockPosition1.setTicker("AAPL");
        mockPosition1.setAccountId(1);
        mockPosition1.setPosition(1000);
        mockPosition2.setTicker("MSFT");
        mockPosition2.setAccountId(1);
        mockPosition2.setPosition(500);
        List<Position> positions = new ArrayList<>();
        positions.add(mockPosition1);
        positions.add(mockPosition2);
        when(positionDao.findAllPositionsByAccountId(any())).thenReturn(positions);
    }

    @Test
    public void getTraderAccount() {
        // test exception.
        try {
            dashboardService.getTraderAccount(null);
            fail();
        } catch (IllegalArgumentException ex) {
            assertTrue(true);
        }
        try {
            dashboardService.getTraderAccount(2);
            fail();
        } catch (IllegalArgumentException ex) {
            assertTrue(true);
        }

        // test implementation.
        TraderAccountView traderAccountView = dashboardService.getTraderAccount(1);
        assertEquals("Two", traderAccountView.getTrader().getFirstName());
        assertEquals(10000, (int) traderAccountView.getAccount().getAmount());
    }

    @Test
    public void getPortfolioViewByTraderId() {
        // test exception.
        try {
            dashboardService.getPortfolioViewByTraderId(null);
            fail();
        } catch (IllegalArgumentException ex) {
            assertTrue(true);
        }
        try {
            dashboardService.getPortfolioViewByTraderId(2);
            fail();
        } catch (IllegalArgumentException ex) {
            assertTrue(true);
        }

        // test implementation
        PortfolioView portfolioView = dashboardService.getPortfolioViewByTraderId(1);
        assertEquals("AAPL", portfolioView.getSecurities().get(0).getTicker());
        assertEquals("MSFT", portfolioView.getSecurities().get(1).getTicker());
    }
}