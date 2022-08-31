package com.pavlenko.payments.controller.administration;

import com.pavlenko.payments.model.DB.AdminDAO;
import com.pavlenko.payments.model.entity.Payment;
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
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class UserPaymentsTest {

    @Test
    void doGetTest() throws ServletException, IOException {
        UserPayments servlet = new UserPayments();

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        AdminDAO dao = mock(AdminDAO.class);
        HttpSession session = mock(HttpSession.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        ArrayList<Payment> payments = new ArrayList<>();

        when(req.getAttribute("adminDAO")).thenReturn(dao);

        when(req.getSession()).thenReturn(session);
        when(dao.getAllUserPayments(anyInt())).thenReturn(payments);
        when(req.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        when(req.getParameter(anyString())).thenReturn("0");

        servlet.doGet(req, resp);

        verify(req, times(1)).getSession();
        verify(req, times(1)).getAttribute("adminDAO");
        verify(req, times(1)).getParameter(anyString());

        assertThat(dao.getAllUserPayments(anyInt())).isEqualTo(payments);
    }

    @Test
    void doGetTestFail() throws ServletException, IOException {
        UserPayments servlet = new UserPayments();

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        AdminDAO dao = mock(AdminDAO.class);
        HttpSession session = mock(HttpSession.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(req.getAttribute("adminDAO")).thenReturn(dao);

        when(req.getSession()).thenReturn(session);
        when(dao.getAllUserPayments(0)).thenThrow(RuntimeException.class);
        when(req.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        when(req.getParameter(anyString())).thenReturn("0");

        servlet.doGet(req, resp);

        verify(req, times(0)).getSession();
        verify(req, times(1)).getAttribute("adminDAO");
        verify(req, times(1)).getParameter(anyString());
    }
}