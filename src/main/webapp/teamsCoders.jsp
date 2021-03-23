<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Teams</title>
<link rel="icon" href="data:;base64,=">
<link rel="stylesheet" type="text/css" href="/jeed/css/simple.css">
</head>
<body>
    <h1>Teams</h1>
    <c:forEach var="team" items="${teams}">
        <h2>${team.id}&nbsp;${team.name}</h2>
        <table>
            <tr>
                <th>id</th>
                <th>name</th>
            </tr>
            <c:forEach var="coder" items="${team.coders}">
                <tr>
                    <td>${coder.id}</td>
                    <td>${coder.firstName}&nbsp;${coder.lastName}</td>
                </tr>
            </c:forEach>
        </table>
    </c:forEach>

    <p>
        Back <a href="/jeed/index.html">home</a>
    </p>
</body>
</html>
