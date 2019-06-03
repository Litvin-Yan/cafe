package by.epam.cafe.dao;

import by.epam.cafe.exception.DAOException;
import by.epam.cafe.type.ExpectResultType;

import java.math.BigDecimal;
import java.util.Map;

public abstract class CommonDAO extends DAO {

    /**
     * Statistic for admin
     *
     * @return registered people count, locked people count, news count, sports count
     */
    public abstract Map<String, Object> findAdminStatistic();
}
