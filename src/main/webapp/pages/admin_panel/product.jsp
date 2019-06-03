<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@include file="/pages/partial/header.jsp" %>

<fmt:setLocale value="${sessionScope.get('locale')}" scope="session"/>
<fmt:setBundle basename="locale/text" var="rb"/>
<fmt:message bundle="${rb}" key="txt.before.delete.competition" var="txtBeforeDelete"/>
<fmt:message bundle="${rb}" key="txt.yes" var="txtYes"/>
<fmt:message bundle="${rb}" key="txt.symbols" var="txtSymbols"/>
<fmt:message bundle="${rb}" key="txt.all.fields.be.filled" var="txtAllFieldsMustFilled"/>
<fmt:message bundle="${rb}" key="txt.delete.error" var="txtDeleteError"/>
<fmt:message bundle="${rb}" key="txt.try.again" var="txtTryAgain"/>
<fmt:message bundle="${rb}" key="txt.wrong.access" var="txtWrongAccess"/>
<fmt:message bundle="${rb}" key="txt.delete.failed" var="txtDeleteFailed"/>
<fmt:message bundle="${rb}" key="txt.edit.error" var="txtEditError"/>
<fmt:message bundle="${rb}" key="txt.wrong.number.format" var="txtWrongNumberFormat"/>
<fmt:message bundle="${rb}" key="txt.some.kind.mistake" var="txtSomeKindMistake"/>
<fmt:message bundle="${rb}" key="txt.check.input.data" var="txtCheckInputData"/>
<fmt:message bundle="${rb}" key="lbl.or" var="txtOr"/>
<fmt:message bundle="${rb}" key="txt.ok" var="txtOk"/>
<fmt:message bundle="${rb}" key="txt.no" var="txtNo"/>
<fmt:message bundle="${rb}" key="txt.invalid.value" var="txtInvalidValue"/>
<fmt:message bundle="${rb}" key="txt.without" var="txtWithout"/>
<fmt:message bundle="${rb}" key="txt.name.already.exist" var="txtNameAlreadyExist"/>
<fmt:message bundle="${rb}" key="txt.products" var="txtProducts"/>
<fmt:message bundle="${rb}" key="txt.admin" var="txtAdmin"/>
<fmt:message bundle="${rb}" key="txt.userEntity" var="txtUser"/>
<fmt:message bundle="${rb}" key="txt.cash" var="txtCash"/>
<fmt:message bundle="${rb}" key="txt.bonus" var="txtBonus"/>
<fmt:message bundle="${rb}" key="txt.role" var="txtRole"/>
<fmt:message bundle="${rb}" key="txt.weight" var="txtWeight"/>
<fmt:message bundle="${rb}" key="txt.price" var="txtPrice"/>
<fmt:message bundle="${rb}" key="txt.consist" var="txtConsist"/>
<fmt:message bundle="${rb}" key="txt.type" var="txtType"/>
<fmt:message bundle="${rb}" key="txt.select.role" var="txtSelectRole"/>
<fmt:message bundle="${rb}" key="txt.select.action" var="txtSelectAction"/>
<fmt:message bundle="${rb}" key="txt.change.role.wrong" var="txtChangeWrongRole"/>
<fmt:message bundle="${rb}" key="txt.change.lock.wrong" var="txtChangeWrongLock"/>
<fmt:message bundle="${rb}" key="lbl.Name" var="txtName"/>
<fmt:message bundle="${rb}" key="txt.error.check.connection" var="txtErrorCheckConnection"/>
<fmt:message bundle="${rb}" key="txt.before.unlock.user" var="txtBeforeUnlockUser"/>
<fmt:message bundle="${rb}" key="txt.before.block.user" var="txtBeforeBlockUser"/>
<fmt:message bundle="${rb}" key="txt.add.bonuses" var="txtAddBonuses"/>
<fmt:message bundle="${rb}" key="txt.withdraw.bonuses" var="txtWithdrawBonuses"/>
<fmt:message bundle="${rb}" key="txt.enter.amount" var="txtEnterAmount"/>

