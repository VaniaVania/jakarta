<%--
  Created by IntelliJ IDEA.
  User: Vania
  Date: 05.04.2025
  Time: 18:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html xml:lang="en">
<head>
    <meta charset="UTF-8">
    <title>Бюро знахідок</title>
    <style>
        body { font-family: sans-serif; }
        .card { border: 1px solid #ccc; padding: 1em; margin: 1em 0; }
    </style>
</head>
<body>
<h1>Список знахідок</h1>

<jsp:useBean id="findings" scope="request" type="java.util.List"/>
<c:forEach var="finding" items="${findings}">
    <div class="card">
        <h2>${fn:escapeXml(finding.title)}</h2>

        <!-- Умовне форматування -->
        <c:if test="${not empty finding.description}">
            <p><strong>Опис:</strong> ${fn:escapeXml(finding.description)}</p>
        </c:if>

        <!-- Елемент вибору -->
        <c:choose>
            <c:when test="${fn:length(finding.keywords) > 0}">
                <p><strong>Ключові слова:</strong>
                    <c:forEach var="kw" items="${finding.keywords}">
                        <span>${fn:escapeXml(kw)}</span>
                    </c:forEach>
                </p>
            </c:when>
            <c:otherwise>
                <p>Ключові слова відсутні.</p>
            </c:otherwise>
        </c:choose>

        <p><strong>Контакт:</strong>
                ${fn:escapeXml(finding.defaultContactInfo.name)} |
                ${fn:escapeXml(finding.defaultContactInfo.phone)} |
                ${fn:escapeXml(finding.defaultContactInfo.email)}
        </p>
    </div>
</c:forEach>

</body>
</html>

