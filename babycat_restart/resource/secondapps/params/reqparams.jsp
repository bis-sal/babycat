<%@ page import = "javax.servlet.http.*" %>

<html>
<head>
<title>과제: Request Parameters</title>
<style>
* { font-family: Verdana }
</style>
</head>
<body>
<h3>Request Parameters Example</h3>
Parameters in this request:<br>
<%
String firstName = getParameterValue(request, "firstname");
String lastName = getParameterValue(request, "lastname");

if (firstName == null && lastName == null) {
%>
No Parameters, Please enter some
<%
} else {
%>
First Name: = <%= firstName %><br>
Last Name: = <%= lastName %><br>
<%
}
%>
<p>
<form action="reqparams.jsp" method="POST">
	First Name: <input type="text" size="20" name="firstname"><br>
	Last Name: <input type="text" size="20" name="lastname"><br>
	<input type="submit">
</form>
</body>
</html>

<%!
public String getParameterValue(HttpServletRequest req, String name) {
	String[] values = req.getParameterValues(name);
	
	if (values == null || values.length == 0) {
		return null;
	} else {
		return values[0];
	}
}
%>
