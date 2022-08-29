package com.pavlenko.payments.model.services;

import com.pavlenko.payments.model.DB.CustomerDAO;
import com.pavlenko.payments.model.DB.CustomerDAOImpl;
import com.pavlenko.payments.model.entity.Account;
import com.pavlenko.payments.model.entity.Payment;
import com.pavlenko.payments.model.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Comparator;

public class CustomerService {

    private static final Logger LOG = LoggerFactory.getLogger(CustomerService.class);

    CustomerDAO dao;

    public CustomerService(CustomerDAO dao) {
        this.dao = dao;
    }

    public ArrayList<Account> getAccountsSortedBy(User user, String sortBy) {
        ArrayList<Account> accounts;
        if (sortBy.equals("balance")) {
            accounts = dao.getAccounts(user, "balance_amount");
            LOG.info("made sql statement (balance_amount)");
        } else if (sortBy.equals("name")) {
            accounts = dao.getAccounts(user, "account_name");
            LOG.info("made sql statement (account_name)");
        } else {
            accounts = dao.getAccounts(user, "id");
            LOG.info("made sql statement (id)");
        }
        return accounts;
    }

    public ArrayList<Payment> getPaymentsSortedBy(User user, String sortBy) {
        ArrayList<Payment> payments;
        if (sortBy.equals("price")) {
            payments = dao.getPayments(user, sortBy);
            LOG.info("made sql statement (price)");
        } else if (sortBy.equals("date")) {
            payments = dao.getPayments(user, "creation_date");
            LOG.info("made sql statement (creation_date)");
        } else {
            payments = dao.getPayments(user, "id");
            LOG.info("made sql statement (id)");
        }
        return payments;
    }
}
