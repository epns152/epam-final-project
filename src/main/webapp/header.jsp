<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="my" uri="myTags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<link href="css/style1.css" rel="stylesheet">--%>
<div class="container">
    <a href="/" class="logo">Payments</a>
<%--    <div class="container">--%>
    <c:choose>
        <c:when test="${user==null}">
            <div class="header-right">
                <a class="active" href="/"><fmt:message key="label.home" /></a>
                <a onclick="showForm('login')"><fmt:message key="button.login"/></a>
                <a onclick="showForm('register')"><fmt:message key="button.signIn"/></a>
                <a href="lang?lang=en&current=${requestScope['javax.servlet.forward.request_uri']}">EN</a>
                <a href="lang?lang=ua&current=${requestScope['javax.servlet.forward.request_uri']}">UA</a>
            </div>
        </c:when>

        <c:when test="${user.getRole()=='admin'}">
            <div>
                <a href="users"><fmt:message key="label.users"/></a>
                <a href="accounts-to-unblock"><fmt:message key="label.accountsToUnblock"/></a>
                <a href="sign-out"><fmt:message key="label.signOut"/></a>
                <a href="lang?lang=en&current=${requestScope['javax.servlet.forward.request_uri']}">EN</a>
                <a href="lang?lang=ua&current=${requestScope['javax.servlet.forward.request_uri']}">UA</a>
            </div>
        </c:when>
        <c:when test="${user.getRole()=='customer'}">
            <div class="header-right">
                <a href="index.jsp"><fmt:message key="label.home"/></a>
                <a href="accounts?sorted-by=id"><fmt:message key="label.myAccounts"/></a>
                <a href="payments?sorted-by=id"><fmt:message key="label.myPayments"/></a>
                <a href="profile"><fmt:message key="label.profile"/></a>
                <a href="sign-out"><fmt:message key="label.signOut"/></a>
                <a href="lang?lang=en&current=${requestScope['javax.servlet.forward.request_uri']}">EN</a>
                <a href="lang?lang=ua&current=${requestScope['javax.servlet.forward.request_uri']}">UA</a>
            </div>
        </c:when>
    </c:choose>
<%--    </div>--%>
</div>
