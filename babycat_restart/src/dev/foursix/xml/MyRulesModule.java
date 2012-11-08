package dev.foursix.xml;

import java.io.File;

import org.apache.commons.digester3.xmlrules.FromXmlRulesModule;

public class MyRulesModule extends FromXmlRulesModule {

	@Override
	protected void loadRules() {
		// TODO Auto-generated method stub
		loadXMLRules(new File("resource/rules.xml"));
	}
}
