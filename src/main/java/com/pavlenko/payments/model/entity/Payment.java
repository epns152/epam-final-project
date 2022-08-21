package com.pavlenko.payments.model.entity;

import java.util.Date;

public class Payment {
    private int id;
    private double price;
    private String name;
    private Date date;
    private int paymentStatus;
    private int accountId;

    public Payment(int id, double price, String name, Date date, int paymentStatus, int accountId) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.date = date;
        this.paymentStatus = paymentStatus;
        this.accountId = accountId;
    }

    public int getId() {
        return id;
    }

    public int getPaymentStatus() {
        return paymentStatus;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "price=" + price +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", paymentStatus='" + paymentStatus + '\'' +
                '}';
    }
}
