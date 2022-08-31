package com.pavlenko.payments.controller.filters_listeners;

import org.junit.jupiter.api.Test;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class LangFilterTest {

    @Test
    void doFilterTest() throws ServletException, IOException {
        LangFilter adminFilter = new LangFilter();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain chain = mock(FilterChain.class);
        HttpSession session = mock(HttpSession.class);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("lang")).thenReturn("en");

        adminFilter.doFilter(request, response, chain);

        verify(chain, times(1)).doFilter(request, response);
        verify(session).getAttribute("lang");
    }
}