package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.AccountDao;
import ca.jrvs.apps.trading.dao.PositionDao;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.dao.SecurityOrderDao;
import ca.jrvs.apps.trading.dto.MarketOrderDto;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Position;
import ca.jrvs.apps.trading.model.domain.Quote;
import ca.jrvs.apps.trading.model.domain.SecurityOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceUnitTest {

    // capture parameter when calling securityOrderDao.save
    @Captor
    ArgumentCaptor<SecurityOrder> captorSecurityOrder;

    // mock all dependencies
    @Mock
    private AccountDao accountDao;
    @Mock
    private SecurityOrderDao securityOrderDao;
    @Mock
    private QuoteDao quoteDao;
    @Mock
    private PositionDao positionDao;

    // injecting mocked dependencies to the testing class via constructor
    @InjectMocks
    private OrderService orderService;

    @Test
    public void executeMarketOrder() {
        Account mockAccount = new Account();
        mockAccount.setTraderId(1);
        mockAccount.setId(1);
        mockAccount.setAmount(10000);
        when(accountDao.findById(any())).thenReturn(Optional.of(mockAccount));

        SecurityOrder mockOrder = new SecurityOrder();
        mockOrder.setStatus("IN PROGRESS");
        // make securityOrderDao.save() returns the arguments it receives,
        //     in this case, the first argument is just an securityOrder passed into it.
        when(securityOrderDao.save(any())).thenAnswer(x -> x.getArguments()[0]);

        Quote mockQuote = new Quote();
        mockQuote.setAskPrice(100d);
        mockQuote.setTicker("AAPL");
        mockQuote.setBidPrice(100d);
        when(quoteDao.findById(any())).thenReturn(Optional.of(mockQuote));
        
        Position mockPosition = new Position();
        mockPosition.setPosition(1000);
        when(positionDao.findByIdAndTicker(any(), any())).thenReturn(Optional.of(mockPosition));

        // first test validating marketOrderDto
        MarketOrderDto marketOrderDto = new MarketOrderDto();
        try {
            orderService.executeMarketOrder(marketOrderDto);
            fail();
        } catch (IllegalArgumentException ex) {
            assertTrue(true);
        }

        // test insufficient balance to buy.
        marketOrderDto.setAccountId(mockAccount.getId());
        marketOrderDto.setSize(200);
        marketOrderDto.setTicker("AAPL");
        SecurityOrder testOrder = orderService.executeMarketOrder(marketOrderDto);
        assertEquals("CANCELLED", testOrder.getStatus());

        // test insufficient position to sell.
        marketOrderDto.setSize(-2000);
        SecurityOrder testOrder2 = orderService.executeMarketOrder(marketOrderDto);
        assertEquals("CANCELLED", testOrder2.getStatus());

        // test sufficient position to sell.
        marketOrderDto.setSize(-500);
        SecurityOrder testOrder3 = orderService.executeMarketOrder(marketOrderDto);
        assertEquals("FILLED", testOrder3.getStatus());
        assertEquals((int) 10000 + 500 * 100, (int) mockAccount.getAmount());

        // test sufficient balance to buy.
        marketOrderDto.setSize(200);
        SecurityOrder testOrder4 = orderService.executeMarketOrder(marketOrderDto);
        assertEquals("FILLED", testOrder4.getStatus());
        assertEquals((int) 10000 + 500 * 100 - 200 * 100, (int) mockAccount.getAmount());
    }
}