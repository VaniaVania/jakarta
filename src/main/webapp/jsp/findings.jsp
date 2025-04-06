<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="uk">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Знахідки</title>
</head>
<body>

<h1>Список знахідок</h1>

<form action="/finding-app/search-findings" method="get">
    <label for="keywords">Пошук за ключовими словами:</label>
    <input type="text" id="keywords" name="keywords" />
    <input type="submit" value="Пошук" />
</form>

<hr>

<a href="/finding-app/create-finding">Додати нову знахідку</a>

<hr>

<c:if test="${not empty findings}">
    <ul>
        <c:forEach items="${findings}" var="finding">
            <li>
                <h3>${finding.title}</h3>
                <p>${finding.description}</p>

                <a href="/finding-app/view-finding?id=${finding.id}">Переглянути</a>

                <form action="/finding-app/delete-finding" method="post" style="display:inline;">
                    <input type="hidden" name="id" value="${finding.id}" />
                    <input type="submit" value="Видалити" onclick="return confirm('Ви впевнені, що хочете видалити цю знахідку?');" />
                </form>
            </li>
        </c:forEach>
    </ul>
</c:if>

<c:if test="${empty findings}">
    <p>Зараз немає жодної знахідки.</p>
</c:if>

</body>
</html>
