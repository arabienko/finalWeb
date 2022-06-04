<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>

<fmt:setBundle basename="locale.pagecontent" var="locale"/>
<fmt:setLocale value="by_BE" scope="session" />

<fmt:message bundle="${locale}" key="text_java" var="java_text"/>
<fmt:message bundle="${locale}" key="text_javascript" var="javascript_text"/>
<fmt:message bundle="${locale}" key="text_c" var="java_c_text"/>
<fmt:message bundle="${locale}" key="text_about" var="about_project"/>
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
<div class="container mt-5 btn-group align-center">
 <h4 class="card-title">
${about_project}
 </h4>
</div>
<div class="container-fluid p-5 my-4 mt-3 border bg-secondary text-white">
        <ctg:info-footer/>
    </div>
</body>
</html>