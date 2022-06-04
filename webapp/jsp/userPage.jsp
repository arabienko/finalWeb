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
<div class="container-fluid mt-1">

    <div class="container mt-3 btn-group">
        <div class="col-sm-4 p-3 btn-group">
            <form method="POST" action="/webProject/my">
                <div class="btn  btn-secondary">
                    <div class="card zoom">
                        <span class="spinner-grow spinner-grow-sm"></span>
                        <h2> Your account</h2>
                        <img class="card-img-top" src= "${pageContext.request.contextPath}/${userInfo.pathImage}" alt="Card image">
                        <div class="card-body">
                            <h4 class="card-title"> ${userInfo.surname} ${userInfo.name}</h4>
                            <p class="card-text"> ${userInfo.phone}</p>
                        </div>
                    </div>
                </div>
            </form>
        </div>
        <br>
          </div>
         <form method="POST" action="/webProject/my">
         <button type="submit" class="btn  btn-primary btn-secondary" name="command" value="welcome">
             Welcome page</button>
         </form>
<div class="container-fluid p-5 my-4 mt-3 border bg-secondary text-white">
        <ctg:info-footer/>
    </div></div>
</body>
</html>