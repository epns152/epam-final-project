package com.pavlenko.payments.controller;

import com.pavlenko.payments.model.DB.CustomerDAO;
import com.pavlenko.payments.model.DB.CustomerDAOImpl;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
//@WebServlet("/h")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        CustomerDAO customerDAO = new CustomerDAOImpl();
        String mess = String.valueOf(customerDAO.makePayment( 1, 2));
//        String mess = request.getParameter("name");
        System.out.println("cringe");
//        String mess = String.valueOf(customerDAO.register("romann", "pavlenko", "роман", "Павленко"));
//        String mess = String.valueOf(customerDAO.register("romannn", "pavlenko", "романієї", "Павленко"));
//        response.setContentType("text/html;charset=windows-1251");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + mess + "</h1>");
        out.println("</body></html>");
    }

    public void destroy() {
    }


}