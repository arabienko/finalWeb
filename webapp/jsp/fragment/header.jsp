<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setLocale value="${sessionScope.local}" scope="session"/>
<fmt:setBundle basename="locale.pagecontent" var="locale"/>
<fmt:message bundle="${locale}" key="title_text" var="title"/>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/stylesheet" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <title>Header</title>
</head>
<body>
<div class="container-fluid pt-3 my-4 mt-10 border bg-secondary text-white">
    <img class="card-img-top" src="${pageContext.request.contextPath}/img/books.png" alt="Card image"
         style="width:30%">
    <p class="h1">${title}</p>
    <nav class="navbar navbar-expand-sm bg-secondary navbar-dark ">
        <div class="collapse navbar-collapse" id="collapsibleNavbar">
            <ul class="navbar-nav">
                <form method="POST" action="/webProject/my">
                    <li class="nav-item">
                        <button class="btn btn-primary btn-secondary" type="submit" name="command" value="welcome">
                            HOME
                        </button>
                    </li>
                </form>
                <form method="POST" action="/webProject/my">
                    <li class="nav-item">
                        <button class="btn btn-primary btn-secondary" name="command" value="teacherCourses"
                                type="submit">All Courses
                        </button>
                    </li>
                </form>

                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown">MENU</a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" type="submit"
                               href="${pageContext.request.contextPath}/my?command=myCourses">My courses</a></li>
                        <li><a class="dropdown-item" type="submit" href="${pageContext.request.contextPath}/my?command=userPage">My page</a></li>
                        <li><a class="dropdown-item" type="submit"
                               href="${pageContext.request.contextPath}/my?command=about">About</a>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
        <ctg:hello role="${role}"/>
    </nav>
</div>
</body>
</html>