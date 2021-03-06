package by.epam.cafe.receiver.impl;

import by.epam.cafe.constant.GeneralConstant;
import by.epam.cafe.content.RequestContent;
import by.epam.cafe.dao.ProductDAO;
import by.epam.cafe.dao.TransactionManager;
import by.epam.cafe.dao.impl.CommentDAOImpl;
import by.epam.cafe.dao.impl.ProductDAOImpl;
import by.epam.cafe.entity.ProductEntity;
import by.epam.cafe.exception.DAOException;
import by.epam.cafe.exception.ReceiverException;
import by.epam.cafe.receiver.ProductReceiver;
import by.epam.cafe.type.UploadType;
import by.epam.cafe.util.Formatter;
import by.epam.cafe.util.ImageUploader;
import by.epam.cafe.validator.impl.CommonValidatorImpl;
import by.epam.cafe.validator.impl.ProductValidatorImpl;
import com.google.gson.Gson;
import org.apache.commons.io.FilenameUtils;

import javax.servlet.http.Part;
import java.io.File;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static by.epam.cafe.constant.GeneralConstant.*;


public class ProductReceiverImpl implements ProductReceiver {

    @Override
    public void openMenuPage(RequestContent content) throws ReceiverException {
        Formatter formatter = new Formatter();
        String[] stringPage = content.getRequestParameters().get(GeneralConstant.PAGE_NUMBER);
        int page = formatter.formatToPage(stringPage);
        Optional<String[]> newProductType = Optional.ofNullable(content.getRequestParameters().get(GeneralConstant.PRODUCT_TYPE));
        if (newProductType.isPresent()){
            if (newProductType.get()[0].equals(GeneralConstant.ALL_EN)){
                newProductType.get()[0] = GeneralConstant.ALL_RU;
            }
        }
        String currentProductType = (String) content.getSessionAttributes().get(GeneralConstant.PRODUCT_TYPE);
        if (page == -1) {
            content.getRequestAttributes().put(GeneralConstant.PAGE_NOT_FOUND, true);
            return;
        }

        int startIndex = formatter.formatToStartIndex(page, GeneralConstant.COUNT_PRODUCTS_ON_PAGE);
        TransactionManager handler = new TransactionManager();
        List<ProductEntity> productList;
        List<String> productTypeList;
        int productCount;
        try {
            ProductDAOImpl productDAO = new ProductDAOImpl();
            handler.beginTransaction(productDAO);

            if  ((newProductType.isPresent() && newProductType.get()[0].equals(GeneralConstant.ALL_RU)) ||
                    (currentProductType.equals(GeneralConstant.ALL_RU) && !newProductType.isPresent())) {
                productList = productDAO.findWithLimit(startIndex, GeneralConstant.COUNT_PRODUCTS_ON_PAGE);
                productTypeList = productDAO.findProductType();
                productCount = productDAO.findProductCount();
                content.getSessionAttributes().remove(GeneralConstant.PRODUCT_TYPE);
                content.getSessionAttributes().put(GeneralConstant.PRODUCT_TYPE, GeneralConstant.ALL_RU);

            } else if (newProductType.isPresent()){
                productList = productDAO.findProductByType(newProductType.get()[0], startIndex, GeneralConstant.COUNT_PRODUCTS_ON_PAGE);
                productTypeList = productDAO.findProductType();
                productCount = productDAO.findProductCountByType(newProductType.get()[0]);
                content.getSessionAttributes().remove(GeneralConstant.PRODUCT_TYPE);
                content.getSessionAttributes().put(GeneralConstant.PRODUCT_TYPE, newProductType.get()[0]);

            } else {
                productList = productDAO.findProductByType(currentProductType, startIndex, GeneralConstant.COUNT_PRODUCTS_ON_PAGE);
                productTypeList = productDAO.findProductType();
                productCount = productDAO.findProductCountByType(currentProductType);
            }
            handler.commit();
            handler.endTransaction();

            if (productList.isEmpty() && page != 1) {
                content.getRequestAttributes().put(GeneralConstant.PAGE_NOT_FOUND, true);
                return;
            }

            content.getRequestAttributes().put(GeneralConstant.PRODUCT_LIST, productList);
            content.getRequestAttributes().put(GeneralConstant.PRODUCT_TYPE_LIST, productTypeList);
            content.getRequestAttributes().put(GeneralConstant.LIMIT, GeneralConstant.COUNT_PRODUCTS_ON_PAGE);
            content.getRequestAttributes().put(GeneralConstant.PRODUCTS_COUNT, productCount);
            content.getRequestAttributes().put(GeneralConstant.PRODUCT_IMAGE_PATH, UploadType.PRODUCTS.getUploadFolder());

        } catch (DAOException e) {
            try {
                handler.rollback();
                handler.endTransaction();

            } catch (DAOException e1) {
                throw new ReceiverException("Open menu rollback error", e);
            }
            throw new ReceiverException(e);
        }
    }

