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
<title>Regions</title>
<link rel="icon" href="data:;base64,=">
<link rel="stylesheet" href="/jeed/css/simple.css">
</head>
<body>
    <header>
        <h1>Regions</h1>
        <nav>
            <a href="/jeed/index.html#1toM">Home</a>
        </nav>
    </header>

    <c:forEach var="region" items="${regions}">
        <h2>${region.id}&nbsp;${region.name}</h2>
    </c:forEach>
</body>
</html>
