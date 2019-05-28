<%@include file="/pages/partial/header.jsp" %>
<script src="../js/button.js"></script>
<fmt:message bundle="${rb}" key="txt.menuEntity.category" var="txtMenuCategory"/>
<fmt:message bundle="${rb}" key="lbl.Menu" var="txtMenu"/>
<fmt:message bundle="${rb}" key="txt.button.in.basket" var="txtButton"/>
<fmt:message bundle="${rb}" key="txt.gramm" var="txtGramm"/>
<fmt:message bundle="${rb}" key="txt.choose.product.type" var="txtChooseProductType"/>
<fmt:message bundle="${rb}" key="txt.all" var="txtAll"/>
<br>

<table class="products w3-table w3-centered">
        <thead>
        <div class="w3-dropdown-hover" style="padding: 0">
            <button class="w3-button w3-xlarge">${txtChooseProductType}</button>
            <div class="w3-dropdown-content w3-bar-block w3-card-4">
                <form action="${pageContext.request.contextPath}/generalController">
                    <input type="hidden" name="command" value="OPEN_MENU">
                    <input type="hidden" name="productType" value="${txtAll}">
                    <input type="submit" style="height: 38px" class='w3-text-amber w3-bar-item w3-button w3-black' value="${txtAll}"/>
                </form>
                <c:forEach var="productType" items="${productTypeList}">
                    <form action="${pageContext.request.contextPath}/generalController">
                        <input type="hidden" name="command" value="OPEN_MENU">
                        <input type="hidden" name="productType" value="${productType}">
                        <input type="submit" style="height: 38px" class='w3-text-amber w3-bar-item w3-button w3-black' value="${productType}"/>
                    </form>
                </c:forEach>
            </div>
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
                <span>${product.weight}${txtGramm}</span>
            </td>
            <td>
                <c:choose>
                    <c:when test="${orderData.products.containsKey(product)}">
                        <div class="btn-group mr-2 pod" id="pod${product.id}" role="group" aria-label="First group">
                            <button type='button' id='minus${product.id}'
                                    onclick='clickMinus(${product.id},"${txtButton}")'
                                    style="height: 38px" class='w3-button w3-black w3-text-amber border-right-round'>-
                            </button>
                            <button type='button' style="height: 38px" class='w3-button w3-black w3-text-amber'>
                                <p id='counter${product.id}'>${orderData.products.get(product)}</p>
                            </button>
                            <button type='button' id='plus${product.id}' onclick='clickPlus(${product.id})'
                                    style="height: 38px" class='w3-button w3-black w3-text-amber border-left-round'>+
                            </button>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="btn-group mr-2 pod" id="pod${product.id}" role="group" aria-label="First group">
                            <button type="button"
                                    class="w3-button w3-black w3-border w3-border-black w3-round-large w3-text-amber"
                                    id="basket${product.id}"
                                    onclick="clickBasket(${product.id},'${txtButton}')">
                                <c:out value="${txtButton}"/>
                            </button>
                        </div>
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pagination w3-margin-left1 w3-hide-medium w3-xlarge w3-border-black w3-padding-small w3-text-amber">
    <ctg:pagination total="${productCount}" limit="${limit}" command="open_menu"/>
</div>
<%@include file="/pages/partial/footer.jsp" %>
