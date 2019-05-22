package by.epam.cafe.receiver.impl;

import by.epam.cafe.content.RequestContent;
import by.epam.cafe.dao.TransactionManager;
import by.epam.cafe.dao.impl.*;
import by.epam.cafe.entity.OrderDataEntity;
import by.epam.cafe.entity.ProductEntity;
import by.epam.cafe.entity.UserEntity;
import by.epam.cafe.exception.DAOException;
import by.epam.cafe.exception.ReceiverException;
import by.epam.cafe.receiver.CommonReceiver;
import by.epam.cafe.type.UploadType;
import com.google.gson.JsonObject;

import java.util.List;
import java.util.Map;

import static by.epam.cafe.constant.GeneralConstant.*;
import static javax.servlet.jsp.PageContext.SESSION;

public class CommonReceiverImpl implements CommonReceiver {

    @Override
    public void changeLocale(RequestContent requestContent) throws ReceiverException {
        String locale = requestContent.getRequestParameters().get(LOCALE)[0];
        requestContent.getSessionAttributes().put(LOCALE, locale.toLowerCase());

        JsonObject object = new JsonObject();
        requestContent.setAjaxResult(object);
    }

    @Override
    public void openMainPage(RequestContent content) throws ReceiverException {
        UserEntity user = (UserEntity)  content.getSessionAttributes().get(USER);
        TransactionManager handler = new TransactionManager();
        try {
            ProductDAOImpl productDAO = new ProductDAOImpl();
            OrderDAOImpl orderDAO = new OrderDAOImpl();
            UserDAOImpl userDAO = new UserDAOImpl();
            OrderDataDAOImpl orderDataDAO = new OrderDataDAOImpl();
            handler.beginTransaction(productDAO, orderDAO, userDAO);

            if (user != null) {
                user = userDAO.findEntityById(user.getId());
            }
            handler.commit();
            handler.endTransaction();

            List<ProductEntity> productList = productDAO.findAll();
            List<OrderDataEntity> orderDataEntityList = orderDataDAO.findAll();

            content.getSessionAttributes().put(SESSION, true);
            content.getRequestAttributes().put(PRODUCT_LIST, productList);
            content.getRequestAttributes().put(ORDER_DATA, orderDataEntityList);
            content.getSessionAttributes().put(PRODUCTS_IMAGE_PATH, UploadType.PRODUCTS.getUploadFolder());
            content.getSessionAttributes().put(USER_IMAGE_PATH, UploadType.AVATARS.getUploadFolder());
            content.getSessionAttributes().put(USER, user);

        } catch (DAOException e) {
            try {
                handler.rollback();
                handler.endTransaction();
            } catch (DAOException e1) {
                throw new ReceiverException("Open main page rollback error", e);
            }
            throw new ReceiverException(e);
        }
    }

    @Override
    public void openNotFoundPage(RequestContent requestContent) throws ReceiverException {
    }

    @Override
    public void openAdminStatistic(RequestContent requestContent) throws ReceiverException {

        TransactionManager manager = new TransactionManager();
        try {
            CommonDAOImpl commonDAO = new CommonDAOImpl();
            manager.beginTransaction(commonDAO);

            Map<String, Object> statisticMap = commonDAO.findAdminStatistic();

            manager.commit();
            manager.endTransaction();

            requestContent.getRequestAttributes().put(STATISTIC_MAP, statisticMap);

        } catch (DAOException e) {
            try {
                manager.rollback();
                manager.endTransaction();
            } catch (DAOException e1) {
                throw new ReceiverException("Open admin main page rollback error", e);
            }
            throw new ReceiverException(e);
        }
    }
}
