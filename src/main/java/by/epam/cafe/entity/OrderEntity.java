package by.epam.cafe.entity;

import by.epam.cafe.type.PaymentType;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class OrderEntity extends Entity {

    private int id;
    private int userId;
    private BigDecimal cash;
    private Boolean paid;
    private BigDecimal bonus;
    private Date expectedTime;
    private Date time;
    private PaymentType paymentType;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public BigDecimal getCash() {
        return cash;
    }

    public void setCash(BigDecimal cash) {
        this.cash = cash;
    }

    public Boolean getPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    public BigDecimal getBonus() {
        return bonus;
    }

    public void setBonus(BigDecimal bonus) {
        this.bonus = bonus;
    }

    public Date getExpectedTime() {
        return expectedTime;
    }

    public void setExpectedTime(Date expectedTime) {
        this.expectedTime = expectedTime;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderEntity)) return false;
        OrderEntity that = (OrderEntity) o;
        return id == that.id &&
                userId == that.userId &&
                Objects.equals(cash, that.cash) &&
                Objects.equals(paid, that.paid) &&
                Objects.equals(bonus, that.bonus) &&
                Objects.equals(expectedTime, that.expectedTime) &&
                Objects.equals(time, that.time) &&
                paymentType == that.paymentType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, cash, paid, bonus, expectedTime, time, paymentType);
    }
}
