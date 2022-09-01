package com.pavlenko.payments.model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Payment implements Serializable {
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

    public double getPrice() {
        return price;
    }

    public Date getDate() {
        return date;
    }

    public String getName() {
        return name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return id == payment.id && Double.compare(payment.price, price) == 0 && paymentStatus == payment.paymentStatus && accountId == payment.accountId && Objects.equals(name, payment.name) && Objects.equals(date, payment.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, name, date, paymentStatus, accountId);
    }
}
