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
<title>Employees and car</title>
<link rel="icon" href="data:;base64,=">
<link rel="stylesheet" type="text/css" href="/jeed/css/simple.css">
</head>
<body>
    <header>
        <h1>Employees and car</h1>
        <nav>
            <a href="/jeed/index.html#1to1">Home</a>
        </nav>
    </header>

    <table>
        <tr>
            <th>id</th>
            <th>first name</th>
            <th>last name</th>
            <th>car</th>
        </tr>
        <c:forEach var="employee" items="${employees}">
            <tr>
                <td>${employee.id}</td>
                <td>${employee.firstName}</td>
                <td>${employee.lastName}</td>
                <td>
                    <c:if test="${not empty employee.car}">${employee.car.name}</c:if>
                    <c:if test="${empty employee.car}">-</c:if>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>