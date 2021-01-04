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
    <table>
        <tr>
            <th>id</th>
            <th>name</th>
            <th>leading team</th>
        </tr>
        <c:forEach var="coder" items="${coders}">
            <tr>
                <td>${coder.id}</td>
                <td>${coder.firstName} ${coder.lastName}</td>
                <td>
                    <c:if test="${coder.leadingTeam ne null}">
                        ${coder.leadingTeam.name}
                    </c:if>
                    <c:if test="${coder.leadingTeam eq null}">-</c:if>
                </td>
            </tr>
        </c:forEach>
    </table>
    <p>
        Back <a href="/jed/index.html">home</a>
    </p>
</body>
</html>
