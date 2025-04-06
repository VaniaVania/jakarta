package com.example.jakarta.controller;

import com.example.jakarta.service.FindingService;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/delete-finding")
public class DeleteFindingServlet extends HttpServlet {

    @EJB
    private FindingService findingService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        long id = Long.parseLong(request.getParameter("id"));
        findingService.removeFinding(id);

        response.sendRedirect("/finding-app/findings");
    }
}

