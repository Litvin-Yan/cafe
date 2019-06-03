package by.epam.cafe.dao;

import by.epam.cafe.entity.CommentEntity;
import by.epam.cafe.entity.UserEntity;
import by.epam.cafe.exception.DAOException;
import javafx.util.Pair;

import java.util.List;

public abstract class CommentDAO extends DAO<CommentEntity> {

    /**
     * find data for comment with limit
     *
     * @param startIndex
     * @param limit
     * @return data for comment
     * @throws DAOException when sql request error
     */
    public abstract List<Pair<UserEntity, CommentEntity>> findDataForComment(int startIndex, int limit) throws DAOException;

    /**
     * Change lock comment by id
     *
     * @param commentId   comment id
     * @param changeValue lock value
     * @throws DAOException when sql request error
     */
    public abstract void changeLockCommentById(int commentId, boolean changeValue) throws DAOException;

    /**
     * find comment count
     *
     * @return count of comment
     * @throws DAOException
     */
    public abstract int findCommentCount() throws DAOException;

}
