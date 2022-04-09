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
<div class="container-fluid mt-1">

    <div class="container mt-3 btn-group">
        <div class="col-sm-4 p-3 btn-group">
            <form method="POST" action="/webProject/my">
                <div class="btn  btn-secondary">
                    <div class="card zoom">
                        <span class="spinner-grow spinner-grow-sm"></span>
                        <h2> Your account</h2>
                        <img class="card-img-top" src= "img/img.png" alt="Card image">
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
           <button type="button" class="btn  btn-primary btn-secondary" href="index.jsp">
                      Go back
                  </button>
    <jsp:include page="/jsp/fragment/footer"/>
</div>
</body>
</html>