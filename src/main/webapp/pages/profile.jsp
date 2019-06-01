<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@include file="/pages/partial/header.jsp" %>
<script src="${pageContext.request.contextPath}/js/sign_in.js"></script>

<fmt:setLocale value="${sessionScope.get('locale')}" scope="session"/>
<fmt:setBundle basename="locale/text" var="rb"/>
<fmt:message key="lbl.ForgotPassword" bundle="${rb}" var="forgotPassword"/>
<fmt:message key="lbl.SignIn" bundle="${rb}" var="signIn"/>
<fmt:message key="lbl.Authorization" bundle="${rb}" var="authorization"/>
<fmt:message key="lbl.Password" bundle="${rb}" var="password"/>
<fmt:message key="lbl.Email" bundle="${rb}" var="email"/>
<fmt:message key="warn.wrongEmailorPassword" bundle="${rb}" var="wrongData"/>

<fmt:message bundle="${rb}" key="txt.wrong.access" var="txtWrongAccess"/>
<fmt:message bundle="${rb}" key="txt.error.check.connection" var="txtError"/>
<fmt:message bundle="${rb}" key="txt.wrong.data.positive.number.format" var="txtWrongDataPositiveNumberFormat"/>
<fmt:message bundle="${rb}" key="txt.havent.match.money" var="txtHaventMatchMoney"/>
<fmt:message bundle="${rb}" key="txt.enter.amount.want" var="txtEnterAmountWant"/>
<fmt:message bundle="${rb}" key="txt.enter.amount" var="txtEnterAmount"/>
<fmt:message bundle="${rb}" key="txt.withdraw.money" var="txtWithdrawMoney"/>
<fmt:message bundle="${rb}" key="txt.add.money" var="txtAddMoney"/>
<fmt:message bundle="${rb}" key="txt.change.avatar" var="txtChangeAvatar"/>
<fmt:message bundle="${rb}" key="txt.change.password" var="txtChangePassword"/>
<fmt:message bundle="${rb}" key="txt.profile" var="txtProfile"/>
<fmt:message bundle="${rb}" key="lbl.Name" var="txtName"/>
<fmt:message bundle="${rb}" key="txt.cash" var="txtCash"/>
<fmt:message bundle="${rb}" key="txt.bonus" var="txtBonus"/>
<fmt:message bundle="${rb}" key="txt.role" var="txtRole"/>
<fmt:message bundle="${rb}" key="txt.email" var="txtEmail"/>
<fmt:message bundle="${rb}" key="txt.ok" var="txtOk"/>
<fmt:message bundle="${rb}" key="txt.add" var="txtAdd"/>
<fmt:message bundle="${rb}" key="txt.withdraw" var="txtWithdraw"/>
<fmt:message bundle="${rb}" key="txt.cancel" var="txtCancel"/>
<fmt:message bundle="${rb}" key="txt.in.waiting.of.comment" var="txtInWaitingOfComment"/>
<fmt:message bundle="${rb}" key="txt.active.order" var="txtActiveOrder"/>
<fmt:message bundle="${rb}" key="txt.order.expected.date" var="txtOrderExpectedDate"/>
<fmt:message bundle="${rb}" key="txt.order.date" var="txtOrderDate"/>
<fmt:message bundle="${rb}" key="txt.order.price" var="txtOrderPrice"/>

