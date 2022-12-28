<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Cars</title>
<link rel="icon" href="data:;base64,=">
<link rel="stylesheet" href="/jeed/css/simple.css">
</head>
<body>
    <header>
        <h1>Cars</h1>
        <nav>
            <a href="/jeed/index.html">Home</a>
        </nav>
    </header>

    <table>
        <tr>
            <th>id</th>
            <th>name</th>
            <th>employee</th>
        </tr>
        <c:forEach var="car" items="${cars}">
            <tr>
                <td>${car.id}</td>
                <td>${car.name}</td>
                <td>${car.employee.firstName}&nbsp;${car.employee.lastName}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>