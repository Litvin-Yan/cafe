package by.epam.cafe.dao.impl;

import by.epam.cafe.constant.SQLFieldConstant;
import by.epam.cafe.dao.CommentDAO;
import by.epam.cafe.entity.CommentEntity;
import by.epam.cafe.exception.DAOException;

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

    public static final String DELETE_COMMENT_BY_ORDER_ID =
            "DELETE FROM comment  " +
                    "WHERE " +
                    "    comment.order_id = ?;";

    @Override
    public List<CommentEntity> findAll() throws DAOException {
        throw new UnsupportedOperationException();

    }

    @Override
    public CommentEntity findEntityById(int id) throws DAOException {
        throw new UnsupportedOperationException();

    }

    public List<Map<String, Object>> findCommentsByNewsId(int newsId) throws DAOException {
        List<Map<String, Object>> newsCommentList = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(FIND_ORDER_COMMENTS)) {
            statement.setInt(1, newsId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Map<String, Object> newsComment = new HashMap<>();
                newsComment.put(SQLFieldConstant.Comment.ORDER_ID,
                        resultSet.getInt(SQLFieldConstant.Comment.ORDER_ID));
                newsComment.put(SQLFieldConstant.Comment.IS_BLOCKED,
                        resultSet.getBoolean(SQLFieldConstant.Comment.IS_BLOCKED));
                newsComment.put(SQLFieldConstant.Comment.POST_DATE,
                        resultSet.getDate(SQLFieldConstant.Comment.POST_DATE));
                newsComment.put(SQLFieldConstant.Comment.CONTENT,
                        resultSet.getString(SQLFieldConstant.Comment.CONTENT));
                newsComment.put(SQLFieldConstant.User.AVATAR_URL,
                        resultSet.getString(SQLFieldConstant.User.AVATAR_URL));
                newsComment.put(SQLFieldConstant.User.NAME,
                        resultSet.getString(SQLFieldConstant.User.NAME));
                newsCommentList.add(newsComment);
            }

        } catch (SQLException e) {
            throw new DAOException("Error find product comments ",e);
        }

        return newsCommentList;
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

    public void changeLockCommentById(int commentId, boolean changeValue) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(CHANGE_LOCK_COMMENT)) {
            statement.setBoolean(1, changeValue);
            statement.setInt(2, commentId);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Error change comment lock ",e);
        }
    }

    public boolean deleteByNewsId(int newsId) throws DAOException {
        boolean isDeleted;
        try (PreparedStatement statement = connection.prepareStatement(DELETE_COMMENT_BY_ORDER_ID)) {
            statement.setInt(1,newsId);
            isDeleted = !statement.execute();

        } catch (SQLException e) {
            throw new DAOException("Error delete comment",e);
        }
        return isDeleted;
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
}
