package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.SecurityOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SecurityOrderDao extends JdbcCrudDao<SecurityOrder> {

    private static final Logger logger = LoggerFactory.getLogger(AccountDao.class);

    private final String TABLE_NAME = "security_order";
    private final String ID_COLUMN = "id";

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    public SecurityOrderDao(DataSource dataSource) {
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
    Class<SecurityOrder> getEntityClass() {
        return SecurityOrder.class;
    }

    @Override
    public int updateOne(SecurityOrder order) {
        String updateSql = "UPDATE " + TABLE_NAME
                + " SET account_id=?, status=?, ticker=?, size=?, price=?, notes=? WHERE " + ID_COLUMN + "=?";
        return jdbcTemplate.update(updateSql, makeUpdateValues(order));
    }

    private Object[] makeUpdateValues(SecurityOrder order) {
        Object[] values = new Object[7];
        values[0] = order.getAccountId();
        values[1] = order.getStatus();
        values[2] = order.getTicker();
        values[3] = order.getSize();
        values[4] = order.getPrice();
        values[5] = order.getNotes();
        values[6] = order.getId();
        return values;
    }

    public Iterable<SecurityOrder> findAllOrdersById(Integer accountId) {
        List<SecurityOrder> orders = new ArrayList<>();
        String selectAllOrdersSql = "SELECT * FROM " + TABLE_NAME + " WHERE account_id=?";
        try {
            orders = jdbcTemplate.query(selectAllOrdersSql,
                    BeanPropertyRowMapper.newInstance(SecurityOrder.class), accountId);
        } catch (DataAccessException e) {
            logger.debug("error: unable to find all security orders for account [" + accountId + "].");
        }

        return orders;
    }

    @Override
    public void delete(SecurityOrder order) {
        throw new UnsupportedOperationException("error: method not implemented");
    }

    @Override
    public void deleteAll(Iterable<? extends SecurityOrder> orders) {
        throw new UnsupportedOperationException("error: method not implemented");
    }
}
