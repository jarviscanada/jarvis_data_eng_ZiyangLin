package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * This class refers to the read-only view position, therefore does not need update, insert, and delete methods.
 */
@Repository
public class PositionDao {

    private static final Logger logger = LoggerFactory.getLogger(AccountDao.class);

    private final String TABLE_NAME = "position";
    private final String ID_COLUMN = "account_id";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PositionDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Optional<Position> findByIdAndTicker(Integer accountId, String ticker) {
        Optional<Position> position = Optional.empty();
        String selectSql = "SELECT * FROM " + TABLE_NAME + " WHERE " + ID_COLUMN + "=? AND ticker=?";
        try {
            position = Optional.ofNullable(jdbcTemplate.queryForObject(selectSql,
                    BeanPropertyRowMapper.newInstance(Position.class), accountId, ticker));
        } catch (IncorrectResultSizeDataAccessException e) {
            logger.debug("error: cannot find position for account ["
                    + accountId + "] and ticker [" + ticker + "].");
        }

        return position;
    }

    public boolean existsByIdAndTicker(Integer accountId, String ticker) {
        return findByIdAndTicker(accountId, ticker).isPresent();
    }

    public Iterable<Position> findAll() {
        List<Position> allRows = null;
        String selectAllSql = "SELECT * FROM " + TABLE_NAME;
        try {
            allRows = jdbcTemplate.query(selectAllSql,
                    BeanPropertyRowMapper.newInstance(Position.class));
        } catch (DataAccessException e) {
            logger.debug("error: unable to retrieve all accounts in table account.");
        }

        return allRows;
    }

    public long count() {
        long count;
        String countSql = "SELECT COUNT(*) FROM " + TABLE_NAME;
        try {
            count = jdbcTemplate.queryForObject(countSql, Long.class);
        } catch (DataAccessException e) {
            logger.debug("error: unable to obtain total number of positions in table position.");
            return 0;
        }

        return count;
    }

    public Iterable<Position> findAllPositionsByAccountId(Integer accountId) {
        List<Position> positions = new ArrayList<>();
        String selectByAccountIdSql = "SELECT * FROM " + TABLE_NAME + " WHERE " + ID_COLUMN + "=?";
        try {
            positions = jdbcTemplate.query(selectByAccountIdSql,
                    BeanPropertyRowMapper.newInstance(Position.class), accountId);
        } catch (DataAccessException e) {
            logger.debug("error: accountId is invalid, please try again.");
        }

        return positions;
    }
}
