package com.pavlenko.payments.controller.administration;

import com.pavlenko.payments.model.DB.AdminDAO;
import com.pavlenko.payments.model.DB.AdminDAOImpl;
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

@WebServlet(name = "BlockAccounts", value = "/a-block-account")
public class BlockAccount extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(BlockAccount.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AdminDAO adminDAO = new AdminDAOImpl();
        try {
            if (req.getParameter("status").equals("0")) {
                adminDAO.blockAccount(Integer.parseInt(req.getParameter("accountId")));
                LOG.info("made sql statement");
            } else if (req.getParameter("status").equals("1")) {
                adminDAO.unblockAccount(Integer.parseInt(req.getParameter("accountId")));
                LOG.info("made sql statement");
            }
            if (req.getSession().getAttribute("userId") == null) {
                req.getRequestDispatcher("/accounts-to-unblock").forward(req, resp);
                LOG.info("forward to /accounts-to-unblock");
            } else {
                req.getRequestDispatcher("/user-accounts?userId=" + (int) req.getSession().getAttribute("userId")).forward(req, resp);
                LOG.info("forward to /user-accounts?userId=");
            }
        } catch (RuntimeException e) {
            LOG.error("Exception caught %s", e);
            resp.sendError(500, "Sorry, something went wrong...(((");
        }
    }
}