package com.pavlenko.payments.controller.users;

import com.pavlenko.payments.model.DB.CustomerDAO;
import com.pavlenko.payments.model.entity.User;
import org.junit.jupiter.api.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.mockito.Mockito.*;

class RequestToUnblockTest {

    @Test
    void doGetTest() throws ServletException, IOException {
        RequestToUnblock servlet = new RequestToUnblock();

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        User user = mock(User.class);
        CustomerDAO dao = mock(CustomerDAO.class);

        when(req.getSession()).thenReturn(session);
        when(req.getAttribute("customerDAO")).thenReturn(dao);
        when(session.getAttribute("user")).thenReturn(user);
        when(req.getParameter("accountId")).thenReturn("2");
        when(dao.requestToUnblockAccount(1, 2)).thenReturn(true);

        servlet.doGet(req, resp);

        verify(req, times(1)).getSession();
        verify(dao, times(1)).requestToUnblockAccount(user.getId(), Integer.parseInt(req.getParameter("accountId")));
    }

    @Test
    void doGetTestFail() throws ServletException, IOException {
        RequestToUnblock servlet = new RequestToUnblock();

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        User user = mock(User.class);
        CustomerDAO dao = mock(CustomerDAO.class);

        when(req.getSession()).thenReturn(session);
        when(req.getAttribute("customerDAO")).thenReturn(dao);
        when(session.getAttribute("user")).thenReturn(user);
        when(req.getParameter("accountId")).thenReturn("2");
        when(dao.requestToUnblockAccount(0, 2)).thenThrow(RuntimeException.class);

        servlet.doGet(req, resp);

        verify(req, times(1)).getSession();
        verify(dao, times(1)).requestToUnblockAccount(user.getId(), Integer.parseInt(req.getParameter("accountId")));
    }
}