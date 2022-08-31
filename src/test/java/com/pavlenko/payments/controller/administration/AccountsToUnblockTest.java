package com.pavlenko.payments.controller.administration;

import com.pavlenko.payments.model.DB.AdminDAO;
import com.pavlenko.payments.model.entity.Account;
import org.junit.jupiter.api.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class AccountsToUnblockTest {

    @Test
    void doGetTest() throws ServletException, IOException {
        AccountsToUnblock servlet = new AccountsToUnblock();

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        AdminDAO dao = mock(AdminDAO.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        ArrayList<Account> accounts = new ArrayList<>();

        when(req.getAttribute("adminDAO")).thenReturn(dao);

        when(req.getSession()).thenReturn(session);
        when(dao.getAllBlockedAccountsWithRequestToUnblock()).thenReturn(accounts);
        when(req.getRequestDispatcher(anyString())).thenReturn(dispatcher);

        servlet.doGet(req, resp);

        verify(req, times(1)).getSession();
        verify(req, times(1)).getAttribute("adminDAO");
        verify(req, times(0)).getParameter(anyString());

        verify(dao, times(1)).getAllBlockedAccountsWithRequestToUnblock();
    }

    @Test
    void doGetTestFail() throws ServletException, IOException {
        AccountsToUnblock servlet = new AccountsToUnblock();

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        AdminDAO dao = mock(AdminDAO.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        ArrayList<Account> accounts = new ArrayList<>();

        when(req.getAttribute("adminDAO")).thenReturn(dao);

        when(req.getSession()).thenReturn(session);
        when(dao.getAllBlockedAccountsWithRequestToUnblock()).thenThrow(RuntimeException.class);
        when(req.getRequestDispatcher(anyString())).thenReturn(dispatcher);

        servlet.doGet(req, resp);

        verify(req, times(0)).getSession();
        verify(req, times(1)).getAttribute("adminDAO");
        verify(req, times(0)).getParameter(anyString());

        verify(dao, times(1)).getAllBlockedAccountsWithRequestToUnblock();
    }
}