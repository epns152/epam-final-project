<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="my" uri="myTags" %>
<%@ taglib prefix="f" tagdir="/WEB-INF/tags" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<c:choose>
    <c:when test="${users!=null}">
        <table border=1>
        <tr>
            <td><fmt:message key="table.number"/></td>
            <td><fmt:message key="table.role"/></td>
            <td><fmt:message key="table.firstname"/></td>
            <td><fmt:message key="table.lastname"/></td>
            <td><fmt:message key="table.status"/></td>
            <td><fmt:message key="table.registrationDate"/></td>
            <td><fmt:message key="table.accounts"/></td>
            <td><fmt:message key="table.payments"/></td>
            <td><fmt:message key="table.blockUnblock"/></td>
        </tr>
        <c:forEach var="user" items="${users}" >
            <div class="container">
                <f:displayUserForAdmin user="${user}"/>
                <td><a href="/user-accounts?userId=${user.getId()}"><fmt:message key="table.userAccounts"/></a></td>
                <td><a href="/user-payments?userId=${user.getId()}"><fmt:message key="table.userPayments"/></a></td>
                <td>
                <c:if test="${user.getStatus()=='unblocked'}">
                    <a href="/block-user?userId=${user.getId()}&status=0"><fmt:message key="execute.blockUser"/></a>
                </c:if>
                <c:if test="${user.getStatus()=='blocked'}">
                    <a href="/block-user?userId=${user.getId()}&status=1"><fmt:message key="execute.unblockUser"/></a>
                </c:if>
                </td>
                </tr>
            </div>
        </c:forEach>
        </table>
        ${users=null}
    </c:when>
    <c:when test="${payments!=null}">
        <table border=1>
        <tr>
            <td><fmt:message key="table.number"/></td>
            <td><fmt:message key="table.name"/></td>
            <td><fmt:message key="table.price"/></td>
            <td><fmt:message key="table.status"/></td>
            <td><fmt:message key="table.date"/></td>
        </tr>
        <c:forEach var="payment" items="${payments}" >
            <div class="container">
                <f:displayPayment payment="${payment}"/>
                </tr>
            </div>
        </c:forEach>
        </table>
        ${payments=null}
    </c:when>
    <c:when test="${accounts!=null}">
        <table border=1>
        <tr>
            <td><fmt:message key="table.number"/></td>
            <td><fmt:message key="table.balance"/></td>
            <td><fmt:message key="table.name"/></td>
            <td><fmt:message key="table.status"/></td>
            <td><fmt:message key="table.request"/></td>
            <td><fmt:message key="table.blockUnblock"/></td>
        </tr>
        <c:forEach var="account" items="${accounts}" >
            <div class="container">
                <my:acc name="${account.getName()}"
                        index="${account.getId()}"
                        balance="${account.getBalance()}"
                        status="${account.getStatus()}"
                        unblockreq="${account.getIsRequestedToUnblock()}"/>
                <td>
                <c:if test="${account.getStatus()=='unblocked'}">
                    <a href="/a-block-account?accountId=${account.getId()}&status=0"><fmt:message key="execute.blockAccount"/></a>
                </c:if>
                <c:if test="${account.getStatus()=='blocked' && account.getIsRequestedToUnblock()==1}">
                    <a href="/a-block-account?accountId=${account.getId()}&status=1"><fmt:message key="execute.unblockAccount"/></a>
                </c:if>
                </td>
                </tr>
            </div>
        </c:forEach>
        </table>
        ${accounts=null}
    </c:when>
</c:choose>
