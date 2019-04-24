package by.epam.cafe.entity_cafe;//package by.epam.cafe.entity_cafe;
//
//import by.epam.cafe.type_cafe.ProductType;
//
//import java.util.Objects;
//
//public class Product extends Entity {
//
//    private int id;
//    private String name;
//    private ProductType productType;
//    private int price;
//    private String imageURL;
//    private String Composition;
//
//
//    public String getComposition() {
//        return Composition;
//    }
//
//    public void setComposition(String composition) {
//        Composition = composition;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public ProductType getProductType() {
//        return productType;
//    }
//
//    public void setProductType(ProductType productType) {
//        this.productType = productType;
//    }
//
//    public int getPrice() {
//        return price;
//    }
//
//    public void setPrice(int price) {
//        this.price = price;
//    }
//
//    public String getImageURL() {
//        return imageURL;
//    }
//
//    public void setImageURL(String imageURL) {
//        this.imageURL = imageURL;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof Product)) return false;
//        Product product = (Product) o;
//        return id == product.id &&
//                price == product.price &&
//                name.equals(product.name) &&
//                productType == product.productType &&
//                imageURL.equals(product.imageURL) &&
//                Composition.equals(product.Composition);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, name, productType, price, imageURL, Composition);
//    }
//}
