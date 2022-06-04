<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setLocale value="${sessionScope.local}" scope="session"/>
<fmt:setBundle basename="locale.pagecontent" var="locale"/>
<fmt:message bundle="${locale}" key="title_text" var="title"/>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>
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
<jsp:include page="../fragment/header.jsp"/>
<div class ="container-fluid mt-1">
<a class="lead">User select course teacher for : <kbd> ${selected}</kbd> </a>
<table class="table table-striped table-hover">
    <tr>
        <th class="col-sm-1 p-3 text-center"> ## </th>
        <th class="col-sm-1 p-3  text-center"> Subject </th>
        <th  class="col-sm-2 p-3 text-center"> Teacher </th>
        <th  class="col-sm-1 p-3 text-center"> Start </th>
        <th  class="col-sm-1 p-3 text-center"> End </th>
        <th  class="col-sm-3 p-3 text-center"> command </th>
    </tr>
    <c:forEach var="cs" items="${teacherCourse}" varStatus="counter">
        <tr>
                    <td class="col-sm-1 p-3 text-center"> ${counter.count}</td>
                    <td class="col-sm-1 p-3 text-center"><c:out value= "${cs.teacherSubject.subject.nameSubject}" /></td>
                    <td class="col-sm-3 p-3 text-center"><c:out value= "${cs.teacherSubject.userInfo}" /></td>
                    <td class="col-sm-2 p-3 text-center"><c:out value= "${cs.startDate}" /></td>
                    <td class="col-sm-2 p-3 text-center"><c:out value= "${cs.endDate}" /></td>
                    <td class="col-sm-3 p-3 text-center">
                    <form method="POST" action="/webProject/my">
                                <button class="btn btn-primary btn-secondary" type="submit" name="command" value="studentCourses" >
                                <input type="hidden" name="find" value="${cs.teacherSubject.subject.nameSubject}"/>
                                Writing to course</button>
                                </form>
                                </td>
                </tr>
    </c:forEach>
</table>
</div>
<br>
<form method="POST" action="/webProject/my">
<button type="submit" class="btn  btn-primary btn-secondary" name="command" value="welcome">
    Go back</button>
</form>

<div class="container-fluid p-5 my-4 mt-3 border bg-secondary text-white">
        <ctg:info-footer/>
    </div></body>
</html>