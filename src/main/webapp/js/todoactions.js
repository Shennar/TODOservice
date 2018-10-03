$(document).ready(function () {
    $.ajax({
        dataType: 'json',
        type: 'GET',
        url: window.location + 'todo',
        success: function (data) {
            $.each(data, function (index, task) {

                var taskRow = '<tr>' +
                    '<td>' + task.datum + '</td>' +
                    '<td>' + task.whatTODO + '</td>' +
                    '<td class="status">' + task.doneStatus + '</td>' +
                    '<td>' +
                    '<button type="submit" class=' + task.id + ' id="change">Change status</button><br/>' +
                    '<button type="submit" class=' + task.id + ' id="delete"> Delete task </button><br/>' +
                    '</td>' +
                    '</tr>';
                $('#taskTable tbody').append(taskRow);
            });
            var addRow = '<tr>' +
                '<td><input type="text" id="datumAdd" ></td>' +
                '<td><input type="text" id="taskAdd" ></td>' +
                '<td><input type="text" id="statusAdd" ></td>' +
                '<td>' +
                '<button type="submit" class="addbutton" id="add"> Add task </button>' +
                '</td>' +
                '</tr>';
            $('#taskTable tfoot').append(addRow);
            checkButton();
            // $('#taskTable').ajax.reload();
        }
    });


});

function checkButton() {
    $('button').on('click', function () {
        var idd = $(event.target).attr('class');
        var buttonType = $(this).attr('id');

        if (buttonType === 'delete') {
            $.ajax({
                url: window.location + 'todo?idToDelete=' + idd,
                type: 'DELETE',
                dataType: 'json',
                success: function () {
                    event.preventDefault();
                    var rowToChange = $(event.target).closest('tr');
                    rowToChange.remove();

                    //$(event.target).closest('tbody').reload(true);
                }
            });
            $('#taskTable').ajax.reload();
        }

        if (buttonType === 'change') {
            $.ajax({
                url: window.location + 'todo?idToChange=' + idd,
                type: 'PUT',
                dataType: 'json',
                success: function (data) {
                    event.preventDefault();
                    var rowToChange = $(event.target).closest('tr');
                    rowToChange.$('.status').replaceWith('<td class="status">' + data.doneStatus + '</td>');
                   // $(event.target).closest('tbody').reload(true);
                    $('#taskTable').reload();
                }
            });
            $('#taskTable').ajax.reload();
        }
        if (buttonType === 'add') {
            var datum = $('#datumAdd').val();
            var whatTODO = $('#taskAdd').val();
            var doneStatus = $('#statusAdd').val();
            var addedTask = {"datum": datum, "whatTODO": whatTODO, "doneStatus": doneStatus};
            console.log(addedTask);
            $.ajax({
                url: window.location + 'todo',
                dataType: 'json',
                data: addedTask,
                type: 'POST',
                success: function (task) {
                    event.preventDefault();
                    console.log(task);
                    var taskRow = '<tr>' +
                        '<td>' + task.datum + '</td>' +
                        '<td>' + task.whatTODO + '</td>' +
                        '<td class="status">' + task.doneStatus + '</td>' +
                        '<td>' +
                        '<button type="submit" class=' + task.id + ' id="change">Change status</button><br/>' +
                        '<button type="submit" class=' + task.id + ' id="delete"> Delete task </button><br/>' +
                        '</td>' +
                        '</tr>';
                    $('#taskTable tbody').append(taskRow);
                   // $('#taskTable').reload();
                }
            });
            $('#taskTable').ajax.reload();
        }
    });
}