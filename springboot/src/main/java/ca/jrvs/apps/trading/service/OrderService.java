package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.AccountDao;
import ca.jrvs.apps.trading.dao.PositionDao;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.dao.SecurityOrderDao;
import ca.jrvs.apps.trading.dto.MarketOrderDto;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.SecurityOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    private final AccountDao accountDao;
    private final SecurityOrderDao securityOrderDao;
    private final QuoteDao quoteDao;
    private final PositionDao positionDao;

    @Autowired
    public OrderService(AccountDao accountDao, SecurityOrderDao securityOrderDao,
                        QuoteDao quoteDao, PositionDao positionDao) {
        this.accountDao = accountDao;
        this.securityOrderDao = securityOrderDao;
        this.quoteDao = quoteDao;
        this.positionDao = positionDao;
    }

    /**
     * Execute a market order.
     *  - validate the order (e.g. size, ticker)
     *  - create a securityOrder (for security_order table)
     *  - handle buy or sell order
     *    - buy order: check account balance
     *    - sell order: check position for the ticker
     *    - update securityOrder.status
     *  - save and return the securityOrder
     *
     * @param marketOrderDto market order
     * @return SecurityOrder from security_order table.
     * @throws org.springframework.dao.DataAccessException if unable to get data from DAO
     * @throws  IllegalArgumentException for invalid input
     */
    public SecurityOrder executeMarketOrder(MarketOrderDto marketOrderDto) {
        // validate marketOrderDto
        boolean validated = marketOrderDto != null &&
                marketOrderDto.getSize() != 0 && marketOrderDto.getTicker() != null;
        if (!validated || !quoteDao.findById(marketOrderDto.getTicker()).isPresent()) {
            throw new IllegalArgumentException("error: input marketOrderDto is invalid, " +
                    "size should be greater than 0.");
        } else if (!quoteDao.findById(marketOrderDto.getTicker()).isPresent()) {
            throw new DataRetrievalFailureException("error: unable to find ticker ["
                    + marketOrderDto.getTicker() + "] for marketOrderDto in database.");
        }

        // retrieve account for this marketOrderDto
        Optional<Account> account = accountDao.findById(marketOrderDto.getAccountId());
        if (!account.isPresent()) {
            throw new DataRetrievalFailureException("error: unable to find account with account id ["
                    + marketOrderDto.getAccountId() + "] for marketOrderDto in database.");
        }

        // construct and handle order
        SecurityOrder securityOrder = new SecurityOrder();
        securityOrder.setAccountId(marketOrderDto.getAccountId());
        securityOrder.setSize(marketOrderDto.getSize());
        securityOrder.setTicker(marketOrderDto.getTicker());
        securityOrder.setStatus("IN PROGRESS");
        securityOrder.setPrice(-1d); // default price before handling order.
        SecurityOrder savedOrder = securityOrderDao.save(securityOrder);
        if (securityOrder.getSize() > 0) {
            handleBuyMarketOrder(marketOrderDto, securityOrder, account.get());
        } else {
            handleSellMarketOrder(marketOrderDto, securityOrder, account.get());
        }

        return savedOrder;
    }

    /**
     * Helper method for executeMarketOrder() with buy order.
     * @param marketOrderDto user order
     * @param securityOrder to be saved in database
     * @param account trader's account
     */
    protected void handleBuyMarketOrder(MarketOrderDto marketOrderDto,
                                        SecurityOrder securityOrder, Account account) {
        securityOrder.setPrice(quoteDao.findById(marketOrderDto.getTicker()).get().getAskPrice());
        if (account.getAmount() < marketOrderDto.getSize() * securityOrder.getPrice()) {
            securityOrder.setStatus("CANCELLED");
            securityOrder.setNotes("Cancelled due to insufficient account balance.\n"
                    + "account balance: " + account.getAmount()
                    + "current order total: " + marketOrderDto.getSize() * securityOrder.getPrice());
        } else {
            account.setAmount(account.getAmount() - marketOrderDto.getSize() * securityOrder.getPrice());
            securityOrder.setStatus("FILLED");
            accountDao.save(account);
        }

        securityOrderDao.save(securityOrder);
    }

    /**
     * Helper method for executeMarketOrder() with sell order.
     * @param marketOrderDto user order
     * @param securityOrder to be saved in database
     * @param account trader's account
     */
    protected void handleSellMarketOrder(MarketOrderDto marketOrderDto,
                                         SecurityOrder securityOrder, Account account) {
        securityOrder.setPrice(quoteDao.findById(marketOrderDto.getTicker()).get().getBidPrice());
        int currPosition = positionDao.findByIdAndTicker(account.getId(),
                securityOrder.getTicker()).get().getPosition();
        if (Math.abs(marketOrderDto.getSize()) > currPosition) {
            securityOrder.setStatus("CANCELLED");
            securityOrder.setNotes("Cancelled due to insufficient current position.\n"
                    + "current position: " + currPosition
                    + "order size: " + marketOrderDto.getSize());
        } else {
            account.setAmount(account.getAmount() + Math.abs(marketOrderDto.getSize()) * securityOrder.getPrice());
            securityOrder.setStatus("FILLED");
            accountDao.save(account);
        }

        securityOrderDao.save(securityOrder);
    }
}
