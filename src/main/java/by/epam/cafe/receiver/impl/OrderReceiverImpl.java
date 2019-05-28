package by.epam.cafe.receiver.impl;

import by.epam.cafe.constant.GeneralConstant;
import by.epam.cafe.constant.RequestNameConstant;
import by.epam.cafe.content.RequestContent;
import by.epam.cafe.dao.TransactionManager;
import by.epam.cafe.dao.impl.OrderDAOImpl;
import by.epam.cafe.dao.impl.OrderDataDAOImpl;
import by.epam.cafe.dao.impl.UserDAOImpl;
import by.epam.cafe.entity.OrderDataEntity;
import by.epam.cafe.entity.OrderEntity;
import by.epam.cafe.entity.ProductEntity;
import by.epam.cafe.entity.UserEntity;
import by.epam.cafe.exception.DAOException;
import by.epam.cafe.exception.ReceiverException;
import by.epam.cafe.receiver.OrderReceiver;
import by.epam.cafe.type.UploadType;
import by.epam.cafe.util.Formatter;
import by.epam.cafe.util.StringEncoder;
import by.epam.cafe.validator.impl.CommonValidatorImpl;
import by.epam.cafe.validator.impl.OrderValidatorImpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static by.epam.cafe.constant.GeneralConstant.ORDER;
import static by.epam.cafe.constant.GeneralConstant.ORDER_DATA;
import static by.epam.cafe.constant.RequestNameConstant.WRONG_PASSWORD;
import static by.epam.cafe.constant.RequestNameConstant.WRONG_REPEAT_PASSWORD;

public class OrderReceiverImpl implements OrderReceiver {

    public void openBasketPage(RequestContent content) {
        Formatter formatter = new Formatter();
        String[] stringPage = content.getRequestParameters().get(GeneralConstant.PAGE_NUMBER);
        int page = formatter.formatToPage(stringPage);
        if (page == -1) {
            content.getRequestAttributes().put(GeneralConstant.PAGE_NOT_FOUND, true);
            return;
        }

        int startIndex = formatter.formatToStartIndex(page, GeneralConstant.COUNT_PRODUCTS_ON_PAGE);
        List<ProductEntity> productList;
        int productCount;
        OrderDataEntity orderDataEntity;

        orderDataEntity = (OrderDataEntity) content.getSessionAttributes().get(ORDER_DATA);
        productCount = orderDataEntity.getProducts().size();

        productList = new ArrayList<>(orderDataEntity.getProducts().keySet());

        if (startIndex + GeneralConstant.COUNT_PRODUCTS_ON_PAGE > productCount) {
            productList = productList.subList(startIndex, productCount);
        } else {
            productList = productList.subList(startIndex, startIndex + GeneralConstant.COUNT_PRODUCTS_ON_PAGE);
        }

        content.getRequestAttributes().put(GeneralConstant.PRODUCT_LIST, productList);
        content.getRequestAttributes().put(GeneralConstant.LIMIT, GeneralConstant.COUNT_PRODUCTS_ON_PAGE);
        content.getRequestAttributes().put(GeneralConstant.PRODUCT_COUNT, productCount);
        content.getRequestAttributes().put(GeneralConstant.PRODUCTS_IMAGE_PATH, UploadType.PRODUCTS.getUploadFolder());

    }


    @Override
    public void createOrder(RequestContent content) throws ReceiverException {

        OrderValidatorImpl validator = new OrderValidatorImpl();
        CommonValidatorImpl commonValidator = new CommonValidatorImpl();
        int userId = ((UserEntity) content.getSessionAttributes().get(GeneralConstant.USER)).getId();
        String[] date = content.getRequestParameters().get(GeneralConstant.EXPECTED_DATE);
        String[] time = content.getRequestParameters().get(GeneralConstant.EXPECTED_TIME);
        Timestamp expectedTime = Timestamp.valueOf(date[0]+" "+time[0]);
        String[] paymentMethod = content.getRequestParameters().get(GeneralConstant.PAYMENT_METHOD);


        boolean isValidData = true;

        content.getRequestAttributes().put(RequestNameConstant.EXPECTED_TIME, expectedTime);
        content.getRequestAttributes().put(RequestNameConstant.PAYMENT_METHOD, paymentMethod[0]);

        if (!commonValidator.isVarExist(expectedTime) || !validator.checkPassword(password[0])) {
            content.getRequestAttributes().put(WRONG_PASSWORD, true);
            isValidData = false;
        }

        if (!commonValidator.isVarExist(repeatPassword) || !password[0].equals(repeatPassword[0])) {
            content.getRequestAttributes().put(WRONG_REPEAT_PASSWORD, true);
            isValidData = false;
        }

        if (!commonValidator.isVarExist(email) || !validator.checkEmail(email[0])) {
            content.getRequestAttributes().put(WRONG_EMAIL, true);
            isValidData = false;
        }

        if (!commonValidator.isVarExist(name) || !validator.checkName(name[0])) {
            content.getRequestAttributes().put(WRONG_NAME, true);
            isValidData = false;
        }

        if (!isValidData) {
            return;
        }

        OrderEntity order = new OrderEntity();
        order.setCash(cash);
        order.setPaid(false);
        order.setUserId(userId);
        order.setBonus();
        order.setExpectedTime();
        order.setId(userId);
        order.setPaymentType();
        order.setTime();

        TransactionManager handler = new TransactionManager();
        try {
            UserDAOImpl userDao = new UserDAOImpl();
            OrderDAOImpl orderDAO = new OrderDAOImpl();
            OrderDataDAOImpl orderDataDAO = new OrderDataDAOImpl();
            handler.beginTransaction(userDao, orderDAO, orderDataDAO);

            if (orderDAO.create(order)) {
                handler.commit();
            } else {
//                content.getRequestAttributes().put(EMAIL_EXISTS, true);
            }
            handler.commit();
            handler.endTransaction();

        } catch (DAOException e) {
            try {
                handler.rollback();
                handler.endTransaction();
            } catch (DAOException e1) {
                throw new ReceiverException("Create order rollback error", e);
            }
            throw new ReceiverException(e);
        }

    }
}
