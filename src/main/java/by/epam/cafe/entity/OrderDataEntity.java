package by.epam.cafe.entity;

import java.util.Map;
import java.util.Objects;

public class OrderDataEntity extends Entity {
    private int order_id;
    private Map<ProductEntity, Integer[]> products;

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public Map<ProductEntity, Integer[]> getProducts() {
        return products;
    }

    public void setProducts(Map<ProductEntity, Integer[]> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderDataEntity)) return false;
        OrderDataEntity orderDataEntity = (OrderDataEntity) o;
        return order_id == orderDataEntity.order_id &&
                Objects.equals(products, orderDataEntity.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(order_id, products);
    }
}
