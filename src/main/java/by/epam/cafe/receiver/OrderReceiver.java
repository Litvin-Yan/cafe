package by.epam.cafe.receiver;

import by.epam.cafe.content.RequestContent;
import by.epam.cafe.exception.ReceiverException;

public interface OrderReceiver extends Receiver {
     /**
      * create order
      *
      * @param content
      * @throws ReceiverException
      */
     void createOrder(RequestContent content) throws ReceiverException;

     /**
      * open basket page
      *
      * @param content
      */
     void openBasketPage(RequestContent content);

     /**
      * cancel the order
      *
      * @param content
      * @throws ReceiverException
      */
     void cancelTheOrder(RequestContent content) throws ReceiverException;
}
