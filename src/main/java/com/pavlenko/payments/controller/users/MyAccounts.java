package com.pavlenko.payments.controller.users;

import com.pavlenko.payments.model.DB.CustomerDAO;
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
        int page = 1;
        int recordsPerPage = 5;
        if (req.getParameter("page") != null) {
            page = Integer.parseInt(req.getParameter("page"));
        }
        if (req.getSession().getAttribute("accounts-page") == null || req.getParameter("page") != null) {
            req.getSession().setAttribute("accounts-page", page);
        }
        if (req.getParameter("page") == null) {
            page = (int) req.getSession().getAttribute("accounts-page");
        }
        User user = (User) req.getSession().getAttribute("user");
        CustomerDAO customerDAO = (CustomerDAO) req.getAttribute("customerDAO");

        CustomerService service = new CustomerService(customerDAO);

        String sortingCriterion = req.getParameter("sorted-by");
        if (sortingCriterion == null) {
            sortingCriterion = "id";
        }
        if (req.getSession().getAttribute("accounts-sorted-by") == null || req.getParameter("sorted-by") != null) {
            req.getSession().setAttribute("accounts-sorted-by", sortingCriterion);
        }
        if (req.getParameter("sorted-by") == null) {
            sortingCriterion = (String) req.getSession().getAttribute("accounts-sorted-by");
        }
        try {
            ArrayList<Account> accounts = service.getAccountsSortedBy(user, sortingCriterion, (page - 1) * recordsPerPage, recordsPerPage);
            LOG.info("service call");
            int noOfRecords = customerDAO.getNoOfRecordsAccounts();
            int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
            req.setAttribute("noOfPages", noOfPages);
            req.setAttribute("currentPage", page);
            req.getSession().setAttribute("accounts", accounts);
            String paymentId = req.getParameter("paymentId");
            String paymentPrice = req.getParameter("paymentPrice");
            if (paymentId != null && paymentPrice != null) {
                req.getSession().setAttribute("paymentId", paymentId);
                req.getSession().setAttribute("paymentPrice", paymentPrice);
                LOG.info("payment info added " + paymentPrice + " " + paymentId);
            }
            req.getRequestDispatcher("/userInfo.jsp").forward(req, resp);
            LOG.info("forwarded to /userInfo.jsp");
        } catch (RuntimeException e) {
            LOG.error("Exception caught", e);
            resp.sendError(500, "Sorry, something went wrong...(((");
        }
    }
}
