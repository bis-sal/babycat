package dev.foursix.xml.model;
import java.util.ArrayList;

public class GlobalNamingResources {
	private ArrayList<Resource>		resources;
	
	public GlobalNamingResources() {
		resources = new ArrayList<Resource>();
	}
	
	public void addResource(Resource resource) {
		resources.add(resource);
	}
	
	public Resource getResource(int index) {
		return resources.get(index);
	}
	
	public int getResouceSize() {
		return resources.size();
	}
	
	public String toString() {
		return this.getClass().getName();
	}
}
