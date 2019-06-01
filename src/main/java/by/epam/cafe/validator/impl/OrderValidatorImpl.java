package by.epam.cafe.validator.impl;

import by.epam.cafe.entity.OrderEntity;

import java.util.Date;

public class OrderValidatorImpl {

    public boolean isValidExpectedDate(OrderEntity order) {
        boolean isValid = true;

        if (order.getExpectedTime().before(new Date())) {
            isValid = false;
        }

        return isValid;
    }

}
