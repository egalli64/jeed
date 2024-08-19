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
<title>Employee and Car</title>
<%@include file="/include/link.html"%>
</head>
<body>
    <header>
        <h1>Employee and Car</h1>
        <nav>
            <a href="/jeed/index.html#1to1">Home</a>
        </nav>
    </header>

    <c:choose>
        <c:when test="${empty employee}">
            <p>
                Failure operating on employee
                <c:if test="${empty param.id}">with id ${param.id}</c:if>
            </p>
        </c:when>
        <c:otherwise>
            <p>${employee.firstName} ${employee.lastName}
                <c:if test="${not empty employee.car}">uses ${employee.car.name}</c:if>
                <c:if test="${empty employee.car}">has no car assigned</c:if>
            </p>
        </c:otherwise>
    </c:choose>
</body>
</html>