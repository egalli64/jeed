<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Coder</title>
<link rel="icon" type="image/x-icon" href="/jed/favicon.ico">
<link rel="stylesheet" type="text/css" href="/jed/css/simple.css">
</head>
<body>
    <h1>Coder</h1>
    <c:choose>
        <c:when test="${coder eq null}">
            <p>
                Failure operating on coder
                <c:if test="${param.id ne null}">with id ${param.id}</c:if>
            </p>
        </c:when>
        <c:otherwise>
            <p>
                Coder is ${coder.firstName} ${coder.lastName}
                <c:if test="${coder ne null}">
                    leader of team ${coder.leadingTeam.name}
                </c:if>
            </p>
        </c:otherwise>
    </c:choose>
    <p>
        Back <a href="/jed/index.html">home</a>
    </p>
</body>
</html>