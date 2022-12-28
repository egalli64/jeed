<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Car - Employee</title>
<link rel="icon" href="data:;base64,=">
<link rel="stylesheet" href="/jeed/css/simple.css">
</head>
<body>
    <header>
        <h1>Car - Employee</h1>
        <nav>
            <a href="/jeed/index.html">Home</a>
        </nav>
    </header>

    <c:choose>
        <c:when test="${not empty car}">
            <p>
                Car named ${car.name}
                <c:if test="${not empty car.employee}">
                    is assigned to ${car.employee.firstName} ${car.employee.lastName}
                </c:if>
            </p>
        </c:when>
        <c:when test="${not empty employee}">
            <p>
                ${employee.firstName} ${employee.lastName}
                <c:if test="${not empty employee.car}">
                    is assignee of ${employee.car.name}
                </c:if>
                <c:if test="${empty employee.car}">
                    has no car assigned!
                </c:if>
            </p>
        </c:when>
        <c:otherwise>
            <p>
                Can't get car/employee (or employee/car)
                <c:if test="${param.id ne null}">with id ${param.id}</c:if>
            </p>
        </c:otherwise>
    </c:choose>
</body>
</html>