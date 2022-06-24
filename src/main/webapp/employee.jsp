<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Coder</title>
<link rel="icon" href="data:;base64,=">
<link rel="stylesheet" type="text/css" href="/jeed/css/simple.css">
</head>
<body>
    <h1>Coder</h1>
    <c:choose>
        <c:when test="${employee eq null}">
            <p>
                Failure operating on employee
                <c:if test="${param.id ne null}">with id ${param.id}</c:if>
            </p>
        </c:when>
        <c:otherwise>
            <p>Employee is ${employee.firstName} ${employee.lastName}</p>
            <p>hired on ${employee.hired}, salary is ${employee.salary}</p>
        </c:otherwise>
    </c:choose>
    <p>
        Back <a href="/jeed/index.html">home</a>
    </p>
</body>
</html>