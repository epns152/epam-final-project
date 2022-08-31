package com.pavlenko.payments.model.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AccountTest {

    Account account = new Account(1, 100.0, "name", "blocked", 1);
    Account second = new Account(1, 100.0, "name", "blocked", 1);
    Account third = new Account(2, 100.0, "name", "blocked", 1);

    @Test
    void getId() {
        assertThat(account.getId()).isEqualTo(1);
    }

    @Test
    void getBalance() {
        assertThat(account.getBalance()).isEqualTo(100.0);
    }

    @Test
    void getName() {
        assertThat(account.getName()).isEqualTo("name");
    }

    @Test
    void getStatus() {
        assertThat(account.getStatus()).isEqualTo("blocked");
    }

    @Test
    void getIsRequestedToUnblock() {
        assertThat(account.getIsRequestedToUnblock()).isEqualTo(1);
    }

    @Test
    void toStringTest() {
        assertThat(account.toString()).isEqualTo("Account{id=1, balance=100.0, name='name', status='blocked', isRequestedToUnblock=1}");
    }

    @Test
    void equalsTest() {
        assertThat(account.equals(second)).isEqualTo(true);
        assertThat(account.equals(third)).isEqualTo(false);
    }
}