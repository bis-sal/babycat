package dev.foursix.xml.model;

import java.util.ArrayList;

public class Service {
	private String					name;

	private ArrayList<Connector>	connectors;
	private ArrayList<Engine>		engines;
	
	public Service() {
		connectors = new ArrayList<Connector>();
		engines = new ArrayList<Engine>();
	}

	public void addConnector(Connector connector) {
		connectors.add(connector);
	}
	
	public Connector getConnector(int index) {
		return connectors.get(index);
	}
	
	public int getConnectorSize() {
		return connectors.size();
	}

	public void addEngine(Engine engine) {
		engines.add(engine);
	}
	
	public Engine getEngine(int index) {
		return engines.get(index);
	}
	
	public int getEngineSize() {
		return engines.size();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		return this.getClass().getName();
	}
}
