package by.epam.cafe.dao.impl;

import by.epam.cafe.constant.GeneralConstant;
import by.epam.cafe.constant.SQLFieldConstant;
import by.epam.cafe.dao.CommentDAO;
import by.epam.cafe.entity.CommentEntity;
import by.epam.cafe.entity.UserEntity;
import by.epam.cafe.exception.DAOException;
import javafx.util.Pair;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommentDAOImpl extends CommentDAO {

    public static final String FIND_ORDER_COMMENTS =
            "SELECT DISTINCT " +
                    "    comment.order_id, " +
                    "    comment_is_blocked, " +
                    "    comment_date, " +
                    "    comment_content, " +
                    "    user_avatar_url, " +
                    "    user_name " +
                    "FROM " +
                    "    comment, " +
                    "    user, " +
                    "    order " +
                    "WHERE " +
                    "    order.user_id = user.user_id " +
                    "    AND comment.order_id = ?;";

    public static final String CREATE_COMMENT =
            "INSERT INTO comment (comment_content, comment_rate, comment_date, order_id) " +
                    "VALUES (?, ?, now(), ?);";

    public static final String CHANGE_LOCK_COMMENT =
            "UPDATE comment " +
                    "SET comment_is_blocked = ? " +
                    "WHERE comment.order_id = ?;";

    public static final String FIND_DATA_FOR_COMMENT =
            "SELECT comment_content"+
                    ", comment_rate"+
                    ", comment_date"+
                    ", user_name"+
                    ", user_avatar_url "+
                    ", comment.order_id "+
                    "FROM comment "+
                    "JOIN `order` "+
                    "ON (comment.order_id = `order`.order_id) "+
                    "JOIN user "+
                    "ON (user.user_id = `order`.user_id) "+
                    "ORDER BY comment_date DESC "+
                    "LIMIT ?, ?;";

    private static final String FIND_COMMENT_COUNT =
            "SELECT  " +
                    "    COUNT(order_id) AS count " +
                    "FROM " +
                    "    comment;";

    @Override
    public List<CommentEntity> findAll() throws DAOException {
        throw new UnsupportedOperationException();

    }

    @Override
    public CommentEntity findEntityById(int id) throws DAOException {
        throw new UnsupportedOperationException();

    }

    @Override
    public boolean create(CommentEntity entity) throws DAOException {
        boolean isCreated;

        try (PreparedStatement statement = connection.prepareStatement(CREATE_COMMENT)) {
            statement.setString(1, entity.getText());
            statement.setInt(2, entity.getRate());
            statement.setInt(3, entity.getOrderId());
            isCreated = statement.executeUpdate() == 1;

        } catch (SQLException e) {
            throw new DAOException("Error create comment",e);
        }
        return isCreated;
    }

    @Override
    public void changeLockCommentById(int commentId, boolean changeValue) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(CHANGE_LOCK_COMMENT)) {
            statement.setBoolean(1, changeValue);
            statement.setInt(2, commentId);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Error change comment lock ",e);
        }
    }

    @Override
    public boolean delete(int id) throws DAOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(CommentEntity entity) throws DAOException {
        throw new UnsupportedOperationException();

    }

    @Override
    public boolean update(CommentEntity entity) throws DAOException {
        throw new UnsupportedOperationException();

    }

    @Override
    public List<Pair<UserEntity, CommentEntity>> findDataForComment(int startIndex, int limit) throws DAOException {

        List<Pair<UserEntity, CommentEntity>> commentDataList = new ArrayList<>();
        Pair<UserEntity, CommentEntity> commentData;

        try (PreparedStatement statement = connection.prepareStatement(FIND_DATA_FOR_COMMENT)) {
            statement.setInt(1, startIndex);
            statement.setInt(2, limit);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                UserEntity foundUser = new UserEntity();
                CommentEntity foundComment = new CommentEntity();

                foundUser.setName(result.getString(SQLFieldConstant.User.NAME));
                foundUser.setAvatarURL(result.getString(SQLFieldConstant.User.AVATAR_URL));
                foundComment.setRate(result.getInt(SQLFieldConstant.Comment.RATE));
                foundComment.setPostDate(result.getTimestamp(SQLFieldConstant.Comment.POST_DATE));
                foundComment.setText(result.getString(SQLFieldConstant.Comment.CONTENT));
                foundComment.setOrderId(result.getInt(SQLFieldConstant.Comment.ORDER_ID));

                commentData = new Pair<>(foundUser, foundComment);
                commentDataList.add(commentData);
            }

        } catch (SQLException e) {
            throw new DAOException("Find limit users error ", e);
        }

        return commentDataList;
    }

    @Override
    public int findCommentCount() throws DAOException {
        int count = 0;

        try (PreparedStatement statement = connection.prepareStatement(FIND_COMMENT_COUNT)) {

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                count = resultSet.getInt(GeneralConstant.COUNT);
            }

        } catch (SQLException e) {
            throw new DAOException("Find users count error ", e);
        }

        return count;
    }
}