<body>
<div class="w3-row-padding">
    <div class="w3-col l2 m1">
        <div class="w3-container"></div>
    </div>
    <div class="w3-col l8 m10">

        <div class="w3-display-container">
            <div class="w3-display-topright">
                <button type="button" class="w3-button w3-card-4 w3-circle w3-theme-l3"
                        style="padding: 15px 17px;" onclick="history.back(); return false;">
                    <i class='fa fa-long-arrow-left'></i>
                </button>
            </div>
            <h1>${txtProfile}</h1>
        </div>
        <div class=" w3-margin-top">
            <%--User info section--%>
            <div class="w3-col m4 w3-container w3-card-2 w3-display-container w3-margin-bottom">
                <div class="w3-display-topright w3-black">
                    <a href="${pageContext.request.contextPath}/pages/change_avatar.jsp"
                       class="w3-button w3-tiny w3-black w3-padding-small w3-display-hover">${txtChangeAvatar}</a>
                    <a href="${pageContext.request.contextPath}/pages/change_password.jsp"
                       class="w3-button w3-tiny w3-black w3-padding-small w3-display-hover">${txtChangePassword}</a>
                </div>
                <div class="w3-col s12 w3-center">
                    <div class=" w3-padding">
                        <img src="${userImagePath}${user.avatarURL}" alt="${user.name}"
                             class="w3-image w3-circle" style="height: 150px">
                    </div>
                </div>
                <div class="w3-col s12 w3-container w3-center w3-medium">
                    <div class="w3-col s12">
                        <button onclick="(modalAddMoney).style.display = 'inherit'"
                                class="w3-button w3-input w3-black w3-small w3-margin-bottom w3-padding-small">
                            ${txtAddMoney}
                        </button>
                    </div>
                    <div class="w3-col s12">
                        <button onclick="(modalWithdrawMoney).style.display = 'inherit'"
                                class="w3-button w3-input w3-black w3-small w3-margin-bottom w3-padding-small">
                            ${txtWithdrawMoney}
                        </button>
                    </div>
                </div>
                <div class="w3-col s12 w3-container w3-left-align w3-medium">
                    <span>
                        <i>${txtName}: </i>
                        <b> <c:out value="${user.name}"/> </b>
                    </span>
                    <br>
                    <span>
                        <i>${txtCash}: </i>
                        <b> <ctg:decimal-presenter number="${user.cash}"/>$ </b>
                    </span>
                    <br>
                    <span>
                        <i>${txtBonus}: </i>
                        <b> <ctg:decimal-presenter number="${user.bonus}"/>$ </b>
                    </span>
                    <br>
                    <span>
                        <i>${txtRole}: </i>
                        <b> <c:out value="${user.type}"/> </b>
                    </span>
                    <br>
                    <span>
                        <i>${txtEmail}: </i>
                        <b> <c:out value="${user.email}"/> </b>
                    </span>
                    <br>
                </div>
            </div>
            <%--Order info section--%>
            <div class="w3-col m8 w3-container">
                <%--Incude active order part--%>
                <table class="items w3-table w3-centered" style="width: auto">
                    <td>
                        <table class="items w3-table w3-centered" style="width: auto">
                            <thead>${txtActiveOrder}</thead>
                            <tbody>
                            <c:forEach items="${activeOrders}" var="activeOrder">
                                <tr class="w3-large  backgraund-opacity">
                                    <td>
                                <span>
                                    <i class="w3-small">${txtOrderDate}:</i>
                                    <b class="w3-small"><ctg:date-presenter date="${activeOrder.time}"/></b>
                                </span>
                                        <br>
                                        <span>
                                    <i class="w3-small">${txtOrderExpectedDate}:</i>
                                    <b class="w3-small"><ctg:date-presenter date="${activeOrder.expectedTime}"/></b>
                                </span>
                                    </td>
                                    <td>
                                <span>
                                    <i class="w3-small">${txtOrderPrice}:</i>
                                    <b class="w3-small"> <ctg:decimal-presenter number="${activeOrder.cash}"/>$ </b>
                                </span>
                                        <br>
                                        <span>

                                </span>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </td>
                    <td>
                        <table class="items w3-table w3-centered" style="width: auto">
                            <thead>${txtInWaitingOfComment}</thead>
                            <tbody>
                            <c:forEach items="${ordersWithoutComment}" var="orderWithoutComment">
                                <tr class="w3-large  backgraund-opacity">
                                    <td>
                                <span>
                                    <i class="w3-small">${txtOrderDate}:</i>
                                    <b class="w3-small"><ctg:date-presenter date="${orderWithoutComment.time}"/></b>
                                </span>
                                        <br>
                                        <span>
                                    <i class="w3-small">${txtOrderExpectedDate}:</i>
                                    <b class="w3-small"><ctg:date-presenter
                                            date="${orderWithoutComment.expectedTime}"/></b>
                                </span>
                                    </td>
                                    <td>
                                <span>
                                    <i class="w3-small">${txtOrderPrice}:</i>
                                    <b class="w3-small"> <ctg:decimal-presenter
                                            number="${orderWithoutComment.cash}"/>$ </b>
                                </span>
                                        <br>
                                        <span>

                                </span>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </td>
                </table>
            </div>
        </div>
    </div>
