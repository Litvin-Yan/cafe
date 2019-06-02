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
import by.epam.cafe.type.PaymentType;
import by.epam.cafe.type.UploadType;
import by.epam.cafe.util.Formatter;
import by.epam.cafe.validator.impl.CommonValidatorImpl;
import by.epam.cafe.validator.impl.OrderValidatorImpl;
import com.google.gson.Gson;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static by.epam.cafe.constant.GeneralConstant.ORDER_DATA;
import static by.epam.cafe.constant.GeneralConstant.WRONG_DATA;

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
        content.getRequestAttributes().put(GeneralConstant.PRODUCTS_COUNT, productCount);
        content.getRequestAttributes().put(GeneralConstant.PRODUCT_IMAGE_PATH, UploadType.PRODUCTS.getUploadFolder());

    }


    @Override
    public void createOrder(RequestContent content) throws ReceiverException {

        OrderValidatorImpl validator = new OrderValidatorImpl();
        CommonValidatorImpl commonValidator = new CommonValidatorImpl();

        int userId = ((UserEntity) content.getSessionAttributes().get(GeneralConstant.USER)).getId();
        String[] date = content.getRequestParameters().get(GeneralConstant.EXPECTED_DATE);
        String[] time = content.getRequestParameters().get(GeneralConstant.EXPECTED_TIME);
        Timestamp expectedTime = Timestamp.valueOf(date[0] + " " + time[0] + ":00.0");
        String[] paymentMethod = content.getRequestParameters().get(GeneralConstant.PAYMENT_METHOD);
        PaymentType paymentType = PaymentType.valueOf(paymentMethod[0]);
        String[] orderPrice = content.getRequestParameters().get(GeneralConstant.ORDER_PRICE);

        content.getRequestAttributes().put(RequestNameConstant.EXPECTED_TIME, expectedTime);
        content.getRequestAttributes().put(RequestNameConstant.PAYMENT_METHOD, paymentMethod[0]);
        content.getRequestAttributes().put(RequestNameConstant.ORDER_PRICE, orderPrice[0]);

        OrderEntity order = new OrderEntity();
        order.setPaid(false);
        order.setUserId(userId);
        order.setExpectedTime(expectedTime);
        order.setPaymentType(paymentType);
        BigDecimal cash = BigDecimal.valueOf(Double.parseDouble(orderPrice[0])).setScale(10, RoundingMode.HALF_DOWN);
        order.setCash(cash);
        BigDecimal bonus = order.getCash().multiply(GeneralConstant.PROCENTAGE_OF_BONUSES_OF_THE_ORDER_AMOUNT).setScale(10, RoundingMode.HALF_DOWN);
        order.setBonus(bonus);

        OrderDataEntity orderData = (OrderDataEntity) content.getSessionAttributes().get(ORDER_DATA);

        if (orderData.getProducts().isEmpty()) {
            content.getRequestAttributes().put(WRONG_DATA, true);
            return;
        }

        if (!validator.isValidExpectedDate(order)) {
            content.getRequestAttributes().put(WRONG_DATA, true);
            return;
        }
        TransactionManager manager = new TransactionManager();
        try {
            UserDAOImpl userDao = new UserDAOImpl();
            OrderDAOImpl orderDAO = new OrderDAOImpl();
            OrderDataDAOImpl orderDataDAO = new OrderDataDAOImpl();
            OrderDataReceiverImpl orderDataReceiver = new OrderDataReceiverImpl();
            manager.beginTransaction(userDao, orderDAO, orderDataDAO);

            UserEntity user = userDao.findEntityById(userId);
            boolean enoughMoney;
            switch (order.getPaymentType()) {
                case CASH:
                    enoughMoney = true;
                    break;
                case MONEY:
                    enoughMoney = user.getCash().compareTo(order.getCash()) != -1;
                    break;
                case BONUSES:
                    enoughMoney = user.getBonus().compareTo(order.getCash()) != -1;
                    break;
                default:
                    enoughMoney = false;
            }

            if (!enoughMoney){
                content.getAjaxResult().add(GeneralConstant.LITTLE_MONEY, new Gson().toJsonTree(true));
                return;
            }

            int orderId = orderDAO.createAndGetOrderId(order);
            manager.commit();
            if (orderId != 0) {
                if (orderDataReceiver.createOrderData(content, orderId)) {
                    switch (order.getPaymentType()) {
                        case CASH:
                            break;
                        case MONEY:
                            user.setCash(user.getCash().subtract(order.getCash()));
                            user.setBonus(user.getBonus().add(order.getBonus()));
                            userDao.updateCash(user);
                            userDao.updateBonus(user);
                            break;
                        case BONUSES:
                            user.setBonus(user.getBonus().subtract(order.getCash()));
                            userDao.updateBonus(user);
                            break;
                        default:
                            enoughMoney = false;
                    }
                }
            } else {
                manager.rollback();
                manager.endTransaction();
            }
            manager.commit();
            manager.endTransaction();

        } catch (DAOException e) {
            try {
                manager.rollback();
                manager.endTransaction();
            } catch (DAOException e1) {
                throw new ReceiverException("Create order rollback error", e);
            }
            throw new ReceiverException(e);
        }

    }

    public void cancelTheOrder(RequestContent content) throws ReceiverException{

        OrderValidatorImpl validator = new OrderValidatorImpl();
        CommonValidatorImpl commonValidator = new CommonValidatorImpl();

        int userId = ((UserEntity) content.getSessionAttributes().get(GeneralConstant.USER)).getId();
        String[] paymentMethod = content.getRequestParameters().get(GeneralConstant.PAYMENT_METHOD);
        PaymentType paymentType = PaymentType.valueOf(paymentMethod[0]);
        String[] orderPrice = content.getRequestParameters().get(GeneralConstant.ORDER_PRICE);

        content.getRequestAttributes().put(RequestNameConstant.PAYMENT_METHOD, paymentMethod[0]);
        content.getRequestAttributes().put(RequestNameConstant.ORDER_PRICE, orderPrice[0]);

        OrderEntity order = new OrderEntity();
        order.setUserId(userId);
        order.setPaymentType(paymentType);
        BigDecimal cash = BigDecimal.valueOf(Double.parseDouble(orderPrice[0])).setScale(10, RoundingMode.HALF_DOWN);
        order.setCash(cash);

        if (!validator.isValidExpectedDate(order)) {
            content.getRequestAttributes().put(WRONG_DATA, true);
            return;
        }

        TransactionManager manager = new TransactionManager();
        try {
            UserDAOImpl userDao = new UserDAOImpl();
            OrderDAOImpl orderDAO = new OrderDAOImpl();
            OrderDataDAOImpl orderDataDAO = new OrderDataDAOImpl();
            manager.beginTransaction(userDao, orderDAO, orderDataDAO);

            UserEntity user = userDao.findEntityById(userId);
            switch (order.getPaymentType()) {
                case CASH:
                    break;
                case MONEY:
                    user.setCash(user.getCash().add(order.getCash()));
                    userDao.updateCash(user);
                    break;
                case BONUSES:
                    user.setBonus(user.getBonus().add(order.getCash()));
                    userDao.updateBonus(user);
                    break;
            }

            if (orderDataDAO.delete(order.getId())) {
                manager.commit();
            } else {
                manager.rollback();
                manager.endTransaction();
            }
            if (orderDAO.delete(order.getId())){
                manager.commit();
            } else {
                manager.rollback();
                manager.endTransaction();
            }
            manager.commit();
            manager.endTransaction();
        } catch (DAOException e) {
            try {
                manager.rollback();
                manager.endTransaction();
            } catch (DAOException e1) {
                throw new ReceiverException("Cancel the order rollback error", e);
            }
            throw new ReceiverException(e);
        }
    }
}
