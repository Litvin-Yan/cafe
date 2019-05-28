package by.epam.cafe.receiver.impl;

import by.epam.cafe.constant.GeneralConstant;
import by.epam.cafe.content.RequestContent;
import by.epam.cafe.entity.OrderDataEntity;
import by.epam.cafe.entity.ProductEntity;
import by.epam.cafe.exception.ReceiverException;
import by.epam.cafe.receiver.OrderReceiver;
import by.epam.cafe.type.UploadType;
import by.epam.cafe.util.Formatter;

import java.util.ArrayList;
import java.util.List;

import static by.epam.cafe.constant.GeneralConstant.ORDER_DATA;

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
//        private int id;
//        private int userId;
//        private BigDecimal cash;
//        private Boolean paid;
//        private BigDecimal bonus;
//        private Date expectedTime;
//        private Date time;
//        private PaymentType paymentType;

//        OrderValidatorImpl validator = new OrderValidatorImpl();
//        CommonValidatorImpl commonValidator = new CommonValidatorImpl();
//        int userId = ((UserEntity) content.getSessionAttributes().get(GeneralConstant.USER)).getId();
//        String[] date = content.getRequestParameters().get(GeneralConstant.EXPECTED_DATE);
//        String[] time = content.getRequestParameters().get(GeneralConstant.EXPECTED_TIME);
//        String[] paymentMethod = content.getRequestParameters().get(GeneralConstant.PAYMENT_METHOD);
//        OrderDataEntity orderData = (OrderDataEntity) content.getSessionAttributes().get(ORDER_DATA);
//        List<ProductEntity> orderList = new ArrayList<>(orderData.getProducts().keySet());
//
//        String dbPassword;
//        boolean isValidData = true;
//
//        content.getRequestAttributes().put(NAME, name[0]);
//        content.getRequestAttributes().put(EMAIL, email[0]);
//        content.getRequestAttributes().put(PASSWORD, password[0]);
//        content.getRequestAttributes().put(REPEAT_PASSWORD, repeatPassword[0]);
//
//        if (!commonValidator.isVarExist(password) || !validator.checkPassword(password[0])) {
//            content.getRequestAttributes().put(WRONG_PASSWORD, true);
//            isValidData = false;
//        }
//
//        if (!commonValidator.isVarExist(repeatPassword) || !password[0].equals(repeatPassword[0])) {
//            content.getRequestAttributes().put(WRONG_REPEAT_PASSWORD, true);
//            isValidData = false;
//        }
//
//        if (!commonValidator.isVarExist(email) || !validator.checkEmail(email[0])) {
//            content.getRequestAttributes().put(WRONG_EMAIL, true);
//            isValidData = false;
//        }
//
//        if (!commonValidator.isVarExist(name) || !validator.checkName(name[0])) {
//            content.getRequestAttributes().put(WRONG_NAME, true);
//            isValidData = false;
//        }
//
//        if (!isValidData) {
//            return;
//        }
//
//        dbPassword = StringEncoder.encode(password[0]);
//
//        UserEntity user = new UserEntity();
//        user.setName(name[0]);
//        user.setEmail(email[0]);
//        user.setPassword(dbPassword);
//
//        TransactionManager handler = new TransactionManager();
//        try {
//            UserDAOImpl userDao = new UserDAOImpl();
//            handler.beginTransaction(userDao);
//
//            if (userDao.create(user)) {
//                handler.commit();
//                content.getSessionAttributes().put(USER, userDao.findUser(user));
//                content.getSessionAttributes().put(ORDER_DATA, new OrderDataEntity());
//            } else {
//                content.getRequestAttributes().put(EMAIL_EXISTS, true);
//            }
//            handler.commit();
//            handler.endTransaction();
//
//        } catch (DAOException e) {
//            try {
//                handler.rollback();
//                handler.endTransaction();
//            } catch (DAOException e1) {
//                throw new ReceiverException("Sign up rollback error", e);
//            }
//            throw new ReceiverException(e);
//        }

    }
}
