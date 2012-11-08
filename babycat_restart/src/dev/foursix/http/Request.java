package dev.foursix.http;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Request {
	private BufferedInputStream	requestIn;
	private ArrayList<String>	messageTokens;
	private String				requestedContent;
	private String				requestedHost;

	public Request(InputStream inputStream) {		
		requestIn = new BufferedInputStream(inputStream);
	}

	public void parse() {

		StringBuffer bufMessage = null;

		try {
			bufMessage = readRequestMessage();
			
			String requestMessage = bufMessage.toString();
			messageTokens = makeToken(requestMessage);

			setRequestedContent(messageTokens.get(1));
			setRequestedHost(messageTokens.get(4));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IndexOutOfBoundsException e) {
			// TODO Auto-generated catch block
			System.err.println(e);
		}
	}

	private StringBuffer readRequestMessage() throws IOException {
		
		StringBuffer bufMessage = new StringBuffer();
		
		while (requestIn.available() > 0) {
			bufMessage.append((char)requestIn.read());
		}
//		System.out.println(bufMessage.toString());
		return bufMessage;
	}

	private ArrayList<String> makeToken(String requestMessage) {

		StringTokenizer tokenizer = new StringTokenizer(requestMessage);
		ArrayList<String> messageTokens = new ArrayList<String>();

		while (tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken();
			messageTokens.add(token);
//			System.out.println("Token: " + token);
		}
		return messageTokens;
	}

	public String getRequestedContent() {
		return requestedContent;
	}

	private void setRequestedContent(String requestedContent) {
		this.requestedContent = requestedContent;
	}

	public String getRequestedHost() {
		return requestedHost;
	}

	private void setRequestedHost(String requestedHost) {
		int colonIndex = requestedHost.indexOf(":");

		if (colonIndex < 0) {
			this.requestedHost = requestedHost;
		} else {
			this.requestedHost = requestedHost.substring(0, colonIndex);
		}
	}
}