<div class="w3-container w3-content main-container">
    <div class="w3-col m3 w3-margin-top">
        <br>
        <%@include file="/pages/admin_panel/left_bar.jsp" %>
    </div>
    <div class="w3-col m9">

        <!-- First Photo Grid-->
        <div class="w3-row-padding">
            <div class="w3-container w3-xlarge">
                ${txtProducts}
            </div>

            <div class="w3-col s12">
                <div class="w3-container w3-padding-small w3-card-2 w3-center  backgraund-opacity">
                    <c:forEach var="productEntity" items="${productList}">
                        <div id='${productEntity.id}' class="w3-container w3-margin-top">
                            <div class="w3-row w3-card-2 w3-display-container">
                                    <div class="w3-display-topright w3-display-hover w3-small">
                                            <button class="w3-button w3-black w3-padding-small"
                                                    onclick="(modal_change_role${productEntity.id}).style.display='block'">
                                                <i class="fa fa-pencil-square" aria-hidden="true"></i>
                                            </button>
                                            <button class="w3-button w3-black w3-padding-small"
                                                    onclick="(modal_delete${productEntity.id}).style.display='block'">
                                                <i class="fa fa-window-close" aria-hidden="true"></i>
                                            </button>
                                    </div>


                                <div id="modal_change_lock${productEntity.id}" class="w3-modal w3-center">
                                    <div class="w3-modal-content">
                                        <div class="w3-container">
                                            <span onclick="(modal_change_lock${productEntity.id}).style.display='none'"
                                                  class="w3-button w3-display-topright">
                                                &times;
                                            </span>
                                            <form id="lockForm${productEntity.id}" method="post" action="/ajaxController"
                                                  onsubmit="return false">
                                                <input type="hidden" name="command" value="CHANGE_USER_LOCK">
                                                <input type="hidden" name="userId" value="${productEntity.id}">
                                            </form>
                                            <div class="w3-row">
                                                <div class="w3-half">
                                                    <input onclick="
                                                            changeLock(this,'lockForm${productEntity.id}', 'modal_change_lock${productEntity.id}');"
                                                           type="button"
                                                           class="w3-button" value="${txtYes}">
                                                </div>
                                                <div class="w3-half">
                                                    <input type="button" class="w3-button" value="${txtNo}"
                                                           onclick="(modal_change_lock${productEntity.id}).style.display='none'">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div id="modal_delete${productEntity.id}" class="w3-modal">
                                    <div class="w3-modal-content">
                                        <div class="w3-container">
                                                        <span onclick="(modal_delete${productEntity.id}).style.display='none'"
                                                              class="w3-button w3-display-topright">&times;</span>
                                            <p>${txtBeforeDelete}</p>
                                            <div class="w3-row">
                                                <div class="w3-half">
                                                    <input onclick="del(this, '${productEntity.id}', '${productEntity.imageURL}')"
                                                           type="button" class="w3-button"
                                                           value="${txtYes}">
                                                </div>
                                                <div class="w3-half">
                                                    <input type="button" class="w3-button" value="${txtNo}"
                                                           onclick="(modal_delete${productEntity.id}).style.display='none'">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>


                                <div class="w3-col s4">
                                    <div class=" w3-padding">
                                        <img src="/image/products/${productEntity.imageURL}" alt="${productEntity.name}"
                                             class="w3-image w3-circle" style="width: auto; height: 80px;">
                                    </div>
                                </div>
                                <div class="w3-col s8">
                                    <div class="w3-row">
                                        <div class="w3-col s12 w3-left-align w3-small ">
                                            <div style="margin: 4px">
                                                <i>${txtName}: </i>
                                                <b> <c:out value="${productEntity.name}"/> </b>
                                                <i>${txtPrice}: </i>
                                                <b> <ctg:decimal-presenter number="${productEntity.price}"/>$ </b>
                                            </div>
                                            <div style="margin: 4px">
                                                <i>${txtType}: </i>
                                                <b> <c:out value="${productEntity.productType}"/></b>
                                            </div>
                                            <div style="margin: 4px">
                                                <i>${txtConsist}: </i>
                                                <b> <c:out value="${productEntity.ingredients}"/> </b>
                                            </div>
                                            <div style="margin: 4px">
                                                <i>${txtWeight}: </i>
                                                <b> <c:out value="${productEntity.weight}"/> </b>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <div class="pagination  w3-margin-top w3-row w3-container w3-center">
                    <ctg:pagination total="${productsCount}" limit="${limit}" command="open_product_settings"/>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="modal_delete_error" class="w3-modal">
    <div class="w3-modal-content">
        <div class="w3-container">
            <span onclick="(modal_delete_error).style.display='none'" class="w3-button w3-display-topright">&times;</span>
            <p>${txtErrorCheckConnection}</p>
            <input type="button" class="w3-button" value="${txtOk}" onclick="(modal_delete_error).style.display='none'">
        </div>
    </div>
</div>
<div id="modal_delete_wrong" class="w3-modal">
    <div class="w3-modal-content">
        <div class="w3-container">
            <span onclick="(modal_delete_wrong).style.display='none'" class="w3-button w3-display-topright">&times;</span>
            <p>${txtChangeWrongLock}</p>
            <input type="button" class="w3-button" value="${txtOk}" onclick="(modal_delete_wrong).style.display='none'">
        </div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/js/product.js"></script>
<link rel="stylesheet" type="text/css"
      href="${pageContext.request.contextPath}/vendors/jquery.imgareaselect-0.9.10/css/imgareaselect-default.css"/>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/vendors/jquery.imgareaselect-0.9.10/scripts/jquery.min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/vendors/jquery.imgareaselect-0.9.10/scripts/jquery.imgareaselect.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/vendors/jquery.imgareaselect-0.9.10/scripts/jquery.imgareaselect.min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/vendors/jquery.imgareaselect-0.9.10/scripts/jquery.imgareaselect.pack.js"></script>
<script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"></script>
<script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/additional-methods.min.js"></script>

<%@include file="/pages/partial/footer.jsp" %>



