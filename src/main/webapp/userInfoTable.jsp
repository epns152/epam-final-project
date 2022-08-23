<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
    <c:when test="${accounts!=null}">
        <p>Sort by <a href="/accounts?sorted-by=id">number</a> <a href="/accounts?sorted-by=balance">balance</a> <a href="/accounts?sorted-by=name">name</a></p>
        <c:forEach var="account" items="${accounts}" >
            <div class="container">
                <p>${account.toString()}</p>
                <c:if test="${paymentId!=null && account.getStatus()=='unblocked'}">
                    <a href="/make-payment?paymentId=${paymentId}&accountId=${account.getId()}">make payment</a>
                </c:if>
                <c:if test="${account.getStatus()=='unblocked' && paymentId==null}">
                    <a href="/block-account?accountId=${account.getId()}">Block account</a>
                </c:if>
                <c:if test="${account.getIsRequestedToUnblock() == 0 && account.getStatus()=='blocked'}">
                    <a href="/request-to-unblock?accountId=${account.getId()}">Request to unblock account</a>
                </c:if>
            </div>
        </c:forEach>
        ${paymentId=null}
    </c:when>
    <c:when test="${payments!=null}">
        <c:forEach var="payment" items="${payments}" >
            <div class="container">
                <p>${payment.toString()}</p>
                    <c:if test="${payment.getPaymentStatus()==1}">
                        <a href="/accounts?paymentId=${payment.getId()}">make payment</a>
                    </c:if>
            </div>
        </c:forEach>
    </c:when>
</c:choose>