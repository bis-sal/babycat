package javax.servlet.http;

import java.util.Enumeration;

public interface HttpServletRequest {
	
	String getServerName();
	
	int getServerPort();
	
	String getHeader(String name);
	
	Enumeration<String> getHeaderNames();
	
	String[] getParameterValues(String name);
	
	Enumeration<String> getParameterNames();
}
