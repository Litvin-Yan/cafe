<%@include file="/pages/partial/header.jsp" %>

<fmt:message bundle="${rb}" key="txt.comments" var="txtComments"/>
<br>

<table class="products w3-table w3-centered">
    <thead>
    <span class="w3-xlarge">${txtComments}</span>
    </thead>
    <tbody>
    <c:forEach var="commentData" items="${commentDataList}">
        <tr class="w3-large backgraund-opacity">
            <td>
                <span><img src="../image/avatar/${commentData.key.avatarURL}" height="70"></span>
                <span>${commentData.key.name}</span>
            </td>
            <td>
                <span>${commentData.value.rate}</span>
                <span><ctg:date-presenter date="${commentData.value.postDate}"/></span>
            </td>
            <td>
                <span>${commentData.value.text}</span>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pagination w3-margin-left1 w3-hide-medium w3-xlarge w3-border-black w3-padding-small w3-text-amber">
    <ctg:pagination total="${commentCount}" limit="${limit}" command="open_comment"/>
</div>
<%@include file="/pages/partial/footer.jsp" %>
