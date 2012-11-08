package javax.servlet.http;

import java.io.IOException;
import java.io.PrintWriter;

public interface HttpServletResponse {

	String getContentType();
	
	void setContentType(String contentType);
	
	long getContentLength();
	
	PrintWriter getWriter() throws IOException;
}
