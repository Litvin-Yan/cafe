package by.epam.cafe.dao;

import by.epam.cafe.exception.ConnectionPoolException;
import by.epam.cafe.exception.DAOException;
import by.epam.cafe.pool.ConnectionPool;
import by.epam.cafe.pool.ProxyConnection;

import java.sql.SQLException;

public class TransactionManager {

    private ProxyConnection connection;
    /**
     * Begin transaction
     *
     * @param daos daos
     * @throws DAOException when sql error
     */
    public void beginTransaction(DAO... daos) throws DAOException {

        try {
            connection = ConnectionPool.getInstance().retrieveConnection();
        } catch (ConnectionPoolException e) {
            throw new DAOException(e);
        }
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DAOException("Set auto commit 'false' error", e);
        }
        for (DAO d : daos) {
            d.setConnection(connection);
        }
    }

    /**
     * End transaction
     *
     * @throws DAOException when sql error
     */
    public void endTransaction() throws DAOException {
        try {
            connection.setAutoCommit(true);

        } catch (SQLException e) {
            throw new DAOException("Set auto commit 'true' error", e);
        }
        ConnectionPool.getInstance().putBackConnection(connection);
    }

    /**
     * Commit transaction
     *
     * @throws DAOException when sql error
     */
    public void commit() throws DAOException {
        try {
            connection.commit();
        } catch (SQLException e) {
            throw new DAOException("Commit error", e);
        }
    }

    /**
     * Rollback transaction
     *
     * @throws DAOException when sql error
     */
    public void rollback() throws DAOException {
        try {
            connection.rollback();
        } catch (SQLException e) {
            throw new DAOException("Rollback error", e);
        }
    }
}
