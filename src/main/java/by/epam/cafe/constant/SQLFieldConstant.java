package by.epam.cafe.constant;


final public class SQLFieldConstant {

    private SQLFieldConstant() {
    }

    public final class User {
        private static final String USER = "user_";
        public static final String ID =  USER + "id";
        public static final String NAME = USER + "name";
        public static final String TYPE = USER + "type";
        public static final String EMAIL = USER + "email";
        public static final String PASSWORD = USER + "password";
        public static final String MONEY = USER + "money";
        public static final String IS_BLOCKED = USER + "is_blocked";
        public static final String BONUS = USER + "bonus";
        public static final String AVATAR_URL = USER + "avatar_url";
        public static final String BLOCKED_TEXT = USER + "blocked_text";
    }

    public final class Order {
        private static final String ORDER = "order_";
        public static final String ID = ORDER + "id";
        public static final String PAID = ORDER + "paid";
        public static final String PRICE = ORDER + "price";
        public static final String PAYMENT_METHOD = ORDER + "payment_method";
        public static final String BONUS = ORDER + "bonus";
        public static final String USER_ID = "user_id";
        public static final String EXPECTED_TIME = ORDER + "expected_time";
        public static final String EXPECTED_DATE = ORDER + "expected_date";
        public static final String TIME = ORDER + "time";
    }

    public final class Comment {
        private static final String COMMENT = "comment_";
        public static final String CONTENT = COMMENT + "content";
        public static final String RATE = COMMENT + "rate";
        public static final String POST_DATE = COMMENT + "date";
        public static final String IS_BLOCKED = COMMENT + "is_blocked";
        public static final String ORDER_ID = "order_id";
    }

    public final class Product {
        private static final String PRODUCT = "product_";
        public static final String ID = PRODUCT + "id";
        public static final String NAME = PRODUCT + "name";
        public static final String TYPE = PRODUCT + "type";
        public static final String PRICE = PRODUCT + "price";
        public static final String IMAGE_URL = PRODUCT + "image_url";
        public static final String INGREDIENTS = PRODUCT + "ingredients";
        public static final String WEIGHT = PRODUCT + "weight";
    }
    public final class OrderData {
        public static final String ORDER_ID = "order_data_";
        public static final String PRODUCT_ID = "product_id";
        public static final String NUMBER = "number";
        public static final String PRODUCT_PRICE = "product_price";
    }
}

