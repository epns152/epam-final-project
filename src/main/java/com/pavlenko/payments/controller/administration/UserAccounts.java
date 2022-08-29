package com.pavlenko.payments.controller.administration;

import com.pavlenko.payments.model.DB.AdminDAO;
import com.pavlenko.payments.model.DB.AdminDAOImpl;
import com.pavlenko.payments.model.DB.CustomerDAOImpl;
import com.pavlenko.payments.model.entity.Account;
import com.pavlenko.payments.model.entity.User;
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
        if (req.getSession().getAttribute("logged") != null && (boolean) req.getSession().getAttribute("logged")) {
            AdminDAO adminDAO = new AdminDAOImpl();
            ArrayList<Account> accounts = adminDAO.getAllUserAccounts(Integer.parseInt(req.getParameter("userId")));
            LOG.info("made sql statement");
            req.getSession().setAttribute("accounts", accounts);
            req.getSession().setAttribute("userId", Integer.parseInt(req.getParameter("userId")));
            req.getRequestDispatcher("/adminInfo.jsp").forward(req, resp);
            LOG.info("redirected to /adminInfo.jsp");
        } else resp.sendError(404, "not logged");
    }
}
