package com.pavlenko.payments.controller.administration;

import com.pavlenko.payments.model.DB.AdminDAO;
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

@WebServlet(name = "Users", value = "/users")
public class Users extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(Users.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("userId", null);
        AdminDAO adminDAO = (AdminDAO) req.getAttribute("adminDAO");
        try {
            ArrayList<User> users = adminDAO.getAllUsers();
            LOG.info("made sql statement");
            req.getSession().setAttribute("users", users);
            req.getRequestDispatcher("/adminInfo.jsp").forward(req, resp);
            LOG.info("redirected to /adminInfo.jsp");
        } catch (RuntimeException e) {
            LOG.error("Exception caught %s", e);
            resp.sendError(500, "Sorry, something went wrong...(((");
        }
    }
}
