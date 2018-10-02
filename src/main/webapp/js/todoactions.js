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
                '<td><input type="text" id="datumAdd"></td>' +
                '<td><input type="text" id="taskAdd"></td>' +
                '<td ><input type="text" id="statusAdd"></td>' +
                '<td>' +
                '<button type="submit" class="addbutton" id="add"> Add task </button><br/>' +
                '</td>' +
                '</tr>';
            $('#taskTable tfoot').append(addRow);
        }
        /*,
        error: function (e) {
            alert("ERROR: ", e);
            console.log("ERROR: ", e);
        }*/
    });

});
$('button').on('click', function () {
    var idd = $(event.target).attr('class');
    console.log("Button class " + idd);
    var buttonType = $(this).attr('id');
    console.log("Button id "+buttonType);

    if (buttonType==='delete') {
        $.ajax({
            url: window.location + 'todo',
            data: idToDelete = idd,
            type: 'DELETE',
            dataType: "json",
            /*   beforeSend: function (data) {
                   console.log("Data to send "+data);
               },*/
            success: function (result/*, xhr*/) {
                var rowToChange = $(event.target).closest('tr');
               // console.log(xhr.responseText);
                rowToChange.remove();
               //  console.log(result.deleteResponse);
            },
            error: function (result/*, xhr*/) {
                var rowToChange = $(event.target).closest('tr');
               // rowToChange.remove();
               // console.log(xhr.responseText);
            }

        })

    }
    if (buttonType==='change') {

        $.ajax({
            url: window.location + 'todo',
            data: idToChange = idd,
            type: 'UPDATE',
            dataType: 'json',
            success: function (data) {
                var rowToChange = $(event.target).closest('tr');
                rowToChange.$('.status').replaceWith('<td class="status">' + data.doneStatus + '</td>');
                alert("Booo!");
            }
        })
    }
    if (buttonType==='add') {
        console.log(buttonType);
        var datum = $('#datumAdd').val();
        var whatTODO = $('#taskAdd').val();
        var doneStatus = $('#statusAdd').val();
        var json = {"datum": datum, "whatTODO": whatTODO, "doneStatus": doneStatus};
        console.log(json);
        $.ajax({
            url: window.location + 'todo',
            data: json,
            type: 'POST',
            success: function (task) {
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
            }
        });

    }
});
