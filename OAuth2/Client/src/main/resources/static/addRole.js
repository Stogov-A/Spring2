function addRole() {
    $.ajax({
        type: "GET",
        dataType: "json",
        url: "/getAllRoles",
        success: function (data) {
            for (let i in data){
                $("#addRole").append(
                "<label>" + data[i].name + "</label>" +
                "<input type='checkbox' id="+"add" + data[i].name + "  name=" + data[i].name + "  >"
                + "<br><br>"
                    )
            }
        }
    })
}
addRole();