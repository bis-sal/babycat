<%@ page import = "java.util.*" %>

<html>
<head>
<title>과제: Date</title>
<style>
* { font-family: Verdana }
</style>
</head>
<body>
<%
	Calendar calendar = Calendar.getInstance();
	int dayOfMonth = getDayOfMonth(calendar);
	int year = getYear(calendar);
	String month = getMonth(calendar);
%>
<h3>Date Example</h3>
<ul>
<li>Day of month: is <%= dayOfMonth %>
<li>Year: is <%= year %>
<li>Month: is <%= month %>
</ul>
</body>
</html>

<%!
public int getDayOfMonth(Calendar calendar) {
	return calendar.get(Calendar.DAY_OF_MONTH);
}

public int getYear(Calendar calendar) {
	return calendar.get(Calendar.YEAR);
}

public String getMonth(Calendar calendar) {
	String[] months = new String [] {
		"January", "February", "March",
		"April", "May", "June",
		"July", "August", "September",
		"October", "November", "December" };
	
	return months[calendar.get(Calendar.MONTH)];
}
%>
