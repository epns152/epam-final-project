<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<c:choose>
    <c:when test="${accounts!=null}">
        <form action="/add-account" method="post" class="" id="">
            <label><fmt:message key="label.name"/></label>
            <input type="text" name="name">
            <label><fmt:message key="label.balance"/></label>
            <input type="text" name="balance" pattern="\d{1,5}[\.\d{1,5}]?" title="Three letter country code">
            <button type="submit"><fmt:message key="button.addAccount"/></button>
        </form>
        ${accounts=null}
    </c:when>
    <c:when test="${payments!=null}">
        <form action="/add-payment" method="post" class="" id="">
            <label><fmt:message key="label.name"/></label>
            <input type="text" name="name">
            <label><fmt:message key="label.Price"/></label>
            <input type="text" name="price" pattern="\d{1,5}[\.\d{1,5}]?" title="Three letter country code">
            <button type="submit"><fmt:message key="button.addPayment"/></button>
        </form>
        ${payments=null}
    </c:when>
</c:choose>
