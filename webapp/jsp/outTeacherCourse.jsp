<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>

<fmt:setBundle basename="locale.pagecontent" var="locale"/>
<fmt:setLocale value="by_BE" scope="session" />

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
<div class ="container-fluid mt-1">
<a class="lead">User select course for : <kbd> ${course}</kbd> </a>
<table class="table table-striped table-hover" border="1" cellpadding="5" cellspacing="5">
    <tr>
        <th class="col-sm-1 p-3 text-center"> ## </th>
        <th class="col-sm-1 p-3  text-center"> Subject </th>
        <th class="col-sm-3 p-3 text-center"> Teacher </th>
        <th class="col-sm-2 p-3 text-center"> Start </th>
        <th class="col-sm-2 p-3 text-center"> End </th>
       <th  class="col-sm-3 p-3 text-center"> Select course </th>
       <th  class="col-sm-3 p-3 text-center"> Choose course </th>

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
                        <button class="btn btn-primary btn-secondary" type="submit" name="command" value="subjectTeacherCourse" >
                        <input type="hidden" name="find" value="${cs.teacherSubject.subject.nameSubject}"/>
                        Select</button>
                        </form>
                        </td>
            <td class="col-sm-3 p-3 text-center">
                        <c:choose>
                         <c:when test="${userRole == 2}">
                                <form action="${pageContext.servletContext.contextPath}/my?command=about" method="post">
                                    <input type="submit" class="btn btn-info" value="CHOOSE">
                                </form>
                            </c:when>
                             </c:choose>
            </td>
        </tr>
    </c:forEach>
</table>
</div>
<div>
<%--For displaying Previous link except for the 1st page --%>
  <c:if test="${currentPage != 1}">
   <form action="${pageContext.servletContext.contextPath}/my" method="post">
   <td><a href= "${pageContext.servletContext.contextPath}/my?command=teacherCourses">Previous</a></td>
   <input type="hidden" name="page" value="${currentPage - 1}">
   </form>
  </c:if>

  <%--For displaying Page numbers. The when condition does not display
              a link for the current page--%>
  <table border="1" cellpadding="5" cellspacing="5">
      <tr>
          <c:forEach varStatus="loop" begin="1" end="${noOfPages}" step="1" var="i">
              <c:choose>
                  <c:when test="${currentPage eq i}">
                    <input type="submit" class="btn btn-info" name="page" value="${i}">
                    </c:when>
                  <c:otherwise>
                  <form action="${pageContext.servletContext.contextPath}/my?command=teacherCourses" method="post">
                    <input type="submit" class="btn btn-info" name="page" value="${i}">
                    </form>
                  </c:otherwise>
              </c:choose>
          </c:forEach>
      </tr>
  </table>
  </div>

<br>
<form method="POST" action="/webProject/my">
<button type="submit" class="btn  btn-primary btn-secondary" name="command" value="welcome">
    Go back</button>
</form>
<div class="container-fluid p-5 my-4 mt-3 border bg-secondary text-white">
        <ctg:info-footer/>
    </div>
    </body>
</html>