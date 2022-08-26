<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="my" uri="myTags" %>
<%@ taglib prefix="f" tagdir="/WEB-INF/tags" %>


<c:choose>
    <c:when test="${users!=null}">
        <table border=1>
        <tr>
            <td>id</td>
            <td>role</td>
            <td>firstname</td>
            <td>lastname</td>
            <td>status</td>
            <td>registration date</td>
            <td>accounts</td>
            <td>payments</td>
            <td>block/unblock</td>
        </tr>
        <c:forEach var="user" items="${users}" >
            <div class="container">
<%--                <p>${user.toString()}</p>--%>
                <f:displayUserForAdmin user="${user}"/>
                <td><a href="/user-accounts?userId=${user.getId()}">user accounts</a></td>
                <td><a href="/user-payments?userId=${user.getId()}">user payments</a></td>
                <td>
                <c:if test="${user.getStatus()=='unblocked'}">
                    <a href="/block-user?userId=${user.getId()}&status=0">block user</a>
                </c:if>
                <c:if test="${user.getStatus()=='blocked'}">
                    <a href="/block-user?userId=${user.getId()}&status=1">unblock user</a>
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
            <td>id</td>
            <td>name</td>
            <td>price</td>
            <td>status</td>
            <td>date</td>
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
            <td>id</td>
            <td>balance</td>
            <td>name</td>
            <td>status</td>
            <td>request to unblock</td>
            <td>         </td>
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
                    <a href="/a-block-account?accountId=${account.getId()}&status=0">block account</a>
                </c:if>
                <c:if test="${account.getStatus()=='blocked' && account.getIsRequestedToUnblock()==1}">
                    <a href="/a-block-account?accountId=${account.getId()}&status=1">unblock account</a>
                </c:if>
                </td>
                </tr>
            </div>
        </c:forEach>
        </table>
        ${accounts=null}
    </c:when>
</c:choose>
