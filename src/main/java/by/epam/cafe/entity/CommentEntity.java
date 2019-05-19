package by.epam.cafe.entity;

import java.util.Date;
import java.util.Objects;

public class CommentEntity extends Entity{

    private int id;
    private String text;
    private Boolean isBlocked;
    private Date postDate;
    private int rate;
    private int orderId;
    private int userId;

    /**
     * Get ID.
     *
     * @return comment id
     */
    public int getId() {
        return id;
    }

    /**
     * Set ID.
     *
     * @param id comment id
     */
    public void setId(int id) {
        this.id = id;
    }

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
    public int getNewsId() {
        return orderId;
    }

    /**
     * Set order ID.
     *
     * @param orderId order id
     */
    public void setNewsId(int orderId) {
        this.orderId = orderId;
    }

    /**
     * Get user ID.
     *
     * @return user id
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Set user ID.
     *
     * @param userId user id
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommentEntity)) return false;
        CommentEntity commentEntity = (CommentEntity) o;
        return id == commentEntity.id &&
                rate == commentEntity.rate &&
                orderId == commentEntity.orderId &&
                userId == commentEntity.userId &&
                text.equals(commentEntity.text) &&
                isBlocked.equals(commentEntity.isBlocked) &&
                postDate.equals(commentEntity.postDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, isBlocked, postDate, rate, orderId, userId);
    }
}
