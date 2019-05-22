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
    public abstract List<ProductEntity> findProductByType(String type) throws DAOException;

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
}
