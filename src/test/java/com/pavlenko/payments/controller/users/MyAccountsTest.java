package com.pavlenko.payments.controller.users;

import com.pavlenko.payments.model.DB.CustomerDAO;
import com.pavlenko.payments.model.entity.Account;
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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class MyAccountsTest {

    @Test
    void doGetTest() throws ServletException, IOException {
        MyAccounts servlet = new MyAccounts();

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        User user = mock(User.class);
        CustomerDAO dao = mock(CustomerDAO.class);
        CustomerService service = mock(CustomerService.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        ArrayList<Account> accounts = new ArrayList<>();

        when(req.getSession()).thenReturn(session);
        when(req.getAttribute("customerDAO")).thenReturn(dao);

        when(session.getAttribute("user")).thenReturn(user);
        when(session.getAttribute("accounts-page")).thenReturn(1);
        when(session.getAttribute("accounts-sorted-by")).thenReturn("id");

        when(service.getAccountsSortedBy(user, eq(anyString()), anyInt(), anyInt())).thenReturn(accounts);
        when(req.getRequestDispatcher(anyString())).thenReturn(dispatcher);

        servlet.doGet(req, resp);

        verify(req, times(6)).getSession();
        verify(req, times(1)).getAttribute("customerDAO");
        verify(req, times(8)).getParameter(anyString());

        assertThat(service.getAccountsSortedBy(user, eq(anyString()), anyInt(), anyInt())).isEqualTo(accounts);
    }
}