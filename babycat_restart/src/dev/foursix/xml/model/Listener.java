package dev.foursix.xml.model;

public class Listener {
	private String	className;
	private String	SSLEngine;

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getSSLEngine() {
		return SSLEngine;
	}

	public void setSSLEngine(String SSLEngine) {
		this.SSLEngine = SSLEngine;
	}

	public String toString() {
		return this.getClass().getName();
	}
}
