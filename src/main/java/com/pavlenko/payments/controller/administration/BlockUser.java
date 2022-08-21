package com.pavlenko.payments.controller.administration;

import com.pavlenko.payments.model.DB.AdminDAO;
import com.pavlenko.payments.model.DB.AdminDAOImpl;
import com.pavlenko.payments.model.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "BlockUser", value = "/block-user")
public class BlockUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("logged") != null && (boolean) req.getSession().getAttribute("logged")) {
            User user = (User) req.getSession().getAttribute("user");
            if (user.getRole().equals("customer")) {
                resp.sendError(404, "not admin logged");
            }
            AdminDAO adminDAO = new AdminDAOImpl();
            if (req.getParameter("status").equals("0")) {
                adminDAO.blockUser(Integer.parseInt(req.getParameter("userId")));
            } else if (req.getParameter("status").equals("1")) {
                adminDAO.unblockUser(Integer.parseInt(req.getParameter("userId")));
            }
            req.getRequestDispatcher("/users").forward(req, resp);
        } else resp.sendError(404, "not logged");
    }
}
