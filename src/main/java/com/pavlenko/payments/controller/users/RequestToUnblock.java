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

@WebServlet(name = "RequestToUnblock", value = "/request-to-unblock")
public class RequestToUnblock extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(RequestToUnblock.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("logged") != null && (boolean) req.getSession().getAttribute("logged")) {
            User user = (User) req.getSession().getAttribute("user");
            CustomerDAO customerDAO = new CustomerDAOImpl();
            customerDAO.requestToUnblockAccount(user.getId(), Integer.parseInt(req.getParameter("accountId")));
            LOG.info("made sql statement");
            resp.sendRedirect("/accounts");
            LOG.info("redirected to /accounts");
        } else resp.sendError(404, "not logged");
    }
}
