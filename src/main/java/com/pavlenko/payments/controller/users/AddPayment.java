package com.pavlenko.payments.controller.users;

import com.pavlenko.payments.model.DB.CustomerDAO;
import com.pavlenko.payments.model.DB.CustomerDAOImpl;
import com.pavlenko.payments.model.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddPayment", value = "/add-payment")
public class AddPayment extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(AddAccount.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        int userId = user.getId();
        CustomerDAO customerDAO = new CustomerDAOImpl();
        try {
            customerDAO.addPayment(userId, req.getParameter("name"), Double.parseDouble(req.getParameter("price")));
            LOG.info("made sql statement");
            resp.sendRedirect("/payments");
            LOG.info("redirected to /payments");
        } catch (RuntimeException e) {
            LOG.error("Exception caught %s", e);
            resp.sendError(500, "Sorry, something went wrong...(((");
        }
    }
}
