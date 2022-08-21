<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
    <c:when test="${users!=null}">
        <c:forEach var="user" items="${users}" >
            <div class="container">
                <p>${user.toString()}</p>
                <c:if test="${user.getStatus()=='unblocked'}">
                    <a href="/block-user?userId=${user.getId()}&status=0">block user</a>
                </c:if>
                <c:if test="${user.getStatus()=='blocked'}">
                    <a href="/block-user?userId=${user.getId()}&status=1">unblock user</a>
                </c:if>
                <a href="/user-accounts?userId=${user.getId()}">user accounts</a>
                <a href="/user-payments?userId=${user.getId()}">user payments</a>
            </div>
        </c:forEach>
        ${users=null}
    </c:when>
    <c:when test="${payments!=null}">
        <c:forEach var="payment" items="${payments}" >
            <div class="container">
                <p>${payment.toString()}</p>
            </div>
        </c:forEach>
        ${payments=null}
    </c:when>
    <c:when test="${accounts!=null}">
        <c:forEach var="account" items="${accounts}" >
            <div class="container">
                <p>${account.toString()}</p>
                <c:if test="${account.getStatus()=='unblocked'}">
                    <a href="/a-block-account?accountId=${account.getId()}&status=0">block account</a>
                </c:if>
                <c:if test="${account.getStatus()=='blocked' && account.getIsRequestedToUnblock()==1}">
                    <a href="/a-block-account?accountId=${account.getId()}&status=1">unblock account</a>
                </c:if>
            </div>
        </c:forEach>
        ${accounts=null}
    </c:when>
</c:choose>
