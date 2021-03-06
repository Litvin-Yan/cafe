<%@include file="/pages/partial/header.jsp" %>
<script src="${pageContext.request.contextPath}/js/sign_in.js"></script>

<fmt:message key="lbl.ForgotPassword" bundle="${rb}" var="forgotPassword"/>
<fmt:message key="lbl.SignIn" bundle="${rb}" var="signIn"/>
<fmt:message key="lbl.Authorization" bundle="${rb}" var="authorization"/>
<fmt:message key="lbl.Password" bundle="${rb}" var="password"/>
<fmt:message key="lbl.Email" bundle="${rb}" var="email"/>
<fmt:message key="warn.wrongEmailorPassword" bundle="${rb}" var="wrongData"/>

<body>
<div class="w3-row-padding">
    <div class="w3-third">
        <div class="w3-container"></div>
    </div>
    <div class="w3-third">
        <h1>${authorization}</h1>

        <form class="w3-container  w3-card-2" name="form" action="${pageContext.request.contextPath}/generalController" method="post">
            <input type="hidden" name="command" value="Sign_in">
            <p>${email}:</p>
            <input  title="${email}" class="w3-input w3-border" type="email" id="email" name="email">
            <br>
            <p>${password}:</p>
            <input title="${password}" class="w3-input w3-border" type="password" id="password" name="password">
            <c:if test="${requestScope.containsKey('wrongData')}">
                <span class="w3-text-red">${wrongData}</span>
            </c:if>
            <br>
            <p>
                <input class="w3-button w3-black w3-text-amber" style="margin-bottom: 8px" type="submit" id="submit" value="${signIn}">
            </p>
        </form>
    </div>
    <div class="w3-third">
        <div class="w3-container"></div>
    </div>
</div>
<div class="sign"></div>
</body>

<%@include file="/pages/partial/footer.jsp" %>
