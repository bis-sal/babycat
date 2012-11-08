package dev.foursix.xml.model;

public class Realm {
	private String	className;
	private String	resourceName;

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String toString() {
		return this.getClass().getName();
	}
}
