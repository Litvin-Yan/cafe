package by.epam.cafe.dao.impl;

import by.epam.cafe.constant.GeneralConstant;
import by.epam.cafe.constant.SQLFieldConstant;
import by.epam.cafe.dao.ProductDAO;
import by.epam.cafe.entity.ProductEntity;
import by.epam.cafe.exception.DAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import static by.epam.cafe.constant.GeneralConstant.COUNT;

public class ProductDAOImpl extends ProductDAO {

    private static final String CREATE_PRODUCT =
            "INSERT INTO product (product_name, product_type, product_price, product_ingredients, product_weight) " +
                    "VALUES (?, ?, ?, ?, ?);";

    private static final String FIND_PRODUCT_BY_ID =
            "SELECT product_id, product_name , product_type, product_price, product_image_url, product_ingredients, product_weight " +
                    "FROM product " +
                    "WHERE product_id = ?;";

    private static final String FIND_ALL_PRODUCT =
            "SELECT product_id, product_name , product_type, product_price, product_avatar_url, product_ingredients " +
                    " FROM product " +
                    " WHERE product_is_blocked = false; ";

    private static final String FIND_PRODUCT_BY_TYPE =
            "SELECT product_id," +
                    " product_name," +
                    " product_image_url," +
                    " product_price," +
                    " product_type, " +
                    " product_ingredients, " +
                    " product_weight" +
                    " FROM product" +
                    " WHERE product_type = ? AND " +
                    " product_is_blocked = false " +
                    " ORDER BY product_price DESC , product_id " +
                    " LIMIT ?, ?;";

    private static final String FIND_LIMIT_PRODUCTS =
            "SELECT product_id," +
                    " product_name," +
                    " product_image_url," +
                    " product_price," +
                    " product_type, " +
                    " product_ingredients, " +
                    " product_weight " +
                    "FROM product " +
                    "WHERE product_is_blocked = false " +
                    "ORDER BY product_price DESC , product_id " +
                    "LIMIT ?, ?;";

    private static final String FIND_PRODUCT_COUNT =
            "SELECT  " +
                    "    COUNT(product_id) AS count " +
                    "FROM product " +
                    "WHERE product_is_blocked = false;";

    private static final String FIND_PRODUCT_COUNT_BY_TYPE =
            "SELECT  " +
                    " COUNT(product_id) AS count " +
                    " FROM product " +
                    " WHERE product_type = ? AND " +
                    " product_is_blocked = false;";

    private static final String MARK_AS_DELETED_PRODUCT_BY_ID =
            "UPDATE  product " +
                    " SET product_is_blocked = true" +
                    " WHERE product_id = ?";

    private static final String UPDATE_PRODUCT_TYPE =
            "UPDATE product SET product_type = ? WHERE product_id = ?;";

    private static final String UPDATE_AVATAR_PATH =
            "UPDATE product " +
                    "SET product_avatar_url = ? " +
                    "WHERE product_id = ?;";

    private static final String UPDATE_PRODUCT_PRICE =
            "UPDATE product " +
                    "SET product_price = ? " +
                    "WHERE product_id = ?;";

    public static final String UPDATE_IMAGE_PATH_PRODUCT =
            "UPDATE product  " +
                    "SET product_image_url = ? " +
                    "WHERE product_id = ? ;";

    public static final String FIND_PRODUCT_TYPE =
            "SELECT DISTINCT product.product_type  " +
                    "FROM product " +
                    "WHERE product_is_blocked = false;";

    @Override
    public List<ProductEntity> findAll() throws DAOException {
        List<ProductEntity> productList;

        try (PreparedStatement statement = connection.prepareStatement(FIND_ALL_PRODUCT)) {
            ResultSet resultSet = statement.executeQuery();
            productList = new ArrayList<>();

            while (resultSet.next()) {
                ProductEntity product = new ProductEntity();
                product.setId(resultSet.getInt(SQLFieldConstant.Product.ID));
                product.setName(resultSet.getString(SQLFieldConstant.Product.NAME));
                product.setPrice(resultSet.getBigDecimal(SQLFieldConstant.Product.PRICE));
                productList.add(product);
            }
        } catch (SQLException e) {
            throw new DAOException("find all products error", e);
        }

        return productList;
    }

    @Override
    public ProductEntity findEntityById(int id) throws DAOException {
        ProductEntity foundProduct;
        try (PreparedStatement statement = connection.prepareStatement(FIND_PRODUCT_BY_ID)) {
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            foundProduct = extractProducts(result).get(0);
        } catch (SQLException e) {
            throw new DAOException("Find all users error ", e);
        }
        return foundProduct;
    }

    @Override
    public boolean delete(int productId) throws DAOException {
        boolean isDeleted = false;
        try (PreparedStatement statement = connection.prepareStatement(MARK_AS_DELETED_PRODUCT_BY_ID)) {

            statement.setInt(1, productId);
            isDeleted = statement.executeUpdate() == 1;

        } catch (SQLException e) {
            if (!GeneralConstant.DUPLICATE_UNIQUE_INDEX.equals(e.getSQLState())) {
                throw new DAOException("Delete product error ", e);
            }
        }
        return isDeleted;
    }

    @Override
    public boolean delete(ProductEntity entity) throws DAOException {
        return false;
    }

