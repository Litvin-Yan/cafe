package by.epam.cafe.entity_cafe;//package by.epam.cafe.entity_cafe;
//
//import by.epam.cafe.type_cafe.UserType;
//
//import java.math.BigDecimal;
//import java.util.Objects;
//
//public class User extends Entity {
//
//    private int id;
//    private String name;
//    private String email;
//    private String password;
//    private UserType type;
//    private boolean isConfirm;
//    private String confirmUrl;
//    private boolean isBlocked;
//    private String blockedText;
//    private BigDecimal cash;
//    private String avatarURL;
//
//    /**
//     * Get ID.
//     *
//     * @return user id
//     */
//    public int getId() {
//        return id;
//    }
//
//    /**
//     * Set ID.
//     *
//     * @param id user id
//     */
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    /**
//     * Get name.
//     *
//     * @return user name
//     */
//    public String getName() {
//        return name;
//    }
//
//    /**
//     * Set name.
//     *
//     * @param name user name
//     */
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    /**
//     * Get email.
//     *
//     * @return user mail
//     */
//    public String getEmail() {
//        return email;
//    }
//
//    /**
//     * Set email.
//     *
//     * @param email user mail
//     */
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    /**
//     * Get password.
//     *
//     * @return user password
//     */
//    public String getPassword() {
//        return password;
//    }
//
//    /**
//     * Set password.
//     *
//     * @param password user password
//     */
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    /**
//     * Get type_cafe.
//     *
//     * @return user type_cafe
//     */
//    public UserType getType() {
//        return type;
//    }
//
//    /**
//     * Set type_cafe.
//     *
//     * @param type user type_cafe
//     */
//    public void setType(UserType type) {
//        this.type = type;
//    }
//
//    /**
//     * Get is confirm.
//     *
//     * @return user confirm state
//     */
//    public boolean getIsConfirm() {
//        return isConfirm;
//    }
//
//    /**
//     * Set confirm.
//     *
//     * @param confirm user confirm state
//     */
//    public void setConfirm(boolean confirm) {
//        isConfirm = confirm;
//    }
//
//    /**
//     * Get is blocked.
//     *
//     * @return user blocked state
//     */
//    public boolean getIsBlocked() {
//        return isBlocked;
//    }
//
//    /**
//     * Set blocked.
//     *
//     * @param blocked user blocked state
//     */
//    public void setBlocked(boolean blocked) {
//        isBlocked = blocked;
//    }
//
//    /**
//     * Get cash.
//     *
//     * @return user cash
//     */
//    public BigDecimal getCash() {
//        return cash;
//    }
//
//    /**
//     * Set cash.
//     *
//     * @param cash user cash
//     */
//    public void setCash(BigDecimal cash) {
//        this.cash = cash;
//    }
//
//    /**
//     * Get confirm URL.
//     *
//     * @return user confirm url
//     */
//    public String getConfirmUrl() {
//        return confirmUrl;
//    }
//
//    /**
//     * Set confirm URL.
//     *
//     * @param confirmUrl user confirm url
//     */
//    public void setConfirmUrl(String confirmUrl) {
//        this.confirmUrl = confirmUrl;
//    }
//
//    /**
//     * Get avatar URL.
//     *
//     * @return user avatar url
//     */
//    public String getAvatarURL() {
//        return avatarURL;
//    }
//
//    /**
//     * Set avatar URL.
//     *
//     * @param avatarURL user avatar url
//     */
//    public void setAvatarURL(String avatarURL) {
//        this.avatarURL = avatarURL;
//    }
//
//    /**
//     * Get blocked text.
//     *
//     * @return user blocked text
//     */
//    public String getBlockedText() {
//        return blockedText;
//    }
//
//    /**
//     * Set blocked text.
//     *
//     * @param blockedText user blocked text
//     */
//    public void setBlockedText(String blockedText) {
//        this.blockedText = blockedText;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof User)) return false;
//        User user = (User) o;
//        return id == user.id &&
//                isConfirm == user.isConfirm &&
//                isBlocked == user.isBlocked &&
//                name.equals(user.name) &&
//                email.equals(user.email) &&
//                password.equals(user.password) &&
//                type == user.type &&
//                confirmUrl.equals(user.confirmUrl) &&
//                blockedText.equals(user.blockedText) &&
//                cash.equals(user.cash) &&
//                avatarURL.equals(user.avatarURL);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, name, email, password, type, isConfirm, confirmUrl, isBlocked, blockedText, cash, avatarURL);
//    }
//}
