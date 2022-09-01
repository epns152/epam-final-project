package com.pavlenko.payments.model.DB;

import com.pavlenko.payments.model.entity.Account;
import com.pavlenko.payments.model.entity.User;
import com.pavlenko.payments.model.entity.Payment;

import java.util.ArrayList;

public interface CustomerDAO {
    /**
     *
     * @param user the user about whom you want to get all the information
     * @return {@link User} with full information
     */
    User getAllInfo(User user);

    /**
     *
     * @param login users login
     * @param pass  users password
     * @return true if user with that login already is registered and false if it's not
     */
    boolean accountExist(String login, String pass);

    /**
     *
     * @param login users login
     * @param pass  users password
     * @return {@link User} that was logged or null uf it's not
     */
    User login(String login, String pass);

    /**
     * Register new user in the application by adding it to database
     *
     * @param login     users login
     * @param pass      users password
     * @param firstName users firstname
     * @param lastName  users lastname
     * @return  true if {@link User} was registered and false if it's not
     */
    boolean register(String login, String pass, String firstName, String lastName);
    /**
     * Adds new account to user in database
     *
     * @param  userId   users id
     * @param  name     name of payment
     * @param  balance  account balance
     * @return          true if {@link Account} was added and false if it's not
     */
    boolean addAccount(int userId, String name, double balance);
    /**
     * Blocks users account by updating database
     *
     * @param  userId       users id
     * @param  accountId    account id
     * @return              true if {@link Account} was blocked and false if it's not
     */
    boolean blockAccount(int userId, int accountId);
    /**
     * Request users account to unblock
     *
     * @param  userId       users id
     * @param  accountId    account id
     * @return              true if {@link Account} was requested and false if it's not
     */
    boolean requestToUnblockAccount(int userId, int accountId);
    /**
     * Adds new payment to user in database
     *
     * @param  userId   users id
     * @param  name     name of payment
     * @param  price    payment price
     * @return          true if {@link Payment} was added and false if it's not
     */
    boolean addPayment(int userId, String name, double price);
    /**
     *
     * @param  user         users accounts will return
     * @param  sortBy       determines sort order
     * @param  offset       how many rows we want to take from database
     * @param  noOfRecords  how many rows need to skip in database
     * @return              an ArrayList of {@link Account} that is sorted by sortBy
     */
    ArrayList<Account> getAccounts(User user, String sortBy, int offset, int noOfRecords);
    /**
     *
     * @param  user         users accounts will return
     * @param  sortBy       determines sort order
     * @param  offset       how many rows we want to take from database
     * @param  noOfRecords  how many rows need to skip in database
     * @return              an ArrayList of {@link Payment} that is sorted by sortBy
     */
    ArrayList<Payment> getPayments(User user, String sortBy, int offset, int noOfRecords);

    /**
     * Create payment by user with specified account
     *
     * @param  accountId    users accounts will return
     * @param  paymentId    determines sort order
     * @return              true if payment was made and false if it's not
     */
    boolean makePayment(int accountId, int paymentId);

    /**
     * @return  the number of accounts that come after those that were taken from the database
     */
    int getNoOfRecordsAccounts();
    /**
     * @return  the number of payments that come after those that were taken from the database
     */
    int getNoOfRecordsPayments();
}
