<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="css/style1.css" rel="stylesheet">
<div class="header">
    <a href="/" class="logo">Payments</a>
    <c:choose>
        <c:when test="${logged==false}">
            <div class="header-right">
                <a class="active" href="/">Home</a>
                <a onclick="showForm('signIn')">Sign In</a>
                <a onclick="showForm('register')">Register</a>
            </div>
        </c:when>
        <c:when test="${user.getRole()=='admin'}">
            <div>
                <a href="/users">Users</a>
                <a href="/accounts-to-unblock">Accounts to unblock</a>
                <a href="/sign-out">SignOut</a>
            </div>
        </c:when>
        <c:otherwise>
            <div class="header-right">
                <a <c:if test="${active==1}">class="active"</c:if> href="/index.jsp">Home</a>
                <a <c:if test="${active==2}">class="active"</c:if>  href="/accounts">My accounts</a>
                <a <c:if test="${active==3}">class="active"</c:if> href="/payments">My payments</a>
                <a <c:if test="${active==4}">class="active"</c:if> href="/profile">Profile</a>
                <a href="/sign-out">SignOut</a>
            </div>
        </c:otherwise>
    </c:choose>
</div>
