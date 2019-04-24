<%@include file="/pages/partial/header.jsp" %>
<script src="../js/button.js"></script>
<fmt:message bundle="${rb}" key="txt.menu.category" var="txtMenuCategory"/>

<body>
    <table class="products table">
        <thead>
        <div class="w3-col s4 w3-padding-small">
            <label>
                ${txtMenuCategory}
                <select id="selectKind" required name="sportId" class="w3-select">
                    <option value='' selected disabled><c:out value="${txtChooseSport}"/></option>
                    <c:forEach var="kind" items="${kindsOfSport}">
                        <option value="${kind.id}" count="${kind.competitorCount}">
                            <c:out value="${kind.name}"/>
                        </option>
                    </c:forEach>
                </select>
            </label>
        </div>
        </thead>
        <tbody>
        <tr>
            <td></td>
            <td>
                <img src="../images/dishes/borsch.jpg" height="70">
            </td>
            <td>
                <span>Борщ</span>
                <span>145</span>
            </td>
            <td>
                <span>Свекла, Вода, Соль, Сахар, Говядина</span>
                <span>500г.</span>
            </td>
            <td>
                <div class="btn-group mr-2 pod" id="pod1" role="group" aria-label="First group">
                    <button type="button" class="btn btn-secondary basket" id="basket1" onclick="clickBasket(1)">В корзину</button>
                </div>
            </td>
        </tr>
        <tr>
            <td></td>
            <td>
                <img src="../images/dishes/borsch.jpg" height="70">
            </td>
            <td>
                <span>Борщ</span>
                <span>145</span>
            </td>
            <td>
                <span>Свекла, Вода, Соль, Сахар, Говядина</span>
                <span>500г.</span>
            </td>
            <td>
                <div class="btn-group mr-2 pod" id="pod2" role="group" aria-label="First group">
                    <button type="button" class="btn btn-secondary basket" id="basket2" onclick="clickBasket(2)">В корзину</button>
                </div>
            </td>
        </tr>
        <tr>
            <td></td>
            <td>
                <img src="../images/dishes/borsch.jpg" height="70">
            </td>
            <td>
                <span>Борщ</span>
                <span>145</span>
            </td>
            <td>
                <span>Свекла, Вода, Соль, Сахар, Говядина</span>
                <span>500г.</span>
            </td>
            <td>
                <div class="btn-group mr-2 pod" id="pod3" role="group" aria-label="First group">
                    <button type="button" class="btn btn-secondary basket" id="basket3" onclick="clickBasket(3)">В корзину</button>
                </div>
            </td>
        </tr>
        <tr>
            <td></td>
            <td>
                <img src="../images/dishes/borsch.jpg" height="70">
            </td>
            <td>
                <span>Борщ</span>
                <span>145</span>
            </td>
            <td>
                <span>Свекла, Вода, Соль, Сахар, Говядина</span>
                <span>500г.</span>
            </td>
            <td>
                <div class="btn-group mr-2 pod" id="pod4" role="group" aria-label="First group">
                    <button type="button" class="btn btn-secondary basket" id="basket4" onclick="clickBasket(4)">В корзину</button>
                </div>
            </td>
        </tr>
        <tr>
            <td></td>
            <td>
                <img src="../images/dishes/borsch.jpg" height="70">
            </td>
            <td>
                <span>Борщ</span>
                <span>145</span>
            </td>
            <td>
                <span>Свекла, Вода, Соль, Сахар, Говядина</span>
                <span>500г.</span>
            </td>
            <td>
                <div class="btn-group mr-2 pod" id="pod5" role="group" aria-label="First group">
                    <button type="button" class="btn btn-secondary basket" id="basket5" onclick="clickBasket(5)">В корзину</button>
                </div>
            </td>
        </tr>
        </tbody>
    </table>
</body>
<%@include file="/pages/partial/footer.jsp" %>
