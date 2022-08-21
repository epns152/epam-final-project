package com.pavlenko.payments.model.entity;

public class Account {
    private int id;
    private double balance;
    private String name;
    private String status = "unblocked";
    private int isRequestedToUnblock = 0;
    private int userId;

    public Account(int id, double balance, String name, String status, int isRequestedToUnblock) {
        this.id = id;
        this.balance = balance;
        this.name = name;
        this.status = status;
        this.isRequestedToUnblock = isRequestedToUnblock;
    }

    public Account(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public int getIsRequestedToUnblock() {
        return isRequestedToUnblock;
    }

    @Override
    public String toString() {
        return "Account{" +
                "balance=" + balance +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", isRequestedToUnblock=" + isRequestedToUnblock +
                '}';
    }
}
