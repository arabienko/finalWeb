<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>

<fmt:setBundle basename="locale.pagecontent" var="locale"/>
<fmt:setLocale value="by_BE" scope="session" />


<fmt:message bundle="${locale}" key="login.username.placeholder" var="username_placeholder"/>
<fmt:message bundle="${locale}" key="login.pattern.error" var="username_pattern_error"/>
<fmt:message bundle="${locale}" key="register.pattern.error" var="registration_pattern_error"/>
<fmt:message bundle="${locale}" key="login.password.placeholder" var="password_placeholder"/>
<fmt:message bundle="${locale}" key="login.password.error" var="password_pattern_error"/>
<fmt:message bundle="${locale}" key="login.no.account" var="no_account"/>
<fmt:message bundle="${locale}" key="label.login" var="login"/>
<fmt:message bundle="${locale}" key="label.password" var="password"/>
<fmt:message bundle="${locale}" key="button.submit" var="submit"/>
<fmt:message bundle="${locale}" key="message.wrongCredentials" var="wrongCredentials"/>
<fmt:message bundle="${locale}" key="forgot_password" var="forgot_password"/>
<fmt:message bundle="${locale}" key="restore.password.changed" var="passwordChanged"/>
<fmt:message bundle="${locale}" key="remember_me" var="remember_me"/>
<fmt:message bundle="${locale}" key="success_delete" var="success_delete"/>
<fmt:message bundle="${locale}" key="user_activated" var="user_activated"/>
<fmt:message bundle="${locale}" key="time_out" var="time_out"/>
<fmt:message bundle="${locale}" key="home" var="home"/>
<fmt:message bundle="${locale}" key="sign_in" var="sign_in"/>
<fmt:message bundle="${locale}" key="footer.copyright" var="footer"/>

<html>
<head>
    <link rel="shortcut icon" href="img/favicon/1.ico"/>
    <script src="${pageContext.request.contextPath}/js/validation/loginValidation.js"></script>
        <script src="${pageContext.request.contextPath}/js/util.js"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <title>LogIn</title>
</head>
<body class="d-flex flex-column">
<jsp:include page="fragment/header.jsp"/>

<form method="POST" action="${pageContext.request.contextPath}/my" class="text-center border border-light p-5">

    <p class="h4 mb-4">${sign_in}</p>

    <label for="login">${login}</label>
    <input id="login" oninput="checkLogin()" type="text" class="form-control mb-4" name="login" required placeholder="${username_placeholder}" title="${username_pattern_error}"/>

    <label for="password">${password}</label>
    <div class="input-group">
        <input id="password" oninput="checkPassword()" type="password" class="form-control mb-4" name="password" required placeholder="${password_placeholder}" title="${registration_pattern_error}"/>
        <span class="input-group-btn">
            <button type="button" onclick="showHide()" class="btn btn-default" id="eye">
                <img src="https://cdn0.iconfinder.com/data/icons/feather/96/eye-16.png" alt="eye" />
            </button>
        </span>
    </div>

    <c:choose>
        <c:when test="${not empty requestScope.wrongData}">
            <p class="text-danger">${wrongCredentials}</p>
        </c:when>
        <c:when test="${not empty requestScope.passwordChanged}">
            <p class="text-danger">${passwordChanged}</p>
        </c:when>
        <c:when test="${not empty requestScope.invalidPassword}">
            <p class="text-danger">${registration_pattern_error}</p>
        </c:when>
        <c:when test="${not empty requestScope.invalidLogin}">
            <p class="text-danger">${username_pattern_error}</p>
        </c:when>
        <c:when test="${not empty sessionScope.success}">
            <p class="text-success">${success_delete}</p>
            <c:remove var="success" scope="session" />
        </c:when>
        <c:when test="${not empty requestScope.userActivated}">
            <p class="text-success">${user_activated}</p>
        </c:when>
    </c:choose>

    <div class="d-flex justify-content-around">
            <div class="custom-control custom-checkbox">
                <input type="checkbox" id="rememberMe" class="custom-control-input" name="rememberMe" value="true"/>
                <label for="rememberMe" class="custom-control-label">${remember_me}</label>
            </div>
    </div>

    <input onclick="checkForLoginAnyData()" class="btn btn-info my-4 btn-block" type="submit" name = "command" value="login"/>
    <a href="${pageContext.request.contextPath}/my?command=registerPage">${no_account}</a>
    <br/>
    <a href="${pageContext.request.contextPath}/my?command=welcome">${home}</a>
        </form>
<div class="container-fluid p-5 my-4 mt-3 border bg-secondary text-white">
        <ctg:info-footer/>
    </div>
    </body>
</html>
