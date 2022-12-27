<%-- 
    Introduction to Jakarta Enterprise Edition - JPA on Hibernate
    https://github.com/egalli64/jeed
 --%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Get region</title>
<link rel="icon" href="data:;base64,=">
<link rel="stylesheet" href="/jeed/css/simple.css">
</head>
<body>
    <header>
        <h1>Get region</h1>
        <nav>
            <a href="/jeed/index.html">Home</a>
        </nav>
    </header>

    <c:choose>
        <c:when test="${empty region}">
            <p>
                Failure operating on region
                <c:if test="${param.id ne null}">with id ${param.id}</c:if>
            </p>
        </c:when>
        <c:otherwise>
            <p>Region is ${region.id} ${region.name}</p>
        </c:otherwise>
    </c:choose>
</body>
</html>