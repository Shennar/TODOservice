
function validation() {
    $('#datumAdd').on('input', function () {
        var input = $(this);
        var is_name = input.val();
        if (is_name) {
            input.removeClass("invalid").addClass("valid");
        }
        else {
            input.removeClass("valid").addClass("invalid");
        }
    });

    $('#taskAdd').keyup(function (event) {
        var input = $(this);
        var message = $(this).val();
        //console.log(message);
        if (message) {
            input.removeClass("invalid").addClass("valid");
        }
        else {
            input.removeClass("valid").addClass("invalid");
        }
    });
    $("#addButton button").click(function (event) {
        //var form_data=$("#contact").serializeArray();
        var error_free_datum = true;
        var element = $("#datumAdd");
        var valid = element.hasClass("valid");
        var error_element = $("span", element.parent());
        if (!valid) {
            error_element.removeClass("error").addClass("error_show");
            error_free_datum = false;
        }
        else {
            error_element.removeClass("error_show").addClass("error");
        }
        var error_free_task = true;
        var element = $("#taskAdd");
        var valid = element.hasClass("valid");
        var error_element = $("span", element.parent());
        if (!valid) {
            error_element.removeClass("error").addClass("error_show");
            error_free_task = false;
        }
        else {
            error_element.removeClass("error_show").addClass("error");
        }
        if (!error_free_datum && !error_free_task) {
            event.preventDefault();
        }
        else {
            alert('No errors: Form will be submitted');
        }
    });
}