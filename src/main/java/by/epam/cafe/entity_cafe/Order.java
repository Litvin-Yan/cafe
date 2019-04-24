package by.epam.cafe.entity_cafe;//package by.epam.cafe.entity_cafe;
//
//import by.epam.cafe.type_cafe.PaymentType;
//
//import java.math.BigDecimal;
//import java.util.Date;
//import java.util.Objects;
//
//public class Order extends Entity {
//
//    private int id;
//    private int userId;
//    private BigDecimal cash;
//    private Boolean paid;
//    private int bonus;
//    private Date expectedTime;
//    private Date time;
//    private PaymentType paymentType;
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public int getUserId() {
//        return userId;
//    }
//
//    public void setUserId(int userId) {
//        this.userId = userId;
//    }
//
//    public BigDecimal getCash() {
//        return cash;
//    }
//
//    public void setCash(BigDecimal cash) {
//        this.cash = cash;
//    }
//
//    public Boolean getPaid() {
//        return paid;
//    }
//
//    public void setPaid(Boolean paid) {
//        this.paid = paid;
//    }
//
//    public int getBonus() {
//        return bonus;
//    }
//
//    public void setBonus(int bonus) {
//        this.bonus = bonus;
//    }
//
//    public Date getExpectedTime() {
//        return expectedTime;
//    }
//
//    public void setExpectedTime(Date expectedTime) {
//        this.expectedTime = expectedTime;
//    }
//
//    public Date getTime() {
//        return time;
//    }
//
//    public void setTime(Date time) {
//        this.time = time;
//    }
//
//    public PaymentType getPaymentType() {
//        return paymentType;
//    }
//
//    public void setPaymentType(PaymentType paymentType) {
//        this.paymentType = paymentType;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof Order)) return false;
//        Order order = (Order) o;
//        return id == order.id &&
//                userId == order.userId &&
//                bonus == order.bonus &&
//                cash.equals(order.cash) &&
//                paid.equals(order.paid) &&
//                expectedTime.equals(order.expectedTime) &&
//                time.equals(order.time) &&
//                paymentType == order.paymentType;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, userId, cash, paid, bonus, expectedTime, time, paymentType);
//    }
//}
