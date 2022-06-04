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

<div class="container mt-5 btn-group align-center">
    <div class="row ">
        <div class="col-sm-4 col-md-4">
            <form method="POST" action="/webProject/my">
                <div class="btn  btn-secondary">
                    <button type="submit" name="command" value="studentCourses">
                        <input type="hidden" name="find" value="java"/>
                        <div class="card zoom" >
                            <span class="spinner-grow spinner-grow-sm"></span>
                            <h2>Available courses</h2>
                            <p>Java course:</p>
                            <img class="card-img-top" src="${pageContext.request.contextPath}/img/java.png" alt="Card image">
                            <div class="card-body">
                                <h4 class="card-title">About java</h4>
                                <p class="card-text">
                                ${java_text}
                                </p>
                            </div>
                        </div>
                    </button>
                </div>
            </form>
        </div>
        <div class="col-sm-4 col-md-4">
        <form method="POST" action="/webProject/my">
            <div class="btn  btn-secondary">
                <button type="submit" name="command" value="studentCourses">
                    <input type="hidden" name="find" value="JavaScript"/>
                    <div class="card zoom" >
                        <span class="spinner-grow spinner-grow-sm"></span>
                        <h2>Available courses</h2>
                        <p>JavaScript course:</p>
                        <img class="card-img-top" src="${pageContext.request.contextPath}/img/javaScript.png" alt="Card image">
                        <div class="card-body">
                            <h4 class="card-title">About javaScript</h4>
                            <p class="card-text">
                            ${javascript_text}
                            </p>
                        </div>
                    </div>
                </button>
            </div>
        </form>
    </div>
    <div class="col-sm-4 col-md-4">
        <form method="POST" action="/webProject/my">
            <div class="btn  btn-secondary">
                <button type="submit" name="command" value="studentCourses">
                    <input type="hidden" name="find" value="C++"/>
                    <div class="card zoom" name="find" value="C++">
                        <span class="spinner-grow spinner-grow-sm"></span>
                        <h2>Available courses</h2>
                        <p>C++ course:</p>
                        <img class="card-img-top" src="${pageContext.request.contextPath}/img/CLogo.png" alt="Card image">
                        <div class="card-body">
                            <h4 class="card-title">About C++</h4>
                            <p class="card-text">
                            ${java_c_text}
                            </p>
                        </div>
                    </div>
                </button>
            </div>
        </form>
    </div>
</div>

</div>


<div class="container-fluid p-5 my-4 mt-3 border bg-secondary text-white">
        <ctg:info-footer/>
    </div>
</body>
</html>