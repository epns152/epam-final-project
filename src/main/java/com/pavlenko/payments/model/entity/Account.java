package com.pavlenko.payments.model.entity;

import java.util.Objects;

public class Account {
    private int id;
    private double balance;
    private String name;
    private String status = "unblocked";
    private int isRequestedToUnblock = 0;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return id == account.id && Double.compare(account.balance, balance) == 0 && isRequestedToUnblock == account.isRequestedToUnblock && Objects.equals(name, account.name) && Objects.equals(status, account.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, balance, name, status, isRequestedToUnblock);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", balance=" + balance +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", isRequestedToUnblock=" + isRequestedToUnblock +
                '}';
    }
}
