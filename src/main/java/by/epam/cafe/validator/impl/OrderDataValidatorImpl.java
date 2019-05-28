package by.epam.cafe.validator.impl;

public class OrderDataValidatorImpl {

    private static final int MAX_COUNT = 50;
    private static final int MIN_COUNT = 0;

    public boolean isPositiveInteger(int count){
        return (count > MIN_COUNT && count <= MAX_COUNT);
    }

}
