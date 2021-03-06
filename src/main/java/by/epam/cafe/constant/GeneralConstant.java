package by.epam.cafe.constant;

import java.math.BigDecimal;

public class GeneralConstant {

    public static final String ORDER_ID = "orderId";
    public static final String _PRODUCT_DOT = "_product.";
    public static final String _AVATAR_DOT = "_avatar.";
    public static final String DB_PROPERTIES = "config/database";
    public static final String PAGE_PATH_PROPERTIES = "config/pages";
    public static final String UPLOAD_PROPERTIES = "config/upload";
    public static final String ALL_RU = "Всё";
    public static final String ALL_EN = "All";


    public static final String EXPECTED_TIME = "timeOfReceiving";
    public static final String EXPECTED_DATE = "dateOfReceiving";
    public static final String PAYMENT_METHOD = "paymentMethod";
    public static final String ORDER_PRICE = "orderPrice";
    public static final String PRODUCT_ID = "productId";
    public static final String DUPLICATE_UNIQUE_INDEX = "23000";
    public static final String CAN_NOT_DELETE_OR_UPDATE = "23000";
    public static final String TEMPORARY = "temporary";
    public static final String ACCESS_DENIED = "accessDenied";
    public static final String DATA_EMPTY = "dataEmpty";
    public static final String WRONG_CASH = "wrongCash";
    public static final String WRONG_DATA = "wrongData";
    public static final String WRONG_DATE = "wrongDate";
    public static final String WRONG_TYPE = "wrongType";
    public static final String WRONG_ACTIVE = "wrongActive";
    public static final String WRONG_CREATION = "wrongCreation";
    public static final String WRONG_NUMBER_FORMAT = "wrongNumberFormat";
    public static final String WRONG_COUNT = "wrongCount";
    public static final String CREATE_ERROR = "createError";
    public static final String FILL_ERROR = "fillError";
    public static final String DUPLICATE_NAME = "duplicateName";
    public static final String WRONG_UPLOAD = "wrongUpload";
    public static final String POINT_X1 = "x1";
    public static final String POINT_X2 = "x2";
    public static final String POINT_Y1 = "y1";
    public static final String POINT_Y2 = "y2";
    public static final String HEIGHT = "height";
    public static final String WIDTH = "width";
    public static final String USER = "user";
    public static final String ACTIVE_ORDER = "activeOrders";
    public static final String ORDER_WITHOUT_COMMENT = "ordersWithoutComment";
    public static final String ORDER_DATA = "orderData";
    public static final String ORDER = "order";
    public static final String PRODUCT_LIST = "productList";
    public static final String PRODUCT_TYPE_LIST = "productTypeList";
    public static final String PRODUCT_TYPE = "productType";
    public static final String STATISTIC_MAP = "statisticMap";
    public static final String NAME = "name";
    public static final String USER_LIST = "userList";
    public static final String COMMENT_DATA_LIST = "commentDataList";
    public static final String USERS_COUNT = "usersCount";
    public static final String USER_ID = "userId";
    public static final String USER_TYPE = "userType";
    public static final String BLOCK_STATE = "blockState";
    public static final String TEXT_BLOCK = "textBlock";
    public static final String UPDATE_ERROR = "updateError";
    public static final String PRODUCT_IMAGE_PATH = "newsImagePath";
    public static final String USER_IMAGE_PATH = "userImagePath";
    public static final String LOCALE = "locale";
    public static final String COMMENT_ID = "commentId";
    public static final String IS_BLOCKED = "isBlocked";
    public static final String TEXT = "text";
    public static final String COMMENT_RATE = "commentRate";
    public static final String STATE = "state";
    public static final String ON = "on";
    public static final String COMMAND = "command";
    public static final String REGISTERED_COUNT = "registeredCount";
    public static final String LOCKED_COUNT = "lockedCount";
    public static final String PRODUCTS_COUNT = "productCount";
    public static final String PRODUCTS_COUNT_ON_ADMIN = "productCountOnAdmin";
    public static final String COMMENT_COUNT = "commentCount";
    public static final String PRODUCT_TYPE_COUNT = "productTypeCount";
    public static final String TYPE_ID = "typeId";
    public static final String TITLE = "title";
    public static final String PRODUCT_IMAGE_URL = "productImageUrl";
    public static final String OLD_PASSWORD = "oldPassword";
    public static final String NEW_NAME = "newName";
    public static final String ORDER_COMMENT = "orderComment";
    public static final String ORDER_ATTR = "orderAttr";
    public static final String SUCCESS = "success";
    public static final String COUNT = "count";
    public static final String CASH = "cash";
    public static final String BONUS = "bonus";
    public static final String VALUE = "value";
    public static final String PAGE_NOT_FOUND = "pageNotFound";
    public static final String PAGE_NUMBER = "pageNumber";
    public static final String LITTLE_MONEY = "littleMoney";
    public static final String IMAGE = "image";
    public static final String LIMIT = "limit";
    public static final String ORDER_DATA_ORDER_ID = "orderDataOrderId";
    public static final String ORDER_DATA_PRODUCT_ID = "orderDataProductId";
    public static final String ORDER_DATA_NUMBER = "orderDataNumber";
    public static final String ORDER_DATA_PRODUCT_PRICE = "orderDataProductPrice";

    public static final int COUNT_PRODUCTS_ON_PAGE = 5;
    public static final int COUNT_COMMENT_ON_PAGE = 5;
    public static final int COUNT_PRODUCTS_ON_ADMIN_PAGE = 4;
    public static final int COUNT_USERS_ON_PAGE = 4;
    public static final BigDecimal PERCENTAGE_OF_BONUSES_OF_THE_ORDER_AMOUNT = new BigDecimal(0.05);
    public static final String PRICE = "price";
    public static final String CONSIST = "consist";
    public static final String WEIGHT = "weight";

    private GeneralConstant() {
    }

    public static final class DataBase {
        public static final String DRIVER = "driver";
        public static final String URL = "url";
        public static final String USER = "user";
        public static final String PASSWORD = "password";
        public static final String USE_UNICODE = "useUnicode";
        public static final String CHARACTER_ENCODING = "characterEncoding";
        public static final String AUTO_RECONNECT = "autoReconnect";
    }

    public static class ConnectionPool {
        public static final String CAPACITY = "poolCapacity";
    }
}
