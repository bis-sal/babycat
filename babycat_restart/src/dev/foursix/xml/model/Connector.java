package dev.foursix.xml.model;

public class Connector {
	private String	port;
	private String	protocol;
	private String	connectionTimeout;
	private String	redirectPort;

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getConnectionTimeout() {
		return connectionTimeout;
	}

	public void setConnectionTimeout(String connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
	}

	public String getRedirectPort() {
		return redirectPort;
	}

	public void setRedirectPort(String redirectPort) {
		this.redirectPort = redirectPort;
	}

	public String toString() {
		return this.getClass().getName();
	}
}
