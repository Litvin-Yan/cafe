<%@include file="/pages/partial/header.jsp" %>
<script src="../js/button.js"></script>
<fmt:message bundle="${rb}" key="txt.menuEntity.category" var="txtMenuCategory"/>
<fmt:message bundle="${rb}" key="lbl.Menu" var="txtMenu"/>
<fmt:message bundle="${rb}" key="txt.button.in.basket" var="txtButton"/>
<fmt:message bundle="${rb}" key="txt.gramm" var="txtGramm"/>
<fmt:message bundle="${rb}" key="txt.choose.product.type" var="txtChooseProductType"/>
<br>
<table class="products w3-table w3-centered">
    <thead>
    <div class="w3-col s4 w3-padding-small w3-margin-left">
        <label>
            <span class="w3-xlarge">
            ${txtMenuCategory}
            </span>
            <select id="selectProductType" required name="productId" class="w3-select">
                <option value='' selected disabled><c:out value="${txtChooseProductType}"/></option>
                <c:forEach var="productType" items="${productTypeList}">
                    <option value="${productType}" count="${productTypeCount}" onclick="">
                        <c:out value="${productType}"/>
                    </option>
                </c:forEach>
            </select>

        </label>
    </div>
    </thead>
    <tbody>
    <c:forEach var="product" items="${productList}">
        <tr class="w3-large  backgraund-opacity">
            <td></td>
            <td>
                <img src="../image/products/${product.imageURL}" height="70">
            </td>
            <td>
                <span>${product.name}</span>
                <span>${product.price}$</span>
            </td>
            <td>
                <span>${product.ingredients}</span>
                <span>500<c:out value="${txtGramm}"/></span>
            </td>
            <td>
                <div class="btn-group mr-2 pod" id="pod${product.id}" role="group" aria-label="First group">
                    <button type="button" class="w3-button w3-black w3-border w3-border-black w3-round-large w3-text-amber" id="basket${product.id}"
                            onclick="clickBasket(${product.id},'${txtButton}')"><c:out value="${txtButton}"/></button>
                </div>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pagination w3-margin-left1 w3-hide-medium w3-large w3-text-amber">
    <ctg:pagination total="${productCount}" limit="${limit}" command="open_menu"/>
</div>
<%@include file="/pages/partial/footer.jsp" %>
<%--<body>--%>
<%--    <table class="productEntities table">--%>
<%--        <thead>--%>
<%--        <div class="w3-col s4 w3-padding-small">--%>
<%--            <label>--%>
<%--                ${txtMenuCategory}--%>
<%--                <select id="selectKind" required name="sportId" class="w3-select">--%>
<%--                    <option value='' selected disabled><c:out value="${txtChooseSport}"/></option>--%>
<%--                    <c:forEach var="kind" items="${kindsOfSport}">--%>
<%--                        <option value="${kind.id}" count="${kind.competitorCount}">--%>
<%--                            <c:out value="${kind.name}"/>--%>
<%--                        </option>--%>
<%--                    </c:forEach>--%>
<%--                </select>--%>
<%--            </label>--%>
<%--        </div>--%>
<%--        </thead>--%>
<%--        <tbody>--%>
<%--        <tr>--%>
<%--            <td></td>--%>
<%--            <td>--%>
<%--                <img src="../images/dishes/borsch.jpg" height="70">--%>
<%--            </td>--%>
<%--            <td>--%>
<%--                <span>Борщ</span>--%>
<%--                <span>145</span>--%>
<%--            </td>--%>
<%--            <td>--%>
<%--                <span>Свекла, Вода, Соль, Сахар, Говядина</span>--%>
<%--                <span>500г.</span>--%>
<%--            </td>--%>
<%--            <td>--%>
<%--                <div class="btn-group mr-2 pod" id="pod1" role="group" aria-label="First group">--%>
<%--                    <button type="button" class="btn btn-secondary basket" id="basket1" onclick="clickBasket(1)">В корзину</button>--%>
<%--                </div>--%>
<%--            </td>--%>
<%--        </tr>--%>
<%--        <tr>--%>
<%--            <td></td>--%>
<%--            <td>--%>
<%--                <img src="../images/dishes/borsch.jpg" height="70">--%>
<%--            </td>--%>
<%--            <td>--%>
<%--                <span>Борщ</span>--%>
<%--                <span>145</span>--%>
<%--            </td>--%>
<%--            <td>--%>
<%--                <span>Свекла, Вода, Соль, Сахар, Говядина</span>--%>
<%--                <span>500г.</span>--%>
<%--            </td>--%>
<%--            <td>--%>
<%--                <div class="btn-group mr-2 pod" id="pod2" role="group" aria-label="First group">--%>
<%--                    <button type="button" class="btn btn-secondary basket" id="basket2" onclick="clickBasket(2)">В корзину</button>--%>
<%--                </div>--%>
<%--            </td>--%>
<%--        </tr>--%>
<%--        <tr>--%>
<%--            <td></td>--%>
<%--            <td>--%>
<%--                <img src="../images/dishes/borsch.jpg" height="70">--%>
<%--            </td>--%>
<%--            <td>--%>
<%--                <span>Борщ</span>--%>
<%--                <span>145</span>--%>
<%--            </td>--%>
<%--            <td>--%>
<%--                <span>Свекла, Вода, Соль, Сахар, Говядина</span>--%>
<%--                <span>500г.</span>--%>
<%--            </td>--%>
<%--            <td>--%>
<%--                <div class="btn-group mr-2 pod" id="pod3" role="group" aria-label="First group">--%>
<%--                    <button type="button" class="btn btn-secondary basket" id="basket3" onclick="clickBasket(3)">В корзину</button>--%>
<%--                </div>--%>
<%--            </td>--%>
<%--        </tr>--%>
<%--        <tr>--%>
<%--            <td></td>--%>
<%--            <td>--%>
<%--                <img src="../images/dishes/borsch.jpg" height="70">--%>
<%--            </td>--%>
<%--            <td>--%>
<%--                <span>Борщ</span>--%>
<%--                <span>145</span>--%>
<%--            </td>--%>
<%--            <td>--%>
<%--                <span>Свекла, Вода, Соль, Сахар, Говядина</span>--%>
<%--                <span>500г.</span>--%>
<%--            </td>--%>
<%--            <td>--%>
<%--                <div class="btn-group mr-2 pod" id="pod4" role="group" aria-label="First group">--%>
<%--                    <button type="button" class="btn btn-secondary basket" id="basket4" onclick="clickBasket(4)">В корзину</button>--%>
<%--                </div>--%>
<%--            </td>--%>
<%--        </tr>--%>
<%--        <tr>--%>
<%--            <td></td>--%>
<%--            <td>--%>
<%--                <img src="../images/dishes/borsch.jpg" height="70">--%>
<%--            </td>--%>
<%--            <td>--%>
<%--                <span>Борщ</span>--%>
<%--                <span>145</span>--%>
<%--            </td>--%>
<%--            <td>--%>
<%--                <span>Свекла, Вода, Соль, Сахар, Говядина</span>--%>
<%--                <span>500г.</span>--%>
<%--            </td>--%>
<%--            <td>--%>
<%--                <div class="btn-group mr-2 pod" id="pod5" role="group" aria-label="First group">--%>
<%--                    <button type="button" class="btn btn-secondary basket" id="basket5" onclick="clickBasket(5)">В корзину</button>--%>
<%--                </div>--%>
<%--            </td>--%>
<%--        </tr>--%>
<%--        </tbody>--%>
<%--    </table>--%>
<%--</body>--%>
