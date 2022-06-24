<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Countries and Region</title>
<link rel="icon" href="data:;base64,=">
<link rel="stylesheet" type="text/css" href="/jeed/css/simple.css">
</head>
<body>
    <h1>Countries and Region</h1>
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
                    <c:if test="${country.region ne null}">${country.region.name}</c:if>
                    <c:if test="${country.region eq null}">-</c:if>
                </td>
            </tr>
        </c:forEach>
    </table>
    <p>
        Back <a href="/jeed/index.html">home</a>
    </p>
</body>
</html>
