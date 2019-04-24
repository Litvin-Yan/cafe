package by.epam.cafe.dao;

import by.epam.cafe.entity.TeamEntity;
import by.epam.cafe.exception.DAOException;

import java.util.List;
import java.util.Map;

public abstract class TeamDAO extends DAO<TeamEntity> {

    /**
     * Find teams by kind of sport id
     *
     * @param sportId kind of sport id
     * @return teams
     * @throws DAOException when sql request error
     */
    public abstract List<TeamEntity> findBySportId(int sportId) throws DAOException;

    /**
     * Find all teams with kind of sport
     *
     * @return teams
     * @throws DAOException when sql request error
     */
    public abstract List<Map<String, Object>> findAllWithKindOfSport() throws DAOException;
}
