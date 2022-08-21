package com.pavlenko.payments.controller.administration;

import com.pavlenko.payments.model.DB.AdminDAO;
import com.pavlenko.payments.model.DB.AdminDAOImpl;
import com.pavlenko.payments.model.entity.Account;
import com.pavlenko.payments.model.entity.Payment;
import com.pavlenko.payments.model.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "UserPayments", value = "/user-payments")
public class UserPayments extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("logged") != null && (boolean) req.getSession().getAttribute("logged")) {
            User user = (User) req.getSession().getAttribute("user");
            if (user.getRole().equals("customer")) {
                resp.sendError(404, "not admin logged");
            }
            AdminDAO adminDAO = new AdminDAOImpl();
            ArrayList<Payment> payments = adminDAO.getAllUserPayments(Integer.parseInt(req.getParameter("userId")));
            req.getSession().setAttribute("payments", payments);
            req.getRequestDispatcher("/adminInfo.jsp").forward(req, resp);
        } else resp.sendError(404, "not logged");
    }
}
