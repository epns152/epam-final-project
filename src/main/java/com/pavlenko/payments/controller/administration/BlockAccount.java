package com.pavlenko.payments.controller.administration;

import com.pavlenko.payments.model.DB.AdminDAO;
import com.pavlenko.payments.model.DB.AdminDAOImpl;
import com.pavlenko.payments.model.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "BlockAccounts", value = "/a-block-account")
public class BlockAccount extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("logged") != null && (boolean) req.getSession().getAttribute("logged")) {
            User user = (User) req.getSession().getAttribute("user");
            if (user.getRole().equals("customer")) {
                resp.sendError(404, "not admin logged");
            } else {
                AdminDAO adminDAO = new AdminDAOImpl();
                if (req.getParameter("status").equals("0")) {
                    adminDAO.blockAccount(Integer.parseInt(req.getParameter("accountId")));
                } else if (req.getParameter("status").equals("1")) {
                    adminDAO.unblockAccount(Integer.parseInt(req.getParameter("accountId")));
                }
                if (req.getSession().getAttribute("userId") == null) {
                    req.getRequestDispatcher("/accounts-to-unblock").forward(req, resp);
                } else {
                    req.getRequestDispatcher("/user-accounts?userId=" + (int) req.getSession().getAttribute("userId")).forward(req, resp);
                }
            }
        } else resp.sendError(404, "not logged");
    }
}