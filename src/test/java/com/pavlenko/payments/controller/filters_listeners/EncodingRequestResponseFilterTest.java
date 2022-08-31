package com.pavlenko.payments.controller.filters_listeners;

import org.junit.jupiter.api.Test;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class EncodingRequestResponseFilterTest {

    @Test
    void doFilterTest() throws ServletException, IOException {
        EncodingRequestResponseFilter adminFilter = new EncodingRequestResponseFilter();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain chain = mock(FilterChain.class);

        adminFilter.doFilter(request, response, chain);

        verify(chain, times(1)).doFilter(request, response);
        verify(request).setCharacterEncoding("UTF-8");
        verify(response).setCharacterEncoding("UTF-8");
    }
}