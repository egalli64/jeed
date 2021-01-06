<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Regions</title>
<link rel="icon" type="image/x-icon" href="/jed/favicon.ico">
<link rel="stylesheet" type="text/css" href="/jed/css/simple.css">
</head>
<body>
    <h1>Regions</h1>

    <div>
        <c:if test="${error ne null}">
            <p>!!! ${error} !!!</p>
        </c:if>
    </div>

    <table>
        <tr>
            <th>id</th>
            <th>name</th>
        </tr>
        <c:forEach var="region" items="${regions}">
            <tr>
                <td>${region.id}</td>
                <td>${region.name}</td>
            </tr>
        </c:forEach>
    </table>

    <h2>New</h2>
    <form action="new">
        <input name="name" placeholder="region name">
        <button>Add</button>
    </form>

    <h2>Delete</h2>
    <form action="del">
        <select name="id">
            <option value="">--Please choose a region--</option>
            <c:forEach var="region" items="${regions}">
                <option value="${region.id}">${region.id}&nbsp;${region.name}</option>
            </c:forEach>
        </select>
        <button>Remove</button>
    </form>

    <h2>Edit</h2>
    <form action="edit">
        <select name="id">
            <option value="">--Please choose a region--</option>
            <c:forEach var="region" items="${regions}">
                <option value="${region.id}">${region.id}&nbsp;${region.name}</option>
            </c:forEach>
        </select> <input name="name" placeholder="New region name">
        <button>Modify</button>
    </form>

    <%@include file="/ex/footer.html"%>
</body>
</html>