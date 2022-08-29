package com.pavlenko.payments.controller.users;

import com.pavlenko.payments.model.DB.CustomerDAO;
import com.pavlenko.payments.model.DB.CustomerDAOImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "MakePayment", value = "/make-payment")
public class MakePayment extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(MakePayment.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("logged") != null && (boolean) req.getSession().getAttribute("logged")) {
            int accountId = Integer.parseInt(req.getParameter("accountId"));
            int paymentId = Integer.parseInt(req.getParameter("paymentId"));
            CustomerDAO customerDAO = new CustomerDAOImpl();
            customerDAO.makePayment(accountId, paymentId);
            LOG.info("made sql statement");
            resp.sendRedirect("/payments");
            LOG.info("redirected to /payments");
        } else resp.sendError(404, "not logged");
    }
}
