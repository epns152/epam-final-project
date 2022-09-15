package com.pavlenko.payments.controller.users;

import com.pavlenko.payments.model.DB.CustomerDAO;
import com.pavlenko.payments.model.entity.Payment;
import com.pavlenko.payments.model.entity.User;
import com.pavlenko.payments.model.services.CustomerService;
import org.junit.jupiter.api.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class MyPaymentsTest {

    @Test
    void doGetTest() throws ServletException, IOException {
        MyPayments servlet = new MyPayments();

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        User user = mock(User.class);
        CustomerDAO dao = mock(CustomerDAO.class);
        CustomerService service = mock(CustomerService.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        ArrayList<Payment> payments = new ArrayList<>();

        when(req.getSession()).thenReturn(session);
        when(req.getAttribute("customerDAO")).thenReturn(dao);
        when(session.getAttribute("user")).thenReturn(user);
        when(service.getPaymentsSortedBy(user, eq(anyString()), anyInt(), anyInt())).thenReturn(payments);
        when(req.getRequestDispatcher(anyString())).thenReturn(dispatcher);

        when(session.getAttribute("payments-page")).thenReturn(1);
        when(session.getAttribute("payments-sorted-by")).thenReturn("id");

        servlet.doGet(req, resp);

        verify(req, times(6)).getSession();
        verify(req, times(1)).getAttribute("customerDAO");
        verify(req, times(6)).getParameter(anyString());

        assertThat(service.getPaymentsSortedBy(user, eq(anyString()), anyInt(), anyInt())).isEqualTo(payments);
    }
}