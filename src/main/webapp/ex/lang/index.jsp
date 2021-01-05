<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Languages</title>
<link rel="icon" type="image/x-icon" href="/jed/favicon.ico">
<link rel="stylesheet" type="text/css" href="/jed/css/simple.css">
</head>
<body>
    <h1>Languages</h1>

    <div>
        <c:if test="${error ne null}">
            <p>!!! ${error} !!!</p>
        </c:if>
    </div>

    <table>
        <tr>
            <th>id</th>
            <th>name</th>
        </tr>
        <c:forEach var="language" items="${languages}">
            <tr>
                <td>${language.id}</td>
                <td>${language.name}</td>
            </tr>
        </c:forEach>
    </table>

    <h2>New language</h2>
    <form action="new">
        <input name="name" placeholder="Language name">
        <button>Add</button>
    </form>

    <h2>Delete language</h2>
    <form action="del">
        <select name="id">
            <option value="">--Please choose a language--</option>
            <c:forEach var="language" items="${languages}">
                <option value="${language.id}">${language.name}</option>
            </c:forEach>
        </select>
        <button>Remove</button>
    </form>

    <%@include file="/ex/footer.html"%>
</body>
</html>