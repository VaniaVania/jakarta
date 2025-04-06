package com.example.jakarta.controller;

import com.example.jakarta.model.impl.DefaultContactInfo;
import com.example.jakarta.model.impl.DefaultFinding;
import com.example.jakarta.service.FindingService;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet("/create-finding")
public class CreateFindingServlet extends HttpServlet {

    @EJB
    private FindingService findingService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/createFinding.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String keywords = request.getParameter("keywords");
        String contactName = request.getParameter("contactName");
        String contactPhone = request.getParameter("contactPhone");
        String contactEmail = request.getParameter("contactEmail");
        List<String> keywordList = Arrays.asList(keywords.split(","));
        DefaultFinding finding = new DefaultFinding(null, name, description, keywordList,
                new DefaultContactInfo(contactName, contactPhone, contactEmail));
        findingService.addFinding(finding);

        response.sendRedirect("/finding-app/findings");
    }
}