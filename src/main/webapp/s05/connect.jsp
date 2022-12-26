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
<title>Hibernate Session</title>
<link rel="icon" href="data:;base64,=">
<link rel="stylesheet" href="/jeed/css/simple.css">
</head>
<body>
    <header>
        <h1>Check Hibernate session</h1>
        <nav>
            <a href="/jeed/index.html">Home</a>
        </nav>
    </header>

    <p>
        The connection has
        <c:if test="${not connected}">
            <b>NOT</b>
        </c:if>
        worked correctly
    </p>
</body>
</html>