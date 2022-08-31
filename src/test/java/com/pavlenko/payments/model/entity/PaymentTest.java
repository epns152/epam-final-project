package com.pavlenko.payments.model.entity;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class PaymentTest {

    Payment payment = new Payment(1, 100.0, "name", new Date(213), 1, 21);
    Payment second = new Payment(1, 100.0, "name", new Date(213), 1, 21);
    Payment first = new Payment(21, 100.0, "name", new Date(213), 1, 21);

    @Test
    void getId() {
        assertThat(payment.getId()).isEqualTo(1);
    }

    @Test
    void getPaymentStatus() {
        assertThat(payment.getPaymentStatus()).isEqualTo(1);
    }

    @Test
    void getPrice() {
        assertThat(payment.getPrice()).isEqualTo(100.0);
    }

    @Test
    void getDate() {
        assertThat(payment.getDate()).isEqualTo(new Date(213));
    }

    @Test
    void getName() {
        assertThat(payment.getName()).isEqualTo("name");
    }

    @Test
    void testToString() {
        assertThat(payment.toString()).isEqualTo("Payment{price=100.0, name='name', date=Thu Jan 01 03:00:00 EET 1970, paymentStatus='1'}");
    }

    @Test
    void testEquals() {
        assertThat(payment.equals(second)).isEqualTo(true);
        assertThat(payment.equals(first)).isEqualTo(false);
    }
}