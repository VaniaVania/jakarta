package com.example.jakarta.controller;

import com.example.jakarta.model.Finding;
import com.example.jakarta.service.FindingService;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/view-finding")
public class ViewFindingServlet extends HttpServlet {

    @EJB
    private FindingService findingService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        Finding finding = findingService.findById(id);
        request.setAttribute("finding", finding);
        request.getRequestDispatcher("/jsp/viewFinding.jsp").forward(request, response);
    }
}
