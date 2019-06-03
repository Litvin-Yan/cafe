package by.epam.cafe.dao;

import by.epam.cafe.entity.ProductEntity;
import by.epam.cafe.exception.DAOException;

import java.util.List;

public abstract class ProductDAO extends DAO<ProductEntity> {
    /**
     * Find limit products
     *
     * @param startIndex start index
     * @param limit      limit
     * @return products
     * @throws DAOException when sql request error
     */
    public abstract List<ProductEntity> findWithLimit(int startIndex, int limit) throws DAOException;

    /**
     * Find products count
     *
     * @return products count
     * @throws DAOException when sql request error
     */
    public abstract int findProductCount() throws DAOException;

    /**
     * Find product by id
     *
     * @param id productId
     * @return productEntity
     * @throws DAOException when sql request error
     */
    public abstract ProductEntity findProductById(int id) throws DAOException;

    /**
     * Find product by type
     *
     * @param type productType
     * @return productEntity
     * @throws DAOException when sql request error
     */
    public abstract List<ProductEntity> findProductByType(String type, int page, int limit) throws DAOException;

    /**
     * Update product type
     *
     * @param entity product
     * @return update success
     * @throws DAOException when sql request error
     */
    public abstract boolean updateType(ProductEntity entity) throws DAOException;

    /**
     * Update product role
     *
     * @param entity product
     * @return update success
     * @throws DAOException when sql request error
     */
    public abstract boolean updateAvatarPath(ProductEntity entity) throws DAOException;

    /**
     * Update product price
     *
     * @param entity product
     * @return update success
     * @throws DAOException when sql request error
     */
    public abstract boolean updatePrice(ProductEntity entity) throws DAOException;

    /**
     * Delete product
     *
     * @param entity product
     * @return update success
     * @throws DAOException when sql request error
     */
    public abstract boolean deleteProduct(ProductEntity entity) throws DAOException;

    /**
     * Update product ingredients
     *
     * @param entity product
     * @return update success
     * @throws DAOException when sql request error
     */
    public abstract boolean updateIngredients(ProductEntity entity) throws DAOException;

    /**
     * find product count by type
     *
     * @param productType
     * @return  count of product type
     * @throws DAOException
     */
    public abstract int findProductCountByType(String productType) throws DAOException;

    /**
     * find product type
     *
     * @return  product type list
     * @throws DAOException
     */
    public abstract List<String> findProductType()throws DAOException;

    /**
     * create product
     *
     * @param entity
     * @return  product id
     * @throws DAOException
     */
    public abstract int createAndGetId(ProductEntity entity) throws DAOException;

    /**
     * update image path
     *
     * @param entity
     * @return true if updated
     * @throws DAOException
     */
    public abstract boolean updateImagePath(ProductEntity entity) throws DAOException;
}
