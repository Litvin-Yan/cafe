<%@include file="/pages/partial/header.jsp" %>
<script src="../js/button.js"></script>
<fmt:message bundle="${rb}" key="txt.menuEntity.category" var="txtMenuCategory"/>
<fmt:message bundle="${rb}" key="lbl.Menu" var="txtMenu"/>
<fmt:message bundle="${rb}" key="txt.button.in.basket" var="txtButton"/>
<fmt:message bundle="${rb}" key="txt.gramm" var="txtGramm"/>
<fmt:message bundle="${rb}" key="txt.choose.product.type" var="txtChooseitemType"/>
<fmt:message bundle="${rb}" key="txt.order.date" var="txtDateOfReceiving"/>
<fmt:message bundle="${rb}" key="txt.order.time" var="txtTimeOfReceiving"/>
<fmt:message bundle="${rb}" key="txt.send" var="send"/>
<fmt:message bundle="${rb}" key="txt.payment.type.money" var="txtMoney"/>
<fmt:message bundle="${rb}" key="txt.payment.type.cash" var="txtCash"/>
<fmt:message bundle="${rb}" key="txt.payment.type.bonus" var="txtBonuses"/>
<fmt:message bundle="${rb}" key="txt.payment.type.choose" var="txtChoosePaymentType"/>
<fmt:message bundle="${rb}" key="txt.order.price" var="txtOrderPrice"/>
<fmt:message bundle="${rb}" key="txt.confirm.the.order" var="txtConfirmOrder"/>
<br>
<br>
<br>
<div class="w3-container main-container">
    <div class="w3-col m3 ">
        <div class="w3-container w3-card-2 backgraund-opacity">
            <form class="" name="form" action="${pageContext.request.contextPath}/generalController" method="post">
                <input type="hidden" name="command" value="CREATE_ORDER">
                <label>
                    ${txtDateOfReceiving}
                    <input id="dateOfReceiving" type="date" class="w3-input" name="dateOfReceiving" required>
                </label>
                <br>
                <br>
                <label>
                    ${txtTimeOfReceiving}
                    <input id="timeOfReceiving" type="time" class="w3-input" name="timeOfReceiving" required>
                </label>
                <br>
                <br>
                <select id="paymentType" required name="typeId" class="w3-select">
                    <option value='' selected disabled> ${txtChoosePaymentType}</option>
                    <option value="1">
                        ${txtMoney}
                    </option>
                    <option value="2">
                        ${txtCash}
                    </option>
                    <option value="3">
                        ${txtBonuses}
                    </option>
                </select>
                <br>
                <br>
                <label><c:out value="${txtOrderPrice}"/></label>
                <label class="orderPrice">
                    <c:set var="total" value="${0}"/>
                    <c:forEach var="product" items="${orderData.products}">
                        <c:set var="total" value="${total + product.key.price*product.value}"/>
                    </c:forEach>
                    <c:out value="${total}$"/>
                </label>
                <p>
                    <input class="w3-button w3-black w3-text-amber" style="margin-bottom: 8px" type="submit" id="submit"
                           value="${send}" onclick="alert(${txtConfirmOrder})">
                </p>
            </form>
        </div>
    </div>
    <div class="w3-col m9">
        <div class="w3-container">
            <table class="items w3-table w3-centered">
                <tbody>
                <c:forEach items="${productList}" var="product">
                    <tr class="w3-large  backgraund-opacity">
                        <td>
                            <img src="../image/products/${product.imageURL}" height="70">
                        </td>
                        <td>
                            <span>${product.name}</span>
                            <span class="price">${product.price}$</span>
                        </td>
                        <td>
                            <div class="btn-group mr-2 pod" id="pod${product.id}" role="group"
                                 aria-label="First group">
                                <button type='button' id='minus${product.id}'
                                        onclick='clickMinusUpdate(${product.id},"${txtButton}")'
                                        style="height: 38px"
                                        class='w3-button w3-black w3-text-amber border-right-round'>-
                                </button>
                                <button type='button' style="height: 38px"
                                        class='w3-button w3-black w3-text-amber'>
                                    <p class="count" id='counter${product.id}'>${orderData.products.get(product)}</p>
                                </button>
                                <button type='button' id='plus${product.id}' onclick='clickPlusUpdate(${product.id})'
                                        style="height: 38px"
                                        class='w3-button w3-black w3-text-amber border-left-round'>+
                                </button>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div class="pagination w3-margin-left1 w3-hide-medium w3-xlarge w3-border-black w3-padding-small w3-text-amber">
                <ctg:pagination total="${productCount}" limit="${limit}" command="open_basket"/>
            </div>
        </div>
    </div>
</div>
<%@include file="/pages/partial/footer.jsp" %>
