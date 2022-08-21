package com.pavlenko.payments.controller;

import com.pavlenko.payments.model.DB.CustomerDAO;
import com.pavlenko.payments.model.DB.CustomerDAOImpl;
import com.pavlenko.payments.model.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Login", value = "/login")
public class Login extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        CustomerDAO customerDAO = new CustomerDAOImpl();
        User user = customerDAO.login(login, password);
        if (user != null) {
            req.getSession().setAttribute("logged", true);
            req.getSession().setAttribute("user", user);
        } else resp.sendError(404, "Wrong Username or Password"); // CRINGE
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}
