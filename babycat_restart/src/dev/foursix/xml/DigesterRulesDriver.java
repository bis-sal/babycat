package dev.foursix.xml;

import java.io.File;
import java.io.IOException;

import org.apache.commons.digester3.Digester;
import org.apache.commons.digester3.binder.DigesterLoader;
import org.xml.sax.SAXException;

import dev.foursix.xml.model.*;

public class DigesterRulesDriver {
	private Digester	digester;

	public Server parse(File source) {

		try {
			MyRulesModule rulesmodule = new MyRulesModule();
			DigesterLoader loader = DigesterLoader.newLoader(rulesmodule);
			digester = loader.newDigester();
			Server server = digester.parse(source);
			return server;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
