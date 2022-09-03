package com.pavlenko.payments.controller.users;

import com.pavlenko.payments.model.DB.CustomerDAO;
import org.junit.jupiter.api.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class MakePaymentTest {

    @Test
    void doGetTest() throws ServletException, IOException {
        MakePayment servlet = new MakePayment();

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        CustomerDAO dao = mock(CustomerDAO.class);

        when(req.getSession()).thenReturn(session);
        when(req.getAttribute("customerDAO")).thenReturn(dao);
        when(req.getParameter(anyString())).thenReturn("0");
        when(dao.makePayment(anyInt(), anyInt())).thenReturn(true);

        servlet.doGet(req, resp);

        verify(req, times(2)).getSession();
        verify(req, times(2)).getParameter(anyString());
        verify(req, times(1)).getAttribute("customerDAO");
        verify(dao, times(1)).makePayment(anyInt(), anyInt());
    }

    @Test
    void doGetTestFail() throws ServletException, IOException {
        MakePayment servlet = new MakePayment();

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        CustomerDAO dao = mock(CustomerDAO.class);

        when(req.getSession()).thenReturn(session);
        when(req.getAttribute("customerDAO")).thenReturn(dao);
        when(req.getParameter(anyString())).thenReturn("0");
        when(dao.makePayment(anyInt(), anyInt())).thenThrow(RuntimeException.class);

        servlet.doGet(req, resp);

        verify(req, times(0)).getSession();
        verify(req, times(2)).getParameter(anyString());
        verify(req, times(1)).getAttribute("customerDAO");
        verify(dao, times(1)).makePayment(anyInt(), anyInt());
    }
}