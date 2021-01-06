<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Employees</title>
<link rel="icon" type="image/x-icon" href="/jed/favicon.ico">
<link rel="stylesheet" type="text/css" href="/jed/css/simple.css">
</head>
<body>
    <h1>Employees</h1>

    <div>
        <c:if test="${error ne null}">
            <p>!!! ${error} !!!</p>
        </c:if>
    </div>

    <div>
        <a href="all"><button>All</button></a>
    </div>
    <form action="salaryRange">
        <input type="number" name="low" placeholder="low" value="10000">
        <input type="number" name="high" placeholder="high" value="15000">
        <button>Range</button>
    </form>
    <form action="salaryTop">
        <input type="number" name="low" placeholder="low" value="12000">
        <button>Top</button>
    </form>

    <table>
        <tr>
            <th>id</th>
            <th>name</th>
            <th>salary</th>
        </tr>
        <c:forEach var="employee" items="${employees}">
            <tr>
                <td>${employee.id}</td>
                <td>${employee.firstName}&nbsp;${employee.lastName}</td>
                <td class="right"><fmt:formatNumber type="number" value="${employee.salary}"
                        minFractionDigits="2" maxFractionDigits="2" /></td>
            </tr>
        </c:forEach>
    </table>

    <%@include file="/ex/footer.html"%>
</body>
</html>