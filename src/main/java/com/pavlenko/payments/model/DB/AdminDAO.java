package com.pavlenko.payments.model.DB;

import com.pavlenko.payments.model.entity.Account;
import com.pavlenko.payments.model.entity.Payment;
import com.pavlenko.payments.model.entity.User;

import java.util.ArrayList;

public interface AdminDAO {
    ArrayList<User> getAllUsers(int offset, int noOfRecords);
    /**
     *
     * @param  userId       users payments will return
     * @param  offset       how many rows we want to take from database
     * @param  noOfRecords  how many rows need to skip in database
     * @return              an ArrayList of {@link Payment} that is sorted by sortBy
     */
    ArrayList<Payment> getAllUserPayments(int userId, int offset, int noOfRecords);

    ArrayList<Account> getAllUserAccounts(int userId, int offset, int noOfRecords);

    boolean blockUser(int userId);

    boolean unblockUser(int userId);

    boolean blockAccount(int accountId);

    boolean unblockAccount(int accountId);

    ArrayList<Account> getAllBlockedAccountsWithRequestToUnblock();
    /**
     * @return      the number of accounts that come after those that were taken from the database
     */
    int getNoOfRecordsAccounts();
    /**
     * @return      the number of payments that come after those that were taken from the database
     */
    int getNoOfRecordsPayments();
    /**
     * @return      the number of users that come after those that were taken from the database
     */
    int getNoOfRecordsUsers();
}
