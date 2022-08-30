<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute
        name="payment"
        type="com.pavlenko.payments.model.entity.Payment"
        required="true"
        rtexprvalue="true"
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

    <tr>
        <td>
            ${payment.getId()}
        </td>
        <td>
            ${payment.getName()}
        </td>
        <td>
            ${payment.getPrice()}
        </td>
        <td>
            <c:choose>
                <c:when test="${payment.getPaymentStatus() == 0}">
                    <fmt:message key="status.sent"/>
                </c:when>
                <c:otherwise>
                    <fmt:message key="status.prepared"/>
                </c:otherwise>
            </c:choose>
        </td>
        <td>
            ${payment.getDate()}
        </td>

