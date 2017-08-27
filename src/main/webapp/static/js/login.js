$(document).ready(function () {
    $("#loginButton").click(function () {
        checkUser($('#login').val(), $('#password').val());
    });
});

function checkUser(login, password) {
    $.ajax({
        method: "GET",
        url: "/login/ajax/",
        data: {login: login, password: password}
    }).done(function (response) {
        if (response === "success") {
            $("#loginPage").empty();
            listBooks();
            $("#logout").html("<button class=\"btn\" onclick=logout() id=\"invalidate\">Logout</button>");
        } else {
            $("#status").html("<p>Incorrect username or password</p>");
        }
    });
}

function listBooks() {

    var table = "<div class=\"container\">" +
        "<table class=\"table table-bordered table-hover table-striped\" id=\"table\" data-height=\"460\">" +
        "<thead class=\"thead-inverse\">" +
        "<tr>\n" +
        "<th data-field=\"id\">Id</th>\n" +
        "<th data-field=\"name\">Name</th>\n" +
        "<th data-field=\"image\">Image</th>\n" +
        "<th data-field=\"action\">Action</th>\n" +
        "</tr>\n" +
        "</thead>\n" +
        "</table>\n" +
        "</div>";

    var favBooks = "<div class=\"container\">" +
        "<div class=\"row\">" +
        "<div class=\"col-md-4\"></div>" +
        "<div class=\"col-md-4\">" +
        "<button class=\"btn\" onclick=btn1() id=\"btn1\">Show favorites books page</button>" +
        "<button class=\"btn\" onclick=btn2() id=\"btn2\">Close favorites books page</button>" +
        "</div>" +
        "<div class=\"col-md-4\">" + "</div>" +
        "</div>"
        + "</div>";

    $('#showBooks').html(table);
    $.get({
        contentType: "application/json",
        url: window.location.origin + '/all/book/',
        dataType: "json",
        success: function (data) {
            $.each(data, function (index, value) {
                $('<tr>').html("<td>" + value.id + "</td><td>" + value.name + "</td><td>" +
                    '<div class="avatar">' +
                    '<img width="45" height="46" src="data:image/png;base64,' + value.image + '">' +
                    '</div>' + "</td>" +
                    "<td>" + "<button class=\"btn\" onclick=addBook(" + value.id + ")>Add book</button>" + "</td>").appendTo('#table');

            });
        }
    });
    $('#favBook').html(favBooks);
}

function addBook(id) {
    alert(id);
    $.get({
        contentType: "application/json",
        url: window.location.origin + '/book/add/',
        dataType: "json",
        data: {id: id}
    });
}

function deleteBook(id) {
    alert(id + "delete");
    $.ajax({
        method: "GET",
        contentType: "application/json",
        url: window.location.origin + '/book/delete/',
        dataType: "json",
        data: {bookId: id}
    });
    $('#favBookList').empty();
    btn1();
}

function btn1() {
    var tablef = "<div class=\"container\">" +
        "<table class=\"table table-bordered table-hover table-striped\" id=\"tableF\" data-height=\"460\">" +
        "<thead class=\"thead-inverse\">" +
        "<tr>\n" +
        "<th data-field=\"id\">Id</th>\n" +
        "<th data-field=\"name\">Name</th>\n" +
        "<th data-field=\"image\">Image</th>\n" +
        "<th data-field=\"action\">Action</th>\n" +
        "</tr>\n" +
        "</thead>\n" +
        "</table>\n" +
        "</div>";
    $('#favBookList').html(tablef);
    $.get({
        contentType: "application/json",
        url: window.location.origin + '/book/fav/',
        dataType: "json",
        success: function (data) {
            $.each(data, function (index, value) {
                $('<tr>').html("<td>" + value.id + "</td><td>" + value.name + "</td><td>" +
                    '<div class="avatar">' +
                    '<img width="45" height="46" src="data:image/png;base64,' + value.image + '">' +
                    '</div>' + "</td>" +
                    "<td>" + "<button class=\"btn\" onclick=deleteBook(" + value.id + ")>Delete book</button>" + "</td>").appendTo('#tableF');

            });
        }
    });
}

function btn2() {
    $('#favBookList').empty();
}

function logout() {
    $.get({
        method: "GET",
        url: "/logout/",

    }).done(function (response) {
    });
    location.reload();
}