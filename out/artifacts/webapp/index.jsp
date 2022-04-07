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

<jsp:include page="jsp/fragment/header"/>

 <div class="container mt-3">
    <form method="POST" action="/webProject/my">

        <div class="btn  btn-primary btn-secondary">
  <button type="submit" name="commandSCAll" value="selectALL">

                <div class="card zoom" style="width:400px">
                    <span class="spinner-grow spinner-grow-sm"></span>
                   <h2>Available courses</h2>
                   <p>Java course:</p>
                    <img class="card-img-top" src="img/java.png" alt="Card image" style="width:100%">
                    <div class="card-body">
                            <h4 class="card-title">About java</h4>
                        <p class="card-text">Java is a programming language and computing platform first released by Sun Microsystems in 1995. It has evolved from humble beginnings to power a large share of today’s digital world, by providing the reliable platform upon which many services and applications are built. New, innovative products and digital services designed for the future continue to rely on Java, as well.</p>
                    </div>
                </div>
            button </button>
            </div>
            </form>
       </div>


<div class="container-fluid p-5 my-4 mt-3 border bg-secondary text-white text-center">
    <p>Footer</p>
</div>
</body>
</html>
