package by.epam.cafe.receiver.impl;

import by.epam.cafe.constant.GeneralConstant;
import by.epam.cafe.content.RequestContent;
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
import java.util.List;
import java.util.Map;


public class ProductReceiverImpl implements ProductReceiver {

    @Override
    public void openMenuPage(RequestContent content) throws ReceiverException {
        Formatter formatter = new Formatter();
        String[] stringPage = content.getRequestParameters().get(GeneralConstant.PAGE_NUMBER);
        int page = formatter.formatToPage(stringPage);

        if (page == -1) {
            content.getRequestAttributes().put(GeneralConstant.PAGE_NOT_FOUND, true);
            return;
        }

        int startIndex = formatter.formatToStartIndex(page, GeneralConstant.COUNT_PRODUCTS_ON_PAGE);
        TransactionManager handler = new TransactionManager();
        try {
            ProductDAOImpl productDAO = new ProductDAOImpl();
            handler.beginTransaction(productDAO);
            List<ProductEntity> productsList = productDAO.findWithLimit(startIndex, GeneralConstant.COUNT_PRODUCTS_ON_PAGE);
            List<String> productTypeList = productDAO.findProductType();
            int productTypeCount = productTypeList.size();
            int productCount = productDAO.findProductCount();
            handler.commit();
            handler.endTransaction();

            if (productsList.isEmpty() && page != 1) {
                content.getRequestAttributes().put(GeneralConstant.PAGE_NOT_FOUND, true);
                return;
            }

            content.getRequestAttributes().put(GeneralConstant.PRODUCT_LIST, productsList);
            content.getRequestAttributes().put(GeneralConstant.PRODUCT_TYPE_LIST, productTypeList);
            content.getRequestAttributes().put(GeneralConstant.LIMIT, GeneralConstant.COUNT_PRODUCTS_ON_PAGE);
            content.getRequestAttributes().put(GeneralConstant.PRODUCT_COUNT, productCount);
            content.getRequestAttributes().put(GeneralConstant.PRODUCTS_IMAGE_PATH, UploadType.PRODUCTS.getUploadFolder());
            content.getRequestAttributes().put(GeneralConstant.PRODUCT_TYPE_COUNT, productTypeCount);

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
        String[] textArray = content.getRequestParameters().get(GeneralConstant.TEXT);
        String[] titleArray = content.getRequestParameters().get(GeneralConstant.TITLE);
        Part imagePart = content.getRequestParts().get(GeneralConstant.IMAGE);

        if (imagePart == null || !validator.checkParamsForInteger(stringPointX1, stringPointX2,
                stringPointY1, stringPointY2, stringHeight, stringWidth) ||
                !validator.isVarExist(textArray) || !validator.isVarExist(titleArray)) {
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

        if (!productValidator.isTitleValid(titleArray[0]) ||
                !productValidator.isTextValid(textArray[0]) ||
                !validator.isImageExtensionValid(imageExtension) ||
                pointX1 == pointX2 || pointY1 == pointY2) {
            content.setAjaxSuccess(false);
            content.getAjaxResult().add(GeneralConstant.WRONG_DATA, new Gson().toJsonTree(true));
            return;
        }

        ProductEntity product = new ProductEntity();

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
        int newsId = Integer.valueOf(content.getRequestParameters().get(GeneralConstant.PRODUCT_ID)[0]);
        String newsImageUrl = content.getRequestParameters().get(GeneralConstant.PRODUCT_IMAGE_URL)[0];

        File directoryPath = new File(content.getRealPath(), UploadType.PRODUCTS.getUploadFolder());
        File file = new File(directoryPath, newsImageUrl);

        TransactionManager manager = new TransactionManager();
        try {
            ProductDAOImpl newsDAO = new ProductDAOImpl();
            CommentDAOImpl commentDAO = new CommentDAOImpl();
            manager.beginTransaction(newsDAO, commentDAO);
            boolean isTransactionSuccess = commentDAO.deleteByNewsId(newsId);

            if (isTransactionSuccess) {
                isTransactionSuccess = newsDAO.delete(newsId);
            }

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
                throw new ReceiverException("Delete news rollback error", e);
            }
            throw new ReceiverException(e);
        }

    }

    @Override
    public void openConcreteProductPage(RequestContent content) throws ReceiverException {
        CommonValidatorImpl commonValidator = new CommonValidatorImpl();
        String[] newsIdString = content.getRequestParameters().get(GeneralConstant.PRODUCT_ID);

        if (!commonValidator.checkParamsForInteger(newsIdString)) {
            content.getRequestAttributes().put(GeneralConstant.PAGE_NOT_FOUND, true);
            return;
        }

        int newsId = Integer.valueOf(newsIdString[0]);


        TransactionManager handler = new TransactionManager();
        try {
            ProductDAOImpl productDAO = new ProductDAOImpl();
            CommentDAOImpl commentDAO = new CommentDAOImpl();
            handler.beginTransaction(productDAO, commentDAO);
            ProductEntity product = productDAO.findEntityById(newsId);

            if (product.equals(new ProductEntity())) {
                content.getRequestAttributes().put(GeneralConstant.PAGE_NOT_FOUND, true);
                handler.rollback();
                handler.endTransaction();
                return;
            }

            List<Map<String, Object>> newsCommentList = commentDAO.findCommentsByNewsId(newsId);
            handler.commit();
            handler.endTransaction();

            content.getRequestAttributes().put(GeneralConstant.ORDER_COMMENT, newsCommentList);
            content.getRequestAttributes().put(GeneralConstant.ORDER_ATTR, product);

        } catch (DAOException e) {
            try {
                handler.rollback();
                handler.endTransaction();
            } catch (DAOException e1) {
                throw new ReceiverException("Open concrete news rollback error", e);
            }
            throw new ReceiverException(e);
        }
    }
}
