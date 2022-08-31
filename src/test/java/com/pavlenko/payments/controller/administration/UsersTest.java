package com.pavlenko.payments.controller.administration;

import com.pavlenko.payments.model.DB.AdminDAO;
import com.pavlenko.payments.model.entity.User;
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

class UsersTest {

    @Test
    void doGetTest() throws ServletException, IOException {
        Users servlet = new Users();

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        AdminDAO dao = mock(AdminDAO.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        ArrayList<User> users = new ArrayList<>();

        when(req.getSession()).thenReturn(session);
        when(req.getAttribute("adminDAO")).thenReturn(dao);

        when(dao.getAllUsers()).thenReturn(users);
        when(req.getRequestDispatcher(anyString())).thenReturn(dispatcher);

        servlet.doGet(req, resp);

        verify(req, times(2)).getSession();
        verify(req, times(1)).getAttribute("adminDAO");
        verify(req, times(0)).getParameter(anyString());
    }

    @Test
    void doGetTestFail() throws ServletException, IOException {
        Users servlet = new Users();

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        AdminDAO dao = mock(AdminDAO.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(req.getSession()).thenReturn(session);
        when(req.getAttribute("adminDAO")).thenReturn(dao);

        when(dao.getAllUsers()).thenThrow(RuntimeException.class);
        when(req.getRequestDispatcher(anyString())).thenReturn(dispatcher);

        servlet.doGet(req, resp);

        verify(req, times(1)).getSession();
        verify(req, times(1)).getAttribute("adminDAO");
        verify(req, times(0)).getParameter(anyString());
    }
}