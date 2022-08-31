package com.pavlenko.payments.controller.users;

import com.pavlenko.payments.model.DB.CustomerDAO;
import com.pavlenko.payments.model.entity.User;
import org.junit.jupiter.api.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class ProfileInfoTest {

    @Test
    void doGetTest() throws ServletException, IOException {
        ProfileInfo servlet = new ProfileInfo();

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        User muser = mock(User.class);
        CustomerDAO dao = mock(CustomerDAO.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        User user = new User("admin", 1, "asd", "wasd", "unblocked", new Date(123));

        when(req.getSession()).thenReturn(session);
        when(req.getAttribute("customerDAO")).thenReturn(dao);
        when(session.getAttribute("user")).thenReturn(muser);
        when(dao.getAllInfo(muser)).thenReturn(user);
        when(req.getRequestDispatcher("/profile.jsp")).thenReturn(dispatcher);

        servlet.doGet(req, resp);

        verify(req, times(2)).getSession();
        verify(dao, times(1)).getAllInfo(muser);

        assertThat(dao.getAllInfo(muser)).isEqualTo(user);
    }

    @Test
    void doGetTestFail() throws ServletException, IOException {
        ProfileInfo servlet = new ProfileInfo();

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        User user = mock(User.class);
        CustomerDAO dao = mock(CustomerDAO.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(req.getSession()).thenReturn(session);
        when(req.getAttribute("customerDAO")).thenReturn(dao);
        when(session.getAttribute("user")).thenReturn(user);
        when(dao.getAllInfo(user)).thenThrow(RuntimeException.class);
        when(req.getRequestDispatcher("/profile.jsp")).thenReturn(dispatcher);

        servlet.doGet(req, resp);

        verify(req, times(2)).getSession();
        verify(dao, times(1)).getAllInfo(user);
    }
}