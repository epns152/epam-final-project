package com.pavlenko.payments.controller.users;

import com.pavlenko.payments.model.DB.ConnectionPool;
import com.pavlenko.payments.model.DB.CustomerDAO;
import com.pavlenko.payments.model.entity.User;
import org.junit.jupiter.api.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import static org.mockito.Mockito.*;

class RequestToUnblockTest {
    private final static String path = "";

//    @Mock
//    private CustomerDAO dao;
//    RequestToUnblock servlet;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.initMocks(this);
//        servlet = new RequestToUnblock();
//    }

    @Test
    void doGetTest() throws ServletException, IOException, SQLException {
        RequestToUnblock servlet = new RequestToUnblock();

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        User user = mock(User.class);
        CustomerDAO dao = mock(CustomerDAO.class);

        when(req.getSession()).thenReturn(session);
        when(session.getAttribute("logged")).thenReturn(true);
        when(session.getAttribute("user")).thenReturn(user);
        when(req.getParameter("accountId")).thenReturn("2");
        when(dao.requestToUnblockAccount(1, 2)).thenReturn(true);

        servlet.doGet(req, resp);

        verify(req, times(3)).getSession();
        verify(session, times(2)).getAttribute("logged");
    }


}