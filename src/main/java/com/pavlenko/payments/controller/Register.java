package com.pavlenko.payments.controller;

import com.pavlenko.payments.model.DB.CustomerDAO;
import com.pavlenko.payments.model.DB.CustomerDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Register", value = "/register")
public class Register extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        CustomerDAO customerDAO = new CustomerDAOImpl();
        if (!customerDAO.accountExist(login, password)) {
            customerDAO.register(login, password, firstname, lastname);
            req.getSession().setAttribute("logged", true);
            req.getSession().setAttribute("user", customerDAO.login(login, password));
        }
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}
