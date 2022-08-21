<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
    <c:when test="${accounts!=null}">
        <form action="/add-account" method="post" class="" id="">
            <label>Name</label>
            <input type="text" name="name">
            <label>Balance</label>
            <input type="text" name="balance" pattern="\d{1,5}[\.\d{1,5}]?" title="Three letter country code">
            <button type="submit">Add account</button>
        </form>
        ${accounts=null}
    </c:when>
    <c:when test="${payments!=null}">
        <form action="/add-payment" method="post" class="" id="">
            <label>Name</label>
            <input type="text" name="name">
            <label>price</label>
            <input type="text" name="price" pattern="\d{1,5}[\.\d{1,5}]?" title="Three letter country code">
            <button type="submit">Add payment</button>
        </form>
        ${payments=null}
    </c:when>
</c:choose>
