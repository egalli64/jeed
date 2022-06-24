<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Car and Employee</title>
<link rel="icon" href="data:;base64,=">
<link rel="stylesheet" type="text/css" href="/jeed/css/simple.css">
</head>
<body>
    <h1>Car and Employee</h1>
    <c:choose>
        <c:when test="${car eq null}">
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
    <p>
        Back <a href="/jeed/index.html">home</a>
    </p>
</body>
</html>