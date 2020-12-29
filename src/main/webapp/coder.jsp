<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Coders</title>
<link rel="icon" type="image/x-icon" href="/jes/favicon.ico">
<link rel="stylesheet" type="text/css" href="/jes/css/simple.css">
</head>
<body>
    <h1>Coder</h1>
    <c:choose>
        <c:when test="${coder eq null}">
            <p>No coder found with id ${param.id}</p>
        </c:when>
        <c:otherwise>
            <p>Coder is ${coder.firstName} ${coder.lastName}</p>
        </c:otherwise>
    </c:choose>
    <p>
        Back <a href="/jed/index.html">home</a>
    </p>
</body>
</html>