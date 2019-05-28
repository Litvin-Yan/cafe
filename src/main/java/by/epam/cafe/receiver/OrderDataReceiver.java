package by.epam.cafe.receiver;

import by.epam.cafe.content.RequestContent;
import by.epam.cafe.exception.DAOException;
import by.epam.cafe.exception.ReceiverException;

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
}
