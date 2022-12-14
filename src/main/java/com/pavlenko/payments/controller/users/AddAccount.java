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
import java.util.regex.Pattern;

@WebServlet(name = "AddAccount", value = "/add-account")
public class AddAccount extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(AddAccount.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        int userId = user.getId();
        CustomerDAO customerDAO = (CustomerDAO) req.getAttribute("customerDAO");
        String name = req.getParameter("name");
        if (validate(name)) {
            try {
                customerDAO.addAccount(userId, name, Double.parseDouble(req.getParameter("balance")));
                LOG.info("made sql statement");
                resp.sendRedirect("/accounts");
                LOG.info("redirected to /accounts");
            } catch (RuntimeException e) {
                LOG.error("Exception caught", e);
                resp.sendError(500, "Sorry, something went wrong...(((");
            }
        } else {
            LOG.error("not valid data");
            resp.sendError(404, "not valid data");
            LOG.info("redirected to errorPage");
        }
    }

    private static boolean validate(String name) {
        Pattern namePattern = Pattern.compile("(?=.{8,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z\\dа-яіїєА-ЯІЇЄ ]+(?<![_.])");
        if (name == null) {
            return false;
        }
        return namePattern.matcher(name).matches();
    }
}
