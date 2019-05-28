package by.epam.cafe.entity;

import java.math.BigDecimal;
import java.util.Objects;

public class ProductEntity extends Entity {

    private int id;
    private String name;
    private String productType;
    private BigDecimal price;
    private String imageURL;
    private String ingredients;
    private int weight;

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

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

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
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
        ProductEntity that = (ProductEntity) o;
        return id == that.id &&
                weight == that.weight &&
                name.equals(that.name) &&
                productType.equals(that.productType) &&
                price.equals(that.price) &&
                imageURL.equals(that.imageURL) &&
                ingredients.equals(that.ingredients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, productType, price, imageURL, ingredients, weight);
    }
}
