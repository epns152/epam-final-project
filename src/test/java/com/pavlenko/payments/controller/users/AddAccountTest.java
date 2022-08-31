package com.pavlenko.payments.controller.users;

import com.pavlenko.payments.model.DB.CustomerDAO;
import com.pavlenko.payments.model.entity.User;
import org.junit.jupiter.api.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class AddAccountTest {

    @Test
    void doPostTest() throws ServletException, IOException {
        AddAccount servlet = new AddAccount();

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        User user = mock(User.class);
        CustomerDAO dao = mock(CustomerDAO.class);

        when(req.getSession()).thenReturn(session);
        when(req.getAttribute("customerDAO")).thenReturn(dao);

        when(session.getAttribute("user")).thenReturn(user);

        when(dao.addAccount(anyInt(), anyString(), anyInt())).thenReturn(true);
        when(req.getParameter(anyString())).thenReturn("0");

        servlet.doPost(req, resp);

        verify(req, times(1)).getSession();
        verify(req, times(1)).getAttribute("customerDAO");
        verify(req, times(2)).getParameter(anyString());
        verify(resp, times(1)).sendRedirect(anyString());
    }

    @Test
    void doPostTestFail() throws ServletException, IOException {
        AddAccount servlet = new AddAccount();

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        User user = mock(User.class);
        CustomerDAO dao = mock(CustomerDAO.class);

        when(req.getSession()).thenReturn(session);
        when(req.getAttribute("customerDAO")).thenReturn(dao);

        when(session.getAttribute("user")).thenReturn(user);

        when(dao.addAccount(anyInt(), anyString(), anyDouble())).thenThrow(RuntimeException.class);


        when(req.getParameter(anyString())).thenReturn("0");

        servlet.doPost(req, resp);

        verify(req, times(1)).getSession();
        verify(req, times(1)).getAttribute("customerDAO");
        verify(req, times(2)).getParameter(anyString());
        verify(resp, times(1)).sendError(500, "Sorry, something went wrong...(((");
    }
}