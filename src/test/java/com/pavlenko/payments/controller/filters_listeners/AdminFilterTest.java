package com.pavlenko.payments.controller.filters_listeners;

import com.pavlenko.payments.model.entity.User;
import org.junit.jupiter.api.Test;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class AdminFilterTest {

    @Test
    void doFilter() throws IOException, ServletException {
        AdminFilter adminFilter = new AdminFilter();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain chain = mock(FilterChain.class);
        HttpSession session = mock(HttpSession.class);
        User user = mock(User.class);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(anyString())).thenReturn(user);
        when(user.getRole()).thenReturn("admin");

        adminFilter.doFilter(request, response, chain);

        verify(response, times(0)).sendRedirect("index.jsp");
        verify(chain, times(1)).doFilter(request, response);
    }
}