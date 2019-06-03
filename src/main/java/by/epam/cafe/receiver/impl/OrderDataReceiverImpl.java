package by.epam.cafe.receiver.impl;

import by.epam.cafe.content.RequestContent;
import by.epam.cafe.dao.TransactionManager;
import by.epam.cafe.dao.impl.OrderDataDAOImpl;
import by.epam.cafe.dao.impl.ProductDAOImpl;
import by.epam.cafe.entity.OrderDataEntity;
import by.epam.cafe.entity.ProductEntity;
import by.epam.cafe.exception.DAOException;
import by.epam.cafe.exception.ReceiverException;
import by.epam.cafe.receiver.OrderDataReceiver;
import by.epam.cafe.validator.impl.OrderDataValidatorImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static by.epam.cafe.constant.GeneralConstant.ORDER_DATA;
import static by.epam.cafe.constant.RequestNameConstant.WRONG_COUNTER;

public class OrderDataReceiverImpl implements OrderDataReceiver {

    @Override
    public void removeProduct(RequestContent requestContent) throws ReceiverException, DAOException {
        OrderDataEntity orderData = (OrderDataEntity) requestContent.getSessionAttributes().get(ORDER_DATA);
        int productId = Integer.valueOf(requestContent.getRequestParameters().get("productId")[0]);
        Optional<ProductEntity> productEntity = orderData.getProducts()
                .keySet()
                .stream()
                .filter(p -> p.getId() == productId)
                .findFirst();
        if (orderData.getProducts().get(productEntity.get()) == 1) {
            orderData.getProducts().remove(productEntity.get());
        } else {
            Integer counter = orderData.getProducts().get(productEntity.get());
            orderData.getProducts().put(productEntity.get(), --counter);
        }
        requestContent.setAjaxSuccess(true);
    }

    @Override
    public void addProduct(RequestContent requestContent) throws ReceiverException, DAOException {
        OrderDataEntity orderData = (OrderDataEntity) requestContent.getSessionAttributes().get(ORDER_DATA);
        int productId = Integer.valueOf(requestContent.getRequestParameters().get("productId")[0]);
        Optional<ProductEntity> productEntity = orderData.getProducts()
                .keySet()
                .stream()
                .filter(p -> p.getId() == productId)
                .findFirst();
        TransactionManager manager = new TransactionManager();
        if (productEntity.isPresent()) {
            Integer counter = orderData.getProducts().get(productEntity.get());
            orderData.getProducts().put(productEntity.get(), ++counter);
        } else {
            ProductDAOImpl productDAO = new ProductDAOImpl();
            manager.beginTransaction(productDAO);
            ProductEntity product = productDAO.findEntityById(productId);
            orderData.getProducts().put(product, 1);
        }
        requestContent.setAjaxSuccess(true);
    }

    public boolean createOrderData(RequestContent content, int orderId) throws ReceiverException {
        boolean allCreated = true;

        OrderDataValidatorImpl validator = new OrderDataValidatorImpl();

        OrderDataEntity orderData = (OrderDataEntity) content.getSessionAttributes().get(ORDER_DATA);
        List<ProductEntity> productList = new ArrayList<>(orderData.getProducts().keySet());

        boolean isValidData = true;

        for (ProductEntity product : productList) {
            if (!validator.isValidCount(orderData.getProducts().get(product))) {
                content.getRequestAttributes().put(WRONG_COUNTER, true);
                isValidData = false;
            }
        }
        if (!isValidData) {
            return false;
        }

        TransactionManager manager = new TransactionManager();
        OrderDataDAOImpl orderDataDAO = new OrderDataDAOImpl();

        try {
            manager.beginTransaction(orderDataDAO);
            for (ProductEntity product : productList) {
                try {
                    if (orderDataDAO.create(product.getId(), orderData.getProducts().get(product), product.getPrice(), orderId)) {
                        manager.commit();
                    }
                } catch (DAOException e) {
                    try {
                        manager.rollback();
                        manager.endTransaction();
                        allCreated = false;
                    } catch (DAOException e1) {
                        throw new ReceiverException("Create orderData rollback error", e);
                    }
                }
            }
            manager.endTransaction();
            content.getSessionAttributes().remove(ORDER_DATA);
            content.getSessionAttributes().put(ORDER_DATA, new OrderDataEntity());
        } catch (DAOException e) {
            throw new ReceiverException(e);
        }
        return allCreated;
    }

    public BigDecimal findOrderPriceById(int orderId, RequestContent content) throws ReceiverException {
        BigDecimal orderPrice = new BigDecimal(0);

        OrderDataEntity orderData = (OrderDataEntity) content.getSessionAttributes().get(ORDER_DATA);
        List<ProductEntity> orderList = new ArrayList<>(orderData.getProducts().keySet());

        for (ProductEntity productEntity : orderList) {
            BigDecimal price = productEntity.getPrice();
            BigDecimal count = BigDecimal.valueOf(orderData.getProducts().get(productEntity));
            orderPrice = orderPrice.add(price.multiply(count));
        }
        return orderPrice;
    }
}
