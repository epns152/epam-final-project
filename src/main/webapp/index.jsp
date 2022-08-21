<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
        <label>Login</label>
        <input type="text" name="login">
        <label>Password</label>
        <input type="password" name="password">
        <button type="submit">Log In</button>
    </form>
<%--    <a id="closeButtonSign" class="header" onclick="closeForm('signIn')">X</a>--%>
</div>
<div class="hidden">
    <form action="/register" method="post" class="openForm" id="register">
        <label>Firstname</label>
        <input type="text" name="firstname">
        <label>Lastname</label>
        <input type="text" name="lastname">

        <label>Login</label>
        <input type="text" name="login">
        <label>Password</label>
        <input type="password" name="password">

        <button type="submit">Sign In</button>
    </form>
<%--    <a id="closeButtonReg" class="header" onclick="closeForm('register')">X</a>--%>
</div>
<%--<div class="bgImage" style="z-index: -1"></div>--%>
</body>

</html>