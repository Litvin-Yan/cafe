package by.epam.cafe.dao;

import by.epam.cafe.entity.CommentEntity;
import by.epam.cafe.exception.DAOException;

public abstract class CommentDAO extends DAO<CommentEntity> {

    /**
     * Change lock comment by id
     *
     * @param commentId   comment id
     * @param changeValue lock value
     * @throws DAOException when sql request error
     */
    public abstract void changeLockCommentById(int commentId, boolean changeValue) throws DAOException;

    /**
     * Delete news by id
     *
     * @param newsId news id
     * @return success delete
     * @throws DAOException when sql request error
     */
    public abstract boolean deleteByNewsId(int newsId) throws DAOException;
}
