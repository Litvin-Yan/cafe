package by.epam.cafe.dao;

import by.epam.cafe.entity.Entity;
import by.epam.cafe.exception.DAOException;
import by.epam.cafe.pool.ProxyConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public abstract class DAO<T extends Entity> {

    protected final static Logger LOGGER = LogManager.getLogger();

    protected ProxyConnection connection;

    public abstract List<T> findAll() throws DAOException;

    public abstract T findEntityById(int id) throws DAOException;

    public abstract boolean delete(int id) throws DAOException;

    public abstract boolean delete(T entity) throws DAOException;

    public abstract boolean create(T entity) throws DAOException;

    public abstract boolean update(T entity) throws DAOException;

    public void setConnection(ProxyConnection connection) {
        this.connection = connection;
    }

}
