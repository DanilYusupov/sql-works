<%@ page import="java.util.List" %>
<%@ page import="com.sqlworks.model.Engineer" %>
<!DOCTYPE html>
<%@ page pageEncoding="UTF-8" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Home</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body data-spy="scroll" data-target=".list-group" data-offset="50">

<% if (request.getSession().getAttribute("message") != null) { %>
<!-- Modal message -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Message</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <%=request.getSession().getAttribute("message")%>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<% } %>

<div class="jumbotron bg-dark rounded-0" style="height: 134px">
    <div class="display-4"><span style="color: #007bff">CRUD</span> <span class="text-light">interface</span>
    </div>
    <div class="lead">Powered by PostgreSQL</div>
</div>
<div class="container" style="padding-top: 1.5%; padding-bottom:0%; max-width: 85%;">
    <div class="row align-items-start">

        <!--Engineer creating field #007bff-->

        <div class="col mx-3 border rounded">
            <h2 class="text-center"><span style="color: #007bff">C</span>reate engineer</h2>
            <form action="/create_engineer" method="post">
                <div class="form-row">
                    <div class="col">
                        <label for="firstName">First name:</label>
                        <input type="text" class="form-control" id="firstName" name="firstName" placeholder="Ivan"
                               value="" required>
                    </div>
                    <div class="col">
                        <label for="lastName">Last name:</label>
                        <input type="text" class="form-control" id="lastName" name="lastName" placeholder="Ivanov"
                               value="" required>
                    </div>
                </div>
                <div class="form-row">
                    <div class="col-">
                        <label for="major">Major:</label>
                        <input type="text" class="form-control" id="major" name="major" placeholder="Mechanic" value=""
                               required>
                    </div>
                </div>
                <div class="form-row">
                    <div class="col-">
                        <label for="tel">Phone number:</label>
                        <input type="text" class="form-control" id="tel" name="tel" placeholder="+780012345678" value=""
                               required>
                    </div>
                </div>
                <br>
                <div class="row justify-content-center">
                    <div class="btn-group" role="group" aria-label="Basic example">
                        <button class="btn btn-primary" type="submit">Create</button>
                        <button class="btn btn-secondary" type="reset">Clear</button>
                    </div>
                </div>
                <br>
            </form>
        </div>

        <!--Reading engineer-->

        <div class="col mx-3 border rounded">
            <h2 class="text-center"><span style="color: #007bff">R</span>ead engineer</h2>
            <form action="/read_engineer" method="post">
                <div class="form-group">
                    <label for="readFullName">Full name:</label>
                    <input type="text" class="form-control" id="readFullName"
                           aria-describedby="nameHelp" placeholder="Ivan Ivanov" name="readFullName" value="" required>
                    <small id="readHelp" class="form-text text-muted">Only full name of engineer</small>
                </div>
                <div class="row justify-content-center">
                    <button type="submit" class="btn btn-dark">Read</button>
                </div>
            </form>
            <br>
        </div>

        <!--Update engineer-->

        <div class="col mx-3 border rounded">
            <h2 class="text-center"><span style="color: #007bff">U</span>pdate engineer</h2>
            <form action="/update_engineer" method="post">
                <div class="form-row">
                    <div class="col-">
                        <label for="idUpdate">Existent id:</label>
                        <input type="text" class="form-control" id="idUpdate" name="idUpdate" placeholder="552004"
                               value="" required>
                    </div>
                </div>
                <div class="form-row">
                    <div class="col">
                        <label for="firstNameUpdate">First name:</label>
                        <input type="text" class="form-control" id="firstNameUpdate" name="firstNameUpdate"
                               placeholder="Ivan">
                    </div>
                    <div class="col">
                        <label for="lastNameUpdate">Last name:</label>
                        <input type="text" class="form-control" id="lastNameUpdate" name="lastNameUpdate"
                               placeholder="Ivanov">
                    </div>
                </div>
                <div class="form-row">
                    <div class="col-">
                        <label for="majorUpdate">Major:</label>
                        <input type="text" class="form-control" id="majorUpdate" name="majorUpdate"
                               placeholder="Mechanic">
                    </div>
                </div>
                <div class="form-row">
                    <div class="col-">
                        <label for="telUpdate">Phone number:</label>
                        <input type="text" class="form-control" id="telUpdate" name="telUpdate"
                               placeholder="+780012345678">
                    </div>
                </div>
                <br>
                <div class="row justify-content-center">
                    <div class="btn-group" role="group" aria-label="Basic example">
                        <button class="btn btn-primary" type="submit">Update</button>
                        <button class="btn btn-secondary" type="reset">Clear</button>
                    </div>
                </div>
                <br>
            </form>

        </div>

        <!--Engineer deleting field-->

        <div class="col mx-3 border rounded">
            <h2 class="text-center"><span style="color: #007bff">D</span>elete engineer</h2>
            <div id="accordion">

                <!--Delete by name-->

                <div class="card">
                    <div class="card-header" id="headingOne" style="padding: unset !important;">
                        <h5 class="mb-0">
                            <button class="btn btn-dark" data-toggle="collapse" data-target="#collapseOne"
                                    aria-expanded="true" aria-controls="collapseOne" style="width: 100%">
                                <h5>By name:</h5>
                            </button>
                        </h5>
                    </div>

                    <div id="collapseOne" class="collapse show" aria-labelledby="headingOne"
                         data-parent="#accordion">
                        <br>
                        <div class="col">
                            <form action="/delete_engineer" method="post">
                                <div class="form-group">
                                    <label for="fullNameRemoval">Full name:</label>
                                    <input type="text" class="form-control" id="fullNameRemoval"
                                           aria-describedby="nameHelp" placeholder="Ivan Ivanov" name="fullName"
                                           value="" required>
                                    <small id="nameHelp" class="form-text text-muted">Only full name of engineer to
                                        be
                                        deleted
                                    </small>
                                </div>
                                <button type="submit" class="btn btn-danger">Delete</button>
                            </form>
                            <br>
                        </div>
                    </div>
                </div>

                <!--Delete by ID-->

                <div class="card">
                    <div class="card-header" id="headingTwo" style="padding: unset !important;">
                        <h5 class="mb-0">
                            <button class="btn btn-dark collapsed" data-toggle="collapse" data-target="#collapseTwo"
                                    aria-expanded="false" aria-controls="collapseTwo" style="width: 100%">
                                <h5>By identifier:</h5>
                            </button>
                        </h5>
                    </div>
                    <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordion">
                        <br>
                        <div class="col">
                            <form action="/delete_engineer" method="post">
                                <div class="form-group">
                                    <label for="exampleInputEmail1">Engineer identifier:</label>
                                    <input type="text" class="form-control" id="exampleInputEmail1"
                                           aria-describedby="emailHelp" placeholder="110452" name="idOnDelete" value=""
                                           required>
                                    <small id="emailHelp" class="form-text text-muted">For database holder only
                                    </small>
                                </div>
                                <button type="submit" class="btn btn-danger">Delete</button>
                            </form>
                            <br>
                        </div>
                    </div>
                </div>
            </div>
            <br>
        </div>
    </div>

    <!--SQL toolbar-->

    <div class="container" style="padding-top: 2%; padding-bottom:0%; max-width: 60%;">
        <div class="text-center" style="padding-bottom: 1%;">Table output selector</div>
        <div class="border rounded">
            <form action="/get_all" method="post">
                <div class="row align-items-center" style="padding-top: 2%; padding-bottom:2%;">
                    <!--Choosing-->

                    <div class="col mx-3 h5" style="border-right: 1px solid #dee2e6; height: 100%;">
                        <div class="custom-control custom-checkbox">
                            <input type="checkbox" class="custom-control-input" id="all" name="all">
                            <label class="custom-control-label" for="all">Select all</label>
                        </div>

                        <div class="custom-control custom-checkbox">
                            <input type="checkbox" class="custom-control-input" id="engId" name="engId">
                            <label class="custom-control-label" for="engId">Engineer id</label>
                        </div>

                        <div class="custom-control custom-checkbox">
                            <input type="checkbox" class="custom-control-input" id="engFullName" name="engFullName">
                            <label class="custom-control-label" for="engFullName">Engineer full name</label>
                        </div>

                        <div class="custom-control custom-checkbox ml-4">
                            <input type="checkbox" class="custom-control-input" id="engFirstName" name="engFirstName">
                            <label class="custom-control-label" for="engFirstName">Engineer first name</label>
                        </div>

                        <div class="custom-control custom-checkbox ml-4">
                            <input type="checkbox" class="custom-control-input" id="engLastName" name="engLastName">
                            <label class="custom-control-label" for="engLastName">Engineer last name</label>
                        </div>

                        <div class="custom-control custom-checkbox">
                            <input type="checkbox" class="custom-control-input" id="engMajor" name="engMajor">
                            <label class="custom-control-label" for="engMajor">Engineer major</label>
                        </div>

                        <div class="custom-control custom-checkbox">
                            <input type="checkbox" class="custom-control-input" id="engTel" name="engTel">
                            <label class="custom-control-label" for="engTel">Engineer phone</label>
                        </div>
                    </div>

                    <!--Buttons-->
                    <%--col pr-5 text-center--%>

                    <div id="spy-1" class="col mx-3 h5 list-group">
                        <button type="reset" class="btn btn-secondary">Clear</button>
                        <button type="submit" class="btn btn-primary">Get information</button>
                    </div>
                </div>
            </form>
            <button class="btn btn-primary" id="testButton">Click</button>
            <div id="result1" style="width: 200px; height: 50px"></div>
        </div>
    </div>

    <%
        List<Engineer> list = (List<Engineer>) request.getSession().getAttribute("table");
        if (list != null) {
    %>
    <div class="container" data-spy="scroll" data-target="#spy-1" style="padding-top: 2%; padding-bottom:0%;">
        <table id="table" class="table">
            <thead class="thead-dark">
            <tr>
                <th scope="col">Id</th>
                <th scope="col">First name</th>
                <th scope="col">Last name</th>
                <th scope="col">Major</th>
                <th scope="col">Phone</th>
            </tr>
            </thead>
            <tbody>
            <% for (Engineer man : list) { %>
            <tr>
                <th scope="row"><%=man.getId()%>
                </th>
                <td><%=man.getFirstName()%>
                </td>
                <td><%=man.getLastName()%>
                </td>
                <td><%=man.getMajor()%>
                </td>
                <td><%=man.getTel()%>
                </td>
            </tr>
            <% } %>
            </tbody>
        </table>
    </div>
    <% } %>
</div>

<script
        src="https://code.jquery.com/jquery-3.3.1.js"
        integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
<script type="text/javascript">
    $('#myModal').modal('show');


</script>
<script type="text/javascript">
    $('#tableModal').modal('show');

</script>
<script type="text/javascript">
    $('#all').click(function () {
        $('#engId, #engFullName, #engFirstName, #engLastName, #engMajor, #engTel').prop('checked', this.checked);
    });

    $('#engFullName').click(function () {
        $('#engFullName, #engFirstName, #engLastName').prop('checked', this.checked);
    });

    $('#engFirstName, #engLastName').click(function () {
        if (!$(this).is(':checked')) {
            $('#engFullName').prop('checked', false);
        }
    });

    $('#engId, #engFullName, #engFirstName, #engLastName, #engMajor, #engTel').click(function () {
        if (!$(this).is(':checked')) {
            $('#all').prop('checked', false);
        }
    });
</script>
<script type="text/javascript">
    $(document).on("click", "#testButton", function () {
        $.get("/ajax", function (responseText) {
            $("#result1").text(responseText);
        });
    });
</script>
</body>
</html>