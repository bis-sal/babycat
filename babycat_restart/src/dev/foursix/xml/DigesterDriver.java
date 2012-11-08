package dev.foursix.xml;

import java.io.File;
import java.io.IOException;

import org.apache.commons.digester3.Digester;
import org.xml.sax.SAXException;

import dev.foursix.xml.model.*;

public class DigesterDriver {
	private Digester digester;
		
	private void createServerObject() {
		digester.addObjectCreate("Server", Server.class);
		digester.addSetProperties("Server", "port", "port");
		digester.addSetProperties("Server", "SHUTDOWN", "SHUTDOWN");
	}
	
	private void createListenerObject() {
		digester.addObjectCreate("Server/Listener", Listener.class);
		digester.addSetProperties("Server/Listener", "className", "className");
		digester.addSetProperties("Server/Listener", "SSLEngine", "SSLEngine");
		digester.addSetNext("Server/Listener", "addListener");
	}
	
	private void createGlobalNamingResourcesObject() {
		digester.addObjectCreate("Server/GlobalNamingResources", GlobalNamingResources.class);
	}
	
	private void createResourceObject() {
		digester.addObjectCreate("Server/GlobalNamingResources/Resource", Resource.class);
		digester.addSetProperties("Server/GlobalNamingResources/Resource",  "name", "name");
		digester.addSetProperties("Server/GlobalNamingResources/Resource",  "auth", "auth");
		digester.addSetProperties("Server/GlobalNamingResources/Resource",  "type", "type");
		digester.addSetProperties("Server/GlobalNamingResources/Resource",  "description", "description");
		digester.addSetProperties("Server/GlobalNamingResources/Resource",  "factory", "factory");
		digester.addSetProperties("Server/GlobalNamingResources/Resource",  "pathname", "pathname");
		digester.addSetNext("Server/GlobalNamingResources/Resource", "addResource");
		
		digester.addSetNext("Server/GlobalNamingResources", "addGlobalNamingResources");
	}
	
	private void createServiceObject() {
		digester.addObjectCreate("Server/Service", Service.class);
		digester.addSetProperties("Server/Service", "name", "name");
	}
	
	private void createConnectorObject() {
		digester.addObjectCreate("Server/Service/Connector", Connector.class);
		digester.addSetProperties("Server/Service/Connector", "port", "port");
		digester.addSetProperties("Server/Service/Connector", "protocol", "protocol");
		digester.addSetProperties("Server/Service/Connector", "connectionTimeout", "connectionTimeout");
		digester.addSetProperties("Server/Service/Connector", "redirectPort", "redirectPort");
		digester.addSetNext("Server/Service/Connector", "addConnector");
	}
	
	private void createEngineObject() {
		digester.addObjectCreate("Server/Service/Engine", Engine.class);
		digester.addSetProperties("Server/Service/Engine",  "name", "name");
		digester.addSetProperties("Server/Service/Engine",  "defaultHost", "defaultHost");
	}
	
	private void createRealmObject() {
		digester.addObjectCreate("Server/Service/Engine/Realm", Realm.class);
		digester.addSetProperties("Server/Engine/Realm", "className", "className");
		digester.addSetProperties("Server/Engine/Realm", "resourceName", "resourceName");
		digester.addSetNext("Server/Service/Engine/Realm", "addRealm");
	}
	
	private void createHostObject() {
		digester.addObjectCreate("Server/Service/Engine/Host", Host.class);
		digester.addSetProperties("Server/Service/Engine/Host",  "name", "name");
		digester.addSetProperties("Server/Service/Engine/Host",  "appBase", "appBase");
		digester.addSetProperties("Server/Service/Engine/Host",  "unpackWARs", "unpackWARs");
		digester.addSetProperties("Server/Service/Engine/Host",  "autoDeploy", "autoDeploy");
		digester.addSetProperties("Server/Service/Engine/Host",  "xmlValidation", "xmlValidation");
		digester.addSetProperties("Server/Service/Engine/Host",  "xmlNamespaceAware", "xmlNamespaceAware");
		digester.addSetNext("Server/Service/Engine/Host", "addHost");
		
		digester.addSetNext("Server/Service/Engine", "addEngine");
		
		digester.addSetNext("Server/Service", "addService");
	}

	public void parseServer() {		
		digester = new Digester();
		
		digester.setValidating(false);
		
		allocation();

		try {
			Server server = digester.parse(new File("server.xml"));
			System.out.println(server);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void allocation() {
		createServerObject();
		createListenerObject();
		createGlobalNamingResourcesObject();
		createResourceObject();
		createServiceObject();
		createConnectorObject();
		createEngineObject();
		createRealmObject();
		createHostObject();
	}
}
