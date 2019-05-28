package by.epam.cafe.receiver.impl;

import by.epam.cafe.content.RequestContent;
import by.epam.cafe.dao.TransactionManager;
import by.epam.cafe.dao.impl.ProductDAOImpl;
import by.epam.cafe.entity.OrderDataEntity;
import by.epam.cafe.entity.ProductEntity;
import by.epam.cafe.exception.DAOException;
import by.epam.cafe.exception.ReceiverException;
import by.epam.cafe.receiver.OrderDataReceiver;

import java.util.Optional;

import static by.epam.cafe.constant.GeneralConstant.ORDER_DATA;

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
}
