package com.pavlenko.payments.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SignOut", value = "/sign-out")
public class SingOut extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(SingOut.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("logged");
        request.getSession().removeAttribute("user");
        request.getRequestDispatcher("index.jsp").forward(request, response);
        LOG.info("forwarded to index.jsp");
    }
}
