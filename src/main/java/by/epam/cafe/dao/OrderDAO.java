package by.epam.cafe.dao;

import by.epam.cafe.entity.OrderEntity;
import by.epam.cafe.exception.DAOException;

import java.util.List;

public abstract class OrderDAO extends DAO<OrderEntity> {

    /**
     * create order
     *
     * @param entity
     * @return  return orderId
     * @throws DAOException
     */
    public abstract int createAndGetOrderId(OrderEntity entity) throws DAOException;

    /**
     * find active order by user id
     *
     * @param userId
     * @return order entity list
     * @throws DAOException
     */
    public abstract List<OrderEntity> findActiveOrdersByUserId(int userId) throws DAOException;

    /**
     * find order without comment by user id
     *
     * @param userId
     * @return  order without comment
     * @throws DAOException
     */
    public abstract List<OrderEntity> findOrdersWithoutCommentByUserId(int userId) throws DAOException;


}
