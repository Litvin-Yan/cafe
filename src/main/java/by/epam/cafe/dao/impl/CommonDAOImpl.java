package by.epam.cafe.dao.impl;

import by.epam.cafe.constant.GeneralConstant;
import by.epam.cafe.dao.CommonDAO;
import by.epam.cafe.entity.Entity;
import by.epam.cafe.exception.DAOException;
import by.epam.cafe.type.ExpectResultType;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommonDAOImpl extends CommonDAO {

    private static final String SELECT_ADMIN_STATISTIC =
            "SELECT " +
                    "(SELECT COUNT(user_id) FROM user) AS countRegistered, " +
                    "(SELECT COUNT(user_id) FROM user WHERE user_is_blocked) AS countLocked, " +
                    "(SELECT COUNT(product_id) FROM product) AS countProduct;";

    @Override
    public List findAll() throws DAOException {
        return null;
    }

    @Override
    public Entity findEntityById(int id) throws DAOException {
        return null;
    }

    @Override
    public boolean delete(int id) throws DAOException {
        return false;
    }

    @Override
    public boolean delete(Entity entity) throws DAOException {
        return false;
    }

    @Override
    public boolean create(Entity entity) throws DAOException {
        return false;
    }

    @Override
    public boolean update(Entity entity) throws DAOException {
        return false;
    }

    @Override
    public int findCountBetsOnCompetition(int competitionId, ExpectResultType expectResult) throws DAOException {
        return 0;
    }

    @Override
    public int findCountBetsOnCompetitor(int competitorId) throws DAOException {
        return 0;
    }

    @Override
    public BigDecimal findAmountOfMoneyOnCompetitor(int competitorId) throws DAOException {
        return null;
    }

    @Override
    public BigDecimal findAmountOfMoneyOnCompetition(int competitionId, ExpectResultType expectResult) throws DAOException {
        return null;
    }

    public Map<String, Object> findAdminStatistic() {
        Map<String, Object> statisticMap = null;

        try (PreparedStatement statement = connection.prepareStatement(SELECT_ADMIN_STATISTIC)) {
            ResultSet resultSet = statement.executeQuery();
            statisticMap = new HashMap<>();

            if (resultSet.next()) {
                statisticMap.put(GeneralConstant.REGISTERED_COUNT, resultSet.getInt(GeneralConstant.REGISTERED_COUNT));
                statisticMap.put(GeneralConstant.LOCKED_COUNT, resultSet.getInt(GeneralConstant.LOCKED_COUNT));
                statisticMap.put(GeneralConstant.PRODUCT_COUNT, resultSet.getInt(GeneralConstant.PRODUCT_COUNT));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statisticMap;
    }
}