<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Teams</title>
<link rel="icon" type="image/x-icon" href="/jed/favicon.ico">
<link rel="stylesheet" type="text/css" href="/jed/css/simple.css">
</head>
<body>
    <h1>Teams</h1>
    <table>
        <tr>
            <th>id</th>
            <th>name</th>
            <th>leader</th>
        </tr>
        <c:forEach var="team" items="${teams}">
            <tr>
                <td>${team.id}</td>
                <td>${team.name}</td>
                <td>${team.leader.firstName} ${team.leader.lastName}</td>
            </tr>
        </c:forEach>
    </table>
    <p>
        Back <a href="/jed/index.html">home</a>
    </p>
</body>
</html>
