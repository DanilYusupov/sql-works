$('.alert .close').on('click', function(e) {
    $(this).parent().hide();
});

//Checkbox gaming realization
$('#engFullName').click(function () {
    $('#engFullName, #engFirstName, #engLastName').prop('checked', this.checked);
});
$('#engFirstName, #engLastName').click(function () {
    if (!$(this).is(':checked')) {
        $('#engFullName').prop('checked', false);
    }
});

//Places read by Id Engineer into modal
$(document).on("click", "#readByIdBtn", function () {
    var inletId = document.getElementById('readByIdIn').value;
    var readIdUrl = "/ajax?id=" + inletId;
    $.get(readIdUrl, function (responseJson) {
        if (responseJson == null){
            $('#readIdError').show();
        } else {
            $('#readFirstName').text('First name: ' + responseJson['firstName']);
            $('#readLastName').text('Last name: ' + responseJson['lastName']);
            $('#readMajor').text("Major: " + responseJson['major']);
            $('#readTel').text('Phone: ' + responseJson['tel']);
            $('#readByIdModal').modal('show');
        }
    });
});

//Places read by fullName Engineer into modal
$(document).on("click", "#readByFullNameBtn", function () {
    var fullName = document.getElementById('readFullName').value;
    var readNameUrl = "/read_by_name?readFullName=" + fullName;
    $.get(readNameUrl, function (responseJson) {
        if (responseJson == null) {
            $('#readError').show();
        } else {
            $('#readFirstName').text('First name: ' + responseJson['firstName']);
            $('#readLastName').text('Last name: ' + responseJson['lastName']);
            $('#readMajor').text("Major: " + responseJson['major']);
            $('#readTel').text('Phone: ' + responseJson['tel']);
            $('#readByIdModal').modal('show');
        }
    });
});

/*
Moves all Engineer entities in a table & places it in the modal. It's also deleting all appended childs before
appending new one (refresh maintenance).
*/
$(document).on("click", "#getAllBtn", function () {
    $.get("/get_all", function (responseJson) {
        var table = document.getElementById('tableBody');
        while (table.firstChild) {
            table.removeChild(table.firstChild);
        }
        $.each(responseJson, function (index, item) {
            var $tr = $('<tr>').appendTo($('#tableBody'));
            $("<td>").text(item['id']).appendTo($tr);
            $("<td>").text(item['firstName']).appendTo($tr);
            $("<td>").text(item['lastName']).appendTo($tr);
            $("<td>").text(item['major']).appendTo($tr);
            $("<td>").text(item['tel']).appendTo($tr);
        });
        $('#tableModal').modal('show');
    });
});