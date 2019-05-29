package by.epam.cafe.dao.impl;

import by.epam.cafe.constant.GeneralConstant;
import by.epam.cafe.constant.SQLFieldConstant;
import by.epam.cafe.dao.UserDAO;
import by.epam.cafe.entity.OrderEntity;
import by.epam.cafe.entity.UserEntity;
import by.epam.cafe.exception.DAOException;
import by.epam.cafe.type.UserType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UserDAOImpl extends UserDAO {

    private static final String CREATE_USER =
            "INSERT INTO user (user_name, user_email, user_password) " +
                    "VALUES (?, ?, ?);";

    private static final String FIND_USER_BY_EMAIL_AND_PASSWORD =
            "SELECT user_id, user_name , user_type, user_email, user_password, user_money, " +
                    "user_is_blocked, user_bonus, user_avatar_url, user_blocked_text " +
                    "FROM user " +
                    "WHERE user_email = ? AND user_password = ?;";

    private static final String FIND_USER_BY_ID =
            "SELECT user_id, user_name , user_type, user_email, user_password, user_money, " +
                    "user_is_blocked, user_bonus, user_avatar_url, user_blocked_text " +
                    "FROM user " +
                    "WHERE user_id = ?;";

    private static final String FIND_LIMIT_USERS =
            "SELECT user_id" +
                    ", user_name" +
                    ", user_type" +
                    ", user_email" +
                    ", user_password" +
                    ", user_money" +
                    ", user_is_blocked" +
                    ", user_bonus" +
                    ", user_avatar_url" +
                    ", user_blocked_text" +
                    " FROM user " +
                    "ORDER BY user_id DESC " +
                    "LIMIT ?, ?;";

    private static final String FIND_USERS_COUNT =
            "SELECT  " +
                    "    COUNT(user_id) AS count " +
                    "FROM " +
                    "    user;";

    private static final String UPDATE_USER_ROLE =
            "UPDATE user SET user_type = ? WHERE user_id = ?;";

    private static final String UPDATE_AVATAR_PATH =
            "UPDATE user " +
                    "SET user_avatar_url = ? " +
                    "WHERE user_id = ?;";

    private static final String UPDATE_PASSWORD =
            "UPDATE user " +
                    "SET user_password = ? " +
                    "WHERE user_id = ?;";

    private static final String UPDATE_USER_LOCK =
            "UPDATE user " +
                    "SET user_is_blocked = ?, " +
                    " user_blocked_text = ? " +
                    "WHERE user_id = ?;";

    private static final String UPDATE_USER_MONEY =
            "UPDATE cafe.user " +
                    "SET user_money = ? " +
                    "WHERE user_id = ?;";

    private static final String RETURN_MONEY_FOR_CANCELED_ORDER =
            "UPDATE  " +
                    "    user, " +
                    "    bet, " +
                    "    competition, " +
                    "    competitor  " +
                    "SET  " +
                    "    user_cash = user_cash + bet_cash " +
                    "WHERE " +
                    "    user.user_id = bet.user_id " +
                    "        AND bet.competitor_id = competitor.competitor_id " +
                    "        AND competitor.competition_id = competition.competition_id " +
                    "        AND competition.competition_id = ?;";

    @Override
    public List<UserEntity> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public UserEntity findEntityById(int id) throws DAOException {
        UserEntity foundUser;
        try (PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_ID)) {
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            foundUser = extractUser(result);
        } catch (SQLException e) {
            throw new DAOException("Find all users error ", e);
        }
        return foundUser;
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(UserEntity entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean create(UserEntity entity) throws DAOException {
        boolean isCreated = false;
        try (PreparedStatement statement = connection.prepareStatement(CREATE_USER)) {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getEmail());
            statement.setString(3, entity.getPassword());
            isCreated = statement.executeUpdate() == 1;
        } catch (SQLException e) {
            if (!GeneralConstant.DUPLICATE_UNIQUE_INDEX.equals(e.getSQLState())) {
                throw new DAOException("Create user error ", e);
            }
        }
        return isCreated;
    }

    @Override
    public boolean update(UserEntity entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void returnMoneyForCanceledOrder(OrderEntity entity) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(RETURN_MONEY_FOR_CANCELED_ORDER)) {
            statement.setBigDecimal(1, entity.getCash());
            statement.execute();
        } catch (SQLException e) {
            throw new DAOException("Return money for bets error ", e);
        }
    }
