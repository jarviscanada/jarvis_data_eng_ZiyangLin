package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.*;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Position;
import ca.jrvs.apps.trading.view.PortfolioView;
import ca.jrvs.apps.trading.view.TraderAccountView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class DashboardService {

    private final TraderDao traderDao;
    private final PositionDao positionDao;
    private final AccountDao accountDao;
    private final QuoteDao quoteDao;

    @Autowired
    public DashboardService(TraderDao traderDao, PositionDao positionDao, AccountDao accountDao, QuoteDao quoteDao) {
        this.traderDao = traderDao;
        this.positionDao = positionDao;
        this.accountDao = accountDao;
        this.quoteDao = quoteDao;
    }

    /**
     * Create and return a traderAccountView by traderId
     *  - get trader account by traderId
     *  - get trader info by traderId
     *  - create and return a traderAccountView
     * @param traderId must not be null
     * @return traderAccountView
     * @throws IllegalArgumentException if traderId is null or not found.
     */
    public TraderAccountView getTraderAccount(Integer traderId) {
        // validate traderId
        if (traderId == null) {
            throw new IllegalArgumentException("error: traderId cannot be null.");
        } else if (!traderDao.findById(traderId).isPresent()) {
            throw new IllegalArgumentException("error: trader with id [" + traderId + "] is not found.");
        }

        // see if the trader has an account, if not, throw an Exception.
        if (((List<Account>) accountDao.findAccountByTraderId(traderId)).size() == 0) {
            throw new IllegalArgumentException("error: cannot find account for trader with id [" + traderId + "].");
        } else {
            return new TraderAccountView(traderDao.findById(traderId).get(),
                    ((List<Account>) accountDao.findAccountByTraderId(traderId)).get(0));
        }
    }

    /**
     * Create and return PortfolioView by traderId
     *  - get account by traderId
     *  - get all positions by traderId
     *  - create and return a portfolioView.
     *
     * @param traderId must not be null
     * @return portfolioView
     * @throws IllegalArgumentException if traderId is null or not found
     */
    public PortfolioView getPortfolioViewByTraderId(Integer traderId) {
        // validate traderId
        if (traderId == null) {
            throw new IllegalArgumentException("error: traderId cannot be null.");
        } else if (!traderDao.findById(traderId).isPresent()) {
            throw new IllegalArgumentException("error: trader with id [" + traderId + "] is not found.");
        }

        // find account, all positions, and construct portfolio view parameter.
        Account account = ((List<Account>) accountDao.findAccountByTraderId(traderId)).get(0);
        List<Position> positions = (List<Position>) positionDao.findAllPositionsByAccountId(account.getId());
        List<PortfolioView.SecurityView> securities = new ArrayList<>();
        positions.forEach(x -> securities.add(new PortfolioView.SecurityView(x.getTicker(),
                x, quoteDao.findById(x.getTicker()).get())));

        return new PortfolioView(securities);
    }
}
