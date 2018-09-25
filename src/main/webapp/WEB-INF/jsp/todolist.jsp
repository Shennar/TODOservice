<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>TODO list</title>
    <link href="resources/static/css/style.css" rel="stylesheet" type="text/css"/>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <script type="text/javascript" src="webapp/WEB-INF/js/todoactions.js"></script>

</head>
<body>
<div id="container">
    <h1>TODO list</h1>

    <table id="taskTable">
        <thead>
        <tr>
            <th>Date&Time</th><th>Task</th><th>Done</th><th>Actions</th>
        </tr>
        </thead>

        <tbody>
        <!--
        <c:forEach var="task" items="${TODOlist}">
            <td>"${task.dateTime}"</td>
            <td>"${task.whatTODO}"</td>
            <td>"${task.status}"</td>
            <td>
                <a href="${pageContext.request.contextPath}/changestatus/${task.id}.json">Change status</a><br/>
                <a href="${pageContext.request.contextPath}/delete/${task.id}.json">Delete task</a>
            </td>
        </c:forEach>
        -->
        </tbody>
        <tfoot>
        <tr>
            <form:form id="addTask" action="/add.json" method="post">
            <td><form:input type="datetime-local" path="dateTime" /></td>
            <td><form:input type="text" path="$whatTODO"/></td>
            <td>
                <!--

                -->
                <form:input type="boolean" path="status" /></td)
            </td>
            <td>
             <!--   <a href="${pageContext.request.contextPath}/add.json">Add task</a><br/>
             -->
                <input type="submit" value="Add task" />
            </td>
            </form:form>
        </tr>
        </tfoot>
    </table>

</div>
</body>
</html>