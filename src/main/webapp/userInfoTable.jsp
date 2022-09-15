<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="my" uri="myTags" %>
<%@ taglib prefix="f" tagdir="/WEB-INF/tags" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<c:choose>
    <c:when test="${accounts!=null}">
        <div class="container">
        <p><fmt:message key="label.sortBy"/>
            <a href="accounts?sorted-by=id">
                <fmt:message key="button.number"/>
            </a>
            <a href="accounts?sorted-by=balance">
                <fmt:message key="button.balance"/>
            </a>
            <a href="accounts?sorted-by=name">
                <fmt:message key="button.name"/>
            </a>
        </p>

        <table>
            <tr class="table-header">
                <td class="header__item"><fmt:message key="table.number"/></td>
                <td class="header__item"><fmt:message key="table.balance"/></td>
                <td class="header__item"><fmt:message key="table.name"/></td>
                <td class="header__item"><fmt:message key="table.status"/></td>
                <td class="header__item"><fmt:message key="table.request"/></td>
                <td class="header__item"><fmt:message key="table.requestOrBlock"/></td>
                <td class="header__item"><fmt:message key="table.replenish"/></td>
            </tr>
        <c:forEach var="account" items="${accounts}">
            <my:acc name="${account.getName()}"
                            index="${account.getId()}"
                            balance="${account.getBalance()}"
                            status="${account.getStatus()}"
                            unblockreq="${account.getIsRequestedToUnblock()}"/>
                <td>
                    <c:if test="${paymentId!=null && account.getStatus()=='unblocked'}">
                        <c:choose>
                            <c:when test="${sessionScope.paymentPrice < account.getBalance() && account.getStatus()=='unblocked'}">
                                <a href="make-payment?paymentId=${sessionScope.paymentId}&accountId=${account.getId()}"><fmt:message key="table.makePayment"/></a>
                            </c:when>
<%--                            <c:otherwise>--%>
<%--                                <p><fmt:message key="message.notEnough"/></p>--%>
<%--                            </c:otherwise>--%>
                            <c:otherwise>
                                <p><fmt:message key="message.notEnough"/></p>
                            </c:otherwise>
                        </c:choose>
                    </c:if>
                <c:if test="${account.getStatus()=='unblocked' && paymentId==null}">
                    <a href="block-account?accountId=${account.getId()}"><fmt:message key="execute.blockAccount"/></a>
                </c:if>
                <c:if test="${account.getIsRequestedToUnblock() == 0 && account.getStatus()=='blocked'}">
                    <a href="request-to-unblock?accountId=${account.getId()}"><fmt:message key="execute.requestToUnblockAccount"/></a>
                </c:if>
                </td>
                <td>
                    <form action="replenish" method="post">
                        <input type="text" name="topUpAmount" pattern="\d+\.\d*|\d{1,5}" title="Example : 123 or 123.123">
                        <input name="accountId" type="number" value="${account.getId()}" hidden/>
                        <button type="submit"><fmt:message key="label.replenish"/></button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </table>
        </div>
        <c:if test="${paymentId != null}">
            <a href="discard"><fmt:message key="execute.discard"/></a>
        </c:if>


        <table class="pagination-table">
            <tr>
                <c:if test="${currentPage != 1}">
                    <td><a href="accounts?page=${currentPage - 1}"><fmt:message key="message.previous"/></a></td>
                </c:if>
                <c:forEach begin="1" end="${noOfPages}" var="i">
                    <c:choose>
                        <c:when test="${currentPage eq i}">
                            <td>${i}</td>
                        </c:when>
                        <c:otherwise>
                            <td><a href="accounts?page=${i}">${i}</a></td>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:if test="${currentPage lt noOfPages}">
                    <td><a href="accounts?page=${currentPage + 1}"><fmt:message key="message.next"/></a></td>
                </c:if>
            </tr>
        </table>

    </c:when>
    <c:when test="${payments!=null}">
        <div class="container">

        <p><fmt:message key="label.sortBy"/>
            <a href="payments?sorted-by=id">
                <fmt:message key="button.number"/>
            </a>
            <a href="payments?sorted-by=price">
                <fmt:message key="table.price"/>
            </a>
            <a href="payments?sorted-by=date">
                <fmt:message key="table.date"/>
            </a>
        </p>
        <table>
        <tr class="table-header">
            <td class="header__item"><fmt:message key="table.number"/></td>
            <td class="header__item"><fmt:message key="table.name"/></td>
            <td class="header__item"><fmt:message key="table.price"/></td>
            <td class="header__item"><fmt:message key="table.status"/></td>
            <td class="header__item"><fmt:message key="table.date"/></td>
            <td class="header__item"><fmt:message key="table.makePayment"/></td>
        </tr>
        <c:forEach var="payment" items="${payments}" >
            <f:displayPayment payment="${payment}"/>
            <td>
                <c:if test="${payment.getPaymentStatus()==1}">
                    <a href="accounts?paymentId=${payment.getId()}&paymentPrice=${payment.getPrice()}"><fmt:message key="table.makePayment"/></a>
                </c:if>
            </td>
            </tr>
        </c:forEach>
        </table>
        </div>
        <table class="pagination-table">
            <tr>
                <c:if test="${currentPage != 1}">
                    <td><a href="payments?page=${currentPage - 1}"><fmt:message key="message.previous"/></a></td>
                </c:if>
                <c:forEach begin="1" end="${noOfPages}" var="i">
                    <c:choose>
                        <c:when test="${currentPage eq i}">
                            <td>${i}</td>
                        </c:when>
                        <c:otherwise>
                            <td><a href="payments?page=${i}">${i}</a></td>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:if test="${currentPage lt noOfPages}">
                    <td><a href="payments?page=${currentPage + 1}"><fmt:message key="message.next"/></a></td>
                </c:if>
            </tr>
        </table>
    </c:when>
</c:choose>