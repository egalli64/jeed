<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Coders</title>
<link rel="icon" type="image/x-icon" href="/jed/favicon.ico">
<link rel="stylesheet" type="text/css" href="/jed/css/simple.css">
</head>
<body>
    <h1>Coders</h1>
    <c:forEach var="coder" items="${coders}">
        <h2>${coder.id}:&nbsp;${coder.firstName}&nbsp;${coder.lastName}</h2>
        <table>
            <tr>
                <th>id</th>
                <th>name</th>
            </tr>
            <c:forEach var="team" items="${coder.teams}">
                <tr>
                    <td>${team.id}</td>
                    <td>${team.name}</td>
                </tr>
            </c:forEach>
        </table>
    </c:forEach>

    <p>
        Back <a href="/jed/index.html">home</a>
    </p>
</body>
</html>
