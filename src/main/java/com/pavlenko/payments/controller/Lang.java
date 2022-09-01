package com.pavlenko.payments.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Lang", value = "/lang")
public class Lang extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(Login.class);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("lang", req.getParameter("lang"));
        if (req.getParameter("current").isEmpty()) {
            resp.sendRedirect("index.jsp");
            LOG.info("Lang servlet redirected to index.jsp");
        } else {
            resp.sendRedirect(req.getParameter("current"));
            LOG.info("Lang servlet redirected to " + req.getParameter("current"));
        }
    }
}
