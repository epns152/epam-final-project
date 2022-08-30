<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
</head>
<body>
${sessionScope.logged}
<hr>
${sessionScope.user}
<jsp:include page="header.jsp"/>
<div class="hidden">
    <form action="/login" method="post" class="openForm" id="signIn">
        <label><fmt:message key="label.login"/></label>
        <input type="text" name="login">
        <label><fmt:message key="label.password"/></label>
        <input type="password" name="password">
        <button type="submit"><fmt:message key="button.login"/></button>
    </form>
<%--    <a id="closeButtonSign" class="header" onclick="closeForm('signIn')">X</a>--%>
</div>
<div class="hidden">
    <form action="/register" method="post" class="openForm" id="register">
        <label><fmt:message key="label.firstname"/></label>
        <input type="text" name="firstname">
        <label><fmt:message key="label.lastname"/></label>
        <input type="text" name="lastname">

        <label><fmt:message key="label.login"/></label>
        <input type="text" name="login">
        <label><fmt:message key="label.password"/></label>
        <input type="password" name="password">

        <button type="submit"><fmt:message key="button.signIn"/></button>
    </form>
<%--    <a id="closeButtonReg" class="header" onclick="closeForm('register')">X</a>--%>
</div>
<%--<div class="bgImage" style="z-index: -1"></div>--%>
</body>

</html>