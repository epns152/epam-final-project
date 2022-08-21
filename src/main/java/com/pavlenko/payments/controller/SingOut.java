package com.pavlenko.payments.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SignOut", value = "/sign-out")
public class SingOut extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("logged", false);
        request.getSession().setAttribute("user", null);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
