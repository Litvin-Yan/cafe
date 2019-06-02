<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.get('locale')}" scope="session"/>
<fmt:setBundle basename="locale/text" var="rb"/>
<fmt:message bundle="${rb}" key="txt.users" var="txtUsers"/>
<fmt:message bundle="${rb}" key="txt.products" var="txtProducts"/>
<fmt:message bundle="${rb}" key="txt.orders" var="txtOrders"/>
<fmt:message bundle="${rb}" key="txt.statistic" var="txtStatistic"/>

    <div class="w3-container w3-card-2 backgraund-opacity">
        <form action="${pageContext.request.contextPath}/generalController" class="w3-margin-bottom w3-card-2 w3-margin-top">
            <input type="hidden" name="command" value="OPEN_USER_SETTINGS">
            <button type="submit" class="w3-button w3-block  w3-center w3-black w3-text-amber">
                ${txtUsers}
            </button>
        </form>
        <form action="${pageContext.request.contextPath}/generalController" class="w3-margin-bottom w3-card-2">
            <input type="hidden" name="command" value="open_order_settings">
            <button type="submit" class="w3-button w3-block w3-center w3-black w3-text-amber">
                ${txtOrders}
            </button>
        </form>
        <form action="${pageContext.request.contextPath}/generalController" class="w3-margin-bottom w3-card-2">
            <input type="hidden" name="command" value="open_product_settings">
            <button type="submit" class="w3-button w3-block w3-center w3-black w3-text-amber">
                ${txtProducts}
            </button>
        </form>
        <form action="${pageContext.request.contextPath}/generalController" class="w3-margin-bottom w3-card-2">
            <input type="hidden" name="command" value="OPEN_ADMIN_STATISTIC">
            <button type="submit" class="w3-button w3-block w3-center w3-black w3-text-amber">
                ${txtStatistic}
            </button>
        </form>
    </div>

