package com.pavlenko.payments.controller.users;

import com.pavlenko.payments.model.DB.CustomerDAO;
import com.pavlenko.payments.model.DB.CustomerDAOImpl;
import com.pavlenko.payments.model.entity.Account;
import com.pavlenko.payments.model.entity.User;
import com.pavlenko.payments.model.services.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "MyAccounts", value = "/accounts")
public class MyAccounts extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(MyAccounts.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("logged") != null && (boolean) req.getSession().getAttribute("logged")) {
            User user = (User) req.getSession().getAttribute("user");
            CustomerDAO customerDAO = new CustomerDAOImpl();
            CustomerService service = new CustomerService(customerDAO);
            String sortingCriterion = req.getParameter("sorted-by");
            if (sortingCriterion == null) {
                sortingCriterion = "id";
            }
            ArrayList<Account> accounts = service.getAccountsSortedBy(user, sortingCriterion);
            LOG.info("service call");
            req.getSession().setAttribute("accounts", accounts);
            req.getSession().setAttribute("paymentId", req.getParameter("paymentId"));
            req.getRequestDispatcher("/userInfo.jsp").forward(req, resp);
            LOG.info("forwarded to /userInfo.jsp");
        } else resp.sendError(404, "not logged");
    }
}
