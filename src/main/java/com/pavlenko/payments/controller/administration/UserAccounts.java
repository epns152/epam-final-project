package com.pavlenko.payments.controller.administration;

import com.pavlenko.payments.model.DB.AdminDAO;
import com.pavlenko.payments.model.DB.CustomerDAO;
import com.pavlenko.payments.model.entity.Account;
import com.pavlenko.payments.model.entity.User;
import com.pavlenko.payments.model.services.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "UserAccounts", value = "/user-accounts")
public class UserAccounts extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(UserAccounts.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int userId;
        if (req.getParameter("userId") != null) {
            req.getSession().setAttribute("userId", Integer.valueOf(req.getParameter("userId")));
        }
        userId = (int) req.getSession().getAttribute("userId");

        int page = 1;
        int recordsPerPage = 5;
        if (req.getParameter("page") != null) {
            page = Integer.parseInt(req.getParameter("page"));
        }
        if (req.getSession().getAttribute("accounts-page") == null || req.getParameter("page") != null) {
            req.getSession().setAttribute("accounts-page", page);
        }
        if (req.getParameter("page") == null) {
            page = (int) req.getSession().getAttribute("accounts-page");
        }




        AdminDAO adminDAO = (AdminDAO) req.getAttribute("adminDAO");
        try {
            ArrayList<Account> accounts = adminDAO.getAllUserAccounts(userId, (page - 1) * recordsPerPage, recordsPerPage);
            LOG.info("made sql statement");
            int noOfRecords = adminDAO.getNoOfRecordsAccounts();
            int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
            req.setAttribute("noOfPages", noOfPages);
            req.setAttribute("currentPage", page);
            req.getSession().setAttribute("accounts", accounts);
            req.getRequestDispatcher("/adminInfo.jsp").forward(req, resp);
            LOG.info("redirected to /adminInfo.jsp");
        } catch (RuntimeException e) {
            LOG.error("Exception caught", e);
            resp.sendError(500, "Sorry, something went wrong...(((");
        }
    }
}
