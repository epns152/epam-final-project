package com.pavlenko.payments.controller;

import com.pavlenko.payments.model.DB.CustomerDAO;
import com.pavlenko.payments.model.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

@WebServlet(name = "Login", value = "/login")
public class Login extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(Login.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (validate(login)) {
            CustomerDAO customerDAO = (CustomerDAO) req.getAttribute("customerDAO");
            User user = customerDAO.login(login, password);
            LOG.info("made sql statement");
            if (user != null) {
                if (user.getId() == -1) {
                    LOG.warn("user is blocked");
                    resp.sendError(404, "Your account is blocked");
                } else {
                    req.getSession().setAttribute("logged", true);
                    req.getSession().setAttribute("user", user);
                    resp.sendRedirect("index.jsp");
                    LOG.info("redirected to index.jsp");
                }
            } else {
                LOG.error("not found user in DB");
                resp.sendError(404, "Wrong Username or Password");
                LOG.info("redirected to errorPage");
            }
        } else {
            LOG.error("not valid login");
            resp.sendError(404, "login is not valid");
            LOG.info("redirected to errorPage");
        }
    }

    private static boolean validate(String login) {
        Pattern loginPattern = Pattern.compile("(?=.{3,20}$)(?![_.])(?!.*[_.]{2})\\w+(?<![_.])");
        if (login == null) {
            return false;
        }
        return loginPattern.matcher(login).matches();
    }
}
