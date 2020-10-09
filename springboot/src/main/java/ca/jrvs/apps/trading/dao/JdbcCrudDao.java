package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.Entity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class JdbcCrudDao<T extends Entity<Integer>> implements CrudRepository<T, Integer> {

    private static final Logger logger = LoggerFactory.getLogger(JdbcCrudDao.class);

    abstract public JdbcTemplate getJdbcTemplate();

    abstract public SimpleJdbcInsert getSimpleJdbcInsert();

    abstract public String getTableName();

    abstract public String getIdColumnName();

    abstract Class<T> getEntityClass();

    /**
     * Save an entity and update auto-generated integer ID
     * @param entity to be saved
     * @return saved entity
     */
    @Override
    public <S extends T> S save(S entity) {
        if (existsById(entity.getId())) {
            if (updateOne(entity) != 1) {
                throw new DataRetrievalFailureException("error: unable to update entity ["
                        + entity.getClass().getName() + "] in table [" + getTableName() + "].");
            }
        } else {
            addOne(entity);
        }
        return entity;
    }

    /**
     * Helper method for save() that saves one entity.
     */
    private <S extends T> void addOne(S entity) {
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(entity);

        Number newId = getSimpleJdbcInsert().executeAndReturnKey(parameterSource);
        entity.setId(newId.intValue());
    }

    abstract public int updateOne(T entity);

    @Override
    public <S extends T> Iterable<S> saveAll(Iterable<S> entities) {
        entities.forEach(this::save);
        return entities;
    }

    @Override
    public Optional<T> findById(Integer id) {
        Optional<T> entity = Optional.empty();
        String selectSql = "SELECT * FROM " + getTableName() + " WHERE " + getIdColumnName() + "=?";
        try {
            entity = Optional.ofNullable(getJdbcTemplate()
                    .queryForObject(selectSql,
                    BeanPropertyRowMapper.newInstance(getEntityClass()), id));
        } catch (IncorrectResultSizeDataAccessException ex) {
            logger.debug("error: can't find entity [" + getEntityClass().getName()
                    + "] with id: " + id + " in table [" + getTableName() + "].", ex);
        }

        return entity;
    }

    @Override
    public boolean existsById(Integer id) {
        return findById(id).isPresent();
    }

    @Override
    public Iterable<T> findAll() {
        List<T> allRows = null;
        String selectAllSql = "SELECT * FROM " + getTableName();
        try {
            allRows = getJdbcTemplate().query(selectAllSql,
                    BeanPropertyRowMapper.newInstance(getEntityClass()));
        } catch (DataAccessException e) {
           logger.debug("error: unable to retrieve all entities ["
                   + getEntityClass().getName() + "] in table [" + getTableName() + "].");
        }

        return allRows;
    }

    @Override
    public Iterable<T> findAllById(Iterable<Integer> ids) {
        List<T> entities = new ArrayList<>();
        ids.forEach(x -> {
            if (findById(x).isPresent()) {
                entities.add(findById(x).get());
            }
        });

        return entities;
    }

    @Override
    public long count() {
        String countSql = "SELECT COUNT(*) FROM " + getTableName();
        Long count;
        try {
            count = getJdbcTemplate().queryForObject(countSql, Long.class);
        } catch (DataAccessException e) {
            logger.debug("error: unable to obtain total number of entities ["
                    + getEntityClass().getName() + "] in table [" + getTableName() + "].");
            return 0;
        }

        return (long) count;
    }

    @Override
    public void deleteById(Integer id) {
        String deleteSql = "DELETE FROM " + getTableName() + " WHERE " + getIdColumnName() + "=?";
        try {
            getJdbcTemplate().update(deleteSql, id);
        } catch (DataAccessException e) {
            logger.debug("error: unable to delete entity ["
                    + getEntityClass().getName() + "] in table [" + getTableName() + "].");
        }
    }

    @Override
    public void delete(T t) {
        deleteById(t.getId());
    }

    @Override
    public void deleteAll(Iterable<? extends T> entities) {
        entities.forEach(this::delete);
    }

    @Override
    public void deleteAll() {
        String deleteAllSql = "DELETE FROM " + getTableName();
        try {
            getJdbcTemplate().update(deleteAllSql);
        } catch (DataAccessException e) {
            logger.debug("error: unable to delete all entities ["
                    + getEntityClass().getName() + "] in table [" + getTableName() + "].");
        }
    }
}
