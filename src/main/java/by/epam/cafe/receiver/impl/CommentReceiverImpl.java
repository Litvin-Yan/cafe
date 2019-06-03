package by.epam.cafe.receiver.impl;

import by.epam.cafe.constant.GeneralConstant;
import by.epam.cafe.content.RequestContent;
import by.epam.cafe.dao.TransactionManager;
import by.epam.cafe.dao.impl.CommentDAOImpl;
import by.epam.cafe.dao.impl.UserDAOImpl;
import by.epam.cafe.entity.CommentEntity;
import by.epam.cafe.entity.UserEntity;
import by.epam.cafe.exception.DAOException;
import by.epam.cafe.exception.ReceiverException;
import by.epam.cafe.receiver.CommentReceiver;
import by.epam.cafe.util.Formatter;
import by.epam.cafe.validator.impl.CommentValidatorImpl;
import by.epam.cafe.validator.impl.CommonValidatorImpl;
import by.epam.cafe.validator.impl.UserValidatorImpl;
import com.google.gson.Gson;
import javafx.util.Pair;

import java.util.List;


public class CommentReceiverImpl implements CommentReceiver {

    @Override
    public void changeLockComment(RequestContent content) throws ReceiverException {
        CommonValidatorImpl commonValidator = new CommonValidatorImpl();
        UserValidatorImpl userValidator = new UserValidatorImpl();
        UserEntity user = (UserEntity) content.getSessionAttributes().get(GeneralConstant.USER);
        String[] stringCommentId = content.getRequestParameters().get(GeneralConstant.COMMENT_ID);
        String[] isLockedCommentString = content.getRequestParameters().get(GeneralConstant.IS_BLOCKED);
        boolean isLockedComment = Boolean.valueOf(isLockedCommentString[0]);

        if (!userValidator.isAdmin(user)) {
            content.setAjaxSuccess(false);
            content.getAjaxResult().add(GeneralConstant.ACCESS_DENIED, new Gson().toJsonTree(true));
            return;
        }

        if (!commonValidator.isVarExist(stringCommentId) || !commonValidator.isInteger(stringCommentId[0])) {
            content.setAjaxSuccess(false);
            return;
        }

        int commentId = Integer.valueOf(stringCommentId[0]);
        TransactionManager manager = new TransactionManager();
        try {
            CommentDAOImpl commentDAO = new CommentDAOImpl();
            manager.beginTransaction(commentDAO);
            commentDAO.changeLockCommentById(commentId, !isLockedComment);
            manager.commit();
            manager.endTransaction();

            content.setAjaxSuccess(true);

        } catch (DAOException e) {
            try {
                manager.rollback();
                manager.endTransaction();
            } catch (DAOException e1) {
                throw new ReceiverException("Change block comment error", e);
            }
            throw new ReceiverException(e);
        }
    }

    @Override
    public void createComment(RequestContent content) throws ReceiverException {
        CommonValidatorImpl commonValidator = new CommonValidatorImpl();
        CommentValidatorImpl commentValidator = new CommentValidatorImpl();
        UserEntity user = (UserEntity) content.getSessionAttributes().get(GeneralConstant.USER);
        String[] stringOrderId = content.getRequestParameters().get(GeneralConstant.ORDER_ID);
        String[] commentTextArr = content.getRequestParameters().get(GeneralConstant.TEXT);
        String[] commentRateArr = content.getRequestParameters().get(GeneralConstant.COMMENT_RATE);


        if (user == null) {
            content.setAjaxSuccess(false);
            content.getAjaxResult().add(GeneralConstant.ACCESS_DENIED, new Gson().toJsonTree(true));
            return;
        }

        if (!commonValidator.isVarExist(commentTextArr) ||
                !commonValidator.isVarExist(stringOrderId) ||
                !commonValidator.isInteger(stringOrderId[0]) ||
                !commonValidator.isVarExist(commentRateArr) ||
                !commonValidator.isInteger(commentRateArr[0]) ||
                !commentValidator.isCommentTextValid(commentTextArr[0].trim())) {
            content.setAjaxSuccess(false);
            return;
        }

        CommentEntity comment = new CommentEntity();

        comment.setOrderId(Integer.valueOf(stringOrderId[0]));
        comment.setRate(Integer.valueOf(commentRateArr[0]));
        comment.setText(commentTextArr[0].trim());

        TransactionManager manager = new TransactionManager();
        try {
            CommentDAOImpl commentDAO = new CommentDAOImpl();
            manager.beginTransaction(commentDAO);
            commentDAO.create(comment);
            manager.commit();
            manager.endTransaction();
            content.setAjaxSuccess(true);

        } catch (DAOException e) {
            try {
                manager.rollback();
                manager.endTransaction();
            } catch (DAOException e1) {
                throw new ReceiverException("Create comment rollback error", e);
            }
            throw new ReceiverException(e);
        }
    }

    @Override
    public void openCommentPage(RequestContent content) throws ReceiverException {
        Formatter formatter = new Formatter();
        String[] stringPage = content.getRequestParameters().get(GeneralConstant.PAGE_NUMBER);
        int page = formatter.formatToPage(stringPage);

        if (page == -1) {
            content.getRequestAttributes().put(GeneralConstant.PAGE_NOT_FOUND, true);
            return;
        }

        int startIndex = formatter.formatToStartIndex(page, GeneralConstant.COUNT_COMMENT_ON_PAGE);
        TransactionManager handler = new TransactionManager();

        List<Pair<UserEntity, CommentEntity>> commentDataList;
        int commentCount;

        try {
            UserDAOImpl userDAO = new UserDAOImpl();
            CommentDAOImpl commentDAO = new CommentDAOImpl();
            handler.beginTransaction(userDAO, commentDAO);

            commentDataList = commentDAO.findDataForComment(startIndex, GeneralConstant.COUNT_PRODUCTS_ON_PAGE);
            commentCount = commentDAO.findCommentCount();
            handler.commit();
            handler.endTransaction();

            if (commentDataList.isEmpty() && page != 1) {
                content.getRequestAttributes().put(GeneralConstant.PAGE_NOT_FOUND, true);
                return;
            }

            content.getRequestAttributes().put(GeneralConstant.LIMIT, GeneralConstant.COUNT_PRODUCTS_ON_PAGE);
            content.getRequestAttributes().put(GeneralConstant.COMMENT_COUNT, commentCount);
            content.getRequestAttributes().put(GeneralConstant.COMMENT_DATA_LIST, commentDataList);

        } catch (DAOException e) {
            try {
                handler.rollback();
                handler.endTransaction();

            } catch (DAOException e1) {
                throw new ReceiverException("Open comments rollback error", e);
            }
            throw new ReceiverException(e);
        }
    }
}
