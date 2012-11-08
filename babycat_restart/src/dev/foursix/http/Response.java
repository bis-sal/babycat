package dev.foursix.http;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

public class Response {
	private BufferedInputStream		fileInput;
	private BufferedOutputStream	responseOut;
	
	private StringBuffer			header;

	public Response(OutputStream outputStream) {
		responseOut = new BufferedOutputStream(outputStream);
	}
	
	public void setContent(String contentName, String appBasePath)
			throws FileNotFoundException {
		
		File requestedFile = null;
		requestedFile = findFile(contentName, appBasePath);
		
		setHeader(requestedFile);
		
		fileInput
		= new BufferedInputStream(new FileInputStream(requestedFile));
	}
	
	private File findFile(String filename, String appBasePath)
			throws FileNotFoundException {
		
		if ("".equals(filename) || "/".equals(filename)) {
			filename = "/index.html";
		}

		String filepath = appBasePath + filename;
		File requestedFile = new File(filepath);

		if (!requestedFile.exists()) {
			throw new FileNotFoundException();
		}

		return requestedFile;
	}

	private void setHeader(File requestedFile) {		
		header = new StringBuffer();
		
		header.append("HTTP/1.1 200 OK\r\n");
		header.append("Server: 46\'s Web Server\r\n");
		
		long ldLastModified = requestedFile.lastModified();
		String lastModified = new Date(ldLastModified).toString();
		header.append("Last-Modified: " + lastModified + "\r\n");

		String filename = requestedFile.getName();
		int indexOfExtension = filename.lastIndexOf(".") + 1;
		String extension = filename.substring(indexOfExtension);

		String contentType = findContentType(extension);
		header.append("Content-Type: " + contentType + "\r\n");
		
		header.append("Content-Length: " + requestedFile.length() + "\r\n");
		header.append("Date: " + new Date().toString() + "\r\n");
		header.append("\r\n");
	}

	private String findContentType(String extension) {
		String contentType = null;

		if ("html".equals(extension)) {
			contentType = "text/html";
		} else if ("gif".equals(extension)) {
			contentType = "image/gif";
		} else if ("ico".equals(extension)) {
			contentType = "image/x-icon";
		}
		return contentType;
	}
	
	public void send() throws IOException {
		responseOut.write(header.toString().getBytes());
		
		int c = 0;
		while ((c = fileInput.read()) != -1) {
			responseOut.write(c);
		}
		responseOut.flush();
		responseOut.close();
	}
}
