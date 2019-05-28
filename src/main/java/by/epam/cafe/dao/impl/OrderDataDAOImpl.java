package by.epam.cafe.dao.impl;

import by.epam.cafe.constant.SQLFieldConstant;
import by.epam.cafe.dao.OrderDataDAO;
import by.epam.cafe.entity.OrderDataEntity;
import by.epam.cafe.entity.ProductEntity;
import by.epam.cafe.exception.DAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderDataDAOImpl extends OrderDataDAO {

    private static final String FIND_ORDER_DATA_BY_ID =
            "SELECT order_order_id, product_product_id, number, product_price" +
                    " FROM orderdata" +
                    " WHERE order_order_id = ?;";

    @Override
    public List<OrderDataEntity> findAll() throws DAOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public OrderDataEntity findEntityById(int id) throws DAOException {
        OrderDataEntity foundOrderData;
        try (PreparedStatement statement = connection.prepareStatement(FIND_ORDER_DATA_BY_ID)) {
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            foundOrderData = extractOrderData(result).get(0);
        } catch (SQLException e) {
            throw new DAOException("Find all users error ", e);
        }
        return foundOrderData;
    }

    @Override
    public boolean delete(int id) throws DAOException {
        return false;
    }

    @Override
    public boolean delete(OrderDataEntity entity) throws DAOException {
        return false;
    }

    @Override
    public boolean create(OrderDataEntity entity) throws DAOException {
        return false;
    }

    @Override
    public boolean update(OrderDataEntity entity) throws DAOException {
        return false;
    }

    //TODO: check error
    private List<OrderDataEntity> extractOrderData(ResultSet resultSet) throws SQLException, DAOException {

        List<OrderDataEntity> orderDataList = new ArrayList<>();

        while (resultSet.next()) {
            OrderDataEntity orderData = new OrderDataEntity();
            orderData.setOrder_id(resultSet.getInt(SQLFieldConstant.OrderData.ORDER_ID));
            ProductDAOImpl productDAO = new ProductDAOImpl();
            ProductEntity product = productDAO.findEntityById(resultSet.getInt(SQLFieldConstant.OrderData.PRODUCT_ID));
            int number = (resultSet.getInt(SQLFieldConstant.OrderData.NUMBER));
            Map<ProductEntity, Integer> data = new HashMap<>();
            data.put(product, number);
            orderData.setProducts(data);
            orderDataList.add(orderData);
        }
        return orderDataList;
    }
}
