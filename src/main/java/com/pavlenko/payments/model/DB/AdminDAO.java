package com.pavlenko.payments.model.DB;

import com.pavlenko.payments.model.entity.Account;
import com.pavlenko.payments.model.entity.Payment;
import com.pavlenko.payments.model.entity.User;

import java.util.ArrayList;

public interface AdminDAO {
    ArrayList<User> getAllUsers();

    ArrayList<Payment> getAllUserPayments(int userId);

    ArrayList<Account> getAllUserAccounts(int userId);

    boolean blockUser(int userId);

    boolean unblockUser(int userId);

    boolean blockAccount(int accountId);

    boolean unblockAccount(int accountId);

    ArrayList<Account> getAllBlockedAccountsWithRequestToUnblock();
}
