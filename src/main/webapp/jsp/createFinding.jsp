<%--
  Created by IntelliJ IDEA.
  User: Vania
  Date: 06.04.2025
  Time: 20:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/finding-app/create-finding" method="post">
    <label for="name">Назва:</label>
    <input type="text" id="name" name="name" required /><br/>

    <label for="description">Опис:</label>
    <input type="text" id="description" name="description" required /><br/>

    <label for="keywords">Ключові слова (через кому):</label>
    <input type="text" id="keywords" name="keywords" required /><br/>

    <label for="contactName">Ім'я контакту:</label>
    <input type="text" id="contactName" name="contactName" required /><br/>

    <label for="contactPhone">Телефон:</label>
    <input type="text" id="contactPhone" name="contactPhone" required /><br/>

    <label for="contactEmail">Email:</label>
    <input type="email" id="contactEmail" name="contactEmail" required /><br/>

    <input type="submit" value="Створити" />
</form>

</body>
</html>
