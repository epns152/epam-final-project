<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="f" tagdir="/WEB-INF/tags" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html>
<head>
    <title>Title</title>
    <link href="css/styles.css" rel="stylesheet">
</head>
<body>
    <jsp:include page="header.jsp"/>
    <table>
        <tr class="table-header">
            <td class="header__item"><fmt:message key="table.number"/></td>
            <td class="header__item"><fmt:message key="table.role"/></td>
            <td class="header__item"><fmt:message key="table.firstname"/></td>
            <td class="header__item"><fmt:message key="table.lastname"/></td>
            <td class="header__item"><fmt:message key="table.status"/></td>
            <td class="header__item"><fmt:message key="table.registrationDate"/></td>
        </tr>
        <tr>
            <td>
                ${user.getId()}
            </td>
            <td>
                <fmt:message key="role.customer"/>
            </td>
            <td>
                ${user.getFirstname()}
            </td>
            <td>
                ${user.getLastname()}
            </td>
            <td>
                <fmt:message key="status.${user.getStatus()}"/>
            </td>
            <td>
                ${user.getDate()}
            </td>
        </tr>
    </table>
</body>
</html>
