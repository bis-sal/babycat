package dev.foursix.http;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class HttpRequest implements HttpServletRequest {

	private String					method;
	private String					contentURL;
	private String					protocol;
	
	private Map<String, String>		headers;
	private Map<String, String[]>	parameters;

	public HttpRequest() {
		headers = new HashMap<String, String>();
		parameters = new HashMap<String, String[]>();
		contentURL = "/";
	}

	public String getServerName() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getServerPort() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getHeader(String name) {
		// TODO Auto-generated method stub
		return headers.get(name);
	}
	
	public void addHeader(String name, String value) {
		headers.put(name, value);
//		System.out.println(name + " : " + value);
	}

	public Enumeration<String> getHeaderNames() {
		// TODO Auto-generated method stub
		return Collections.enumeration(headers.keySet());
	}

	public void addParameter(String name, String[] values) {
		parameters.put(name, values);
	}

	public String[] getParameterValues(String name) {
		return parameters.get(name);
	}

	public Enumeration<String> getParameterNames() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getMethod() {
		return this.method;
	}

	public void setMethod(String method) {
		// TODO Auto-generated method stub
		this.method = method;
	}

	public String getContent() {
		return contentURL;
	}

	public void setContent(String contentURL) {
		if (null == contentURL) {
			contentURL = "/";
		}
		this.contentURL = contentURL;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getRequestedHost() {
		
		String requestedHost = headers.get("Host");
		if (null == requestedHost || "".equals(requestedHost) ) {
			return "localhost";
		}
		int colonIndex = requestedHost.indexOf(":");

		if ( !(colonIndex < 0) ) {
			requestedHost = requestedHost.substring(0, colonIndex);
		}
		return requestedHost;
	}
}
