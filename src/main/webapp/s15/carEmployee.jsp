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
<title>Car and Employee</title>
<%@include file="/include/link.html"%>
</head>
<body>
    <header>
        <h1>Car and Employee</h1>
        <nav>
            <a href="/jeed/index.html#1to1">Home</a>
        </nav>
    </header>

    <c:choose>
        <c:when test="${empty car}">
            <p>
                Failure operating on car
                <c:if test="${param.id ne null}">with id ${param.id}</c:if>
            </p>
        </c:when>
        <c:otherwise>
            <p>
                ${car.name} has been assigned to
                <c:if test="${car.employee ne null}">${car.employee.firstName} ${car.employee.lastName}</c:if>
                <c:if test="${car.employee eq null}">nobody</c:if>
            </p>
        </c:otherwise>
    </c:choose>
</body>
</html>