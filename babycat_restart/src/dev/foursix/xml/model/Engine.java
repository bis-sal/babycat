package dev.foursix.xml.model;

import java.util.ArrayList;

public class Engine {
	private String				name;
	private String				defaultHost;

	private ArrayList<Realm>	realms;
	private ArrayList<Host>		hosts;
	
	public Engine() {
		realms = new ArrayList<Realm>();
		hosts = new ArrayList<Host>();
	}

	public void addRealm(Realm realm) {
		realms.add(realm);
	}
	
	public Realm getRealm(int index) {
		return realms.get(index);
	}
	
	public int getRealmSize() {
		return realms.size();
	}

	public void addHost(Host host) {
		hosts.add(host);
	}
	
	public Host getHost(int index) {
		return hosts.get(index);
	}
	
	public int getHostSize() {
		return hosts.size();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDefaultHost() {
		return defaultHost;
	}

	public void setDefaultHost(String defaultHost) {
		this.defaultHost = defaultHost;
	}

	public String toString() {
		return this.getClass().getName();
	}
}
