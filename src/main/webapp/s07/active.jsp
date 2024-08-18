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
<title>JPA Entity Manager</title>
<link rel="icon" href="data:;base64,=">
<link rel="stylesheet" href="/jeed/css/simple.css">
</head>
<body>
    <header>
        <h1>Check JPA Entity Manager</h1>
        <nav>
            <a href="/jeed/index.html">Home</a>
        </nav>
    </header>

    <p>
        The Entity Manager has
        <c:if test="${not activated}">
            <b>NOT</b>
        </c:if>
        been activated correctly
    </p>
</body>
</html>