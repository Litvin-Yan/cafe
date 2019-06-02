<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.get('locale')}" scope="session"/>
<fmt:setBundle basename="locale/text" var="rb"/>
<c:set var="userEntity" value="${sessionScope.get('user')}"/>

<fmt:message bundle="${rb}" key="lbl.SignIn" var="signIn"/>
<fmt:message bundle="${rb}" key="lbl.SignUp" var="signUp"/>
<fmt:message bundle="${rb}" key="lbl.SignOut" var="signOut"/>
<fmt:message bundle="${rb}" key="lbl.title" var="cafe"/>
<fmt:message bundle="${rb}" key="lbl.Profile" var="profile"/>
<fmt:message bundle="${rb}" key="lbl.Menu" var="menuEntity"/>
<fmt:message bundle="${rb}" key="lbl.Product" var="product"/>
<fmt:message bundle="${rb}" key="lbl.Comments" var="comments"/>
<fmt:message bundle="${rb}" key="lbl.Basket" var="basket"/>
<fmt:message bundle="${rb}" key="txt.admin.panel" var="txtAdminPanel"/>


<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1" charset="UTF-8"/>
    <link rel="icon" href="${pageContext.request.contextPath}/image/favicon.png">
    <title >${cafe}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/w3.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/w3-theme-black.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/open-sans.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>

<header>
    <div>
        <div class="w3-bar w3-theme-d2 w3-left-align w3-medium">
            <a class="w3-bar-item w3-button w3-hide-medium w3-hide-large w3-right w3-padding-16 w3-hover-amber w3-large w3-theme-d2"
               href="javascript:void(0);" onclick="openNav()">
                <i class="fa fa-bars"></i>
            </a>
            <c:choose>
                <%--Present if userEntity signed--%>
                <c:when test="${userEntity != null}">
                    <div class="w3-bar-item w3-dropdown-hover w3-right w3-hide-small" style="padding: 0">
                        <form action="${pageContext.request.contextPath}/generalController">
                            <input type="hidden" name="command" value="OPEN_PROFILE">
                            <button type="submit" class="w3-button w3-padding-small">
                            <div class="w3-row" style="max-width: 200px">
                                <div class="w3-col s8">
                                    <div class="w3-row w3-right-align w3-small">
                                        <div class="w3-text-amber w3-col s12 w3-padding-small">
                                            <c:out value="${userEntity.name}"/>
                                        </div>
                                        <div class="w3-text-amber w3-col s12 w3-padding-small">
                                            <ctg:decimal-presenter number="${userEntity.cash}"/>$
                                        </div>
                                    </div>
                                </div>
                                <div class="w3-col s4">
                                    <img style="width:50px" class="w3-circle"
                                         src="${userImagePath}${userEntity.avatarURL}"/>
                                </div>
                            </div>
                            </button>
                        </form>
                        <div class="w3-dropdown-content w3-bar-block w3-border">
                            <form action="${pageContext.request.contextPath}/generalController">
                                <input type="hidden" name="command" value="OPEN_PROFILE">
                                <input type="submit" class="w3-text-amber w3-bar-item w3-button w3-black" value="${profile}"/>
                            </form>
                            <c:if test="${userEntity.type.toString().equals('ADMIN')}">
                                <form action="${pageContext.request.contextPath}/generalController" method="post">
                                    <input type="hidden" name="command" value="OPEN_ADMIN_STATISTIC">
                                    <input type="submit" class="w3-text-amber w3-bar-item w3-button w3-black" value="${txtAdminPanel}"/>
                                </form>
                            </c:if>
                            <form action="${pageContext.request.contextPath}/generalController">
                                <input type="hidden" name="command" value="SIGN_OUT">
                                <input type="submit" class="w3-text-amber w3-bar-item w3-button w3-black" value="${signOut}"/>
                            </form>
                        </div>
                    </div>
                </c:when>
                <%--Present if userEntity not signed--%>
                <c:otherwise>
                    <div class="w3-bar-item w3-hide-small w3-right w3-padding-16 w3-small w3-margin-bottom">
                        <a href="${pageContext.request.contextPath}/pages/sign_up.jsp" class="w3-text-amber w3-hover-text-white">
                            <c:out value="${signUp}"/>
                        </a>
                        <b class="w3-text-amber">
                            <fmt:message key="lbl.or" bundle="${rb}"/>
                        </b>
                        <a href="${pageContext.request.contextPath}/pages/sign_in.jsp" class="w3-text-amber w3-hover-text-white">
                            <c:out value="${signIn}"/>
                        </a>
                    </div>
                </c:otherwise>
            </c:choose>
            <a href="${pageContext.request.contextPath}/index.jsp"
               class="w3-bar-item w3-button w3-padding w3-text-amber w3-xlarge w3-hover-none w3-hover-text-white">
                <c:out value="${cafe}"/>
            </a>
            <div class="w3-left w3-hide-small w3-center w3-padding-small w3-medium w3-margin-bottom">
                <form action="${pageContext.request.contextPath}/generalController"
                      class="w3-bar-item w3-padding-small">
                    <input type="hidden" name="command" value="OPEN_MENU">
                    <input type="submit"
                           class="w3-button w3-hide-small w3-padding-small w3-text-amber w3-hover-none w3-hover-text-white"
                           value="<c:out value='${menuEntity}'/>">
                </form>
                <c:if test="${userEntity != null}">
                <form action="${pageContext.request.contextPath}/generalController"
                      class="w3-bar-item w3-padding-small">
                    <input type="hidden" name="command" value="OPEN_BASKET">
                    <input type="submit"
                           class="w3-button w3-hide-small w3-padding-small w3-text-amber w3-hover-none w3-hover-text-white"
                           value="<c:out value='${basket}'/>">
                </form>
                </c:if>
                <form action="${pageContext.request.contextPath}/generalController"
                      class="w3-bar-item w3-padding-small">
                    <input type="hidden" name="command" value="OPEN_COMMENTS">
                    <input type="submit"
                           class="w3-button w3-hide-small w3-padding-small w3-text-amber w3-hover-none w3-hover-text-white"
                           value="<c:out value='${comments}'/>">
                </form>
            </div>
        </div>
    </div>
</header>
<div id="wrapper">
    <div id="top">
        <script src="../js/w3.js"></script>