package com.pavlenko.payments.controller.administration;

import com.pavlenko.payments.model.DB.AdminDAO;
import com.pavlenko.payments.model.DB.AdminDAOImpl;
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

@WebServlet(name = "BlockUser", value = "/block-user")
public class BlockUser extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(BlockUser.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AdminDAO adminDAO = new AdminDAOImpl();
        try {
            if (req.getParameter("status").equals("0")) {
                adminDAO.blockUser(Integer.parseInt(req.getParameter("userId")));
                LOG.info("made sql statement");
            } else if (req.getParameter("status").equals("1")) {
                adminDAO.unblockUser(Integer.parseInt(req.getParameter("userId")));
                LOG.info("made sql statement");
            }
            resp.sendRedirect("/users");
            LOG.info("redirected to /users");
        } catch (RuntimeException e) {
            LOG.error("Exception caught %s", e);
            resp.sendError(500, "Sorry, something went wrong...(((");
        }
    }
}
