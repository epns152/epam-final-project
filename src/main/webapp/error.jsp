<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html>
<head>
    <title>Title</title>
</head>
<body>
<p>${requestScope['javax.servlet.error.message']}</p>
<a href="index.jsp"><fmt:message key="message.toMain"/></a>
</body>
</html>
