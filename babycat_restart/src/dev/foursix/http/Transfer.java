package dev.foursix.http;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dev.foursix.jsp.JspParser;
import dev.foursix.tools.JavaClassLoader;
import dev.foursix.tools.JavaFileCompiler;

public class Transfer extends Thread {

	private Socket					socket;
	private HttpServerInfo			serverInfo;

	private final String			RESOURCE_ROOT	= "resource/";
	private String					appBasePath;

	public Transfer(Socket socket, HttpServerInfo serverInfo) {
		this.socket = socket;
		this.serverInfo = serverInfo;
	}

	public void run() {
		try {
			InputStream inputStream = socket.getInputStream();
			
			RequestParser requestParser = new RequestParser(inputStream);
			HttpRequest httprequest = requestParser.parse();
			
			String hostname = httprequest.getRequestedHost();
			appBasePath = earnAppBasePath(hostname);
			String contentName = httprequest.getContent();
			
			if ( isJsp(contentName) ) {
				System.out.println("[JSP Parser]");
				
				String javafilepath
				= convertJsp(contentName, appBasePath, hostname);				
				
				HttpResponse httpresponse = new HttpResponse(socket.getOutputStream());				
				httpresponse.setContentType("text/html");
				
				System.out.println("[Java Compiler]");				
				compileJava(javafilepath);
				
				System.out.println("[Class Loader]");
				loadClass(javafilepath, httprequest, httpresponse);
				
				return;
			}
			
			Response response = generateResponse(socket);
			response.setContent(contentName, appBasePath);
			response.send();

		} catch (FileNotFoundException e) {
			System.err.println(e);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
	}

	private void loadClass( String javafilepath,
							HttpRequest httprequest,
							HttpResponse httpresponse)
	
					throws MalformedURLException, ClassNotFoundException,
							SecurityException, IllegalArgumentException,
							NoSuchMethodException, InstantiationException,
							IllegalAccessException, InvocationTargetException {
		
		int lastslash = javafilepath.lastIndexOf('/');
		String filename = javafilepath.substring(lastslash + 1);
		javafilepath = javafilepath.substring(0, lastslash);
		
		File targetpath = new File(javafilepath);
		
		int dot = filename.indexOf('.');
		filename = filename.substring(0, dot);
		
		URL targetUrl = targetpath.toURI().toURL(); 
		JavaClassLoader loader = new JavaClassLoader(targetUrl);
		loader.load(filename);
				
		Object[] parameters = { httprequest, httpresponse };
		Class<?>[] parametersType
		= { HttpServletRequest.class, HttpServletResponse.class };		
		
		loader.invokeMethod("_jspService", parameters, parametersType);
	}

	private void compileJava(String javafilepath) {
		
		File javafile = new File(javafilepath);
		JavaFileCompiler compiler = new JavaFileCompiler();
		
		compiler.compile(javafile);
	}

	private String convertJsp(
			String contentName, String appBasePath2, String hostname) {
		
		JspParser jspParser = new JspParser();
		String javafilepath
		= jspParser.parse(contentName, appBasePath, hostname);
		
		return javafilepath;
	}

	private boolean isJsp(String contentName) {
		int indexOfExtension = contentName.lastIndexOf(".") + 1;
		String extension = contentName.substring(indexOfExtension);
		
		if ("jsp".equals(extension)) {
			return true;
		}		
		return false;
	}

	private Request generateRequest(Socket socket) throws IOException {
		InputStream in = socket.getInputStream();
		return new Request(in);
	}

	private Response generateResponse(Socket socket) throws IOException {
		OutputStream out = socket.getOutputStream();
		return new Response(out);
	}

	private String earnAppBasePath(String hostname) {
		String appBase = serverInfo.getAppBase(hostname);

		String hostPath = RESOURCE_ROOT + appBase;

		return hostPath;
	}
	
	private void close() {
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
