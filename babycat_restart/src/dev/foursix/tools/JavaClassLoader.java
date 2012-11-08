package dev.foursix.tools;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class JavaClassLoader {
	
	private Class<?>		klass;
	private URLClassLoader	classLoader;
	
	public JavaClassLoader() {
		// TODO Auto-generated constructor stub
	}
	
	public JavaClassLoader(URL url) {		
		URL[] urls = { url };
		classLoader = new URLClassLoader(urls);
	}
	
	public JavaClassLoader(URL[] urls) {
		classLoader = new URLClassLoader(urls);
	}
	
	public void load(String className)
			throws ClassNotFoundException {
		
		klass = classLoader.loadClass(className);
	}

	public void invokeMethod(	String methodName,
								Object[] parameters,
								Class<?>[] parametersType)
	
			throws 	SecurityException, NoSuchMethodException,
					InstantiationException, IllegalAccessException,
					IllegalArgumentException, InvocationTargetException {
		
		
		Method method = klass.getMethod(methodName, parametersType);
		
		Object obj = klass.newInstance();
		
		method.invoke(obj, parameters);		
	}
	
	public void drop(String className) {
		klass = null;
		classLoader = null;
	}
}
