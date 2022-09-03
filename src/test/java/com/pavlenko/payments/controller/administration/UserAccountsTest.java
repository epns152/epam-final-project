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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class UserAccountsTest {

    @Test
    void doGetTest() throws ServletException, IOException {
        UserAccounts servlet = new UserAccounts();

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        AdminDAO dao = mock(AdminDAO.class);
        HttpSession session = mock(HttpSession.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        ArrayList<Account> accounts = new ArrayList<>();

        when(req.getAttribute("adminDAO")).thenReturn(dao);
        when(req.getAttribute("adminDAO")).thenReturn(dao);

        when(req.getSession()).thenReturn(session);
        when(dao.getAllUserAccounts(anyInt(), anyInt(), anyInt())).thenReturn(accounts);
        when(req.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        when(req.getParameter(anyString())).thenReturn("0");
        when(session.getAttribute("userId")).thenReturn(anyInt());


        servlet.doGet(req, resp);

        verify(req, times(3)).getSession();
        verify(req, times(1)).getAttribute("adminDAO");
        verify(req, times(4)).getParameter(anyString());

        assertThat(dao.getAllUserPayments(anyInt(), anyInt(), anyInt())).isEqualTo(accounts);
    }

    @Test
    void doGetTestFail() throws ServletException, IOException {
        UserAccounts servlet = new UserAccounts();

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        AdminDAO dao = mock(AdminDAO.class);
        HttpSession session = mock(HttpSession.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(req.getAttribute("adminDAO")).thenReturn(dao);

        when(req.getSession()).thenReturn(session);
        when(dao.getAllUserAccounts(anyInt(), anyInt(), anyInt())).thenThrow(RuntimeException.class);
        when(req.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        when(req.getParameter(anyString())).thenReturn("0");
        when(session.getAttribute("userId")).thenReturn(anyInt());

        servlet.doGet(req, resp);

        verify(req, times(2)).getSession();
        verify(req, times(1)).getAttribute("adminDAO");
        verify(req, times(4)).getParameter(anyString());
    }
}