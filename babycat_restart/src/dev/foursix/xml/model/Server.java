package dev.foursix.xml.model;

import java.util.ArrayList;

public class Server {
	private String					port;
	private String					shutdown;

	private ArrayList<Listener>		listeners;
	private GlobalNamingResources	globalNamingResources;
	private ArrayList<Service>		services;

	public Server() {
		listeners = new ArrayList<Listener>();
		services = new ArrayList<Service>();
	}

	public void addListener(Listener listener) {
		listeners.add(listener);
	}
	
	public Listener getListener(int index) {
		return listeners.get(index);
	}

	public void addGlobalNamingResources(
			GlobalNamingResources globalNamingResources) {
		this.globalNamingResources = globalNamingResources;
	}
	
	public GlobalNamingResources getGlobalNamingResources() {
		return globalNamingResources;
	}

	public void addService(Service service) {
		services.add(service);
	}
	
	public Service getService(int index) {
		return services.get(index);
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getShutdown() {
		return shutdown;
	}

	public void setShutdown(String shutdown) {
		this.shutdown = shutdown;
	}
	
	public String toString() {
		return this.getClass().getName();
	}
}
