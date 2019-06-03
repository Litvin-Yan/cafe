package by.epam.cafe.dao;

import by.epam.cafe.entity.OrderDataEntity;
import by.epam.cafe.exception.DAOException;

import java.math.BigDecimal;
import java.util.List;

public abstract class OrderDataDAO extends DAO<OrderDataEntity> {

    /**
     * create order data
     *
     * @param productId
     * @param productCount
     * @param productPrice
     * @param orderId
     * @return  true if created
     * @throws DAOException
     */
    public abstract boolean create(int productId, int productCount, BigDecimal productPrice, int orderId) throws DAOException;

}
