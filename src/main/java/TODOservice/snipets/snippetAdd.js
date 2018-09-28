/*
<c:forEach var="task" items="${TODOlist}">
    <td>"${task.dateTime}"</td>
    <td>"${task.whatTODO}"</td>
    <td>"${task.status}"</td>
    <td>
    <a href="${pageContext.request.contextPath}/changestatus/${task.id}.json">Change status</a><br/>
<a href="${pageContext.request.contextPath}/delete/${task.id}.json">Delete task</a>
</td>
</c:forEach>


<tfoot>
<tr>
<form:form id="addTask" action="/add.json" method="post">
    <td><form:input type="text" path="dateTime" /></td>
    <td><form:input type="text" path="$whatTODO"/></td>
    <td>

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

"${pageContext.request.contextPath}/todo/'
*/