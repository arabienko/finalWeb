<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>

<fmt:setBundle basename="locale.pagecontent" var="locale"/>
<fmt:setLocale value="by_BE" scope="session" />

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
     <link rel="stylesheet" href="${pageContext.request.contextPath}/../css/stylesheet">
    <title>School Online</title>
</head>
<body class="container-fluid p-10 ">
<jsp:include page="fragment/header.jsp"/>
<div class ="container-fluid mt-1">
<table class="table table-striped table-hover">
    <tr>
        <th class="col-sm-1 p-3 text-center"> ## </th>
        <th class="col-sm-1 p-3  text-center"> Subject </th>
        <th  class="col-sm-2 p-3 text-center"> Teacher </th>
        <th  class="col-sm-2 p-3 text-center"> Student </th>
        <th  class="col-sm-1 p-3 text-center"> Start </th>
        <th  class="col-sm-1 p-3 text-center"> End </th>
        <th  class="col-sm-3 p-3 text-center"> description </th>
    </tr>
    <c:forEach var="cs" items="${courseStudent}" varStatus="counter">
        <tr>
            <td class="col-sm-1 p-3 text-center"> ${counter.count}</td>
            <td class="col-sm-1 p-3 text-center"><c:out value= "${cs.teacherCourse.teacherSubject.subject.nameSubject}" /></td>
            <td class="col-sm-1 p-3 text-center"><c:out value= "${cs.teacherCourse.teacherSubject.userInfo}" /></td>
            <td class="col-sm-1 p-3 text-center"><c:out value= "${cs.userInfo}" /></td>
            <td class="col-sm-1 p-3 text-center"><c:out value= "${cs.teacherCourse.startDate}" /></td>
            <td class="col-sm-1 p-3 text-center"><c:out value= "${cs.teacherCourse.endDate}" /></td>
         <td class="col-sm-1 p-3 text-center"><c:out value= "${cs.teacherCourse.teacherSubject.subject.description}" /></td>
        </tr>
    </c:forEach>
</table>
</div>
<div>
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


<div class="container-fluid p-5 my-4 mt-3 border bg-secondary text-white">
        <ctg:info-footer/>
    </div></body>
</html>