package dev.foursix.http;

import java.io.File;
import java.util.HashMap;

import dev.foursix.xml.DigesterRulesDriver;
import dev.foursix.xml.model.Connector;
import dev.foursix.xml.model.Engine;
import dev.foursix.xml.model.Host;
import dev.foursix.xml.model.Server;
import dev.foursix.xml.model.Service;

public class HttpServerInfo {

	private int						port;
	private HashMap<String, String>	hostList;

	public HttpServerInfo() {
		
	}

	public void infoSetting() {
		DigesterRulesDriver rulesDriver = new DigesterRulesDriver();

		Server server = rulesDriver.parse(new File("resource/server.xml"));
		Service service = server.getService(0);

		Connector httpConnector = findHttpConnector(service);

		String strPort = httpConnector.getPort();
		int port = Integer.parseInt(strPort);
		this.setPort(port);

		hostList = findHosts(service.getEngine(0));
	}

	private Connector findHttpConnector(Service service) {
		String http = "HTTP/1.1";
		int nConnectors = service.getConnectorSize();

		for (int i = 0; i < nConnectors; i++) {
			Connector connector = service.getConnector(i);
			String protocol = connector.getProtocol();

			if (http.equals(protocol)) {
				return connector;
			}
		}
		return null;
	}

	private HashMap<String, String> findHosts(Engine engine) {
		int nHosts = engine.getHostSize();
		HashMap<String, String> hostList = new HashMap<String, String>();

		for (int i = 0; i < nHosts; i++) {
			Host host = engine.getHost(i);
			hostList.put(host.getName(), host.getAppBase());
		}
		return hostList;
	}

	public int getPort() {
		return port;
	}

	private void setPort(int port) {
		this.port = port;
	}

	public String getAppBase(String hostname) {
		if (null == hostList) {
			System.out.println("get hostinfo first");
			return null;
		}
		String appbase = hostList.get(hostname);
		return appbase;
	}
}
