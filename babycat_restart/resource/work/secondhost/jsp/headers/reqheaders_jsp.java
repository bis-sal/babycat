import  java.util.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.io.*;


public final class reqheaders_jsp implements HttpJspPage {



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
out.write("<title>과제: Request Headers</title>\r\n");
out.write("<style>\r\n");
out.write("* { font-family: Verdana }\r\n");
out.write("</style>\r\n");
out.write("</head>\r\n");
out.write("<body>\r\n");
out.write("<h3>Request Headers Example</h3>\r\n");
out.write("<table>\r\n");

Enumeration<String> e = request.getHeaderNames();

while (e.hasMoreElements()) {
	String name = (String) e.nextElement();
	String value = request.getHeader(name);
	
	out.println("<tr>");
	out.println("<td>" + name + "</td><td>" + value + "</td>");
}

out.write("</table>\r\n");
out.write("</body>\r\n");
out.write("</html>\r\n");
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

