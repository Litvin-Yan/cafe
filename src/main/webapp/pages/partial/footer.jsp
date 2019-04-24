</div>
<fmt:message bundle="${rb}" key="txt.cafe" var="txtCafe"/>
<fmt:message bundle="${rb}" key="txt.all.right.reserved" var="txtAllRightReserved"/>

<footer class="footer w3-container w3-theme-d3 w3-padding-16 w3-display-container">
    <h5 class="w3-text-amber"> ${txtAllRightReserved}.</h5>
    <div class="w3-display-right">
            <input type="image" src="${pageContext.request.contextPath}/images/language/ru.png" name="locale" value="RU" onclick="changeLanguage(this)">
            <input type="image" src="${pageContext.request.contextPath}/images/language/en.png" name="locale" value="EN" onclick="changeLanguage(this)">
    </div>
</footer>`
<script src="${pageContext.request.contextPath}/js/w3.js"></script>
</div>
</html>