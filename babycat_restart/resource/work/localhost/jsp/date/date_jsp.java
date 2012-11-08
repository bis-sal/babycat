import  java.util.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.io.*;


public final class date_jsp implements HttpJspPage {

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



public void _jspService(HttpServletRequest request, HttpServletResponse response) throws IOException {
PrintWriter out = null;

try {
response.setContentType("text/html");
out = response.getWriter();

out.write("\r\n");
out.write("\r\n");
out.write("\r\n");
out.write("<html>\r\n");
out.write("<head>\r\n");
out.write("<title>과제: Date</title>\r\n");
out.write("<style>\r\n");
out.write("* { font-family: Verdana }\r\n");
out.write("</style>\r\n");
out.write("</head>\r\n");
out.write("<body>\r\n");

	Calendar calendar = Calendar.getInstance();
	int dayOfMonth = getDayOfMonth(calendar);
	int year = getYear(calendar);
	String month = getMonth(calendar);

out.write("<h3>Date Example</h3>\r\n");
out.write("<ul>\r\n");
out.write("<li>Day of month: is \r\n");
out.print( dayOfMonth );
out.write("<li>Year: is \r\n");
out.print( year );
out.write("<li>Month: is \r\n");
out.print( month );
out.write("</ul>\r\n");
out.write("</body>\r\n");
out.write("</html>\r\n");
out.write("\r\n");
out.write("\r\n");
out.write('\r');
out.write('\n');
} catch (Throwable th) {
throw new IOException(th.getMessage());
} finally {
out.close();
}
}
}

