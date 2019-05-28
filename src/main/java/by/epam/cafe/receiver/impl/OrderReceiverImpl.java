package by.epam.cafe.receiver.impl;

import by.epam.cafe.constant.GeneralConstant;
import by.epam.cafe.content.RequestContent;
import by.epam.cafe.entity.OrderDataEntity;
import by.epam.cafe.entity.ProductEntity;
import by.epam.cafe.receiver.OrderReceiver;
import by.epam.cafe.type.UploadType;
import by.epam.cafe.util.Formatter;

import java.util.ArrayList;
import java.util.List;

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

        orderDataEntity = (OrderDataEntity) content.getSessionAttributes().get(GeneralConstant.ORDER_DATA);
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

}
