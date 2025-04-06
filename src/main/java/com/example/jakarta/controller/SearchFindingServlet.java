package com.example.jakarta.controller;

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

@WebServlet("/search-findings")
public class SearchFindingServlet extends HttpServlet {

    @EJB
    private FindingService findingService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String keywords = request.getParameter("keywords");
        if (keywords != null && !keywords.isEmpty()) {
            List<String> keywordList = Arrays.asList(keywords.split(","));
            List<DefaultFinding> findings = findingService.searchByKeywords(keywordList);
            request.setAttribute("findings", findings);
        }

        request.getRequestDispatcher("/jsp/findings.jsp").forward(request, response);
    }
}

