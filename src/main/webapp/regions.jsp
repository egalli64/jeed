<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Regions</title>
<link rel="icon" type="image/x-icon" href="/jeed/favicon.ico">
<link rel="stylesheet" type="text/css" href="/jeed/css/simple.css">
</head>
<body>
    <h1>Regions</h1>
    <c:forEach var="region" items="${regions}">
        <h2>${region.id}&nbsp;${region.name}</h2>
    </c:forEach>
    <p>
        Back <a href="/jeed/index.html">home</a>
    </p>
</body>
</html>
