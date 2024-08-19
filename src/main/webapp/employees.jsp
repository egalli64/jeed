<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Employees</title>
<%@include file="/include/link.html"%>
</head>
<body>
    <header>
        <h1>Employees</h1>
        <nav>
            <a href="/jeed/index.html">Home</a>
        </nav>
    </header>

    <table>
        <tr>
            <th>id</th>
            <th>first name</th>
            <th>last name</th>
            <th>hire date</th>
            <th>salary</th>
        </tr>
        <c:forEach var="employee" items="${employees}">
            <tr>
                <td>${employee.id}</td>
                <td>${employee.firstName}</td>
                <td>${employee.lastName}</td>
                <td>${employee.hired}</td>
                <td>${employee.salary}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>