package dev.foursix.http;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class HttpResponse implements HttpServletResponse {

	private String	contentType;
	private PrintWriter	out;
	
	public HttpResponse() {
		// TODO Auto-generated constructor stub
	}
	
	public HttpResponse(OutputStream outputstream) {
		out = new PrintWriter(new OutputStreamWriter(outputstream));
	}

	public String getContentType() {
		// TODO Auto-generated method stub
		return contentType;
	}

	public void setContentType(String contentType) {
		// TODO Auto-generated method stub
		this.contentType = contentType;
		
	}

	public long getContentLength() {
		// TODO Auto-generated method stub
		return 0;
	}

	public PrintWriter getWriter() throws IOException {
		// TODO Auto-generated method stub
		return out;
	}
}
