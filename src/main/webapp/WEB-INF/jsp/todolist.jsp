<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8" />
    <title>TODO list</title>
    <link rel="stylesheet" type="text/css" href="/css/style.css"/>
    <script defer>
        function showTODOlist(){

        }
    </script>
</head>
<body>
<br/><br/>
<div>
    <table border="1">
        <tr>
            <th>Date and Time</th>
            <th>Task</th>
            <th>Status</th>
            <th>Actions</th>
        </tr>
        <c:forEach  items="${TODOlist}" var ="todolist">
            <tr>
                <td>${todolist.dateTime}</td>
                <td>${todolist.whatTODO}</td>
                <td>${todolist.status}</td>
                <td>
                    <form action="changeStatus" method="post">
                    <input type="button" name="Change status"/>
                    </form>
                    <form action="deleteTODO" method="post">
                    <input type="button" name="Delete"/>
                    </form>
                </td>
            </tr>
        </c:forEach>
        <tr>
            <form action="addTODO" method="post">
                <td>${todolist.dateTime}
                </td>
                <td>${todolist.whatTODO}
                </td>
                <td>${todolist.status}
                </td>
            <td>
                 <input type="button" name="Add TODO"/>
            </td>
            </form>
        </tr>
    </table>
</div>

</body>

</html>