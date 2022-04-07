<%@ page contentType="text/html; charset=utf8" %>
<html>
	<head><title>JSP-страница</title></head>
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
