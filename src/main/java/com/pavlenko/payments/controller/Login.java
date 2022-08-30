package com.pavlenko.payments.controller;

import com.pavlenko.payments.controller.users.ProfileInfo;
import com.pavlenko.payments.model.DB.CustomerDAO;
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

@WebServlet(name = "Login", value = "/login")
public class Login extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(Login.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        CustomerDAO customerDAO = new CustomerDAOImpl();
        User user = customerDAO.login(login, password);
        LOG.info("made sql statement");
        if (user != null) {
            req.getSession().setAttribute("logged", true);
            req.getSession().setAttribute("user", user);
            resp.sendRedirect("index.jsp");
            LOG.info("redirected to index.jsp");
        } else {
            LOG.error("not found user in DB");
            resp.sendError(404, "Wrong Username or Password"); // CRINGE
            LOG.info("redirected to errorPage");
        }
    }
}
