package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class AccountDao extends JdbcCrudDao<Account> {

    private static final Logger logger = LoggerFactory.getLogger(AccountDao.class);
    
    private final String TABLE_NAME = "account";
    private final String ID_COLUMN = "id";

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    public AccountDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName(TABLE_NAME).usingGeneratedKeyColumns(ID_COLUMN);
    }

    @Override
    public JdbcTemplate getJdbcTemplate() {
        return this.jdbcTemplate;
    }

    @Override
    public SimpleJdbcInsert getSimpleJdbcInsert() {
        return this.simpleJdbcInsert;
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public String getIdColumnName() {
        return ID_COLUMN;
    }

    @Override
    Class<Account> getEntityClass() {
        return Account.class;
    }

    @Override
    public int updateOne(Account account) {
        String updateSql = "UPDATE " + TABLE_NAME + " SET amount=?, trader_id=? WHERE " + ID_COLUMN + "=?";
        return jdbcTemplate.update(updateSql, makeUpdateValues(account));
    }

    /**
     * Helper function for updateOne() that builds up SQL statement variables.
     */
    private Object[] makeUpdateValues(Account account) {
        Object[] values = new Object[3];
        values[0] = account.getAmount();
        values[1] = account.getTraderId();
        values[2] = account.getId();
        return values;
    }

    @Override
    public void delete(Account account) {
        throw new UnsupportedOperationException("error: method not implemented");
    }

    @Override
    public void deleteAll(Iterable<? extends Account> accounts) {
        throw new UnsupportedOperationException("error: method not implemented");
    }

    /**
     * Retrieve all accounts held by trader with id traderId.
     * @param traderId target trader id
     * @return a list of all accounts held by trader with id traderId
     */
    public Iterable<Account> findAccountByTraderId(Integer traderId) {
        String selectAllAccountsSql = "SELECT * FROM " + TABLE_NAME + " WHERE trader_id=?";
        return jdbcTemplate.query(selectAllAccountsSql,
                    BeanPropertyRowMapper.newInstance(Account.class), traderId);
    }
}
