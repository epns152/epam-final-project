package com.pavlenko.payments.controller.administration;

import com.pavlenko.payments.model.DB.AdminDAO;
import com.pavlenko.payments.model.entity.Payment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "UserPayments", value = "/user-payments")
public class UserPayments extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(UserPayments.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AdminDAO adminDAO = (AdminDAO) req.getAttribute("adminDAO");
        try {
            ArrayList<Payment> payments = adminDAO.getAllUserPayments(Integer.parseInt(req.getParameter("userId")));
            LOG.info("made sql statement");
            req.getSession().setAttribute("payments", payments);
            req.getRequestDispatcher("/adminInfo.jsp").forward(req, resp);
            LOG.info("redirected to /adminInfo.jsp");
        } catch (RuntimeException e) {
            LOG.error("Exception caught", e);
            resp.sendError(500, "Sorry, something went wrong...(((");
        }
    }
}
