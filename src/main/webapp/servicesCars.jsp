<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Services and Cars</title>
<link rel="icon" href="data:;base64,=">
<link rel="stylesheet" type="text/css" href="/jeed/css/simple.css">
</head>
<body>
    <h1>Services and Cars</h1>
    <c:forEach var="service" items="${services}">
        <h2>${service.id}&nbsp;${service.name}</h2>
        <table>
            <tr>
                <th>id</th>
                <th>name</th>
            </tr>
            <c:forEach var="car" items="${service.cars}">
                <tr>
                    <td>${car.id}</td>
                    <td>${car.name}</td>
                </tr>
            </c:forEach>
        </table>
    </c:forEach>

    <p>
        Back <a href="/jeed/index.html">home</a>
    </p>
</body>
</html>