    @Override
    public void createProduct(RequestContent content) throws ReceiverException {
        ProductValidatorImpl productValidator = new ProductValidatorImpl();
        CommonValidatorImpl validator = new CommonValidatorImpl();
        File uploadPath = new File(content.getRealPath(), UploadType.PRODUCTS.getUploadFolder());
        ImageUploader uploader = new ImageUploader();

        String[] stringPointX1 = content.getRequestParameters().get(GeneralConstant.POINT_X1);
        String[] stringPointX2 = content.getRequestParameters().get(GeneralConstant.POINT_X2);
        String[] stringPointY1 = content.getRequestParameters().get(GeneralConstant.POINT_Y1);
        String[] stringPointY2 = content.getRequestParameters().get(GeneralConstant.POINT_Y2);
        String[] stringHeight = content.getRequestParameters().get(GeneralConstant.HEIGHT);
        String[] stringWidth = content.getRequestParameters().get(GeneralConstant.WIDTH);
        String[] typeArray = content.getRequestParameters().get(GeneralConstant.PRODUCT_TYPE);
        String[] nameArray = content.getRequestParameters().get(GeneralConstant.NAME);
        String[] priceArray = content.getRequestParameters().get(GeneralConstant.PRICE);
        String[] consistArray = content.getRequestParameters().get(GeneralConstant.CONSIST);
        String[] weightArray = content.getRequestParameters().get(GeneralConstant.WEIGHT);
        Part imagePart = content.getRequestParts().get(GeneralConstant.IMAGE);

        if (imagePart == null || !validator.checkParamsForInteger(stringPointX1, stringPointX2,
                stringPointY1, stringPointY2, stringHeight, stringWidth)) {
            content.setAjaxSuccess(false);
            content.getAjaxResult().add(GeneralConstant.WRONG_DATA, new Gson().toJsonTree(true));
            return;
        }

        String imageExtension = FilenameUtils.getExtension(imagePart.getSubmittedFileName());
        int pointX1 = Integer.valueOf(stringPointX1[0]);
        int pointX2 = Integer.valueOf(stringPointX2[0]);
        int pointY1 = Integer.valueOf(stringPointY1[0]);
        int pointY2 = Integer.valueOf(stringPointY2[0]);
        int height = Integer.valueOf(stringHeight[0]);
        int width = Integer.valueOf(stringWidth[0]);

        if (pointX1 == pointX2 || pointY1 == pointY2) {
            content.setAjaxSuccess(false);
            content.getAjaxResult().add(GeneralConstant.WRONG_DATA, new Gson().toJsonTree(true));
            return;
        }

        ProductEntity product = new ProductEntity();
        product.setName(nameArray[0]);
        product.setPrice(new BigDecimal(priceArray[0]));
        product.setWeight(Integer.valueOf(weightArray[0]));
        product.setProductType(typeArray[0]);
        product.setIngredients(consistArray[0]);

        TransactionManager manager = new TransactionManager();
        try {
            ProductDAOImpl productDAO = new ProductDAOImpl();
            manager.beginTransaction(productDAO);
            product.setId(productDAO.createAndGetId(product));
            product.setImageURL(product.getId() + GeneralConstant._PRODUCT_DOT + imageExtension);
            File path = new File(uploadPath, product.getImageURL());
            boolean isUploaded = uploader.uploadImage(imagePart, path, imageExtension,
                    pointX1, pointX2, pointY1, pointY2, height, width);

            if (!isUploaded) {
                content.getAjaxResult().add(GeneralConstant.WRONG_UPLOAD, new Gson().toJsonTree(true));
            }

            boolean isUpdated = isUploaded && productDAO.updateImagePath(product);

            if (isUploaded && isUpdated) {
                manager.commit();

            } else {
                manager.rollback();
                if (path.exists()) {
                    isUploaded = !path.delete();
                }
            }

            content.setAjaxSuccess(isUploaded && isUpdated);
            manager.commit();
            manager.endTransaction();

        } catch (DAOException e) {
            try {
                manager.rollback();
                manager.endTransaction();

            } catch (DAOException e1) {
                throw new ReceiverException("Create news rollback error", e1);
            }
            throw new ReceiverException(e);
        }
    }

