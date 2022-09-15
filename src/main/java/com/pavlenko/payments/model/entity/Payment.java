package com.pavlenko.payments.model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Payment implements Serializable {

    private static final long serialVersionUID = 30L;

    private final int id;
    private final double price;
    private final String name;
    private final Date date;
    private final int paymentStatus;
    private final long receivedCard;
    private final long sentCard;


    public Payment(int id, double price, String name, Date date, int paymentStatus, long receivedCard, long sentCard) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.date = date;
        this.paymentStatus = paymentStatus;
        this.receivedCard = receivedCard;
        this.sentCard = sentCard;
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
    public long getReceivedCard() {
        return receivedCard;
    }

    public long getSentCard() {
        return sentCard;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return id == payment.id && Double.compare(payment.price, price) == 0 && paymentStatus == payment.paymentStatus && receivedCard == payment.receivedCard && sentCard == payment.sentCard && Objects.equals(name, payment.name) && Objects.equals(date, payment.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, name, date, paymentStatus, receivedCard, sentCard);
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", price=" + price +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", paymentStatus=" + paymentStatus +
                ", receivedCard=" + receivedCard +
                ", sentCard=" + sentCard +
                '}';
    }
}
