package by.epam.cafe.dao.impl;

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

    private static final String FIND_ACTIVE_ORDER_BY_USER_ID =
            "select distinct order.order_id" +
                    ", order.order_paid" +
                    ", order.order_payment_method" +
                    ", order.order_bonus" +
                    ", order.user_id" +
                    ", order.order_expected_time" +
                    ", order.order_time " +
                    "FROM " +
                    "cafe.order "+
                    "WHERE " +
                    "order.user_id = ? AND " +
                    "order.order_expected_time > now() " +
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
        return false;
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
                order.setCash(resultSet.getBigDecimal(12));
                order.setUserId(resultSet.getInt(SQLFieldConstant.User.ID));
                BigDecimal divisor = new BigDecimal(10);
                BigDecimal bonus = order.getCash().divide(divisor,0);
                order.setBonus(bonus);
                order.setExpectedTime(resultSet.getDate(SQLFieldConstant.Order.EXPECTED_TIME));
                order.setPaid(resultSet.getBoolean(SQLFieldConstant.Order.PAID));
                order.setUserId(resultSet.getInt(SQLFieldConstant.Order.USER_ID));
            }

        } catch (SQLException e) {
            throw new DAOException("Find active orders error", e);
        }
        return activeOrders;
    }
}
