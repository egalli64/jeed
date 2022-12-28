<%-- 
    Introduction to Jakarta Enterprise Edition - JPA on Hibernate
    https://github.com/egalli64/jeed
 --%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Teams and Employees</title>
<link rel="icon" href="data:;base64,=">
<link rel="stylesheet" href="/jeed/css/simple.css">
</head>
<body>
    <header>
        <h1>Teams and Employees</h1>
        <nav>
            <a href="/jeed/index.html#MtoM">Home</a>
        </nav>
    </header>

    <c:forEach var="team" items="${teams}">
        <h2>${team.id}&nbsp;${team.name}</h2>
        <table>
            <tr>
                <th>id</th>
                <th>name</th>
            </tr>
            <c:forEach var="employee" items="${team.employees}">
                <tr>
                    <td>${employee.id}</td>
                    <td>${employee.firstName}&nbsp;${employee.lastName}</td>
                </tr>
            </c:forEach>
        </table>
    </c:forEach>
</body>
</html>
