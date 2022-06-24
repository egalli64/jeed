<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Cars and Services</title>
<link rel="icon" href="data:;base64,=">
<link rel="stylesheet" type="text/css" href="/jeed/css/simple.css">
</head>
<body>
    <h1>Cars and Services</h1>
    <c:forEach var="car" items="${cars}">
        <h2>${car.id}&nbsp;${car.name}</h2>
        <table>
            <tr>
                <th>id</th>
                <th>name</th>
            </tr>
            <c:forEach var="service" items="${car.services}">
                <tr>
                    <td>${service.id}</td>
                    <td>${service.name}</td>
                </tr>
            </c:forEach>
        </table>
    </c:forEach>

    <p>
        Back <a href="/jeed/index.html">home</a>
    </p>
</body>
</html>
