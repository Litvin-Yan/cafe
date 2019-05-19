package by.epam.cafe.dao.impl;

import by.epam.cafe.constant.SQLFieldConstant;
import by.epam.cafe.dao.OrderDataDAO;
import by.epam.cafe.entity.OrderDataEntity;
import by.epam.cafe.exception.DAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDataDAOImpl extends OrderDataDAO{

    @Override
    public List<OrderDataEntity> findAll() throws DAOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public OrderDataEntity findEntityById(int id) throws DAOException {
        return null;
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
}
