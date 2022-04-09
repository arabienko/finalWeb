<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="css\stylesheet" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <title>School Online</title>
</head>
<body class="container-fluid p-10 ">

<div class="container-fluid pt-3 my-4 mt-10 border bg-secondary text-white">
<img class="card-img-top" src="${pageContext.request.contextPath}/img/books.png" alt="Card image" style="width:30%">
<p class="h1">My First WebProject: Online SCHOOL</p>
    <nav class="navbar navbar-expand-sm bg-secondary navbar-dark ">
        <div class="collapse navbar-collapse" id="collapsibleNavbar">
          <ul class="navbar-nav">
            <li class="nav-item">
              <a class="nav-link" href="jsp/createUser.jsp">Create account</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="jsp/createUser.jsp">Link</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#">Link</a>
            </li>
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown">Dropdown</a>
              <ul class="dropdown-menu">
                <li><a class="dropdown-item" href="#">Link</a></li>
                <li><a class="dropdown-item" href="#">Another link</a></li>
                <li><a class="dropdown-item" href="#">A third link</a></li>
              </ul>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  </div>
<div class="container mt-3 btn-group">
    <div class="row ">
        <div class="col-sm-4 p-3 btn-group">
            <form method="POST" action="/webProject/my">
                <div class="btn  btn-secondary">
                    <button type="submit" name="command" value="course">
                        <input type="hidden" name="find" value="java"/>
                        <div class="card zoom" >
                            <span class="spinner-grow spinner-grow-sm"></span>
                            <h2>Available courses</h2>
                            <p>Java course:</p>
                            <img class="card-img-top" src="img/java.png" alt="Card image">
                            <div class="card-body">
                                <h4 class="card-title">About java</h4>
                                <p class="card-text">Java is a programming language and computing platform first
                                    released by Sun
                                    Microsystems in 1995. It has evolved from humble beginnings to power a large share
                                    of
                                    today’s digital world, by providing the reliable platform upon which many services
                                    and
                                    applications are built. New, innovative products and digital services designed for
                                    the
                                    future continue to rely on Java, as well.</p>
                            </div>
                        </div>
                    </button>
                </div>
            </form>
        </div>
        <div class="col-sm-4 p-3 ">
            <form method="POST" action="/webProject/my">
                <div class="btn  btn-secondary">
                    <button type="submit" name="command" value="course">
                        <input type="hidden" name="find" value="JavaScript"/>
                        <div class="card zoom" >
                            <span class="spinner-grow spinner-grow-sm"></span>
                            <h2>Available courses</h2>
                            <p>JavaScript course:</p>
                            <img class="card-img-top" src="img/javaScript.png" alt="Card image">
                            <div class="card-body">
                                <h4 class="card-title">About javaScript</h4>
                                <p class="card-text">Javascript is used by programmers across the world to create
                                    dynamic and
                                    interactive web content like applications and browsers. JavaScript is so popular
                                    that it's
                                    the most used programming language in the world, used as a client-side programming
                                    language
                                    by 97.0% of all websites.</p>
                            </div>
                        </div>
                    </button>
                </div>
            </form>
        </div>
        <div class="col-sm-4 p-3">
            <form method="POST" action="/webProject/my">
                <div class="btn  btn-secondary">
                    <button type="submit" name="command" value="course">
                        <input type="hidden" name="find" value="C++"/>
                        <div class="card zoom" name="find" value="C++">
                            <span class="spinner-grow spinner-grow-sm"></span>
                            <h2>Available courses</h2>
                            <p>C++ course:</p>
                            <img class="card-img-top" src="img/CLogo.png" alt="Card image">
                            <div class="card-body">
                                <h4 class="card-title">About C++</h4>
                                <p class="card-text">C++ is an object-oriented programming language which gives a clear
                                    structure to programs and allows code to be reused, lowering development costs. C++
                                    is portable and can be used to develop applications that can be adapted to multiple
                                    platforms.</p>
                            </div>
                        </div>
                    </button>
                </div>
            </form>
        </div>
    </div>

</div>


 <div class="container-fluid p-5 my-4 mt-3 border bg-secondary text-white">
        <p>&#169; 2021-2022, Arabiyenka Tatsiana</p>
    </div>
</body>
</html>