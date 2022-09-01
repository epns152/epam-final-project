package com.pavlenko.payments.controller.filters_listeners;

import com.pavlenko.payments.model.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/add-account", "/add-payment", "/discard",
        "/block-account", "/make-payment", "/accounts", "/payments", "/request-to-unblock"})
public class UserFilter implements Filter {

    private static final Logger LOG = LoggerFactory.getLogger(UserFilter.class);

    @Override
    public void doFilter(ServletRequest req,
                         ServletResponse resp,
                         FilterChain filterChain)
            throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest) req).getSession();
        User user = (User) session.getAttribute("user");
        LOG.info("user filter executed");
        if (user != null && !user.getRole().equals("customer")) {
            ((HttpServletResponse) resp).sendRedirect("index.jsp");
        } else {
            filterChain.doFilter(req, resp);
        }
    }
}