</div>

<div class="w3-col l2 m1">
    <div class="w3-container"></div>
</div>

<div id="modalAddMoney" class="w3-modal">
    <div class="w3-modal-content" style="max-width: 50%;">
        <div class="w3-container">
            <div id="addCashForm">
                <input type="hidden" name="command" value="ADD_MONEY"/>
                <span onclick="(modalAddMoney).style.display='none'"
                      class="w3-button w3-display-topright">&times;</span>
                <p>${txtEnterAmount}($) : </p>
                <p>
                    <input type="number" name="cash" id="addCash" step="1"
                           class="w3-input w3-border" min="1" max="1000"/>
                </p>
                <input type="button" class="w3-button" value="${txtAdd}" onclick="addMoney(this, 'modalAddMoney');">
                <input type="button" class="w3-button" value="${txtCancel}"
                       onclick="(modalAddMoney).style.display='none'">
            </div>
        </div>
    </div>
</div>
<div id="modalWithdrawMoney" class="w3-modal">
    <div class="w3-modal-content" style="max-width: 50%;">
        <div class="w3-container">
            <div id="withdrawCashForm">
                <input type="hidden" name="command" value="WITHDRAW_MONEY"/>
                <span onclick="(modalWithdrawMoney).style.display='none'"
                      class="w3-button w3-display-topright">&times;</span>
                <p>${txtEnterAmount} ($) : </p>
                <p>
                    <input type="number" name="cash" id="withdrawCash" step="1" class="w3-input w3-border" min="1"
                           max="<ctg:decimal-presenter number='${user.cash}' scale='0'/>"/>
                </p>
                <input type="button" class="w3-button" value="${txtWithdraw}"
                       onclick="withdrawMoney(this, 'modalWithdrawMoney')">
                <input type="button" class="w3-button" value="${txtCancel}"
                       onclick="(modalWithdrawMoney).style.display='none'">
                <div class="w3-text-red" id="littleMoney" style="display: none">${txtHaventMatchMoney}.</div>
            </div>
        </div>
    </div>
</div>

<div id="wrongData" class="w3-modal">
    <div class="w3-modal-content" style="max-width: 50%;">
        <div class="w3-container">
            <span onclick="(wrongData).style.display='none'" class="w3-button w3-display-topright">&times;</span>
            <p>${txtWrongDataPositiveNumberFormat} 1-1000$</p>
            <input type="button" class="w3-button" value="${txtOk}" onclick="(wrongData).style.display='none'">
        </div>
    </div>
</div>

<div id="serverError" class="w3-modal">
    <div class="w3-modal-content" style="max-width: 50%;">
        <div class="w3-container">
            <span onclick="(serverError).style.display='none'" class="w3-button w3-display-topright">&times;</span>
            <p>${txtError}</p>
            <input type="button" class="w3-button" value="${txtOk}" onclick="(serverError).style.display='none'">
        </div>
    </div>
</div>

<div id="accessDenied" class="w3-modal">
    <div class="w3-modal-content" style="max-width: 50%;">
        <div class="w3-container">
            <span onclick="(accessDenied).style.display='none'" class="w3-button w3-display-topright">&times;</span>
            <p>${txtWrongAccess}</p>
            <input type="button" class="w3-button" value="${txtOk}" onclick="(accessDenied).style.display='none'">
        </div>
    </div>
</div>
</body>
<script src="${pageContext.request.contextPath}/js/profile.js"></script>

<%@include file="/pages/partial/footer.jsp" %>
