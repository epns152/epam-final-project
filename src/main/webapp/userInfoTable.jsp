<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="my" uri="myTags" %>
<%@ taglib prefix="f" tagdir="/WEB-INF/tags" %>

<c:choose>
    <c:when test="${accounts!=null}">
        <p>Sort by <a href="/accounts?sorted-by=id">number</a> <a href="/accounts?sorted-by=balance">balance</a> <a href="/accounts?sorted-by=name">name</a></p>
        <table border=1>
            <tr>
                <td>id</td>
                <td>balance</td>
                <td>name</td>
                <td>status</td>
                <td>request to unblock</td>
                <td>         </td>
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
                    <a href="/make-payment?paymentId=${paymentId}&accountId=${account.getId()}">make payment</a>
                </c:if>
                <c:if test="${account.getStatus()=='unblocked' && paymentId==null}">
                    <a href="/block-account?accountId=${account.getId()}">Block account</a>
                </c:if>
                <c:if test="${account.getIsRequestedToUnblock() == 0 && account.getStatus()=='blocked'}">
                    <a href="/request-to-unblock?accountId=${account.getId()}">Request to unblock account</a>
                </c:if>
                </td>
                </tr>
            </div>
        </c:forEach>
        </table>
        ${paymentId=null}
    </c:when>
    <c:when test="${payments!=null}">
        <p>Sort by <a href="/payments?sorted-by=id">number</a> <a href="/payments?sorted-by=price">price</a> <a href="/payments?sorted-by=date">date</a></p>
        <table border=1>
        <tr>
            <td>id</td>
            <td>name</td>
            <td>price</td>
            <td>status</td>
            <td>date</td>
            <td>         </td>
        </tr>
        <c:forEach var="payment" items="${payments}" >
            <div class="container">
                <f:displayPayment payment="${payment}"/>
                <td>
                    <c:if test="${payment.getPaymentStatus()==1}">
                        <a href="/accounts?paymentId=${payment.getId()}">make payment</a>
                    </c:if>
                </td>
                </tr>
            </div>
        </c:forEach>
        </table>
    </c:when>
</c:choose>