    @Override
    public boolean create(ProductEntity entity) throws DAOException {
        boolean isCreated = false;
        try (PreparedStatement statement = connection.prepareStatement(CREATE_PRODUCT)) {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getProductType().toString());
            statement.setBigDecimal(3, entity.getPrice());
            statement.setString(4, entity.getIngredients());
            statement.setInt(5, entity.getWeight());
            isCreated = statement.executeUpdate() == 1;
        } catch (SQLException e) {
            if (!GeneralConstant.DUPLICATE_UNIQUE_INDEX.equals(e.getSQLState())) {
                throw new DAOException("Create product error ", e);
            }
        }
        return isCreated;
    }

    @Override
    public boolean update(ProductEntity entity) throws DAOException {
        return false;
    }

    @Override
    public List<ProductEntity> findWithLimit(int startIndex, int limit) throws DAOException {
        List<ProductEntity> productList;

        try (PreparedStatement statement = connection.prepareStatement(FIND_LIMIT_PRODUCTS)) {
            statement.setInt(1, startIndex);
            statement.setInt(2, limit);

            ResultSet resultSet = statement.executeQuery();
            productList = extractProducts(resultSet);

        } catch (SQLException e) {
            throw new DAOException("Find limit news error ", e);
        }

        return productList;
    }

    @Override
    public int findProductCount() throws DAOException {
        int countProduct = 0;

        try (PreparedStatement statement = connection.prepareStatement(FIND_PRODUCT_COUNT)) {

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                countProduct = resultSet.getInt(COUNT);
            }

        } catch (SQLException e) {
            throw new DAOException("Find count products error ", e);
        }

        return countProduct;
    }

    @Override
    public int findProductCountByType(String productType) throws DAOException {

        int countProduct = 0;

        try (PreparedStatement statement = connection.prepareStatement(FIND_PRODUCT_COUNT_BY_TYPE)) {
            statement.setString(1, productType);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                countProduct = resultSet.getInt(COUNT);
            }
        } catch (SQLException e) {
            throw new DAOException("Find count products error ", e);
        }

        return countProduct;
    }

    @Override
    public ProductEntity findProductById(int id) throws DAOException {
        return null;
    }

    @Override
    public List<ProductEntity> findProductByType(String  productType, int startIndex, int limit) throws DAOException {
        List<ProductEntity> foundProducts;
        try (PreparedStatement statement = connection.prepareStatement(FIND_PRODUCT_BY_TYPE)) {
            statement.setString(1, productType);
            statement.setInt(2, startIndex);
            statement.setInt(3, limit);
            ResultSet result = statement.executeQuery();
            foundProducts = extractProducts(result);
        } catch (SQLException e) {
            throw new DAOException("Find product by type error ", e);
        }
        return foundProducts;
    }

    @Override
    public boolean updateType(ProductEntity entity) throws DAOException {
        return false;
    }

    @Override
    public boolean updateAvatarPath(ProductEntity entity) throws DAOException {
        return false;
    }

    @Override
    public boolean updatePrice(ProductEntity entity) throws DAOException {
        return false;
    }

    @Override
    public boolean deleteProduct(ProductEntity entity) throws DAOException {
        return false;
    }

    @Override
    public boolean updateIngredients(ProductEntity entity) throws DAOException {
        return false;
    }

    private List<ProductEntity> extractProducts(ResultSet resultSet) throws SQLException {

        List<ProductEntity> productList = new ArrayList<>();

        while (resultSet.next()) {
            ProductEntity product = new ProductEntity();
            product.setId(resultSet.getInt(SQLFieldConstant.Product.ID));
            product.setPrice(resultSet.getBigDecimal(SQLFieldConstant.Product.PRICE));
            product.setImageURL(resultSet.getString(SQLFieldConstant.Product.IMAGE_URL));
            product.setName(resultSet.getString(SQLFieldConstant.Product.NAME));
            product.setIngredients(resultSet.getString(SQLFieldConstant.Product.INGREDIENTS));
            product.setProductType(resultSet.getString(SQLFieldConstant.Product.TYPE));
            product.setWeight(resultSet.getInt(SQLFieldConstant.Product.WEIGHT));
            productList.add(product);
        }
        return productList;
    }

    @Override
    public int createAndGetId(ProductEntity entity) throws DAOException {
        int productId = 0;
        try (PreparedStatement statement = connection.prepareStatement(CREATE_PRODUCT,
                PreparedStatement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, entity.getName());
            statement.setString(2, entity.getProductType());
            statement.setBigDecimal(3, entity.getPrice());
            statement.setString(4, entity.getIngredients());
            statement.setInt(5, entity.getWeight());

            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();

            if (resultSet.next()) {
                productId = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new DAOException("Create product error ", e);
        }
        return productId;
    }

    @Override
    public boolean updateImagePath(ProductEntity entity) throws DAOException {
        boolean isUpdated;

        try (PreparedStatement statement = connection.prepareStatement(UPDATE_IMAGE_PATH_PRODUCT)) {

            statement.setString(1, entity.getImageURL());
            statement.setInt(2, entity.getId());
            isUpdated = statement.executeUpdate() == 1;

        } catch (SQLException e) {
            throw new DAOException("Update product error ", e);
        }
        return isUpdated;
    }

    @Override
    public List<String> findProductType()throws DAOException{
        List<String> foundProductTypes = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(FIND_PRODUCT_TYPE)) {
            ResultSet result = statement.executeQuery();
            while (result.next()){
                String productType = result.getString(SQLFieldConstant.Product.TYPE);
                foundProductTypes.add(productType);
            }
        } catch (SQLException e) {
            throw new DAOException("Find product types error ", e);
        }
        return foundProductTypes;
    }
}
