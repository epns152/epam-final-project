package com.pavlenko.payments.controller.filters_listeners;

import com.pavlenko.payments.controller.users.RequestToUnblock;
import com.pavlenko.payments.model.DB.AdminDAOImpl;
import com.pavlenko.payments.model.DB.CustomerDAOImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class MyServletRequestListener implements ServletRequestListener {

    private static final Logger LOG = LoggerFactory.getLogger(RequestToUnblock.class);

    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
        ServletRequest servletRequest = servletRequestEvent.getServletRequest();
        servletRequest.removeAttribute("customerDAO");
        servletRequest.removeAttribute("adminDAO");
        LOG.debug("ServletRequest destroyed. Remote IP="+servletRequest.getRemoteAddr());
    }

    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        ServletRequest servletRequest = servletRequestEvent.getServletRequest();
        servletRequest.setAttribute("customerDAO", new CustomerDAOImpl());
        servletRequest.setAttribute("adminDAO", new AdminDAOImpl());
        LOG.debug("ServletRequest initialized. Remote IP="+servletRequest.getRemoteAddr());

    }

}