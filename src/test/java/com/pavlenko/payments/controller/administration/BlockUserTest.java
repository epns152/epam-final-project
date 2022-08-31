package com.pavlenko.payments.controller.administration;

import com.pavlenko.payments.model.DB.AdminDAO;
import org.junit.jupiter.api.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class BlockUserTest {

    @Test
    void doGetTestBlock() throws ServletException, IOException {
        BlockUser servlet = new BlockUser();

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        AdminDAO dao = mock(AdminDAO.class);

        when(req.getAttribute("adminDAO")).thenReturn(dao);

        when(dao.blockUser(anyInt())).thenReturn(true);
        when(dao.unblockUser(anyInt())).thenReturn(true);
        when(req.getParameter(anyString())).thenReturn("0");

        servlet.doGet(req, resp);

        verify(req, times(0)).getSession();
        verify(req, times(1)).getAttribute("adminDAO");
        verify(req, times(2)).getParameter(anyString());

        verify(dao, times(1)).blockUser(anyInt());
    }

    @Test
    void doGetTestUnblock() throws ServletException, IOException {
        BlockUser servlet = new BlockUser();

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        AdminDAO dao = mock(AdminDAO.class);

        when(req.getAttribute("adminDAO")).thenReturn(dao);

        when(dao.blockUser(anyInt())).thenReturn(true);
        when(dao.unblockUser(anyInt())).thenReturn(true);
        when(req.getParameter(anyString())).thenReturn("1");

        servlet.doGet(req, resp);

        verify(req, times(0)).getSession();
        verify(req, times(1)).getAttribute("adminDAO");
        verify(req, times(3)).getParameter(anyString());

        verify(dao, times(1)).unblockUser(anyInt());
    }

    @Test
    void doGetTestFail() throws ServletException, IOException {
        BlockUser servlet = new BlockUser();

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        AdminDAO dao = mock(AdminDAO.class);

        when(req.getAttribute("adminDAO")).thenReturn(dao);

        when(dao.blockUser(anyInt())).thenReturn(true);
        when(dao.unblockUser(1)).thenThrow(RuntimeException.class);
        when(req.getParameter(anyString())).thenReturn("1");

        servlet.doGet(req, resp);

        verify(req, times(0)).getSession();
        verify(req, times(1)).getAttribute("adminDAO");
        verify(req, times(3)).getParameter(anyString());

        verify(dao, times(1)).unblockUser(anyInt());
    }
}