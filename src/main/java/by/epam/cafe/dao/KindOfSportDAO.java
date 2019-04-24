package by.epam.cafe.dao;

import by.epam.cafe.entity.KindOfSportEntity;
import by.epam.cafe.exception.DAOException;

import java.util.List;
import java.util.Map;

public abstract class KindOfSportDAO extends DAO<KindOfSportEntity> {

    /**
     * Find using kinds of sport
     *
     * @return kinds of sport with competition types
     * @throws DAOException when sql request error
     */
    public abstract List<Map<String, Object>> findUsingKindsOfSport() throws DAOException;

}
