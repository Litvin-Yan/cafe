package by.epam.cafe.dao.impl;

import by.epam.cafe.constant.GeneralConstant;
import by.epam.cafe.constant.SQLFieldConstant;
import by.epam.cafe.dao.OrderDAO;
import by.epam.cafe.entity.OrderEntity;
import by.epam.cafe.exception.DAOException;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderDAOImpl extends OrderDAO {

    private static final String CREATE_ORDER =
            "INSERT INTO `order` (order_expected_time, order_bonus, order_payment_method, user_id) " +
                    "VALUES (?, ?, ?, ?);";

    private static final String FIND_ACTIVE_ORDER_BY_USER_ID =
            "select distinct order_id" +
                    ", order_paid" +
                    ", order_payment_method" +
                    ", order_bonus" +
                    ", user_id" +
                    ", order_expected_time" +
                    ", order_time " +
                    "FROM " +
                    "`order` "+
                    "WHERE " +
                    "user_id = ? AND " +
                    "order_expected_time > now() " +
                    "ORDER BY order_time;";

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
            isCreated = statement.executeUpdate() == 1;
        } catch (SQLException e) {
            if (!GeneralConstant.DUPLICATE_UNIQUE_INDEX.equals(e.getSQLState())) {
                throw new DAOException("Create order error ", e);
            }
        }
        return isCreated;
    }

    @Override
    public boolean update(OrderEntity entity) throws DAOException {
        return false;
    }

    public List<Map<String, Object>> findActiveOrdersByUserId(int userId) throws DAOException {
        List<Map<String, Object>> activeOrders;

        try (PreparedStatement statement = connection.prepareStatement(FIND_ACTIVE_ORDER_BY_USER_ID)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            activeOrders = new ArrayList<>();
            while (resultSet.next()) {
                Map<String, Object> activeOrder = new HashMap<>();
                OrderEntity order = new OrderEntity();

                order.setId(resultSet.getInt(SQLFieldConstant.Order.ID));
                order.setCash(resultSet.getBigDecimal(SQLFieldConstant.OrderData.PRODUCT_PRICE));
                order.setUserId(resultSet.getInt(SQLFieldConstant.User.ID));
                BigDecimal divisor = new BigDecimal(10);
                BigDecimal bonus = order.getCash().divide(divisor,0);
                order.setBonus(bonus);
                order.setExpectedTime(resultSet.getTimestamp(SQLFieldConstant.Order.EXPECTED_TIME));
                order.setPaid(resultSet.getBoolean(SQLFieldConstant.Order.PAID));
                order.setUserId(resultSet.getInt(SQLFieldConstant.Order.USER_ID));
            }

        } catch (SQLException e) {
            throw new DAOException("Find active orders error", e);
        }
        return activeOrders;
    }
}
