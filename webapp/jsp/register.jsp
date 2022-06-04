<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>

<fmt:setBundle basename="locale.pagecontent" var="locale"/>
<fmt:setLocale value="by_BE" scope="session" />

<fmt:message bundle="${locale}" key="text_java" var="java_text"/>
<fmt:message bundle="${locale}" key="text_javascript" var="javascript_text"/>
<fmt:message bundle="${locale}" key="text_c" var="java_c_text"/>
<fmt:message bundle="${locale}" key="title_text" var="title"/>



<html lang>
<head>
 <link rel="shortcut icon" href="img/favicon/1.ico"/>
    <script src="${pageContext.request.contextPath}/js/validation/loginValidation.js"></script>
        <script src="${pageContext.request.contextPath}/js/util.js"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <title>School Online</title>
</head>
<body class="container-fluid p-10 ">
<jsp:include page="fragment/header.jsp"/>
<div class="container">
    <h2>Form for creation user</h2>
    <p>Fill the form:</p>
    <form method="POST" enctype="multipart/form-data" action="/webProject/my">
        <div class="form-group">
            <label for="log">Login:</label>
                <input id="login" oninput="checkLogin()" type="text" class="form-control mb-4" name="login" required placeholder="${username_placeholder}" title="${username_pattern_error}"/>
        </div>
        <div class="form-group">
            <label for="pwd">Password:</label>
                    <input id="password" oninput="checkPassword()" type="password" class="form-control mb-4" name="password" required placeholder="${password_placeholder}" title="${registration_pattern_error}"/>
        </div>
        <p>Select a role on the site::</p>
        <div class="form-check">
            <label class="form-check-label" for="radio1">
                <input type="radio" class="form-check-input" id="radio1" name="optradio" value="TEACHER" checked>Teacher
            </label>
        </div>
        <div class="form-check">
            <label class="form-check-label" for="radio2">
                <input type="radio" class="form-check-input" id="radio2" name="optradio" value="STUDENT">Student
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
        <button type="submit" class="btn btn-primary btn-secondary" name="command" value="register">Submit</button>
        <button type="submit" class="btn btn-primary btn-secondary" name="command" value="loginPage">Back</button>

    </form>
</div>


<div class="container-fluid p-5 my-4 mt-3 border bg-secondary text-white">
        <ctg:info-footer/>
    </div>
</body>
</html>