package com.pavlenko.payments.controller.administration;

import com.pavlenko.payments.model.DB.AdminDAO;
import com.pavlenko.payments.model.entity.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "AccountsToUnblock", value = "/accounts-to-unblock")
public class AccountsToUnblock extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(AccountsToUnblock.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AdminDAO adminDAO = (AdminDAO) req.getAttribute("adminDAO");
        try {
            ArrayList<Account> accounts = adminDAO.getAllBlockedAccountsWithRequestToUnblock();
            LOG.info("made sql statement");
            req.getSession().setAttribute("accounts", accounts);
            req.getRequestDispatcher("/adminInfo.jsp").forward(req, resp);
        } catch (RuntimeException e) {
            LOG.error("Exception caught", e);
            resp.sendError(500, "Sorry, something went wrong...(((");
        }
    }
}
