<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setLocale value="${sessionScope.local}" scope="session"/>
<fmt:setBundle basename="locale.pagecontent" var="locale"/>
<fmt:message bundle="${locale}" key="title_text" var="title"/>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>

<fmt:message bundle="${locale}" key="no_access" var="no_access"/>
<fmt:message bundle="${locale}" key="return_to_login" var="return_to_login"/>
<fmt:message bundle="${locale}" key="return_to_main" var="return_to_main"/>
<fmt:message bundle="${locale}" key="footer.copyright" var="footer"/>

<html>
<head>
  <link rel="shortcut icon" href="img/favicon/1.ico"/>
     <script src="${pageContext.request.contextPath}/js/validation/loginValidation.js"></script>
         <script src="${pageContext.request.contextPath}/js/util.js"></script>
     <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
     <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
     <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <title>Restricted</title>
</head>
<body class="d-flex flex-column">
<jsp:include page="fragment/header.jsp"/>

<div class="text-center">
    <h1>${no_access}</h1>
    <a href="${pageContext.servletContext.contextPath}/login">${return_to_login}</a>
    <br/>
    <br/>
    <a href="${pageContext.servletContext.contextPath}/welcome">${return_to_main}</a>
</div>

<footer class="footer mt-auto py-3">
    <div class="container text-center">
        <span class="text-muted">${footer}</span>
    </div>
</footer>
</body>
</html>
