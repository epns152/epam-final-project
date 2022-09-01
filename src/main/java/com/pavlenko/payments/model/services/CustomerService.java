package com.pavlenko.payments.model.services;

import com.pavlenko.payments.model.DB.CustomerDAO;
import com.pavlenko.payments.model.entity.Account;
import com.pavlenko.payments.model.entity.Payment;
import com.pavlenko.payments.model.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class CustomerService {

    private static final Logger LOG = LoggerFactory.getLogger(CustomerService.class);

    CustomerDAO dao;

    public CustomerService(CustomerDAO dao) {
        this.dao = dao;
    }
    /**
     * Calls {@link CustomerDAO#getAccounts(User, String, int, int)}
     *
     * @param  user  users accounts will return
     * @param  sortBy determines sort order
     * @param  offset  how many rows we want to take from database
     * @param  noOfRecords how many rows need to skip in database
     * @return      an ArrayList of {@link Account} that is sorted by sortBy
     * @see         CustomerDAO
     */
    public ArrayList<Account> getAccountsSortedBy(User user, String sortBy, int offset, int noOfRecords) {
        ArrayList<Account> accounts;
        if (sortBy.equals("balance")) {
            accounts = dao.getAccounts(user, "balance_amount", offset, noOfRecords);
            LOG.info("made sql statement (balance_amount)");
        } else if (sortBy.equals("name")) {
            accounts = dao.getAccounts(user, "account_name", offset, noOfRecords);
            LOG.info("made sql statement (account_name)");
        } else {
            accounts = dao.getAccounts(user, "id", offset, noOfRecords);
            LOG.info("made sql statement (id)");
        }
        return accounts;
    }

    /**
     * Calls {@link CustomerDAO#getPayments(User, String, int, int)}
     *
     * @param  user  users accounts will return
     * @param  sortBy determines sort order
     * @param  offset  how many rows we want to take from database
     * @param  noOfRecords how many rows need to skip in database
     * @return      an ArrayList of {@link Payment} that is sorted by sortBy
     * @see         CustomerDAO
     */
    public ArrayList<Payment> getPaymentsSortedBy(User user, String sortBy, int offset, int noOfRecords) {
        ArrayList<Payment> payments;
        if (sortBy.equals("price")) {
            payments = dao.getPayments(user, sortBy, offset, noOfRecords);
            LOG.info("made sql statement (price)");
        } else if (sortBy.equals("date")) {
            payments = dao.getPayments(user, "creation_date", offset, noOfRecords);
            LOG.info("made sql statement (creation_date)");
        } else {
            payments = dao.getPayments(user, "id", offset, noOfRecords);
            LOG.info("made sql statement (id)");
        }
        return payments;
    }
}
