package by.epam.cafe.receiver;

import by.epam.cafe.content.RequestContent;
import by.epam.cafe.exception.ReceiverException;

public interface OrderReceiver extends Receiver {
     void createOrder(RequestContent content) throws ReceiverException;
}
