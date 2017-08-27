$(document).ready(function () {
    $("#btn1").click(function () {
        $('#loaddiv').empty();
    });
    $("#btn2").click(function () {
        $("#loginPage").empty();
        callPage();
    });
});

function drawLoginPage() {
    var page = "<div id=\"loginPage\" class=\"container\" style=\"padding:200px 0\">\n" +
        "<div class=\"row\">\n" +
        "<div class=\"col-md-4\"></div>\n" +
        "<div class=\"col-md-4\">\n" +
        "<span id=\"status\"></span>\n" +
        "<input id=\"login\" type=\"login\" name=\"login\" placeholder=\"Login\" required class=\"form-control input-lg\"/>\n" +
        "<input id=\"password\" type=\"password\" name=\"password\" class=\"form-control input-lg\"\n" +
        "placeholder=\"Password\"\n" +
        "required=\"\"/>\n" +
        "<button id=\"loginButton\" type=\"submit\" name=\"go\" class=\"btn btn-lg btn-success btn-block\">Sign in</button>\n" +
        "<a id=\"btn2\">Not registered?</a>\n" +
        "\n" +
        "</div>\n" +
        "<div class=\"col-md-4\"></div>\n" +
        "</div>\n" +
        "</div>";
    $('#loginP').html(page);

}

function callPage() {
    $('#loaddiv').html("<div id='regDiv' class='container' style='padding:20px 0'>" +
        "<div class='row'>" +
        "<div class='col-md-4'></div>" +
        "<div class='col-md-4'>" +
        "<div class='container'>" +
        "<div class='row'>" +
        "<div class='col-md-4'>" +
        "<div id='legend'>" +
        "<legend>Enter your details:</legend>" +
        "</div>" +
        "<div class='control-group'>" +
        "<label class='control-label' for='firstName'>First Name: </label>" +
        "<div class='controls'>" +
        "<input type='text' id='firstName' name='firstName' class='form-control input-lg'>" +
        "</div>" +
        "</div>" +
        "<div class='control-group'>" +
        "<label class='control-label' for='middleName'>MiddleName: </label>" +
        "<div class='controls'>" +
        "<input type='text' id='middleName' name='middleName' class='form-control input-lg'>" +
        "</div>" +
        "</div>" +
        "<div class='control-group'>" +
        "<label class='control-label' for='lastName'>Last Name: </label>" +
        "<div class='controls'>" +
        "<input type='text' id='lastName' name='lastName' class='form-control input-lg'>" +
        "</div>" +
        "</div>" +
        "<div class='control-group'>" +
        "<label class='control-label' for='loginReg'>Login:</label>"
        + "<div class='controls'>" +
        "<input type='text' id='loginReg' name='loginReg' class='form-control input-lg'>"
        + "</div>" +
        "</div>" +
        "<div class='control-group'>"
        + "<label class='control-label' for='passwordReg'>Password:</label>" +
        "<div class='controls'>" +
        "<input type='password' id='passwordReg' name='passwordReg' class='form-control input-lg'>" +
        "</div>" +
        "</div>" +
        "<div class='control-group'>" +
        "<div class='controls'>" +
        "<button class='btn btn-success btn-block' onclick=registration()>Sign up</button>" +
        "<a onclick=emptyReg()>Already registered?</a>" +
        "</div>" +
        "</div>" +
        "</div>" +
        "</div>" +
        "</div>" +
        "<div class='col-md-4'></div>" +
        "</div>" +
        "</div>");
}

function emptyReg() {
    // drawLoginPage();
    location.reload();
}

function registration() {
    var json = {
        firstName: $('#firstName').val(),
        middleName: $('#middleName').val(),
        lastName: $('#lastName').val(),
        login: $('#loginReg').val(),
        password: $('#passwordReg').val()
    };
    $.post({
        dataType: 'json',
        contentType: "application/json",
        url: '/registration/ajax',
        data: JSON.stringify(json),
        success: function (json) {
            alert('ok');
        }
    }).done(function (response) {
        alert("send registration data");
    });
    alert("Вы успешно зарегистрированы");
    $('#loaddiv').empty();
    location.reload();
}