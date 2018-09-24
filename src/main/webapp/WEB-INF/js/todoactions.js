$(document).ready(function() {

    // Do GET all Customers from Back-End with JQUERY AJAX
    $(function () {
        $.ajax({
            type : "GET",
            url : window.location + "/",
            success: function(result){
                $.each(result, function(task){

                    var taskRow = '<tr>' +
                        '<td>' + task.dateTime + '</td>' +
                        '<td>' + task.whatTODO + '</td>' +
                        '<td>' + task.status + '</td>' +
                        '<td class="text-center">' +
                        '<a href="${pageContext.request.contextPath}/changestatus/${task}">Change status</a><br/>'+
                        '<a href="${pageContext.request.contextPath}/delete">Delete task</a><br/>'+
                        '</td>' +
                        '</tr>';

                    $('#taskTable tbody').append(taskRow);

                });

            },
            error : function(e) {
                alert("ERROR: ", e);
                console.log("ERROR: ", e);
            }
        });
    });

    // Do DELETE a Customer via JQUERY AJAX
    $(document).ready(function() {

        var deleteLink = $("a:contains('Delete')");

        $(deleteLink).click(function(event) {

            $.ajax({
                url: $(event.target).attr("href"),
                type: "POST",

                beforeSend: function(xhr) {
                    xhr.setRequestHeader("Accept", "application/json");
                    xhr.setRequestHeader("Content-Type", "application/json");
                },

                success: function() {

                    var rowToDelete = $(event.target).closest("tr");
                    rowToDelete.remove();

                }
            });

            event.preventDefault();
        });

    });
    // Do Add a Customer via JQUERY AJAX
    $(document).ready(function() {

        $('#addTask').submit(function(event) {

            var dateTime = $('#dateTime').val();
            var whatTODO = $('#whatTODO').val();
            var status = $('#status').val();
            var json = { "dateTime" : dateTime, "whatTODO" : whatTODO, "price": status};

            $.ajax({
                url: $("#addTask").attr( "action"),
                data: JSON.stringify(json),
                type: "POST",

                beforeSend: function(xhr) {
                    xhr.setRequestHeader("Accept", "application/json");
                    xhr.setRequestHeader("Content-Type", "application/json");
                    $(".error").remove();
                },
                success: function(task) {
                    var taskRow = '<tr>' +
                        '<td>' + task.dateTime + '</td>' +
                        '<td>' + task.whatTODO + '</td>' +
                        '<td>' + task.status + '</td>' +
                        '<td class="text-center">' +
                        '<a href="${pageContext.request.contextPath}/changestatus/${task}">Change status</a><br/>'+
                        '<a href="${pageContext.request.contextPath}/delete">Delete task</a><br/>'+
                        '</td>' +
                        '</tr>';

                    $('#taskTable tbody').append(taskRow);

                },
                error: function(e) {
                    alert("ERROR: ", e);
                    console.log("ERROR: ", e);
                }
            });

            event.preventDefault();
        });

    });
    // Do Change Status a Customer via JQUERY AJAX
    $(document).ready(function() {

        var deleteLink = $("a:contains('Change')");
        var customerId = $(this).parent().find('input').val();
        var workingObject = $(this);

        $.ajax({
            type : "POST",
            url : window.location + "${pageContext.request.contextPath}/changestatus/${task.id}.json",
            success: function(){
                workingObject.closest("tr").remove();
            },
            error : function(e) {
                alert("ERROR: ", e);
                console.log("ERROR: ", e);
            }
        });
    });
})