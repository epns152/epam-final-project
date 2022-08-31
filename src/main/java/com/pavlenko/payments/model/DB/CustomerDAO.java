package com.pavlenko.payments.model.DB;

import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;
import com.pavlenko.payments.model.entity.Account;
import com.pavlenko.payments.model.entity.User;
import com.pavlenko.payments.model.entity.Payment;

import java.util.ArrayList;

public interface CustomerDAO {
    User getAllInfo(User user);
    boolean accountExist(String login, String pass);

    User login(String login, String pass);

    boolean register(String login, String pass, String firstName, String lastName);

    boolean addAccount(int userId, String name, double balance);

    boolean blockAccount(int userId, int accountId);

    boolean requestToUnblockAccount(int userId, int accountId);

    boolean addPayment(int userId, String name, double price);

    ArrayList<Account> getAccounts(User user, String sortBy);

    ArrayList<Payment> getPayments(User user, String sortBy);

    boolean makePayment(int accountId, int paymentId);

}
