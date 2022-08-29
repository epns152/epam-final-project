package com.pavlenko.payments.controller.users;

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

@WebServlet(name = "Profile", value = "/profile")
public class ProfileInfo extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(ProfileInfo.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("logged") != null && (boolean) req.getSession().getAttribute("logged")) {
            User user = (User) req.getSession().getAttribute("user");
            CustomerDAO customerDAO = new CustomerDAOImpl();
            req.getSession().setAttribute("user", customerDAO.getAllInfo(user));
            LOG.info("made sql statement");
            req.getRequestDispatcher("/profile.jsp").forward(req, resp);
            LOG.info("forwarded to /profile.jsp");
        } else resp.sendError(404, "not logged");
    }
}
