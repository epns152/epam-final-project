<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<div class="container">
    <c:choose>
        <c:when test="${accounts!=null}">
            <form action="/add-account" method="post" class="" id="">
                <label><fmt:message key="label.name"/></label>
                <input type="text" name="name" pattern="(?=.{8,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9а-яіїєА-ЯІЇЄ ]+(?<![_.])"
                       title="From 8 to 20 characters without any _ or . e.g. asdfasdf or фіва фіва">
                <label><fmt:message key="label.balance"/></label>
                <input type="text" name="balance" pattern="\d+\.\d*|\d{1,5}" title="Example : 123 or 123.123">
                <button type="submit"><fmt:message key="button.addAccount"/></button>
            </form>
            ${accounts=null}
        </c:when>
        <c:when test="${payments!=null}">
            <form action="/add-payment" method="post" class="" id="">
                <label><fmt:message key="label.name"/></label>
                <input type="text" name="name" pattern="(?=.{8,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9а-яіїєА-ЯІЇЄ ]+(?<![_.])"
                       title="From 8 to 20 characters without any _ or . e.g. asdfasdf or фіва фіва">
                <label><fmt:message key="label.Price"/></label>
                <input type="text" name="price" pattern="\d+\.\d*|\d{1,5}" title="Example : 123 or 123.123">
                <button type="submit"><fmt:message key="button.addPayment"/></button>
            </form>
            ${payments=null}
        </c:when>
    </c:choose>
</div>
