package by.epam.cafe.entity;

import by.epam.cafe.type.UserType;

import java.math.BigDecimal;
import java.util.Objects;

public class UserEntity extends Entity {

    private int id;
    private String name;
    private String email;
    private String password;
    private UserType type;
    private boolean isBlocked;
    private String blockedText;
    private BigDecimal cash;
    private BigDecimal bonus;
    private String avatarURL;

    /**
     * Get ID.
     *
     * @return user id
     */
    public int getId() {
        return id;
    }

    /**
     * Set ID.
     *
     * @param id user id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get name.
     *
     * @return user name
     */
    public String getName() {
        return name;
    }

    /**
     * Set name.
     *
     * @param name user name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get email.
     *
     * @return user mail
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set email.
     *
     * @param email user mail
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get password.
     *
     * @return user password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set password.
     *
     * @param password user password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Get type.
     *
     * @return user type
     */
    public UserType getType() {
        return type;
    }

    /**
     * Set type.
     *
     * @param type user type
     */
    public void setType(UserType type) {
        this.type = type;
    }

    /**
     * Get is blocked.
     *
     * @return user blocked state
     */
    public boolean getIsBlocked() {
        return isBlocked;
    }

    /**
     * Set blocked.
     *
     * @param blocked user blocked state
     */
    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    /**
     * Get cash.
     *
     * @return user cash
     */
    public BigDecimal getCash() {
        return cash;
    }

    /**
     * Set cash.
     *
     * @param cash user cash
     */
    public void setCash(BigDecimal cash) {
        this.cash = cash;
    }


    /**
     * Get avatar URL.
     *
     * @return user avatar url
     */
    public String getAvatarURL() {
        return avatarURL;
    }

    /**
     * Set avatar URL.
     *
     * @param avatarURL user avatar url
     */
    public void setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
    }

    /**
     * Get blocked text.
     *
     * @return user blocked text
     */
    public String getBlockedText() {
        return blockedText;
    }

    /**
     * Set blocked text.
     *
     * @param blockedText user blocked text
     */
    public void setBlockedText(String blockedText) {
        this.blockedText = blockedText;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public BigDecimal getBonus() {
        return bonus;
    }

    public void setBonus(BigDecimal bonus) {
        this.bonus = bonus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserEntity)) return false;
        UserEntity that = (UserEntity) o;
        return id == that.id &&
                isBlocked == that.isBlocked &&
                name.equals(that.name) &&
                email.equals(that.email) &&
                password.equals(that.password) &&
                type == that.type &&
                Objects.equals(blockedText, that.blockedText) &&
                cash.equals(that.cash) &&
                bonus.equals(that.bonus) &&
                avatarURL.equals(that.avatarURL);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, password, type, isBlocked, blockedText, cash, bonus, avatarURL);
    }
}
