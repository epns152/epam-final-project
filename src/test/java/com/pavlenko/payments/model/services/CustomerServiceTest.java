//package com.pavlenko.payments.model.services;
//
//import com.pavlenko.payments.model.DB.CustomerDAO;
//import com.pavlenko.payments.model.DB.CustomerDAOImpl;
//import com.pavlenko.payments.model.entity.Account;
//import com.pavlenko.payments.model.entity.Payment;
//import com.pavlenko.payments.model.entity.User;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.exceptions.base.MockitoException;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Date;
//import java.util.List;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.BDDMockito.given;
//
//class CustomerServiceTest {
//
//    @Mock private CustomerDAO dao;
//    private static CustomerService underTest;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.initMocks(this);
//        underTest = new CustomerService(dao);
//    }
//
//    @Test
//    void getAccountsSortedByTestIdCase() {
//        List<Account> accounts = Arrays.asList(
//                new Account(1, 100, "first", "unblocked", 0),
//                new Account(2, 150, "second", "unblocked", 0),
//                new Account(3, 200, "third", "unblocked", 0)
//        );
//
//        given(dao.getAccounts(any())).willReturn(new ArrayList<>(Arrays.asList(
//                new Account(1, 100, "first", "unblocked", 0),
//                new Account(3, 200, "third", "unblocked", 0),
//                new Account(2, 150, "second", "unblocked", 0)
//        )));
//
//        ArrayList<Account> res = underTest.getAccountsSortedBy(any(), "id");
//
//        assertThat(res).isEqualTo(accounts);
//    }
//
//    @Test
//    void getAccountsSortedByTestBalanceCase() {
//        List<Account> accounts = Arrays.asList(
//                new Account(1, 100, "first", "unblocked", 0),
//                new Account(3, 150, "second", "unblocked", 0),
//                new Account(2, 200, "third", "unblocked", 0)
//        );
//
//        given(dao.getAccounts(any())).willReturn(new ArrayList<>(Arrays.asList(
//                new Account(1, 100, "first", "unblocked", 0),
//                new Account(2, 200, "third", "unblocked", 0),
//                new Account(3, 150, "second", "unblocked", 0)
//        )));
//
//        ArrayList<Account> res = underTest.getAccountsSortedBy(any(), "balance");
//
//        assertThat(res).isEqualTo(accounts);
//    }
//
//    @Test
//    void getAccountsSortedByTestNameCase() {
//        List<Account> accounts = Arrays.asList(
//                new Account(1, 1000, "first", "unblocked", 0),
//                new Account(3, 150, "second", "unblocked", 0),
//                new Account(2, 20, "third", "unblocked", 0)
//        );
//
//        given(dao.getAccounts(any())).willReturn(new ArrayList<>(Arrays.asList(
//                new Account(2, 20, "third", "unblocked", 0),
//                new Account(3, 150, "second", "unblocked", 0),
//                new Account(1, 1000, "first", "unblocked", 0)
//        )));
//
//        ArrayList<Account> res = underTest.getAccountsSortedBy(any(), "name");
//
//        assertThat(res).isEqualTo(accounts);
//    }
//
//    @Test
//    void getPaymentsSortedByTestIdCase() {
//        List<Payment> payments = Arrays.asList(
//                new Payment(1, 1000, "first", new Date(1111111), 0, 0),
//                new Payment(2, 20, "third", new Date(3333333), 0, 0),
//                new Payment(3, 150, "second", new Date(2222222), 0, 0)
//        );
//
//        given(dao.getPayments(any())).willReturn(new ArrayList<>(Arrays.asList(
//                new Payment(1, 1000, "first", new Date(1111111), 0, 0),
//                new Payment(3, 150, "second", new Date(2222222), 0, 0),
//                new Payment(2, 20, "third", new Date(3333333), 0, 0)
//        )));
//
//        ArrayList<Payment> res = underTest.getPaymentsSortedBy(any(), "id");
//
//        assertThat(res).isEqualTo(payments);
//    }
//
//    @Test
//    void getPaymentsSortedByTestPriceCase() {
//        List<Payment> payments = Arrays.asList(
//                new Payment(1, 10, "first", new Date(1111111), 0, 0),
//                new Payment(2, 20, "third", new Date(3333333), 0, 0),
//                new Payment(3, 150, "second", new Date(2222222), 0, 0)
//        );
//
//        given(dao.getPayments(any())).willReturn(new ArrayList<>(Arrays.asList(
//                new Payment(1, 10, "first", new Date(1111111), 0, 0),
//                new Payment(3, 150, "second", new Date(2222222), 0, 0),
//                new Payment(2, 20, "third", new Date(3333333), 0, 0)
//        )));
//
//        ArrayList<Payment> res = underTest.getPaymentsSortedBy(any(), "price");
//
//        assertThat(res).isEqualTo(payments);
//    }
//
//    @Test
//    void getPaymentsSortedByTestDateCase() {
//        List<Payment> payments = Arrays.asList(
//                new Payment(1, 1000, "first", new Date(1111111), 0, 0),
//                new Payment(3, 150, "second", new Date(2222222), 0, 0),
//                new Payment(2, 20, "third", new Date(3333333), 0, 0)
//        );
//
//        given(dao.getPayments(any())).willReturn(new ArrayList<>(Arrays.asList(
//                new Payment(3, 150, "second", new Date(2222222), 0, 0),
//                new Payment(2, 20, "third", new Date(3333333), 0, 0),
//                new Payment(1, 1000, "first", new Date(1111111), 0, 0)
//        )));
//
//        ArrayList<Payment> res = underTest.getPaymentsSortedBy(any(), "date");
//
//        assertThat(res).isEqualTo(payments);
//    }
//}