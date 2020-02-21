function getUserTable() {
    $.ajax({
        type: "GET",
        dataType: "json",
        url: "/table",
        success: function (data) {
            for (let i in data) {
                $("#usersTable").append(
                "<tr>" +
                "<td>" + data[i].id + "</td>" +
                "<td>" + data[i].name + "</td>" +
                "<td>" + data[i].lastName + "</td>" +
                "<td>" + data[i].age + "</td>" +
                "<td>" + data[i].email + "</td>" +
                "<td>" + getUserRoles(data[i].roles) + "</td>" +

                "<td><button onclick='openEditForm(" + data[i].id + ")'" +
                " type=\"button\" class=\"btn btn-primary\" data-toggle=\"modal\"" +
                " data-target=\"#editModal\">Edit</button></td>" +

                "<td><button onclick='openDeleteForm(" + data[i].id + ")' " +
                "class=\"btn btn-primary\" data-toggle=\"modal\" data-target =\"#deleteModal\">Delete</button></td>" +
                "</tr>"
                    )
            }
        }
    });
}

getUserTable();

function openDeleteForm(id) {
    $.ajax({
        type: "GET",
        dataType: "json",
        url: "/getUser/" + id,
        success: function (data) {
            $("#deleteButtons").empty();
            $("#deleteId").val(data.id);
            $("#deleteName").val(data.name);
            $("#deleteLastName").val(data.lastName);
            $("#deleteAge").val(data.age);
            $("#deleteEmail").val(data.email);
            $("#deleteRoles").val(getUserRoles(data.roles));
            $("#deleteButtons").append(
            "<button onclick='closeDeleteModal()'" +
            " type=\"button\" class=\"btn btn-primary\">Cancel</button>" +

            "<button onclick='deleteUser(" + data.id + ")'" +
            " type=\"button\" class=\"btn btn-primary\">Delete</button>"
                )
        }
    })
}

function deleteUser(id) {
    $.ajax({
        type: "DELETE",
        dataType: "json",
        url: "/deleteUser/" + id,
        success: function (data) {
            alert(data);
            closeDeleteModal();
        }
    })
}

function getUserRoles(roles) {
    let userRoles = "";
    for (let i in roles) {
        userRoles += roles[i].name + " ";
    }
    return userRoles;
}

function openEditForm(id) {
    $.ajax({
        type: "GET",
        url: "/getUser/" + id,
        dataType: "json",
        success: function (datas) {
            $("#editRoles").empty();
            $("#editButtons").empty();
            $("#editId").val(datas.id);
            $("#editName").val(datas.name);
            $("#editLastName").val(datas.lastName);
            $("#editAge").val(datas.age);
            $("#editEmail").val(datas.email);
            $("#editPassword").val(datas.password);

            $.ajax({
                type: "get",
                url: "/getAllRoles",
                success: function (data) {
                    for (let i in data) {
                        let hasRole = "";
                        for (let r in datas.roles) {
                            console.log(datas.roles[0]+"    "+datas.roles)
                            if (data[i].name == datas.roles[r].name) {
                                hasRole = "checked";
                            }
                        }
                        $("#editRoles").append(
                        "<label>" + data[i].name + "</label>" +
                        "<input type='checkbox' id=" + data[i].name + " " + hasRole + " name=" + data[i].name + "  >"
                        + "<br><br>"
                            )
                    }
                }
            })
            $("#editButtons").append(
            "<button onclick='closeEditModal()'" +
            " type=\"button\" class=\"btn btn-primary\">Close</button>" +

            "<button onclick='sendEditForm()'" +
            " type=\"button\" class=\"btn btn-primary\">Edit</button>"
                )

        }
    });
}

function closeEditModal() {
    $("#editModal").modal('toggle');
    $("#tableBody").empty();
    getUserTable();
}

function closeDeleteModal() {
    $("#deleteModal").modal('toggle');
    $("#tableBody").empty();
    getUserTable();
}

function sendEditForm() {
    let roles = [];
    if ($("#ROLE_USER").is(":checked")) {
        roles.push("ROLE_USER");
    }
    if ($("#ROLE_ADMIN").is(":checked")) {
        roles.push("ROLE_ADMIN");
    }
    let jey = {
        id: $("#editId").val(),
        name: $("#editName").val(),
        lastName: $("#editLastName").val(),
        age: $("#editAge").val(),
        email: $("#editEmail").val(),
        password: $("#editPassword").val(),
        roles: roles
    }
    let myJson = JSON.stringify(jey);
    $.ajax({
        type: "POST",
        url: "/sendEditForm",
        dataType: 'json',
        data: myJson,
        contentType: 'application/json',
        success: function (data) {
            if (data != "") {
                alert(data);
            } else {
                closeEditModal();
            }
        },
    })
}

function addUser() {
    let roles = [];
    if ($("#addROLE_USER").is(":checked")) {
        roles.push("ROLE_USER");
    }
    if ($("#addROLE_ADMIN").is(":checked")) {
        roles.push("ROLE_ADMIN");
    }
    let jey = {
        id: $("#addId").val(),
        name: $("#addName").val(),
        lastName: $("#addLastName").val(),
        age: $("#addAge").val(),
        email: $("#addEmail").val(),
        password: $("#addPassword").val(),
        roles: roles
    }
    let myJson = JSON.stringify(jey);
    $.ajax({
        type: "PUT",
        url: "/addUser",
        dataType: 'json',
        data: myJson,
        contentType: 'application/json',
        success: function (data) {
            if (data == "User successfully added") {
                alert(data);
                $("#addId").val('');
                $("#addName").val('');
                $("#addLastName").val('');
                $("#addAge").val('');
                $("#addEmail").val('');
                $("#addPassword").val('');
                $("#addROLE_USER").prop("checked", false);
                $("#addROLE_ADMIN").prop("checked", false);
                showTab();
            } else {
                alert(data);
            }
        },
    })
}

function showTab() {
    $("#tableBody").empty();
    $("#table").tab('show');
    getUserTable();
}