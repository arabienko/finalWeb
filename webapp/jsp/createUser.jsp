<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <link href="css\stylesheet" rel="stylesheet">
    <title>School Online</title>
</head>
<body class="container-fluid p-10 ">

<jsp:include page="fragment/header"/>

<div class="container">
    <h2>Form for creation user</h2>
    <p>Fill the form:</p>
    <form method="POST" enctype="multipart/form-data" action="/webProject/my">
        <div class="form-group">
            <label for="log">Login:</label>
            <input type="text" class="form-control" id="log" name="login">
        </div>
        <div class="form-group">
            <label for="pwd">Password:</label>
            <input type="password" class="form-control" id="pwd" name="password">
        </div>
        <p>Select a role on the site::</p>
        <div class="form-check">
            <label class="form-check-label" for="radio1">
                <input type="radio" class="form-check-input" id="radio1" name="optradio" value="option1" checked>Teacher
            </label>
        </div>
        <div class="form-check">
            <label class="form-check-label" for="radio2">
                <input type="radio" class="form-check-input" id="radio2" name="optradio" value="option2">Student
            </label>
        </div>
        <div class="form-group">
            <label for="nm">Name:</label>
            <input type="text" class="form-control" id="nm" name="username">
        </div>
        <div class="form-group">
            <label for="snm">Surname:</label>
            <input type="text" class="form-control" id="snm" name="surname">
        </div>
        <div class="form-group">
            <label for="phn">Phone:</label>
            <input type="text" class="form-control" id="phn" name="phone">
        </div>
        <h4>Upload photo:</h4>
        Choose a file: <input type="file" name="multiPartServlet"/>
        <button type="submit" class="btn btn-primary btn-secondary" name="command" value="createUser">Submit</button>
    </form>
</div>


<jsp:include page="fragment/footer"/>

</body>
</html>