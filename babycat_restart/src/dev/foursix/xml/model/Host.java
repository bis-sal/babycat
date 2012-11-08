package dev.foursix.xml.model;

public class Host {
	private String	name;
	private String	appBase;
	private String	unpackWARs;
	private String	autoDeploy;
	private String	xmlValidation;
	private String	xmlNamespaceAware;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAppBase() {
		return appBase;
	}

	public void setAppBase(String appBase) {
		this.appBase = appBase;
	}

	public String isUnpackWARs() {
		return unpackWARs;
	}

	public void setUnpackWARs(String unpackWARs) {
		this.unpackWARs = unpackWARs;
	}

	public String isAutoDeploy() {
		return autoDeploy;
	}

	public void setAutoDeploy(String autoDeploy) {
		this.autoDeploy = autoDeploy;
	}

	public String isXmlValidation() {
		return xmlValidation;
	}

	public void setXmlValidation(String xmlValidation) {
		this.xmlValidation = xmlValidation;
	}

	public String isXmlNamespaceAware() {
		return xmlNamespaceAware;
	}

	public void setXmlNamespaceAware(String xmlNamespaceAware) {
		this.xmlNamespaceAware = xmlNamespaceAware;
	}

	public String toString() {
		return this.getClass().getName();
	}
}
