package com.pavlenko.payments.controller;

import com.pavlenko.payments.model.DB.CustomerDAO;
import com.pavlenko.payments.model.DB.CustomerDAOImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Register", value = "/register")
public class Register extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(Register.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        CustomerDAO customerDAO = new CustomerDAOImpl();
        if (!customerDAO.accountExist(login, password)) {
            customerDAO.register(login, password, firstname, lastname);
            LOG.info("made sql statement (register)");
            req.getSession().setAttribute("logged", true);
            req.getSession().setAttribute("user", customerDAO.login(login, password));
            LOG.info("made sql statement (login)");
        }
        resp.sendRedirect("index.jsp");
        LOG.info("redirected to index.jsp");
    }
}
