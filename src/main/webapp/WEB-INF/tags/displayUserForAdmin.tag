<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute
        name="user"
        type="com.pavlenko.payments.model.entity.User"
        required="true"
        rtexprvalue="true"
%>

<tr>
    <td>
        ${user.getId()}
    </td>
    <td>
        ${user.getRole()}
    </td>
    <td>
        ${user.getFirstname()}
    </td>
    <td>
        ${user.getLastname()}
    </td>
    <td>
        ${user.getStatus()}
    </td>
    <td>
        ${user.getDate()}
    </td>