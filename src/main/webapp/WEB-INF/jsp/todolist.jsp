<%--
  Created by IntelliJ IDEA.
  User: v.kremeneckis
  Date: 9/27/2018
  Time: 2:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<head>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>TODO list</title>
    <c:set var="root" value="${pageContext.request.contextPath}"/>
    <link rel="stylesheet" type="text/css" href="${root}/style.css"/>
    <script type="text/javascript" src="${root}/js/todoactions.js"></script>

</head>
<body>
<table id="taskTable"  align="center" class="taskTable">
    <thead>
    <tr>
        <th colspan="4"><h1>TODO list</h1></th>
    </tr>
    <tr>
        <th>Date&Time</th>
        <th>Task</th>
        <th>Status</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>

    </tbody>
    <tfoot>

    </tfoot>

</table>
</body>
</html>