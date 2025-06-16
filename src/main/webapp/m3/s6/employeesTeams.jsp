<%-- 
    Introduction to Hibernate - JEE ORM
    https://github.com/egalli64/jeed
 --%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Employees and Teams</title>
<%@include file="/include/link.html"%>
</head>
<body>
    <header>
        <h1>Employees and Teams</h1>
        <nav>
            <a href="/jeed/index.html#MtoM">Home</a>
        </nav>
    </header>

    <c:forEach var="employee" items="${employees}">
        <h2>${employee.id}:&nbsp;${employee.firstName}&nbsp;${employee.lastName}</h2>
        <table>
            <tr>
                <th>id</th>
                <th>name</th>
            </tr>
            <c:forEach var="team" items="${employee.teams}">
                <tr>
                    <td>${team.id}</td>
                    <td>${team.name}</td>
                </tr>
            </c:forEach>
        </table>
    </c:forEach>
</body>
</html>
