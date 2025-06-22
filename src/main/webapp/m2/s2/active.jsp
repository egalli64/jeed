<%-- 
    Introduction to Hibernate - JEE ORM
    https://github.com/egalli64/jeed
 --%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>JPA Entity Manager</title>
<%@include file="/include/link.html"%>
</head>
<body>
    <header>
        <h1>Check JPA Entity Manager</h1>
        <%@include file="/include/navHome.html"%>
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