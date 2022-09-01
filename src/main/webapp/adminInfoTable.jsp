<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="my" uri="myTags" %>
<%@ taglib prefix="f" tagdir="/WEB-INF/tags" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<c:choose>
    <c:when test="${users!=null}">
        <div class="container">
        <table>
        <tr class="table-header">
            <td class="header__item"><fmt:message key="table.number"/></td>
            <td class="header__item"><fmt:message key="table.role"/></td>
            <td class="header__item"><fmt:message key="table.firstname"/></td>
            <td class="header__item"><fmt:message key="table.lastname"/></td>
            <td class="header__item"><fmt:message key="table.status"/></td>
            <td class="header__item"><fmt:message key="table.registrationDate"/></td>
            <td class="header__item"><fmt:message key="table.accounts"/></td>
            <td class="header__item"><fmt:message key="table.payments"/></td>
            <td class="header__item"><fmt:message key="table.blockUnblock"/></td>
        </tr>
        <c:forEach var="user" items="${users}" >
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

        </c:forEach>
        </table>
        </div>


        <table class="pagination-table">
            <tr>
                <c:if test="${currentPage != 1}">
                    <td><a href="users?page=${currentPage - 1}"><fmt:message key="message.previous"/></a></td>
                </c:if>
                <c:forEach begin="1" end="${noOfPages}" var="i">
                    <c:choose>
                        <c:when test="${currentPage eq i}">
                            <td>${i}</td>
                        </c:when>
                        <c:otherwise>
                            <td><a href="users?page=${i}">${i}</a></td>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:if test="${currentPage lt noOfPages}">
                    <td><a href="users?page=${currentPage + 1}"><fmt:message key="message.next"/></a></td>
                </c:if>
            </tr>
        </table>
        ${users=null}
    </c:when>


    <c:when test="${payments!=null}">
        <div class="container">
        <table>
        <tr class="table-header">
            <td class="header__item"><fmt:message key="table.number"/></td>
            <td class="header__item"><fmt:message key="table.name"/></td>
            <td class="header__item"><fmt:message key="table.price"/></td>
            <td class="header__item"><fmt:message key="table.status"/></td>
            <td class="header__item"><fmt:message key="table.date"/></td>
        </tr>

        <c:forEach var="payment" items="${payments}" >
            <div class="container">
                <f:displayPayment payment="${payment}"/>
                </tr>
            </div>
        </c:forEach>
        </table>
        </div>

        <table class="pagination-table">
            <tr>
                <c:if test="${currentPage != 1}">
                    <td><a href="user-payments?page=${currentPage - 1}"><fmt:message key="message.previous"/></a></td>
                </c:if>
                <c:forEach begin="1" end="${noOfPages}" var="i">
                    <c:choose>
                        <c:when test="${currentPage eq i}">
                            <td>${i}</td>
                        </c:when>
                        <c:otherwise>
                            <td><a href="user-payments?page=${i}">${i}</a></td>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:if test="${currentPage lt noOfPages}">
                    <td><a href="user-payments?page=${currentPage + 1}"><fmt:message key="message.next"/></a></td>
                </c:if>
            </tr>
        </table>
        ${payments=null}
    </c:when>

    <c:when test="${accounts!=null}">
        <div class="container">
        <table>
        <tr class="table-header">
            <td class="header__item"><fmt:message key="table.number"/></td>
            <td class="header__item"><fmt:message key="table.balance"/></td>
            <td class="header__item"><fmt:message key="table.name"/></td>
            <td class="header__item"><fmt:message key="table.status"/></td>
            <td class="header__item"><fmt:message key="table.request"/></td>
            <td class="header__item"><fmt:message key="table.blockUnblock"/></td>
        </tr>
        <c:forEach var="account" items="${accounts}" >

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

        </c:forEach>
        </table>
        </div>
        <table class="pagination-table">
            <tr>
                <c:if test="${currentPage != 1}">
                    <td><a href="user-accounts?page=${currentPage - 1}"><fmt:message key="message.previous"/></a></td>
                </c:if>
                <c:forEach begin="1" end="${noOfPages}" var="i">
                    <c:choose>
                        <c:when test="${currentPage eq i}">
                            <td>${i}</td>
                        </c:when>
                        <c:otherwise>
                            <td><a href="user-accounts?page=${i}">${i}</a></td>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:if test="${currentPage lt noOfPages}">
                    <td><a href="user-accounts?page=${currentPage + 1}"><fmt:message key="message.next"/></a></td>
                </c:if>
            </tr>
        </table>
        ${accounts=null}
    </c:when>
</c:choose>
