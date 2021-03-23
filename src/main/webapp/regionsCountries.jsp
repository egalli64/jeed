<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Regions</title>
<link rel="icon" href="data:;base64,=">
<link rel="stylesheet" type="text/css" href="/jeed/css/simple.css">
</head>
<body>
    <h1>Regions</h1>
    <c:forEach var="region" items="${regions}">
        <h2>${region.id}&nbsp;${region.name}</h2>
        <table>
            <tr>
                <th>id</th>
                <th>name</th>
            </tr>
            <c:forEach var="country" items="${region.countries}">
                <tr>
                    <td>${country.id}</td>
                    <td>${country.name}</td>
                </tr>
            </c:forEach>
        </table>
    </c:forEach>
    <p>
        Back <a href="/jeed/index.html">home</a>
    </p>
</body>
</html>
