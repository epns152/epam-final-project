package com.pavlenko.payments.controller.users;

import com.pavlenko.payments.model.DB.CustomerDAO;
import com.pavlenko.payments.model.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "Replenish", value = "/replenish")
public class ReplenishAccount extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(ReplenishAccount.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        int userId = user.getId();
        CustomerDAO customerDAO = (CustomerDAO) req.getAttribute("customerDAO");
        String accountId = req.getParameter("accountId");
        System.out.println(accountId);
        try {
            customerDAO.replenishAccount(Integer.parseInt(accountId), userId, Double.parseDouble(req.getParameter("topUpAmount")));
            LOG.info("made sql statement");
            resp.sendRedirect("/accounts");
            LOG.info("redirected to /accounts");
        } catch (RuntimeException e) {
            LOG.error("Exception caught", e);
            resp.sendError(500, "Sorry, something went wrong...(((");
        }
    }
}