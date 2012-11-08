import  javax.servlet.http.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.io.*;


public final class reqparams_jsp implements HttpJspPage {

public String getParameterValue(HttpServletRequest req, String name) {
	String[] values = req.getParameterValues(name);
	
	if (values == null || values.length == 0) {
		return null;
	} else {
		return values[0];
	}
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
out.write("<title>과제: Request Parameters</title>\r\n");
out.write("<style>\r\n");
out.write("* { font-family: Verdana }\r\n");
out.write("</style>\r\n");
out.write("</head>\r\n");
out.write("<body>\r\n");
out.write("<h3>Request Parameters Example</h3>\r\n");
out.write("Parameters in this request:<br>\r\n");

String firstName = getParameterValue(request, "firstname");
String lastName = getParameterValue(request, "lastname");

if (firstName == null && lastName == null) {

out.write("No Parameters, Please enter some\r\n");

} else {

out.write("First Name: = \r\n");
out.print( firstName );
out.write("<br>\r\n");
out.write("Last Name: = \r\n");
out.print( lastName );
out.write("<br>\r\n");

}

out.write("<p>\r\n");
out.write("<form action=\"reqparams.jsp\" method=\"POST\">\r\n");
out.write("	First Name: <input type=\"text\" size=\"20\" name=\"firstname\"><br>\r\n");
out.write("	Last Name: <input type=\"text\" size=\"20\" name=\"lastname\"><br>\r\n");
out.write("	<input type=\"submit\">\r\n");
out.write("</form>\r\n");
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

