package by.epam.cafe.entity;

import java.util.Date;
import java.util.Objects;

public class CommentEntity extends Entity{

    private String text;
    private Boolean isBlocked;
    private Date postDate;
    private int rate;
    private int orderId;


    /**
     * Get Rate.
     *
     * @return comment rate
     */
    public int getRate() {
        return rate;
    }

    /**
     * Set Rate.
     *
     * @param rate comment rate
     */
    public void setRate(int rate) {
        this.rate = rate;
    }

    /**
     * Get text.
     *
     * @return comment text
     */
    public String getText() {
        return text;
    }

    /**
     * Set text.
     *
     * @param text comment text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Get blocked.
     *
     * @return blocked state. True if blocked. False if not blocked
     */
    public Boolean getBlocked() {
        return isBlocked;
    }

    /**
     * Set blocked.
     *
     * @param blocked set blocked state
     */
    public void setBlocked(Boolean blocked) {
        isBlocked = blocked;
    }

    /**
     * Get post date.
     *
     * @return post date
     */
    public Date getPostDate() {
        return postDate;
    }

    /**
     * Set post date.
     *
     * @param postDate post date
     */
    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    /**
     * Get order ID.
     *
     * @return order id
     */
    public int getOrderId() {
        return orderId;
    }

    /**
     * Set order ID.
     *
     * @param orderId order id
     */
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommentEntity)) return false;
        CommentEntity that = (CommentEntity) o;
        return rate == that.rate &&
                orderId == that.orderId &&
                Objects.equals(text, that.text) &&
                Objects.equals(isBlocked, that.isBlocked) &&
                Objects.equals(postDate, that.postDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, isBlocked, postDate, rate, orderId);
    }
}
