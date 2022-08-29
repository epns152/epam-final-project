package com.pavlenko.payments.controller.filters;

import com.pavlenko.payments.model.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/accounts-to-unblock", "/a-block-account", "/block-user", "/user-accounts",
                    "/user-payments", "/users", "/add-account", "/add-payment",
                    "/block-account", "/make-payment", "/accounts", "/payments", "/profile", "/request-to-unblock"})
public class AuthFilter implements Filter {

    private static final Logger LOG = LoggerFactory.getLogger(AuthFilter.class);

    @Override
    public void doFilter(ServletRequest req,
                         ServletResponse resp,
                         FilterChain filterChain)
            throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest) req).getSession();
        User user = (User) session.getAttribute("user");
        LOG.info("auth filter executed");
        if (user == null) {
            ((HttpServletResponse) resp).sendRedirect("index.jsp");
        } else {
            filterChain.doFilter(req, resp);
        }
    }
}
