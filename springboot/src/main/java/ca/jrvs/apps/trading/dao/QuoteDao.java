package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.Quote;
import ch.qos.logback.classic.db.names.TableName;
import com.sun.org.apache.xpath.internal.operations.Quo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Responsible for CRUD Quote objects against the quote table.
 * Targeted endpoint: "PUT /iexMarketData"
 */
@Repository
public class QuoteDao implements CrudRepository<Quote, String> {

    private static final String TABLE_NAME = "quote";
    private static final String ID_COLUMN_NAME = "ticker";

    private static final Logger logger = LoggerFactory.getLogger(QuoteDao.class);
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    public QuoteDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName(TABLE_NAME);
    }


    /**
     * hint: http://bit.ly/2sDz8hq DataAccessException family
     * @throws DataAccessException for unexpected SQL result or SQL execution failure
     */
    @Override
    public Quote save(Quote quote) {
        if (existsById(quote.getId())) {
            int updatedRowNo = updateOne(quote);
            if (updatedRowNo != 1) {
                throw new DataRetrievalFailureException("error: unable to update quote.");
            }
        } else {
            addOne(quote);
        }
        return quote;
    }

    /**
     * Helper method for save() that inserts one quote into the table quote.
     */
    private void addOne(Quote quote) {
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(quote);
        int row = simpleJdbcInsert.execute(parameterSource);
        if (row != 1) {
            throw new IncorrectResultSizeDataAccessException("error: failed to insert", 1, row);
        }
    }

    /**
     * Helper method for save() that updates one quote in the table quote.
     */
    private int updateOne(Quote quote) {
        String updateSql = "UPDATE quote SET last_price=?, bid_price=?, " +
                "bid_size=?, ask_price=?, ask_size=? WHERE ticker=?";
        // jdbcTemplate.update(SqlStatementWithVariables, variablesArray);
        return jdbcTemplate.update(updateSql, makeUpdateValues(quote));
    }

    /**
     * Helper method for updateOne() that return a Object array to construct the SQL update statement.
     * @param quote to be updated
     * @return updateSql values
     */
    private Object[] makeUpdateValues(Quote quote) {
        // note that we declare the result as Object[] because it holds data of different types.
        Object[] values = new Object[6];
        values[0] = quote.getLastPrice();
        values[1] = quote.getBidPrice();
        values[2] = quote.getBidSize();
        values[3] = quote.getAskPrice();
        values[4] = quote.getAskSize();
        values[5] = quote.getId();
        return values;
    }

    /**
     * Insert all quotes in quotes into the table quotes.
     */
    @Override
    public <S extends Quote> Iterable<S> saveAll(Iterable<S> quotes) {
        quotes.forEach(this::save);
        return quotes;
    }

    /**
     * Find a quote by ticker.
     * @param ticker name
     * @return quote or Optional.empty() if not found
     */
    @Override
    public Optional<Quote> findById(String ticker) {
        Optional<Quote> quote;
        String selectSql = "SELECT * FROM " + TABLE_NAME + " WHERE " + ID_COLUMN_NAME + "=?";
        try {
            // jdbcTemplate.queryForObject(selectSQLStatementWithVariables, objectClass.class, variables)
            // we use BeanPropertyRowMapper.newInstance() for custom domain object class
            quote = Optional.ofNullable(jdbcTemplate.queryForObject(selectSql,
                    BeanPropertyRowMapper.newInstance(Quote.class), ticker));
        } catch (DataAccessException ex) {
            logger.debug("error: cannot find quote for ticker [" + ticker + "].");
            return Optional.empty();
        }

        return quote;
    }

    @Override
    public boolean existsById(String ticker) {
        Optional<Quote> quote = findById(ticker);
        return quote.isPresent();
    }

    @Override
    public Iterable<Quote> findAll() {
        List<Quote> quotes = null;
        String selectAllSql = "SELECT * FROM " + TABLE_NAME;
        try {
            quotes = jdbcTemplate.query(selectAllSql,
                    BeanPropertyRowMapper.newInstance(Quote.class));
        } catch (DataAccessException e) {
            logger.debug("error: unable to retrieve all quotes in table quote.");
        }

        return quotes;
    }

    @Override
    public long count() {
        String countSql = "SELECT COUNT(*) FROM " + TABLE_NAME;
        Integer count;
        try {
            count = jdbcTemplate.queryForObject(countSql, Integer.class);
        } catch (DataAccessException ex) {
            logger.debug("error: unable to obtain total number of quotes in quote.");
            return 0;
        }
        return (int) count;
    }

    @Override
    public void deleteById(String ticker) {
        String deleteSql = "DELETE FROM " + TABLE_NAME + " WHERE " + ID_COLUMN_NAME + "=?";
        try {
            // jdbcTemplate.update(deleteSqlWithVariables, variables) for delete operation
            jdbcTemplate.update(deleteSql, ticker);
        } catch (DataAccessException e) {
            logger.debug("error: unable to delete quote [" + ticker +  "] in table quote.");
        }
    }

    @Override
    public void deleteAll() {
        String deleteAllSql = "DELETE FROM " + TABLE_NAME;
        try {
            jdbcTemplate.update(deleteAllSql);
        } catch (DataAccessException e) {
            logger.debug("error: unable to delete all quotes in table quote.");
        }
    }

    @Override
    public void delete(Quote quote) {
        throw new UnsupportedOperationException("error: method not implemented.");
    }

    @Override
    public void deleteAll(Iterable<? extends Quote> iterable) {
        throw new UnsupportedOperationException("error: method not implemented.");
    }

    @Override
    public Iterable<Quote> findAllById(Iterable<String> iterable) {
        throw new UnsupportedOperationException("error: method not implemented.");
    }
}
