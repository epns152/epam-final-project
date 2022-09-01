<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="container">
    <p>${requestScope['javax.servlet.error.message']}</p>
    <c:if test="${requestScope['javax.servlet.error.message']==''}">
        <p><fmt:message key="error.unknown"/></p>
    </c:if>
    <a href="index.jsp"><fmt:message key="message.toMain"/></a>
</div>
</body>
</html>
