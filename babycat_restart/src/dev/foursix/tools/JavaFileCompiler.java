package dev.foursix.tools;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

public class JavaFileCompiler {
	
	public JavaFileCompiler() {
		// TODO Auto-generated constructor stub
	}
	
	public void compile(File javafile) {
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		
		DiagnosticCollector<JavaFileObject> diagnosticListener
		= new DiagnosticCollector<JavaFileObject>();
		
		StandardJavaFileManager fileManager
		= compiler.getStandardFileManager(diagnosticListener, null, null);
		
		Iterable<? extends JavaFileObject> compilationUnits
		= fileManager.getJavaFileObjectsFromFiles(Arrays.asList(javafile));
		
		File file = new File("");
		String currentPath = file.getAbsolutePath();		
		String projectBinPath = currentPath + "\\bin";
		
		ArrayList<String> options = new ArrayList<String>();
		options.addAll(
				Arrays.asList(	"-classpath",
//								System.getProperty("java.class.path"),
//								"D:\\work\\babycat\\bin"
								projectBinPath
								
								));
		
		JavaCompiler.CompilationTask task
		= compiler.getTask( null, 
							fileManager,
							null/*diagnosticListener*/,
							options,
							null,
							compilationUnits
							);
		Boolean sucess = task.call();
		
		for (Diagnostic<?> diagnostic : diagnosticListener.getDiagnostics()) {
			System.console().printf("Code: %s%n" +
									"Kind: %s%n" +
									"Position: %s%n" +
									"Start Position: %s%n" +
									"End Position: %s%n" +
									"Source: %s%n" +
									"Message: %s%n",
									diagnostic.getCode(),
									diagnostic.getKind(),
									diagnostic.getPosition(),
									diagnostic.getStartPosition(),
									diagnostic.getEndPosition(),
									diagnostic.getSource(),
									diagnostic.getMessage(null)									
									);
		}
		
		try {
			fileManager.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Result: " + sucess);
	}

}
