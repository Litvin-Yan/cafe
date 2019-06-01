package by.epam.cafe.dao.impl;

import by.epam.cafe.constant.GeneralConstant;
import by.epam.cafe.constant.SQLFieldConstant;
import by.epam.cafe.dao.OrderDAO;
import by.epam.cafe.entity.OrderEntity;
import by.epam.cafe.exception.DAOException;
import by.epam.cafe.type.PaymentType;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderDAOImpl extends OrderDAO {

    private static final String CREATE_ORDER =
            "INSERT INTO `order` (order_expected_time, order_bonus, order_payment_method, user_id, order_price) " +
                    "VALUES (?, ?, ?, ?, ?);";

    private static final String FIND_ACTIVE_ORDER_BY_USER_ID =
            "select distinct order_payment_method" +
                    ", user_id" +
                    ", order_expected_time" +
                    ", order_time " +
                    ", order_price " +
                    "FROM " +
                    "`order` "+
                    "WHERE " +
                    "user_id = ? AND " +
                    "order_expected_time > now() " +
                    "ORDER BY order_time;";

    private static final String FIND_ORDER_WITHOUT_COMMENT_BY_USER_ID =

            "select distinct `order`.order_id" +
                    ", order_payment_method" +
                    ", user_id" +
                    ", order_expected_time" +
                    ", order_time " +
                    ", order_price " +
                    "FROM " +
                    "`order` "+
                    "LEFT JOIN "+
                    "`comment` "+
                    "ON `order`.order_id = comment.order_id "+
                    "WHERE " +
                    "user_id = ? AND " +
                    "order_expected_time < now() AND " +
                    "comment.order_id IS NULL " +
                    "ORDER BY order_time " +
                    "LIMIT 0, 5;";

    @Override
    public List<OrderEntity> findAll() throws DAOException {
        return null;
    }

    @Override
    public OrderEntity findEntityById(int id) throws DAOException {
        return null;
    }

    @Override
    public boolean delete(int id) throws DAOException {
        return false;
    }

    @Override
    public boolean delete(OrderEntity entity) throws DAOException {
        return false;
    }

    @Override
    public boolean create(OrderEntity entity) throws DAOException {
        boolean isCreated = false;
        try (PreparedStatement statement = connection.prepareStatement(CREATE_ORDER)) {

            statement.setTimestamp(1, entity.getExpectedTime());
            statement.setBigDecimal(2, entity.getBonus());
            statement.setString(3, String.valueOf(entity.getPaymentType()));
            statement.setInt(4, entity.getUserId());
            statement.setBigDecimal(5, entity.getCash());
            isCreated = statement.executeUpdate() == 1;

        } catch (SQLException e) {
            if (!GeneralConstant.DUPLICATE_UNIQUE_INDEX.equals(e.getSQLState())) {
                throw new DAOException("Create order error ", e);
            }
        }
        return isCreated;
    }

    public int createAndGetOrderId(OrderEntity entity) throws DAOException {
        int orderId = 0;
        try (PreparedStatement statement = connection.prepareStatement(CREATE_ORDER)) {

            statement.setTimestamp(1, entity.getExpectedTime());
            statement.setBigDecimal(2, entity.getBonus());
            statement.setString(3, String.valueOf(entity.getPaymentType()));
            statement.setInt(4, entity.getUserId());
            statement.setBigDecimal(5, entity.getCash());

            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();

            if (resultSet.next()) {
                orderId = resultSet.getInt(1);
            }

        } catch (SQLException e) {
            if (!GeneralConstant.DUPLICATE_UNIQUE_INDEX.equals(e.getSQLState())) {
                throw new DAOException("Create order error ", e);
            }
        }
        return orderId;
    }

    @Override
    public boolean update(OrderEntity entity) throws DAOException {
        return false;
    }

    public List<OrderEntity> findActiveOrdersByUserId(int userId) throws DAOException {

        List<OrderEntity> activeOrders;

        try (PreparedStatement statement = connection.prepareStatement(FIND_ACTIVE_ORDER_BY_USER_ID)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            activeOrders = new ArrayList<>();
            while (resultSet.next()) {
                OrderEntity order = new OrderEntity();
                order.setCash(resultSet.getBigDecimal(SQLFieldConstant.Order.PRICE));
                order.setExpectedTime(resultSet.getTimestamp(SQLFieldConstant.Order.EXPECTED_TIME));
                order.setTime(resultSet.getTimestamp(SQLFieldConstant.Order.TIME));
                String paymentType = resultSet.getString(SQLFieldConstant.Order.PAYMENT_METHOD);
                order.setPaymentType(PaymentType.valueOf(paymentType));
                order.setUserId(resultSet.getInt(SQLFieldConstant.Order.USER_ID));

                activeOrders.add(order);
            }

        } catch (SQLException e) {
            throw new DAOException("Find active orders error", e);
        }
        return activeOrders;
    }

    public List<OrderEntity> findOrdersWithoutCommentByUserId(int userId) throws DAOException {

        List<OrderEntity> ordersWithoutComment;

        try (PreparedStatement statement = connection.prepareStatement(FIND_ORDER_WITHOUT_COMMENT_BY_USER_ID)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            ordersWithoutComment = new ArrayList<>();
            while (resultSet.next()) {
                OrderEntity order = new OrderEntity();
                order.setCash(resultSet.getBigDecimal(SQLFieldConstant.Order.PRICE));
                order.setExpectedTime(resultSet.getTimestamp(SQLFieldConstant.Order.EXPECTED_TIME));
                order.setTime(resultSet.getTimestamp(SQLFieldConstant.Order.TIME));
                String paymentType = resultSet.getString(SQLFieldConstant.Order.PAYMENT_METHOD);
                order.setPaymentType(PaymentType.valueOf(paymentType));
                order.setUserId(resultSet.getInt(SQLFieldConstant.Order.USER_ID));

                ordersWithoutComment.add(order);
            }

        } catch (SQLException e) {
            throw new DAOException("Find orders without comments error", e);
        }
        return ordersWithoutComment;
    }
}
