<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<jsp:include page="/jsp/fragment/header"/>

<div class ="container-fluid mt-1">
<a class="lead">User select course for : <kbd> ${parse}</kbd> </a>
<table class="table table-striped table-hover">
    <tr>
        <th class="col-sm-1 p-3 text-center"> ## </th>
        <th class="col-sm-1 p-3  text-center"> Subject </th>
        <th  class="col-sm-3 p-3 text-center"> Teacher </th>
        <th  class="col-sm-3 p-3 text-center"> Start </th>
        <th  class="col-sm-3 p-3 text-center"> End </th>
    </tr>
    <c:forEach var="cs" items="${teacherCourse}" varStatus="counter">
        <tr>
            <td class="col-sm-1 p-3 text-center"> ${counter.count}</td>
            <td class="col-sm-1 p-3 text-center"><c:out value= "${cs.teacherSubject.subject.nameSubject}" /></td>
            <td class="col-sm-3 p-3 text-center"><c:out value= "${cs.teacherSubject.userInfo}" /></td>
            <td class="col-sm-3 p-3 text-center"><c:out value= "${cs.startDate}" /></td>
            <td class="col-sm-3 p-3 text-center"><c:out value= "${cs.endDate}" /></td>
        </tr>
    </c:forEach>
</table>
</div>
<br>
<button type="button" class="btn  btn-primary btn-secondary" onclick="history.back();">
    Go back</button>
<jsp:include page="/jsp/fragment/footer"/>
</body>
</html>