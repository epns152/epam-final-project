package com.pavlenko.payments.controller.users;

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

@WebServlet(name = "Profile", value = "/profile")
public class ProfileInfo extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(ProfileInfo.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        CustomerDAO customerDAO = (CustomerDAO) req.getAttribute("customerDAO");
        try {
            req.getSession().setAttribute("user", customerDAO.getAllInfo(user));
            LOG.info("made sql statement");
            req.getRequestDispatcher("/profile.jsp").forward(req, resp);
            LOG.info("forwarded to /profile.jsp");
        } catch (RuntimeException e) {
            LOG.error("Exception caught", e);
            resp.sendError(500, "Sorry, something went wrong...(((");
        }
    }
}
