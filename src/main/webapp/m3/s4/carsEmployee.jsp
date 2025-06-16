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
<title>Cars and Employee</title>
<%@include file="/include/link.html"%>
</head>
<body>
    <header>
        <h1>Cars and Employee</h1>
        <nav>
            <a href="/jeed/index.html#1to1">Home</a>
        </nav>
    </header>

    <table>
        <tr>
            <th>id</th>
            <th>car name</th>
            <th>employee name</th>
        </tr>
        <c:forEach var="car" items="${cars}">
            <tr>
                <td>${car.id}</td>
                <td>${car.name}</td>
                <td>
                    <c:if test="${car.employee ne null}">${car.employee.firstName} ${car.employee.lastName}</c:if>
                    <c:if test="${car.employee eq null}">-</c:if>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
