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
<title>Countries and Region</title>
<link rel="icon" href="data:;base64,=">
<link rel="stylesheet" href="/jeed/css/simple.css">
</head>
<body>
    <header>
        <h1>Countries and Region</h1>
        <nav>
            <a href="/jeed/index.html#1toM">Home</a>
        </nav>
    </header>

    <table>
        <tr>
            <th>id</th>
            <th>name</th>
            <th>region</th>
        </tr>
        <c:forEach var="country" items="${countries}">
            <tr>
                <td>${country.id}</td>
                <td>${country.name}</td>
                <td>
                    <c:if test="${not empty country.region}">${country.region.name}</c:if>
                    <c:if test="${empty country.region}">-</c:if>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
