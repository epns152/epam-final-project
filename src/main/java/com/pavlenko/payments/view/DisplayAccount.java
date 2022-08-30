package com.pavlenko.payments.view;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class DisplayAccount extends TagSupport {
    private String name;
    private String index;
    private String balance;
    private String status;
    private String unblockreq;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUnblockreq() {
        return unblockreq;
    }

    public void setUnblockreq(String unblockreq) {
        this.unblockreq = unblockreq;
    }


    @Override
    public int doStartTag() throws JspException {

        JspWriter out = pageContext.getOut();

        try {
            out.print("<tr>");
            out.print(String.format("<td>%s</td>", index));
            out.print(String.format("<td>%s</td>", balance));
            out.print(String.format("<td>%s</td>", name));
            out.print(String.format("<td><fmt:message key=\"status.%s\"/></td>", status));
            out.print(String.format("<td>%s</td>", unblockreq.equals("0") ? "<fmt:message key=\"request.toUnblockFalse\"/>" : "<fmt:message key=\"request.toUnblockTrue\"/>"));
        } catch (IOException e) {
            throw new JspException(e);
        }

        return EVAL_BODY_INCLUDE;
    }


}
