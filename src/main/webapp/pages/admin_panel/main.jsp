<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@include file="/pages/partial/header.jsp" %>

<fmt:setLocale value="${sessionScope.get('locale')}" scope="session"/>
<fmt:setBundle basename="locale/text" var="rb"/>
<fmt:message bundle="${rb}" key="txt.admin.panel" var="txtAdminPanel"/>
<fmt:message bundle="${rb}" key="txt.registered.people" var="txtRegisteredPeople"/>
<fmt:message bundle="${rb}" key="txt.blocked.people" var="txtBlockedPeople"/>
<fmt:message bundle="${rb}" key="txt.product.count" var="txtProductCount"/>


<body>

<div class="w3-container w3-content main-container">
    <div class="w3-col m3 w3-margin-top">
        <br>
        <!-- Accordion -->
        <%@include file="/pages/admin_panel/left_bar.jsp" %>
        <!-- End Left Column -->
    </div>
    <div class="w3-col m9">
        <div class="w3-row-padding">
            <div class="w3-container w3-xlarge">
                ${txtAdminPanel}
            </div>
        </div>
        <div class="w3-row-padding w3-container w3-margin-bottom">
            <div class="w3-col s12 w3-card-2 w3-large backgraund-opacity">
                <p>
                    <i>${txtRegisteredPeople}:</i>
                    <b class="w3-xlarge"><c:out value="${statisticMap.registeredCount}"/></b>
                </p>
                <hr>
                <p>
                    <i>${txtBlockedPeople}:</i>
                    <b class="w3-xlarge" ><c:out value="${statisticMap.lockedCount}"/></b>
                </p>
                <hr>
                <p>
                    <i>${txtProductCount}:</i>
                    <b class="w3-xlarge" ><c:out value="${statisticMap.productCount}"/></b>
                </p>
            </div>
        </div>
    </div>
</div>
</body>

<%@include file="/pages/partial/footer.jsp" %>
