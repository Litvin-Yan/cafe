<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<jsp:include page="/pages/partial/header.jsp"/>
<div class="w3-row">
    <div class="w3-third">
        <div class="w3-container"></div>
    </div>
    <div class="w3-third">
        <h1>404 Page not found :( </h1>
        <h2><a class=" w3-button w3-hover-none w3-border-white w3-hover-border-black"
               href="${pageContext.request.contextPath}/index.jsp">Back on main pageNumber</a>
        </h2>
    </div>
    <div class="w3-third">
        <div class="w3-container"></div>
    </div>
</div>
<jsp:include page="/pages/partial/footer.jsp"/>
