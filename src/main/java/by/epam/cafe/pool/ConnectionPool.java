package by.epam.cafe.pool;


import by.epam.cafe.exception.ConnectionPoolException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * Class for contain connections
 */
public class ConnectionPool {

    private final static Logger LOGGER = LogManager.getLogger();
    private static Lock locker = new ReentrantLock();
    private static AtomicBoolean isCreated = new AtomicBoolean(false);
    private static ConnectionPool instance;
    private ArrayBlockingQueue<ProxyConnection> availableConns;
    private ArrayBlockingQueue<ProxyConnection> usedConns;
    private ConnectionPoolConfig config;

    /**
     * Default constructor.
     */
    private ConnectionPool() {
        config = new ConnectionPoolConfig();
        availableConns = new ArrayBlockingQueue<>(config.getPoolCapacity(), true);
        usedConns = new ArrayBlockingQueue<>(config.getPoolCapacity(), true);
    }

    /**
     * Get instance.
     *
     * @return connection pool instance
     */
    public static ConnectionPool getInstance() {
        if (!isCreated.get()) {
            locker.lock();
            if (instance == null) {
                instance = new ConnectionPool();
                isCreated.set(true);
                LOGGER.log(Level.INFO, "Connection pool successfully created");
            }
            locker.unlock();
        }
        return instance;
    }

    private ProxyConnection createConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(config.getUrl(), config.getProperties());
        LOGGER.log(Level.INFO, "New connection created");
        return new ProxyConnection(connection);
    }

    /**
     * Retrieve connection.
     *
     * @return proxy connection
     * @throws ConnectionPoolException when sql error
     */
    public ProxyConnection retrieveConnection() throws ConnectionPoolException {
        ProxyConnection newConn;
        try {
            newConn = (((availableConns.size() + usedConns.size()) < config.getPoolCapacity()) && availableConns.size() == 0) ?
                    createConnection() :
                    availableConns.take();

        } catch (SQLException e) {
            throw new ConnectionPoolException("Connect to data base error. ", e);
        } catch (InterruptedException e) {
            throw new ConnectionPoolException("Connection wait error. ", e);
        }
        return usedConns.offer(newConn) ? newConn : null;
    }

    /**
     * Put back connection.
     *
     * @param connection connection
     */
    public void putBackConnection(ProxyConnection connection) {
        if (connection != null) {
            if (usedConns.remove(connection)) {
                availableConns.offer(connection);
            }
        }
    }

    /**
     * Destroy pool.
     *
     */
    public void destroyPool() {

        for (ProxyConnection availableConn : availableConns) {
            try {
                if (!availableConn.isClosed()) {
                    availableConn.realClose();
                }
            }catch (SQLException e){
                LOGGER.log(Level.ERROR, "Destroy available connection exception"+e);
            }
        }
        availableConns.clear();

        for (ProxyConnection usedConn : usedConns) {
            try {
                if (!usedConn.isClosed()) {
                    usedConn.realClose();
                }
            }catch (SQLException e){
                LOGGER.log(Level.ERROR, "Destroy used connection exception"+e);
            }
        }
        usedConns.clear();

    }
}
