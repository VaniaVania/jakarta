<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Перегляд знахідки</title>
</head>
<body>
<h2>${finding.title}</h2>
<p><strong>Опис:</strong> ${finding.description}</p>

<p><strong>Контактна інформація:</strong></p>
<p>Ім'я: ${finding.defaultContactInfo.name}</p>
<p>Телефон: ${finding.defaultContactInfo.phone}</p>
<p>Email: ${finding.defaultContactInfo.email}</p>

<h3>Ключові слова:</h3>
<ul>
    <c:forEach items="${finding.keywords}" var="keyword">
        <li>${keyword}</li>
    </c:forEach>
</ul>

</body>
</html>
