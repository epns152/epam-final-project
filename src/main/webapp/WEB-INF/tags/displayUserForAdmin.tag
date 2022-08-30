<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute
        name="user"
        type="com.pavlenko.payments.model.entity.User"
        required="true"
        rtexprvalue="true"
%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

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