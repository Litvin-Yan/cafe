package by.epam.cafe.type;

import by.epam.cafe.constant.GeneralConstant;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public enum PageType {

    /* ------ General pages ------*/
    MAIN,
    SIGN_UP,
    SIGN_IN,
    MENU,
    BASKET,
    CONCRETE_PRODUCT,
    PROFILE,
    BLOCK,
    INDEX,

    /* ------- Admin pages --------*/
    ADMIN_MAIN,
    ADMIN_PRODUCTS,
    ADMIN_USER,

    /* ------- Error pages ------- */
    ERROR_404,
    ERROR_500,
    SERVER_ERROR;

    private static final Logger LOGGER = LogManager.getLogger();
    private static ResourceBundle jspBundle = ResourceBundle.getBundle(GeneralConstant.PAGE_PATH_PROPERTIES);

    public String getPage() {
        try {
            return jspBundle.getString(this.toString());

        } catch (MissingResourceException e) {
            LOGGER.log(Level.ERROR, "Path not found.", e);
            throw new RuntimeException("Path not found.", e);

        } catch (ClassCastException e) {
            LOGGER.log(Level.ERROR, "Path is not a string.", e);
            throw new RuntimeException("Path is not a string.", e);
        }
    }

}
