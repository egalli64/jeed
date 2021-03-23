<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Team</title>
<link rel="icon" href="data:;base64,=">
<link rel="stylesheet" type="text/css" href="/jeed/css/simple.css">
</head>
<body>
    <h1>Team</h1>
    <c:choose>
        <c:when test="${team eq null}">
            <p>
                Failure operating on team
                <c:if test="${param.id ne null}">with id ${param.id}</c:if>
            </p>
        </c:when>
        <c:otherwise>
            <p>Team ${team.name}, leader is ${team.leader.firstName} ${team.leader.lastName}</p>
        </c:otherwise>
    </c:choose>
    <p>
        Back <a href="/jeed/index.html">home</a>
    </p>
</body>
</html>