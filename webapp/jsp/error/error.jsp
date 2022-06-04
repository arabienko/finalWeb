<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Error!</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="stylesheet" href="../../css/errorPage.css">
</head>
<body>
<jsp:include page="../fragment/header.jsp"/>
<div class="container">
    <h1>Oops!</h1>
    <div class="container">
        <h2>Error message: ${errorMsg}</h2>
        <a class="btn btn-lg btn-warning" href="${pageContext.request.contextPath}/jsp/login.jsp">Return to login page</a>
        <br/> <br/> <br/>
        <a class="btn btn-lg btn-warning" href="${pageContext.request.contextPath}/jsp/welcome.jsp">Return to main page</a>
    </div>
    <div class="container">
        <br/><br/>
        Request from ${pageContext.errorData.requestURI} is failed
        <br/>
        Servlet name or type: ${pageContext.errorData.servletName}
        <br/>
        Status code: ${pageContext.errorData.statusCode}
        <br/>
        Exception: ${pageContext.errorData.throwable}
    </div>
</div>
<div class="container-fluid p-5 my-4 mt-3 border bg-secondary text-white">
    <ctg:info-footer/>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
</body>
</html>