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
<title>Regions with Country</title>
<%@include file="/include/link.html"%>
</head>
<body>
    <header>
        <h1>Regions with associated country</h1>
        <nav>
            <a href="/jeed/index.html#1toM">Home</a>
        </nav>
    </header>

    <c:forEach var="region" items="${regions}">
        <h2>${region.id}&nbsp;${region.name}</h2>
        <table>
            <tr>
                <th>id</th>
                <th>name</th>
            </tr>
            <c:forEach var="country" items="${region.countries}">
                <tr>
                    <td>${country.id}</td>
                    <td>${country.name}</td>
                </tr>
            </c:forEach>
        </table>
    </c:forEach>
</body>
</html>
