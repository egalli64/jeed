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
<title>Deleted Employees</title>
<%@include file="/include/link.html"%>
</head>
<body>
    <header>
        <h1>Deleted Employees</h1>
        <%@include file="/include/navHome.html"%>
    </header>

    <p>
        <strong>${count}</strong> employees have been deleted
    </p>
</body>
</html>