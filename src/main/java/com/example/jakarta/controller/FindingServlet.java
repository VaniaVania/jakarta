package com.example.jakarta.controller;

import com.example.jakarta.model.Finding;
import com.example.jakarta.model.impl.DefaultContactInfo;
import com.example.jakarta.model.impl.DefaultFinding;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet("/findings")
public class FindingServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Finding> defaultFindings = List.of(
                new DefaultFinding(1L, "Ключі від авто", "Знайдено на зупинці", Arrays.asList("ключі", "авто", "зупинка"),
                        new DefaultContactInfo("Іван", "+380501234567", "ivan@example.com")),
                new DefaultFinding(2L, "Смартфон", "Samsung чорного кольору", Arrays.asList("телефон", "чорний", "самсунг"),
                        new DefaultContactInfo("Олена", "+380671234567", "olena@example.com")),
                new DefaultFinding(3L, "Ноутбук", "Lenovo сірого кольору", null,
                        new DefaultContactInfo("Антон", "+380671234567", "olena@example.com"))
        );

        request.setAttribute("findings", defaultFindings);
        request.getRequestDispatcher("/jsp/findings.jsp")
                .forward(request, response);
    }
}
