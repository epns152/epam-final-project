<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="my" uri="myTags" %>
<%@ taglib prefix="f" tagdir="/WEB-INF/tags" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<c:choose>
    <c:when test="${accounts!=null}">
        <p><fmt:message key="label.sortBy"/>
            <a href="/accounts?sorted-by=id">
                <fmt:message key="button.number"/>
            </a>
            <a href="/accounts?sorted-by=balance">
                <fmt:message key="button.balance"/>
            </a>
            <a href="/accounts?sorted-by=name">
                <fmt:message key="button.name"/>
            </a>
        </p>
        <table border=1>
            <tr>
                <td><fmt:message key="table.number"/></td>
                <td><fmt:message key="table.balance"/></td>
                <td><fmt:message key="table.name"/></td>
                <td><fmt:message key="table.status"/></td>
                <td><fmt:message key="table.request"/></td>
                <td><fmt:message key="table.requestOrBlock"/></td>
            </tr>
        <c:forEach var="account" items="${accounts}">
            <div class="container">
                <my:acc name="${account.getName()}"
                                index="${account.getId()}"
                                balance="${account.getBalance()}"
                                status="${account.getStatus()}"
                                unblockreq="${account.getIsRequestedToUnblock()}"/>
                    <td>
                        <c:if test="${paymentId!=null && account.getStatus()=='unblocked'}">
                            <c:choose>
                                <c:when test="${paymentPrice < account.getBalance()}">
                                    <a href="/make-payment?paymentId=${paymentId}&accountId=${account.getId()}"><fmt:message key="table.makePayment"/></a>
                                </c:when>
                                <c:otherwise>
                                    <p><fmt:message key="message.notEnough"/></p>
                                </c:otherwise>
                            </c:choose>
                        </c:if>
                    <c:if test="${account.getStatus()=='unblocked' && paymentId==null}">
                        <a href="/block-account?accountId=${account.getId()}"><fmt:message key="execute.blockAccount"/></a>
                    </c:if>
                    <c:if test="${account.getIsRequestedToUnblock() == 0 && account.getStatus()=='blocked'}">
                        <a href="/request-to-unblock?accountId=${account.getId()}"><fmt:message key="execute.requestToUnblockAccount"/></a>
                    </c:if>
                    </td>
                </tr>
            </div>
        </c:forEach>
        </table>
        ${paymentId=null}
    </c:when>
    <c:when test="${payments!=null}">
        <p><fmt:message key="label.sortBy"/>
            <a href="/payments?sorted-by=id">
                <fmt:message key="button.number"/>
            </a>
            <a href="/payments?sorted-by=price">
                <fmt:message key="table.price"/>
            </a>
            <a href="/payments?sorted-by=date">
                <fmt:message key="table.date"/>
            </a>
        </p>
        <table border=1>
        <tr>
            <td><fmt:message key="table.number"/></td>
            <td><fmt:message key="table.name"/></td>
            <td><fmt:message key="table.price"/></td>
            <td><fmt:message key="table.status"/></td>
            <td><fmt:message key="table.date"/></td>
            <td>         </td>
        </tr>
        <c:forEach var="payment" items="${payments}" >
            <div class="container">
                <f:displayPayment payment="${payment}"/>
                <td>
                    <c:if test="${payment.getPaymentStatus()==1}">
                        <a href="/accounts?paymentId=${payment.getId()}&paymentPrice=${payment.getPrice()}"><fmt:message key="table.makePayment"/></a>
                    </c:if>
                </td>
                </tr>
            </div>
        </c:forEach>
        </table>
    </c:when>
</c:choose>