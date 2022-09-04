package com.pavlenko.payments.controller;

import com.pavlenko.payments.model.DB.CustomerDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

@WebServlet(name = "Register", value = "/register")
public class Register extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(Register.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (validate(login, firstname, lastname)) {
            CustomerDAO customerDAO = (CustomerDAO) req.getAttribute("customerDAO");
            if (!customerDAO.accountExist(login, password)) {
                customerDAO.register(login, password, firstname, lastname);
                LOG.info("made sql statement (register)");
                req.getSession().setAttribute("logged", true);
                req.getSession().setAttribute("user", customerDAO.login(login, password));
                LOG.info("made sql statement (login)");
            }
            resp.sendRedirect("index.jsp");
            LOG.info("redirected to index.jsp");
        } else {
            LOG.error("not valid data");
            resp.sendError(404, "not valid data");
            LOG.info("redirected to errorPage");
        }
    }

    private static boolean validate(String login, String firstname, String lastname) {
        Pattern loginPattern = Pattern.compile("(?=.{3,20}$)(?![_.])(?!.*[_.]{2})\\w+(?<![_.])");
        Pattern namePattern = Pattern.compile("(?=.{3,20}$)(?![_.])(?!.*[_.]{2})[A-ZА-ЯІЄЇ][a-zа-яіїє]+(?<![_.])");
        if (login == null || firstname == null || lastname == null) {
            return false;
        }
        return loginPattern.matcher(login).matches() && namePattern.matcher(firstname).matches() && namePattern.matcher(lastname).matches();
    }
}
