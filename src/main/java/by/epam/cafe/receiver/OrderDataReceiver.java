package by.epam.cafe.receiver;

import by.epam.cafe.content.RequestContent;
import by.epam.cafe.exception.DAOException;
import by.epam.cafe.exception.ReceiverException;

import java.math.BigDecimal;

public interface OrderDataReceiver extends Receiver{

    /**
     * Remove product from orderData.
     *
     * @param requestContent request content
     * @throws ReceiverException when throw DAOException
     *
     * @see DAOException
     * @see RequestContent
     */
    void removeProduct(RequestContent requestContent) throws ReceiverException, DAOException;

    /**
     * Add product to orderData.
     *
     * @param requestContent request content
     * @throws ReceiverException when throw DAOException
     *
     * @see DAOException
     * @see RequestContent
     */
    void addProduct(RequestContent requestContent) throws ReceiverException, DAOException;

    /**
     * create order data
     *
     * @param content
     * @param orderId
     * @return true if created
     * @throws ReceiverException
     */
    boolean createOrderData(RequestContent content, int orderId) throws ReceiverException;
}
