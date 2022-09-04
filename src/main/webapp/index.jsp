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
    <script>
        function showForm(elementId) {
            document.getElementById('login').parentElement.style.display = 'none';
            document.getElementById('register').parentElement.style.display = 'none';
            document.getElementById(elementId).parentElement.style.display = 'block';
        }

        function closeForm(elementId) {
            document.getElementById(elementId).parentElement.style.display = 'none';
        }
    </script>
</head>
<body>
<jsp:include page="header.jsp"/>
<c:if test="${user==null}">
<div class="hidden">
    <form action="login" method="post" class="openForm" id="login">
        <div>
            <input type="text" name="login" pattern="(?=.{3,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9_]+(?<![_.])"
                   title="From 3 to 20 characters without any _ or . e.g. asdf or asdf_qwerty1">
            <label><fmt:message key="label.login"/></label>
        </div>
        <div>
            <input type="password" name="password">
            <label><fmt:message key="label.password"/></label>
        </div>
        <button type="submit" class="form-button"><fmt:message key="button.login"/></button>
    </form>
</div>
<div class="hidden">
    <form action="register" method="post" class="openForm" id="register">
        <div>
            <input type="text" name="firstname" pattern="(?=.{3,20}$)(?![_.])(?!.*[_.]{2})[A-ZА-ЯІЄЇ]{1}[a-zа-яіїє]+(?<![_.])"
                   title="From 3 to 20 characters without any _ or . e.g. Bob or Андрій">
            <label style="width: 100%"><fmt:message key="label.firstname"/></label>
        </div>
        <div>
            <input type="text" name="lastname" pattern="(?=.{3,20}$)(?![_.])(?!.*[_.]{2})[A-ZА-ЯІЄЇ]{1}[a-zа-яіїє]+(?<![_.])"
                   title="From 3 to 20 characters without any _ or . e.g. Parker or Іванов">
            <label><fmt:message key="label.lastname"/></label>

        </div>
        <div>
            <input type="text" name="login" pattern="(?=.{3,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9_]+(?<![_.])"
                   title="From 3 to 20 characters without any _ or . e.g. asdf or asdf_qwerty1">
            <label><fmt:message key="label.login"/></label>
        </div>
        <div>
            <input type="password" name="password">
            <label><fmt:message key="label.password"/></label>
        </div>

        <button type="submit" class="form-button"><fmt:message key="button.signIn"/></button>
    </form>
</div>
</body>
</c:if>
</html>