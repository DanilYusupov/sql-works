//Checkbox gaming realization
$('#engFullName').click(function () {
    $('#engFullName, #engFirstName, #engLastName').prop('checked', this.checked);
});
$('#engFirstName, #engLastName').click(function () {
    if (!$(this).is(':checked')) {
        $('#engFullName').prop('checked', false);
    }
});

//Deletes engineer by ID
$(document).on("click", "#deleteByIdBtn", function () {
    var deleteId = document.getElementById('deleteById').value;
    if (deleteId === '') {
        $('#dangerBody').text('Fill input field.');
        $('#dangerModal').modal('show');
    } else {
        var delIdUrl = "/delete_engineer?id=" + deleteId;
        $.get(delIdUrl, function (responseStatus) {
            if (responseStatus === "null") {
                $('#dangerBody').text('No such engineer.');
                $('#dangerModal').modal('show');
            } else {
                $('#successBody').text(responseStatus);
                $('#successModal').modal('show');
            }
        });
    }
});

//Deletes engineer by name
$(document).on("click", "#deleteByNameBtn", function () {
    var deleteName = document.getElementById('fullNameRemoval').value;
    if (deleteName === '') {
        $('#dangerBody').text('Define full name.');
        $('#dangerModal').modal('show');
    } else {
        var delUrl = "/delete_engineer?id=&fullName=" + deleteName;
        $.get(delUrl, function (responseStatus) {
            if (responseStatus === "null") {
                $('#dangerBody').text('No such engineer.');
                $('#dangerModal').modal('show');
            } else {
                $('#successBody').text(responseStatus);
                $('#successModal').modal('show');
            }
        });
    }
});

//Puts read by Id Engineer into modal
$(document).on("click", "#readByIdBtn", function () {
    var inletId = document.getElementById('readByIdIn').value;
    if (inletId === '') {
        $('#dangerBody').text('Fill input field.');
        $('#dangerModal').modal('show');
    } else {
        var readIdUrl = "/users?id=" + inletId;
        $.get(readIdUrl, function (responseJson) {
            if (responseJson == null) {
                $('#dangerBody').text('No such engineer.');
                $('#dangerModal').modal('show');
            } else {
                $('#readFirstName').text('First name: ' + responseJson['firstName']);
                $('#readLastName').text('Last name: ' + responseJson['lastName']);
                $('#readMajor').text("Major: " + responseJson['major']);
                $('#readTel').text('Phone: ' + responseJson['tel']);
                $('#readByIdModal').modal('show');
            }
        });
    }
});

//Puts read by fullName Engineer into modal
$(document).on("click", "#readByFullNameBtn", function () {
    var fullName = document.getElementById('readFullName').value;
    if (fullName === '') {
        $('#dangerBody').text('Fill input field');
        $('#dangerModal').modal('show');
    } else {
        var readNameUrl = "/read_by_name?readFullName=" + fullName;
        $.get(readNameUrl, function (responseJson) {
            if (responseJson == null) {
                $('#dangerBody').text('No such engineer.s');
                $('#dangerModal').modal('show');
            } else {
                $('#readFirstName').text('First name: ' + responseJson['firstName']);
                $('#readLastName').text('Last name: ' + responseJson['lastName']);
                $('#readMajor').text("Major: " + responseJson['major']);
                $('#readTel').text('Phone: ' + responseJson['tel']);
                $('#readByIdModal').modal('show');
            }
        });
    }
});


//Creating engineer
$(document).on("click", "#createEngineer", function () {
    var firstName = document.getElementById('firstName').value;
    var lastName = document.getElementById('lastName').value;
    var major = document.getElementById('major').value;
    var tel = document.getElementById('tel').value;
    if (firstName === '' || lastName === '' || major === '' || tel === '') {
        $('#dangerBody').text('Fill all input fields.');
        $('#dangerModal').modal('show');
    } else {
        var createUrl = "/create_engineer?firstName="
            + firstName + "&lastName=" + lastName + "&major=" + major + "&tel=" + tel;
        $.get(createUrl, function (message) {
            $('#successBody').text(message);
            $('#successModal').modal('show');
        });
    }
});

//Clearing create form
$(document).on("click", "#resetEngineer", function () {
    document.getElementById('firstName').value = "";
    document.getElementById('lastName').value = "";
    document.getElementById('major').value = "";
    document.getElementById('tel').value = "";
});

