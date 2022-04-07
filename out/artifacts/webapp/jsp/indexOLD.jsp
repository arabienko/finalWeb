<%@ page contentType="text/html; charset=utf8" %>
<html>
	<head>
 <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <title>School Online</title>
    <link href="css\stylesheet" rel="stylesheet">
	</head>
	<body>
	<%! private int count = 0;
		String version = new String("J2EE 1.7");
		private String getName(){return "J2EE 1.8";} %>
	<% out.println("Значение count: "); %>
	<%= count++ %>
	<br/>
	<% out.println("Значение count после инкремента: " + count); %>
	<br/>
	<% out.println("Старое значение version: "); %>
	<%= version %>
	<br/>
	<% version=getName();
	out.println("Новое значение version: " + version); %>
    <table>
     <c:forEach var="elem" items="${set}" varStatus="status">
    <tr>
     <td><c:out value="${elem.nameSubject}" /></td>

    </tr>
     </c:forEach>
    </table>
    	</body>
</html>
