package com.pavlenko.payments.controller;

import com.pavlenko.payments.model.DB.CustomerDAO;
import com.pavlenko.payments.model.entity.User;
import org.junit.jupiter.api.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class LoginTest {

    @Test
    void doPostTest() throws ServletException, IOException {
        Login servlet = new Login();

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        CustomerDAO dao = mock(CustomerDAO.class);
        User user = new User("customer", 3);

        when(req.getAttribute("customerDAO")).thenReturn(dao);
        when(req.getParameter("login")).thenReturn("login");
        when(req.getParameter("password")).thenReturn("pass");

        when(dao.login("login", "pass")).thenReturn(user);
        when(req.getSession()).thenReturn(session);

        servlet.doPost(req, resp);

        verify(dao, times(1)).login("login", "pass");
        verify(req, times(2)).getSession();
        verify(req, times(1)).getAttribute("customerDAO");
        verify(req, times(2)).getParameter(anyString());
    }
}