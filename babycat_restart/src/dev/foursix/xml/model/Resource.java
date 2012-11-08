package dev.foursix.xml.model;

public class Resource {
	private String	name;
	private String	auth;
	private String	type;
	private String	description;
	private String	factory;
	private String	pathname;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFactory() {
		return factory;
	}

	public void setFactory(String factory) {
		this.factory = factory;
	}

	public String getPathname() {
		return pathname;
	}

	public void setPathname(String pathname) {
		this.pathname = pathname;
	}
	
	public String toString() {
		String newline = System.getProperty("line.separator");
		StringBuffer buf = new StringBuffer();
		
		buf.append("Resource: ").append(newline);
		buf.append("name=\"" + name + "\"").append(newline);
		buf.append("auth=\"" + auth + "\"").append(newline);
		buf.append("type=\"" + type + "\"").append(newline);
		buf.append("description=\"" + description + "\"").append(newline);
		buf.append("factory=\"" + factory + "\"").append(newline);
		buf.append("pathname=\"" + pathname + "\"").append(newline);

		return buf.toString();
	}
}
