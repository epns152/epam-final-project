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
        switch (sortBy) {
            case "balance":
                accounts = dao.getAccounts(user, "balance_amount", offset, noOfRecords);
                LOG.info("made sql statement (balance_amount)");
                break;
            case "name":
                accounts = dao.getAccounts(user, "account_name", offset, noOfRecords);
                LOG.info("made sql statement (account_name)");
                break;
            case "number":
                accounts = dao.getAccounts(user, "card_id", offset, noOfRecords);
                LOG.info("made sql statement (number)");
                break;
            default:
                accounts = dao.getAccounts(user, "id", offset, noOfRecords);
                LOG.info("made sql statement (id)");
                break;
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
        switch (sortBy) {
            case "price":
                payments = dao.getPayments(user, sortBy, offset, noOfRecords);
                LOG.info("made sql statement (price)");
                break;
            case "date":
                payments = dao.getPayments(user, "creation_date", offset, noOfRecords);
                LOG.info("made sql statement (creation_date)");
                break;
            case "date-desc":
                payments = dao.getPayments(user, "creation_date DESC", offset, noOfRecords);
                LOG.info("made sql statement (creation_date)");
                break;
            default:
                payments = dao.getPayments(user, "id", offset, noOfRecords);
                LOG.info("made sql statement (id)");
                break;
        }
        return payments;
    }
}
