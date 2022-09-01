<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <link href="css/styles.css" rel="stylesheet">
</head>
<body>
<jsp:include page="header.jsp"/>
<c:if test="${user==null}">
<div class="hidden">
    <form action="login" method="post" class="openForm" id="signIn">
        <label><fmt:message key="label.login"/></label>
        <input type="text" name="login" pattern="(?=.{3,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9_]+(?<![_.])"
               title="From 3 to 20 characters without any _ or . e.g. asdf or asdf_qwerty1">
        <label><fmt:message key="label.password"/></label>
        <input type="password" name="password">
        <button type="submit"><fmt:message key="button.login"/></button>
    </form>
<%--    <a id="closeButtonSign" class="header" onclick="closeForm('signIn')">X</a>--%>
</div>
<div class="hidden">
    <form action="register" method="post" class="openForm" id="register">
        <label><fmt:message key="label.firstname"/></label>
        <input type="text" name="firstname" pattern="(?=.{3,20}$)(?![_.])(?!.*[_.]{2})[A-ZА-ЯІЄЇ]{1}[a-zа-яіїє]+(?<![_.])"
               title="From 3 to 20 characters without any _ or . e.g. Bob or Андрій">
        <label><fmt:message key="label.lastname"/></label>
        <input type="text" name="lastname" pattern="(?=.{3,20}$)(?![_.])(?!.*[_.]{2})[A-ZА-ЯІЄЇ]{1}[a-zа-яіїє]+(?<![_.])"
               title="From 3 to 20 characters without any _ or . e.g. Bob or Андрій">

        <label><fmt:message key="label.login"/></label>
        <input type="text" name="login" pattern="(?=.{3,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9_]+(?<![_.])"
               title="From 3 to 20 characters without any _ or . e.g. asdf or asdf_qwerty1">
        <label><fmt:message key="label.password"/></label>
        <input type="password" name="password">

        <button type="submit"><fmt:message key="button.signIn"/></button>
    </form>
<%--    <a id="closeButtonReg" class="header" onclick="closeForm('register')">X</a>--%>
</div>
<%--<div class="bgImage" style="z-index: -1"></div>--%>
</body>
</c:if>
</html>