//TODO: extract User add set for all fields
    private UserEntity extractUser(ResultSet result) throws SQLException {
        UserEntity foundUser = null;
        if (result.next()) {
            foundUser = new UserEntity();
            foundUser.setId(result.getInt(SQLFieldConstant.User.ID));
            foundUser.setName(result.getString(SQLFieldConstant.User.NAME));
            foundUser.setEmail(result.getString(SQLFieldConstant.User.EMAIL));
            foundUser.setPassword(result.getString(SQLFieldConstant.User.PASSWORD));
            foundUser.setBlocked(result.getBoolean(SQLFieldConstant.User.IS_BLOCKED));
            foundUser.setAvatarURL(result.getString(SQLFieldConstant.User.AVATAR_URL));
            foundUser.setBlockedText(result.getString(SQLFieldConstant.User.BLOCKED_TEXT));
            foundUser.setCash(result.getBigDecimal(SQLFieldConstant.User.MONEY));
            foundUser.setBonus(result.getBigDecimal(SQLFieldConstant.User.BONUS));
            String userType = result.getString(SQLFieldConstant.User.TYPE);
            foundUser.setType(UserType.valueOf(userType));
        }
        return foundUser;
    }

    public List<UserEntity> findLimit(int startIndex, int limit) throws DAOException {
        List<UserEntity> userList;

        try (PreparedStatement statement = connection.prepareStatement(FIND_LIMIT_USERS)) {
            statement.setInt(1, startIndex);
            statement.setInt(2, limit);
            ResultSet result = statement.executeQuery();
            userList = new ArrayList<>();

            while (result.next()) {
                UserEntity foundUser = new UserEntity();
                foundUser.setId(result.getInt(SQLFieldConstant.User.ID));
                foundUser.setName(result.getString(SQLFieldConstant.User.NAME));
                foundUser.setEmail(result.getString(SQLFieldConstant.User.EMAIL));
                foundUser.setPassword(result.getString(SQLFieldConstant.User.PASSWORD));
                foundUser.setBlocked(result.getBoolean(SQLFieldConstant.User.IS_BLOCKED));
                foundUser.setBlockedText(result.getString(SQLFieldConstant.User.BLOCKED_TEXT));
                foundUser.setAvatarURL(result.getString(SQLFieldConstant.User.AVATAR_URL));
                String userType = result.getString(SQLFieldConstant.User.TYPE);
                foundUser.setType(UserType.valueOf(userType));
                userList.add(foundUser);
            }

        } catch (SQLException e) {
            throw new DAOException("Find limit users error ", e);
        }

        return userList;
    }

    public int findUsersCount() throws DAOException {
        int count = 0;

        try (PreparedStatement statement = connection.prepareStatement(FIND_USERS_COUNT)) {

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                count = resultSet.getInt(GeneralConstant.COUNT);
            }

        } catch (SQLException e) {
            throw new DAOException("Find users count error ", e);
        }

        return count;
    }

    public UserEntity findUser(UserEntity user) throws DAOException {
        UserEntity foundUser;

        try (PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_EMAIL_AND_PASSWORD)) {
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            ResultSet result = statement.executeQuery();
            foundUser = extractUser(result);

        } catch (SQLException e) {
            throw new DAOException("Find user error ", e);
        }

        return foundUser;
    }

    public boolean updateRole(UserEntity entity) throws DAOException {
        boolean isUpdated = false;

        try (PreparedStatement statement = connection.prepareStatement(UPDATE_USER_ROLE)) {

            String type = entity.getType().toString();
            statement.setString(1, type);
            statement.setInt(2, entity.getId());

            isUpdated = statement.executeUpdate() == 1;


        } catch (SQLException e) {
            if (!GeneralConstant.CAN_NOT_DELETE_OR_UPDATE.equals(e.getSQLState())) {
                throw new DAOException("Update user error ", e);
            }
        }
        return isUpdated;
    }

    public boolean updateAvatarPath(UserEntity entity) throws DAOException {
        boolean isUpdated = false;

        try (PreparedStatement statement = connection.prepareStatement(UPDATE_AVATAR_PATH)) {

            statement.setString(1, entity.getAvatarURL());
            statement.setInt(2, entity.getId());

            isUpdated = statement.executeUpdate() == 1;


        } catch (SQLException e) {
            if (!GeneralConstant.CAN_NOT_DELETE_OR_UPDATE.equals(e.getSQLState())) {
                throw new DAOException("Update avatar error ", e);
            }
        }
        return isUpdated;
    }

    public boolean updatePassword(UserEntity entity) throws DAOException {
        boolean isUpdated = false;

        try (PreparedStatement statement = connection.prepareStatement(UPDATE_PASSWORD)) {

            statement.setString(1, entity.getPassword());
            statement.setInt(2, entity.getId());

            isUpdated = statement.executeUpdate() == 1;


        } catch (SQLException e) {
            if (!GeneralConstant.CAN_NOT_DELETE_OR_UPDATE.equals(e.getSQLState())) {
                throw new DAOException("Create user error ", e);
            }
        }

        return isUpdated;
    }

    public boolean updateLock(UserEntity entity) throws DAOException {
        boolean isUpdated = false;

        try (PreparedStatement statement = connection.prepareStatement(UPDATE_USER_LOCK)) {

            statement.setBoolean(1, entity.getIsBlocked());
            statement.setString(2, entity.getBlockedText());
            statement.setInt(3, entity.getId());

            isUpdated = statement.executeUpdate() == 1;

        } catch (SQLException e) {
            if (!GeneralConstant.CAN_NOT_DELETE_OR_UPDATE.equals(e.getSQLState())) {
                throw new DAOException("Create user error ", e);
            }
        }
        return isUpdated;
    }

    public boolean updateCash(UserEntity entity) throws DAOException {
        boolean isUpdated;

        try (PreparedStatement statement = connection.prepareStatement(UPDATE_USER_MONEY)) {
            statement.setBigDecimal(1, entity.getCash());
            statement.setInt(2, entity.getId());
            isUpdated = statement.executeUpdate() == 1;

        } catch (SQLException e) {
            throw new DAOException("Update cash error ", e);
        }

        return isUpdated;
    }
}
