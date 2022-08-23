package com.pavlenko.payments.model.services;

import com.pavlenko.payments.model.DB.CustomerDAO;
import com.pavlenko.payments.model.entity.Account;
import com.pavlenko.payments.model.entity.Payment;
import com.pavlenko.payments.model.entity.User;

import java.util.ArrayList;
import java.util.Comparator;

public class CustomerService {
    CustomerDAO dao;

    public CustomerService(CustomerDAO dao) {
        this.dao = dao;
    }

    public ArrayList<Account> getAccountsSortedBy(User user, String sortBy) {
        ArrayList<Account> accounts = dao.getAccounts(user);
        switch (sortBy) {
            case "id":
                accounts.sort(Comparator.comparing(Account::getId));
                break;
            case "name":
                accounts.sort(Comparator.comparing(Account::getName));
                break;
            case "balance":
                accounts.sort(Comparator.comparing(Account::getBalance));
                break;
        }
        return accounts;
    }

    public ArrayList<Payment> getPaymentsSortedBy(User user, String sortBy) {
        ArrayList<Payment> payments = dao.getPayments(user);
        switch (sortBy) {
            case "id":
                payments.sort(Comparator.comparing(Payment::getId));
                break;
            case "price":
                payments.sort(Comparator.comparing(Payment::getPrice));
                break;
            case "date":
                payments.sort(Comparator.comparing(Payment::getDate));
                break;
        }
        return payments;
    }
}
