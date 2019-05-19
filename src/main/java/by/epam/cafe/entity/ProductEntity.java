package by.epam.cafe.entity;

import by.epam.cafe.type.ProductType;

import java.math.BigDecimal;
import java.util.Objects;

public class ProductEntity extends Entity {

    private int id;
    private String name;
    private ProductType productType;
    private BigDecimal price;
    private String imageURL;
    private String ingredients;


    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductEntity)) return false;
        ProductEntity productEntity = (ProductEntity) o;
        return id == productEntity.id &&
                price == productEntity.price &&
                name.equals(productEntity.name) &&
                productType == productEntity.productType &&
                imageURL.equals(productEntity.imageURL) &&
                ingredients.equals(productEntity.ingredients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, productType, price, imageURL, ingredients);
    }
}
