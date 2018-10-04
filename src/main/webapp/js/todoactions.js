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
                    '<td class="status">' + task.doneStatus + '</td>' +
                    '<td>' +
                    '<button type="submit" class="change" id=' + task.id + '>Change status</button><br/>' +
                    '<button type="submit" class="delete" id=' + task.id + '> Delete task </button><br/>' +
                    '</td>' +
                    '</tr>';
                $('#taskTable tbody').append(taskRow);
            });
            var addRow = '<tr>' +
                '<td><input type="datetime-local" id="datumAdd" required></td>' +
                '<td><input type="text" id="taskAdd" required></td>' +
                '<td><input type="text" id="statusAdd" required></td>' +
                '<td>' +
                '<button type="submit" class="add" id="addbutton"> Add task </button>' +
                '</td>' +
                '</tr>';
            $('#taskTable tfoot').append(addRow);
            checkButton();
        }
    });


});

function checkButton() {
    $('button').on('click', function () {
        var buttonClicked=  $(event.target);
        var idd = buttonClicked.attr('id');
        var buttonType = buttonClicked.attr('class');
        var rowToChange = buttonClicked.closest('tr');

        if (buttonType === 'delete') {
            $.ajax({
                url: window.location + 'todo?idToDelete=' + idd,
                type: "DELETE",
                success: function () {
                    rowToChange.remove();
                }
            });
        }

        if (buttonType === 'change') {
            $.ajax({
                url: window.location + 'todo?idToChange=' + idd,
                type: "PUT",
                dataType: "json",
                success: function (data) {
                    rowToChange.find('.status').replaceWith('<td class="status">' + data.doneStatus + '</td>');
                }
            });
        }
        if (buttonType === 'add') {
            var datums = $('#datumAdd').val();
            var whatTODOs = $('#taskAdd').val();
            var doneStatuss = $('#statusAdd').val();
            var addedTask = {datum: datums, whatTODO: whatTODOs, doneStatus: doneStatuss};
            console.log(addedTask);
            $.ajax({
                url: window.location + 'todo',
                dataType: "json",
                data: addedTask,
                type: "POST",
                success: function (task) {
                    console.log(task);
                    var taskRow = '<tr>' +
                        '<td>' + task.datum + '</td>' +
                        '<td>' + task.whatTODO + '</td>' +
                        '<td class="status">' + task.doneStatus + '</td>' +
                        '<td>' +
                        '<button type="submit" class="change" id=' + task.id + '>Change status</button><br/>' +
                        '<button type="submit" class="delete" id=' + task.id + '> Delete task </button><br/>'+
                        '</td>' +
                        '</tr>';
                    $('#taskTable tbody').append(taskRow);
                    $('#datumAdd').val("");
                    $('#taskAdd').val("");
                    $('#statusAdd').val("");
                    checkButton();
                }
            });

        }
        $('.taskTable tbody').reload();
    });
}