    @Override
    public void deleteProduct(RequestContent content) throws ReceiverException {
        int productId = Integer.valueOf(content.getRequestParameters().get(GeneralConstant.PRODUCT_ID)[0]);
        String newsImageUrl = content.getRequestParameters().get(GeneralConstant.PRODUCT_IMAGE_URL)[0];

        File directoryPath = new File(content.getRealPath(), UploadType.PRODUCTS.getUploadFolder());
        File file = new File(directoryPath, newsImageUrl);

        TransactionManager manager = new TransactionManager();
        try {
            ProductDAOImpl productDAO = new ProductDAOImpl();
            manager.beginTransaction(productDAO);
            boolean isTransactionSuccess = productDAO.delete(productId);

            if (isTransactionSuccess && file.exists()) {
                isTransactionSuccess = file.delete();
            }

            if (isTransactionSuccess) {
                manager.commit();

            } else {
                manager.rollback();
            }

            manager.endTransaction();
            content.setAjaxSuccess(isTransactionSuccess);

        } catch (DAOException e) {
            try {
                manager.rollback();
                manager.endTransaction();
            } catch (DAOException e1) {
                throw new ReceiverException("Delete product rollback error", e);
            }
            throw new ReceiverException(e);
        }
    }

    @Override
    public void openProductSettings(RequestContent content) throws ReceiverException {
        Formatter formatter = new Formatter();
        String[] stringPage = content.getRequestParameters().get(PAGE_NUMBER);
        int page = formatter.formatToPage(stringPage);

        if (page == -1) {
            content.getRequestAttributes().put(PAGE_NOT_FOUND, true);
            return;
        }

        int startIndex = formatter.formatToStartIndex(page, COUNT_PRODUCTS_ON_ADMIN_PAGE);
        TransactionManager manager = new TransactionManager();
        try {
            ProductDAO productDAO = new ProductDAOImpl();
            manager.beginTransaction(productDAO);
            List<ProductEntity> productList = productDAO.findWithLimit(startIndex, COUNT_PRODUCTS_ON_ADMIN_PAGE);
            int productsCount = productDAO.findProductCount();
            manager.commit();
            manager.endTransaction();

            if (productList.isEmpty() && page != 1) {
                content.getRequestAttributes().put(PAGE_NOT_FOUND, true);
                return;
            }

            content.getRequestAttributes().put(PRODUCT_LIST, productList);
            content.getRequestAttributes().put(LIMIT, COUNT_PRODUCTS_ON_ADMIN_PAGE);
            content.getRequestAttributes().put(PRODUCTS_COUNT_ON_ADMIN, productsCount);

        } catch (DAOException e) {
            try {
                manager.rollback();
                manager.endTransaction();

            } catch (DAOException e1) {
                throw new ReceiverException("Open product setting rollback error", e);
            }

            throw new ReceiverException(e);
        }
    }
}
