package com.pavlenko.payments.controller.users;

import com.pavlenko.payments.model.DB.CustomerDAO;
import com.pavlenko.payments.model.DB.CustomerDAOImpl;
import com.pavlenko.payments.model.entity.Account;
import com.pavlenko.payments.model.entity.Payment;
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

@WebServlet(name = "MyPayments", value = "/payments")
public class MyPayments extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(MyPayments.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        CustomerDAO customerDAO = new CustomerDAOImpl();
        CustomerService service = new CustomerService(customerDAO);
        String sortingCriterion = req.getParameter("sorted-by");
        if (sortingCriterion == null) {
            sortingCriterion = "id";
        }
        try {
            ArrayList<Payment> payments = service.getPaymentsSortedBy(user, sortingCriterion);
            LOG.info("service call");
            req.getSession().setAttribute("payments", payments);
            req.getRequestDispatcher("/userInfo.jsp").forward(req, resp);
            LOG.info("forwarded to /userInfo.jsp");
        } catch (RuntimeException e) {
            LOG.error("Exception caught %s", e);
            resp.sendError(500, "Sorry, something went wrong...(((");
        }
    }
}
