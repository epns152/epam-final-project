<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute
        name="payment"
        type="com.pavlenko.payments.model.entity.Payment"
        required="true"
        rtexprvalue="true"
%>

    <tr>
        <td>
            ${payment.getId()}
        </td>
        <td>
            ${payment.getName()}
        </td>
        <td>
            ${payment.getPrice()}
        </td>
        <td>
            ${payment.getPaymentStatus()}
        </td>
        <td>
            ${payment.getDate()}
        </td>

