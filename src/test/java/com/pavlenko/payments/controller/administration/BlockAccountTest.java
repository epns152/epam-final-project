package com.pavlenko.payments.controller.administration;

import com.pavlenko.payments.model.DB.AdminDAO;
import org.junit.jupiter.api.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class BlockAccountTest {

    @Test
    void doGetTestBlock() throws ServletException, IOException {
        BlockAccount servlet = new BlockAccount();

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        AdminDAO dao = mock(AdminDAO.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(req.getAttribute("adminDAO")).thenReturn(dao);

        when(req.getSession()).thenReturn(session);
        when(dao.blockAccount(anyInt())).thenReturn(true);
        when(dao.unblockAccount(anyInt())).thenReturn(true);
        when(req.getParameter(anyString())).thenReturn("0");
        when(req.getRequestDispatcher(anyString())).thenReturn(dispatcher);

        servlet.doGet(req, resp);

        verify(req, times(1)).getSession();
        verify(req, times(1)).getAttribute("adminDAO");
        verify(req, times(2)).getParameter(anyString());

        verify(dao, times(1)).blockAccount(anyInt());
    }

    @Test
    void doGetTestUnblock() throws ServletException, IOException {
        BlockAccount servlet = new BlockAccount();

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        AdminDAO dao = mock(AdminDAO.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(req.getAttribute("adminDAO")).thenReturn(dao);

        when(req.getSession()).thenReturn(session);
        when(dao.blockAccount(anyInt())).thenReturn(true);
        when(dao.unblockAccount(anyInt())).thenReturn(true);
        when(req.getParameter(anyString())).thenReturn("1");
        when(req.getRequestDispatcher(anyString())).thenReturn(dispatcher);

        servlet.doGet(req, resp);

        verify(req, times(1)).getSession();
        verify(req, times(1)).getAttribute("adminDAO");
        verify(req, times(3)).getParameter(anyString());

        verify(dao, times(1)).unblockAccount(anyInt());
    }

    @Test
    void doGetTestFail() throws ServletException, IOException {
        BlockAccount servlet = new BlockAccount();

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        AdminDAO dao = mock(AdminDAO.class);

        when(req.getAttribute("adminDAO")).thenReturn(dao);

        when(req.getSession()).thenReturn(session);
        when(dao.blockAccount(anyInt())).thenReturn(true);
        when(dao.unblockAccount(1)).thenThrow(RuntimeException.class);
        when(req.getParameter(anyString())).thenReturn("1");

        servlet.doGet(req, resp);

        verify(req, times(0)).getSession();
        verify(req, times(1)).getAttribute("adminDAO");
        verify(req, times(3)).getParameter(anyString());

        verify(dao, times(1)).unblockAccount(anyInt());
    }
}