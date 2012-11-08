package dev.foursix.http;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class RequestParser {
	
	private BufferedReader	requestIn;
	private InputStream 	reqIn;
	private HttpRequest		request;

	public RequestParser(InputStream inputStream) {
//		requestIn
//		= new BufferedReader(new InputStreamReader(inputStream));
		reqIn = inputStream;
		request = new HttpRequest();
	}
	
	public HttpRequest parse() {
		try {
			List<String>
			messagelines = readRequestMessage();
			
			List<RequestField>
			requestFields = readRequestFields(messagelines);
			
			int length = requestFields.size();
			
			for (int i = 0; i < length; i++) {
				RequestField field = requestFields.get(i);
				addField(field);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
//			close();
		}
		
		return this.request;
	}

	private void addField(RequestField field) {
		
		String name = field.getFieldName();
		
		if ("GET".equals(name)) {
			
			String contentURL = field.getFieldValue(0);
			
			request.setMethod("GET");
			request.setContent(contentURL);
			
		} else if ("POST".equals(name)) {
			
			String contentURL = field.getFieldValue(0);
			
			request.setMethod("POST");
			request.setContent(contentURL);
			
		} else if (null == name) {
			return;			
		} else {
			
			String fieldname = field.getFieldName();
			int colonIndex = fieldname.indexOf(':');
			
			if (colonIndex < 0) {
				addParameter(fieldname);
			} else {			
				fieldname = fieldname.substring(0, colonIndex);
				
				String value = field.getFieldValue(0);
				
				request.addHeader(fieldname, value);
			}
		}
	}

	private void addParameter(String fieldname) {
		
		System.out.println("param : " + fieldname);
		
		StringTokenizer tokenizer = new StringTokenizer(fieldname, "&");
		
		while (tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken();
			int delim = token.indexOf("=");
			String paramName = token.substring(0, delim);
			String paramValue = token.substring(delim + 1);
			String[] paramValues = paramValue.split("[+]");
			request.addParameter(paramName, paramValues);
		}
		
	}

	private List<RequestField> readRequestFields(List<String> messagelines) {
		
		List<RequestField> fields = new ArrayList<RequestField>();
		
		int length = messagelines.size();
		for (int i = 0; i < length; i++) {
			
			String line = messagelines.get(i);
			RequestField field = getFields(line);
			if (null != field) {
				fields.add(field);
			}
		}
		return fields;
	}

	private RequestField getFields(String line) {
				
		StringTokenizer tokenizer = new StringTokenizer(line);
		RequestField field = new RequestField();
		
		if (tokenizer.hasMoreTokens()) {
			String fieldname = tokenizer.nextToken();
			field.setFieldName(fieldname);
		}
		
		while (tokenizer.hasMoreTokens()) {
			String fieldvalue = tokenizer.nextToken();
			field.addFieldValue(fieldvalue);
		}
		
		return field;
		
	}

	private List<String> readRequestMessage() throws IOException {
		
		List<String> lines = new ArrayList<String>(); 
		String line = null;
		
		byte[] buf = new byte[4096];
		ByteArrayOutputStream arOut = new ByteArrayOutputStream();
		
		while (reqIn.available() > 0) {
			int len = reqIn.read(buf);
			arOut.write(buf, 0, len);
		}
				
		StringTokenizer tokenizer = new StringTokenizer(arOut.toString(), "\r\n");
		
		while (tokenizer.hasMoreTokens()) {
			line = tokenizer.nextToken();
			lines.add(line);
			System.out.println("token: " + line);
		}
		
//		while (requestIn.ready()) {
//			line = requestIn.readLine();
//			lines.add(line);
//			System.out.println(line);
//		}
		
		return lines;
	}
	


	private void close() {
		// TODO Auto-generated method stub
		try {			
			requestIn.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
