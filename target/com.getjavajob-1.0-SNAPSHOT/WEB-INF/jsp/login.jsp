<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <title>Login Page</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <spring:url value="/static/js/books.js" var="customs"/>
    <script src="${customs}"></script>
    <spring:url value="/static/js/login.js" var="login"/>
    <script src="${login}"></script>
</head>
<body>
<div id="logout"></div>
<div id="showBooks"></div>
<div id="favBook"></div>
<div id="favBookList"></div>
<div id="loaddiv"></div>
<div id="loginP"></div>
<div id="loginPage" class="container" style="padding:200px 0">
    <div class="row">
        <div class="col-md-4"></div>
        <div class="col-md-4">
            <span id="status"></span>
            <input id="login" type="login" name="login" placeholder="Login" required class="form-control input-lg"/>
            <input id="password" type="password" name="password" class="form-control input-lg"
                   placeholder="Password"
                   required=""/>
            <button id="loginButton" type="submit" name="go" class="btn btn-lg btn-success btn-block">Sign in</button>
            <a id="btn2">Not registered?</a>

        </div>
        <div class="col-md-4"></div>
    </div>
</div>

</body>
</html>
