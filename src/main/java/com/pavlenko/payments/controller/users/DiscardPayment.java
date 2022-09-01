package com.pavlenko.payments.controller.users;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet(name = "Discard payment", value = "/discard")
public class DiscardPayment extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(DiscardPayment.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().removeAttribute("paymentId");
        req.getSession().removeAttribute("paymentPrice");
        resp.sendRedirect("/payments");
        LOG.info("redirected to /payments");
    }
}
