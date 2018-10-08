$(document).ready(function () {
    $.ajax({
        dataType: "json",
        type: "GET",
        url: window.location + 'todo',
        success: function (data) {
            $.each(data, function (index, task) {
                var taskRow = '<tr>' +
                    '<td>' + task.datum + '</td>' +
                    '<td>' + task.whatTODO + '</td>' +
                    '<td class="status" style="' + statusColor(task.doneStatus) + '">' +
                    task.doneStatus + '</td>' +
                    '<td>' +
                    '<button type="submit" class="change" id=' + task.id + '>Change status</button><br/>' +
                    '<button type="submit" class="delete" id=' + task.id + '> Delete task </button><br/>' +
                    '</td>' +
                    '</tr>';
                $('#taskTable tbody').append(taskRow);
            });
            checkButton();

            var addRow = '<tr>' +
                '<td><input type="datetime-local" id="datumAdd" name="datetime">' +
                '</td>' +
                '<td><textarea id="taskAdd" name="tasktext"></textarea>' +
                '</td>' +
                '<td><select  id="statusAdd">' +
                '<option selected>TO DO</option>' +
                '<option>Done</option>' +
                '</select></td>' +
                '<td>' +
                '<button type="submit" class="add" id="addbutton"> Add task </button>' +
                '</td>' +
                '</tr>';
            $('#taskTable tfoot').append(addRow);
            addAction();
        }
    });

});


function checkButton() {
    $('button').on('click', function () {
        var buttonClicked = $(event.target);
        var idd = buttonClicked.attr('id');
        var buttonType = buttonClicked.attr('class');
        var rowToChange = buttonClicked.closest('tr');

        if (buttonType === 'delete') {
            $.ajax({
                url: window.location + 'todo?idToDelete=' + idd,
                type: "DELETE",
                dataType: "text",
                success: function (r) {
                    if (r !== "Impossible to delete task with ID: ") {
                        rowToChange.remove();
                        alert(r + idd);
                    } else alert(r + idd);
                }
            });
        }

        if (buttonType === 'change') {
            $.ajax({
                url: window.location + 'todo?idToChange=' + idd,
                type: "PUT",
                dataType: "text",
                success: function (r) {
                    if (r !== "No such record in the database.") {

                        rowToChange.find('.status').replaceWith('<td class="status" style="' +
                            statusColor(r) + '">' + r + '</td>');
                    } else alert(r);
                }
            });
        }

    });
}

function addAction() {
    $('button').on('click', function () {
        var buttonClicked = $(event.target);
        var buttonType = buttonClicked.attr('class');

        if (buttonType === 'add') {
            var datums = $('#datumAdd').val();
            var whatTODOs = $('#taskAdd').val();
            var doneStatuss = $('#statusAdd').val();
            var addedTask = {datum: datums, whatTODO: whatTODOs, doneStatus: doneStatuss};
            $.ajax({
                url: window.location + 'todo',
                dataType: "json",
                data: addedTask,
                type: "POST",
                success: function (task) {
                    if (task.errors === 'OK'){
                    console.log(task);
                    var taskRow = '<tr>' +
                        '<td>' + task.datum + '</td>' +
                        '<td>' + task.whatTODO + '</td>' +
                        '<td class="status" style="' + statusColor(task.doneStatus) + '">' +
                        task.doneStatus + '</td>' +
                        '<td>' +
                        '<button type="submit" class="change" id=' + task.id + '>Change status</button><br/>' +
                        '<button type="submit" class="delete" id=' + task.id + '> Delete task </button><br/>' +
                        '</td>' +
                        '</tr>';
                    $('#taskTable tbody').append(taskRow);
                    $('#datumAdd').val("");
                    $('#taskAdd').val("");
                    $('#statusAdd').val("");
                    checkButton();}
                    else alert(task.errors);
                }
            });
        }
    });
}

function statusColor(text) {
    if (text === "Done") {
        return "color:green";
    }
    else {
        return "color:red";
    }
}