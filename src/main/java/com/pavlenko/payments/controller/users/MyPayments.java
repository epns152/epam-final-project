package com.pavlenko.payments.controller.users;

import com.pavlenko.payments.model.DB.CustomerDAO;
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
        int page = 1;
        int recordsPerPage = 5;
        if (req.getParameter("page") != null) {
            page = Integer.parseInt(req.getParameter("page"));
        }
        if (req.getSession().getAttribute("payments-page") == null || req.getParameter("page") != null) {
            req.getSession().setAttribute("payments-page", page);
        }
        if (req.getParameter("page") == null) {
            page = (int) req.getSession().getAttribute("payments-page");
        }
        User user = (User) req.getSession().getAttribute("user");
        CustomerDAO customerDAO = (CustomerDAO) req.getAttribute("customerDAO");

        CustomerService service = new CustomerService(customerDAO);

        String sortingCriterion = req.getParameter("sorted-by");
        if (sortingCriterion == null) {
            sortingCriterion = "id";
        }
        if (req.getSession().getAttribute("payments-sorted-by") == null || req.getParameter("sorted-by") != null) {
            req.getSession().setAttribute("payments-sorted-by", sortingCriterion);
        }
        if (req.getParameter("sorted-by") == null) {
            sortingCriterion = (String) req.getSession().getAttribute("payments-sorted-by");
        }
        try {
            ArrayList<Payment> payments = service.getPaymentsSortedBy(user, sortingCriterion, (page - 1) * recordsPerPage, recordsPerPage);
            LOG.info("service call");
            int noOfRecords = customerDAO.getNoOfRecordsPayments();
            int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
            req.setAttribute("noOfPages", noOfPages);
            req.setAttribute("currentPage", page);
            req.getSession().setAttribute("payments", payments);
            req.getRequestDispatcher("/userInfo.jsp").forward(req, resp);
            LOG.info("forwarded to /userInfo.jsp");
        } catch (RuntimeException e) {
            LOG.error("Exception caught", e);
            resp.sendError(500, "Sorry, something went wrong...(((");
        }
    }
}