//Updating engineer
$(document).on("click", "#updateEngineer", function () {
    var id = document.getElementById('idUpdate').value;
    var firstName = document.getElementById('firstNameUpdate').value;
    var lastName = document.getElementById('lastNameUpdate').value;
    var major = document.getElementById('majorUpdate').value;
    var tel = document.getElementById('telUpdate').value;
    if (id === '' || firstName === '' || lastName === '') {
        $('#dangerBody').text('Define id & full name at minimum.');
        $('#dangerModal').modal('show');
    } else {
        var updateUrl = "/update_engineer?id="
            + id + "&firstName=" + firstName + "&lastName=" + lastName + "&major=" + major + "&tel=" + tel;
        $.get(updateUrl, function (message) {
            if (message === "false") {
                $('#dangerBody').text('No such engineer with id = ' + id);
                $('#dangerModal').modal('show');
            } else {
                $('#successBody').text(message);
                $('#successModal').modal('show');
            }
        });
    }
});

//Clearing update form
$(document).on("click", "#resetUpdation", function () {
    document.getElementById('idUpdate').value = "";
    document.getElementById('firstNameUpdate').value = "";
    document.getElementById('lastNameUpdate').value = "";
    document.getElementById('majorUpdate').value = "";
    document.getElementById('telUpdate').value = "";
});

/*
Moves all Engineer entities in a table & places it in the modal. It's also deleting all appended childs before
appending new one (refresh maintenance).
*/
$(document).on("click", "#getAllBtn", function () {
    $.get("/get_all", function (responseJson) {
        var table = document.getElementById('table');
        while (table.firstChild) {
            table.removeChild(table.firstChild);
        }
        var thead = $("<thead>").attr('class', 'thead-dark').appendTo(table);
        var tr = $("<tr>").appendTo(thead);
        $("<th>").attr('scope', 'col').text('Id').appendTo(tr);
        $("<th>").attr('scope', 'col').text('First name').appendTo(tr);
        $("<th>").attr('scope', 'col').text('Last name').appendTo(tr);
        $("<th>").attr('scope', 'col').text('Major').appendTo(tr);
        $("<th>").attr('scope', 'col').text('Phone').appendTo(tr);
        var tbody = $("<tbody>").appendTo(table);
        $.each(responseJson, function (index, item) {
            var $tr = $('<tr>').appendTo(tbody);
            $("<td>").text(item['id']).appendTo($tr);
            $("<td>").text(item['firstName']).appendTo($tr);
            $("<td>").text(item['lastName']).appendTo($tr);
            $("<td>").text(item['major']).appendTo($tr);
            $("<td>").text(item['tel']).appendTo($tr);
        });
        $('#tableModal').modal('show');
    });
});


//TABLE OUTPUT SELECTOR API


$(document).on("click", "#getCustomTable", function () {
    $.get("/get_all", function (responseJson) {
        var table = document.getElementById('table');
        while (table.firstChild) {
            table.removeChild(table.firstChild);
        }
        var thead = $("<thead>").attr('class', 'thead-dark').appendTo(table);
        var tr = $("<tr>").appendTo(thead);
        if ($('#engId').is(':checked')) {
            $("<th>").attr('scope', 'col').text('Id').appendTo(tr);
        }
        if ($('#engFirstName').is(':checked')) {
            $("<th>").attr('scope', 'col').text('First name').appendTo(tr);
        }
        if ($('#engLastName').is(':checked')) {
            $("<th>").attr('scope', 'col').text('Last name').appendTo(tr);
        }
        if ($('#engMajor').is(':checked')) {
            $("<th>").attr('scope', 'col').text('Major').appendTo(tr);
        }
        if ($('#engTel').is(':checked')) {
            $("<th>").attr('scope', 'col').text('Phone').appendTo(tr);
        }
        var tbody = $("<tbody>").appendTo(table);
        $.each(responseJson, function (index, item) {
            var $tr = $('<tr>').appendTo(tbody);
            if ($('#engId').is(':checked')) {
                $("<td>").text(item['id']).appendTo($tr);
            }
            if ($('#engFirstName').is(':checked')) {
                $("<td>").text(item['firstName']).appendTo($tr);
            }
            if ($('#engLastName').is(':checked')) {
                $("<td>").text(item['lastName']).appendTo($tr);
            }
            if ($('#engMajor').is(':checked')) {
                $("<td>").text(item['major']).appendTo($tr);
            }
            if ($('#engTel').is(':checked')) {
                $("<td>").text(item['tel']).appendTo($tr);
            }
        });
        $('#tableModal').modal('show');
    });
});
