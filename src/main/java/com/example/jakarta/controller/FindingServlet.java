package com.example.jakarta.controller;

import com.example.jakarta.model.Finding;
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
import java.util.List;

@WebServlet("/findings")
public class FindingServlet extends HttpServlet {

    @EJB
    private FindingService findingService;

    @Override
    public void init() {
        findingService.addFinding(new DefaultFinding(null, "Ключі від авто", "Знайдено на зупинці", List.of("ключі", "авто", "зупинка"),
                new DefaultContactInfo("Іван", "+380501234567", "ivan@example.com")));

        findingService.addFinding(new DefaultFinding(null, "Смартфон", "Samsung чорного кольору", List.of("телефон", "чорний", "самсунг"),
                new DefaultContactInfo("Олена", "+380671234567", "olena@example.com")));

        findingService.addFinding(new DefaultFinding(null, "Ноутбук", "Lenovo сірого кольору", null,
                new DefaultContactInfo("Антон", "+380671234567", "anton@example.com")));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Finding> findings = findingService.findAll();
        request.setAttribute("findings", findings);
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("/jsp/findings.jsp")
                .forward(request, response);
    }
}
