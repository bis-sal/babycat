<%@ page import = "java.util.*" %>

<html>
<head>
<title>과제: Request Headers</title>
<style>
* { font-family: Verdana }
</style>
</head>
<body>
<h3>Request Headers Example</h3>
<table>
<%
Enumeration<String> e = request.getHeaderNames();

while (e.hasMoreElements()) {
	String name = (String) e.nextElement();
	String value = request.getHeader(name);
	
	out.println("<tr>");
	out.println("<td>" + name + "</td><td>" + value + "</td>");
}
%>
</table>
</body>
</html>
