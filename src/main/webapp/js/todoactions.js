$(document).ready(function() {
    $.ajax({
        dataType: 'json',
        type: "GET",
        url: window.location+"todo",
        success: function (data) {
            $.each(data, function (index, task) {

                var taskRow = '<tr>' +
                    '<td>' + task.datum + '</td>' +
                    '<td>' + task.whatTODO + '</td>' +
                    '<td class="status">' + task.doneStatus + '</td>' +
                    '<td><form:form class='+task.id+'>' +
                   // '<input type="hidden" value='+task.id+'>' +
                    '<button type="submit" class="changeButton" id="change">Change status</button><br/>' +
                    '<button type="submit" class="delete"> Delete task </button><br/>' +
                    '</form:form></td>' +
                    '</tr>';
                $('#taskTable tbody').append(taskRow);
            });
            var addRow='<tr><form method="post" id="addTaskForm">' +
                '<td>' + 'For date' + '</td>' +
                '<td>' + 'For task' + '</td>' +
                '<td class="status">' + 'For status' + '</td>' +
                '<td>' +
                '<button type="submit" class="addTask"> Add task </button><br/>' +
                '</td>' +
                '</form></tr>';
            $('#taskTable tfoot').append(addRow);
        }
        ,
        error: function (e) {
            alert("ERROR: ", e);
            console.log("ERROR: ", e);
        }
    });
    $('#change').click(function () {
        var buttonType=$(this).attr('class');
        console.log(buttonType);
        var id;
        if (buttonType.eq("changeButton")) {
            id = $('form').attr('class');
            $.ajax({
                url: window.location+"todo",
                data: idToChange=id,
                type: "UPDATE",
                dataType:"json",
                success: function(){
                    var rowToChange=$(event.target).closest("tr");
                    rowToChange.$('.status').replaceWith('<td class="status">' + data.doneStatus + '</td>');
                    console.log("Booo!");
                }
            })
        }
        if (buttonType.eq("delete")) {
            id = $('form').attr('class');
            console.log(id);
            $.ajax({
                url: window.location+"todo",
                data: idToDelete=id,
                type: "DELETE",
                dataType:"json",
                success: function(){
                    var rowToChange=$(event.target).closest("tr");
                    rowToChange.remove();
                    console.log(data.text);
                }
            })
        }
    });

});

    // Do DELETE a task via JQUERY AJAX

/*
    var deleteLink = $("a:contains('Delete')");

    $(deleteLink).click(function(event) {

        $.ajax({
            url: window.location + "todo",
            type: "DELETE",
      //      data:
            /*
                            beforeSend: function(xhr) {
                                xhr.setRequestHeader("Accept", "application/json");
                                xhr.setRequestHeader("Content-Type", "application/json");
                            },

            success: function() {

                var rowToDelete = $(event.target).closest("tr");
                rowToDelete.remove();
                alert(response.toString());
            }
        });

        event.preventDefault();
    });
    */
    // Do Add a task via JQUERY AJAX
 /*   $('#addTask').submit(function(event) {

        var datum = "time";//$('#dateTime').val();
        var whatTODO = "task";//$('#whatTODO').val();
        var status = false;// $('#status').val();
        var json = { "datum" : datum, "whatTODO" : whatTODO, "doneStatus": status};

        $.ajax({
            url: window.location + "todo",
            data: JSON.stringify(json),
            type: "POST",

            beforeSend: function(xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-Type", "application/json");
                $(".error").remove();
            },
            success: function(data) {
            //    var task=JSON.parse(data);
                var taskRow = '<tr>' +
                    '<td>' + data.datum + '</td>' +
                    '<td>' + data.whatTODO + '</td>' +
                    '<td class="status">' + data.doneStatus + '</td>' +
                    '<td>' +
                    '<a href="/todo/${'+task.id+'}">Change status</a><br/>' +
                    '<a href="/todo/${'+task.id+'}">Delete task</a><br/>' +
                    '</td>' +
                    '</tr>';

                $('#taskTable tbody').prepend(taskRow);

            }/*,
                error: function(e) {
                    alert("ERROR: ", e);
                    console.log("ERROR: ", e);
                } */
      /*  });

        event.preventDefault();
    }); */
    // Do Change Status of a task via JQUERY AJAX

    /*
      var changeLink = $("a:contains('Change')");
      var customerId = $(this).parent().find('input').val();
      var workingObject = $(this);

      $.ajax({
          type : "PUT",
          url : window.location + "todo",
          success: function(){
              //   workingObject.closest("tr").remove();
          },
              error : function(e) {
                  alert("ERROR: ", e);
                  console.log("ERROR: ", e);
              }
      });
  */
//}); //doc.ready

// Do DELETE a Customer via JQUERY AJAX
//$(document).ready(function() {

// });
// Do Add a Customer via JQUERY AJAX
// $(document).ready(function() {


// });
// Do Change Status a Customer via JQUERY AJAX
//$(document).ready(function() {


//});