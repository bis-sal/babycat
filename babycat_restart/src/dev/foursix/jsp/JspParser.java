package dev.foursix.jsp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class JspParser {
	private ArrayList<String>	directive;
	private ArrayList<String>	declaration;
	private ArrayList<String>	expression;
	private ArrayList<String>	scriptlet;
	private ArrayList<String>	html;
	private ArrayList<Integer>	expresionIndex;
	private ArrayList<Integer>	scriptletIndex;
	private String	jspName;
	private String	jspPath;

	public JspParser() {
		directive = new ArrayList<String>();
		declaration = new ArrayList<String>();
		expression = new ArrayList<String>();
		scriptlet = new ArrayList<String>();
		html = new ArrayList<String>();
		expresionIndex = new ArrayList<Integer>();
		scriptletIndex = new ArrayList<Integer>();
	}

	public String parse(String jspfilepath, String appBasePath, String hostname) {

		try {
			String jspfullpath = appBasePath + jspfilepath;
			
			readJsp(jspfullpath);
			splitNamePath(jspfilepath);	
			
			String javaFileDirectory = "resource/work/" + hostname + "/jsp" + jspPath;
			File forMkdir = new File(javaFileDirectory);
			forMkdir.mkdirs();
			
			String javafilepath = generateJava(jspName, javaFileDirectory);
			
			return javafilepath;
			
//			System.out.println("----");
//			
//			print(directive);
//			print(declaration);
//			print(expression);
//			print(scriptlet);
//			print(html);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private void splitNamePath(String jspfilepath) {
		int lastslash = jspfilepath.lastIndexOf('/');
		
		jspPath = jspfilepath.substring(0, lastslash);
		jspName = jspfilepath.substring(lastslash);
	}

	private void readJsp(String jspfile) throws FileNotFoundException,
			IOException {

		FileReader freader = new FileReader(jspfile);
		BufferedReader jspIn = new BufferedReader(freader);

		String line = null;

		while ((line = jspIn.readLine()) != null) {
			if (line.indexOf("<%@") != -1) {
				addDirective(line);
			} else if (line.indexOf("<%=") != -1) {
				addExpression(line);
			} else if (line.indexOf("<%!") != -1) {
				addDeclaration(line, jspIn);
			} else if (line.indexOf("<%") != -1) {
				addScriptlet(line, jspIn);
			} else {
				html.add(line);
			}
		}
	}

	private String generateJava(String jspfile, String javaFileDirectory)
			throws IOException {

		String[] fileNameArray = jspfile.split("[.]");
		String javafilename = fileNameArray[0];
		System.out.println(javafilename);
		javafilename += "_";
		javafilename += fileNameArray[1];
		javafilename += ".java";
		System.out.println(javafilename);

		writeJava(javafilename, javaFileDirectory);
		
		return javaFileDirectory + javafilename;
	}

	private void writeJava(String javafilename, String javaFileDirectory)
			throws IOException {
		
		FileWriter fwriter = new FileWriter(javaFileDirectory + javafilename);
		
		String className = convertClassName(javafilename);

		writeDirective(fwriter);		
		writeClassName(className, fwriter);

		writeDeclaration(fwriter);
		writeJspService(fwriter);

		writeHtmlScrptExpr(fwriter);
		writeClassEnd(fwriter);

		fwriter.flush();
		fwriter.close();
	}

	private String convertClassName(String javafilename) {
		int dot = javafilename.indexOf(".");
		String className = javafilename.substring(0, dot);
		className = className.substring(1);
		
		return className;
	}

	private void writeDirective(FileWriter fwriter) throws IOException {
		String buf = bufJavaFile(directive);
		fwriter.write(buf);
		fwriter.write("\r\n\r\n");
	}

	private void writeClassName(String javafilename, FileWriter fwriter)
			throws IOException {
		fwriter.write("public final class "
						+ javafilename
						+ " implements HttpJspPage {");
		
		fwriter.write("\r\n\r\n");
	}

	private void writeDeclaration(FileWriter fwriter) throws IOException {
		String buf;
		buf = bufJavaFile(declaration);
		fwriter.write(buf);
		fwriter.write("\r\n\r\n");
	}

	private void writeJspService(FileWriter fwriter) throws IOException {
		fwriter.write("public void _jspService(");
		fwriter.write("HttpServletRequest request, ");
		fwriter.write("HttpServletResponse response) ");
		fwriter.write("throws IOException {");
		fwriter.write("\r\n");
		fwriter.write("PrintWriter out = null;");
		fwriter.write("\r\n\r\n");
		fwriter.write("try {");
		fwriter.write("\r\n");
		fwriter.write("response.setContentType(\"text/html\");");
		fwriter.write("\r\n");
		fwriter.write("out = response.getWriter();");
		fwriter.write("\r\n\r\n");

		fwriter.write("out.write(\"\\r\\n\");");
		fwriter.write("\r\n");
		fwriter.write("out.write(\"\\r\\n\");");
		fwriter.write("\r\n");
	}

	private void writeHtmlScrptExpr(FileWriter fwriter) throws IOException {

		int exprIndexPointer = 0;
		int indexExpr = 0;
		int sizeExprIndex = expresionIndex.size();

		int scrptIndexPointer = 0;
		int indexScrpt = 0;
		int sizeScrptIndex = scriptletIndex.size();

		for (int i = 0; i < html.size(); i++) {
			String str = html.get(i);
			str = str.replace("\"", "\\\"");
			fwriter.write("out.write(\"" + str + "\\r\\n\");");
			fwriter.write("\r\n");
			
			if (sizeScrptIndex < 1) {
				indexScrpt = -1;
			} else {
				indexScrpt = scriptletIndex.get(scrptIndexPointer);
			}
			
			if (sizeExprIndex < 1) {
				indexExpr = -1;
			} else {
				indexExpr = expresionIndex.get(exprIndexPointer);
			}

			if (i == indexScrpt) {
				fwriter.write("\r\n");
				fwriter.write(scriptlet.get(scrptIndexPointer));

				if (scrptIndexPointer < sizeScrptIndex - 1) {
					scrptIndexPointer++;
				}
			}
			if (i == indexExpr) {			
				fwriter.write("out.print( " + expression.get(exprIndexPointer)
						+ " );");
				fwriter.write("\r\n");

				if (exprIndexPointer < sizeExprIndex - 1) {
					exprIndexPointer++;
				}
			}
		}
	}

	private void writeClassEnd(FileWriter fwriter) throws IOException {
		fwriter.write("out.write(\"\\r\\n\");");
		fwriter.write("\r\n");
		fwriter.write("out.write(\'\\r\');");
		fwriter.write("\r\n");
		fwriter.write("out.write(\'\\n\');");
		fwriter.write("\r\n");
		fwriter.write("} catch (Throwable th) {");
		fwriter.write("\r\n");
		fwriter.write("throw new IOException(th.getMessage());");
		fwriter.write("\r\n");
		fwriter.write("} finally {");
		fwriter.write("\r\n");
		fwriter.write("out.close();");
		fwriter.write("\r\n");
		fwriter.write("}");
		fwriter.write("\r\n");
		fwriter.write("}");
		fwriter.write("\r\n");
		fwriter.write("}");
		fwriter.write("\r\n\r\n");
	}

	private void print(ArrayList<String> arrayList) {

		int length = arrayList.size();

		for (int i = 0; i < length; i++) {
			String str = arrayList.get(i);
//			System.out.println("[" + i + "]" + str);
		}
	}

	private String bufJavaFile(ArrayList<String> arrayList) {

		int length = arrayList.size();
		StringBuffer buf = new StringBuffer();

		for (int i = 0; i < length; i++) {
			String str = arrayList.get(i);
//			System.out.println("[" + i + "]" + str);
			buf.append(str);
		}
		return buf.toString();
	}

	private String addDirective(String line) {

		String[] str = new String[] { "<%@", "page", "=", "\"", "%>" };

		int length = str.length;

		for (int i = 0; i < length; i++) {
			line = line.replace(str[i], "");
		}
		line = line.trim();
		line += ";";

		directive.add(line);
		directive.add("\r\n");
		directive.add("import javax.servlet.http.*;");
		directive.add("\r\n");
		directive.add("import javax.servlet.jsp.*;");
		directive.add("\r\n");
		directive.add("import java.io.*;");
		directive.add("\r\n");
//		System.out.println(line);
		return line;
	}

	private String addScriptlet(String line, BufferedReader jspIn)
			throws IOException {

		String code = "";
		while (line.indexOf("%>") == -1) {
			line = jspIn.readLine();
			code += line;
			code += "\r\n";
		}
		code = code.replace("<%", "");
		code = code.replace("%>", "");

		scriptletIndex.add(this.html.size() - 1);

		scriptlet.add(code);
//		System.out.println(code);
		return code;
	}

	private String addExpression(String line) {

		int beginExpression = line.indexOf("<%=");
		int endExpression = line.indexOf("%>") - 1;

		String beforeExpression = line.substring(0, beginExpression);
		
		beginExpression += 4;
		String expression = line.substring(beginExpression, endExpression);
		
		String afterExpression = line.substring(endExpression + 3);
		

		this.html.add(beforeExpression);
		
		expresionIndex.add(this.html.indexOf(beforeExpression));
		this.expression.add(expression);
		
		this.html.add(afterExpression);
//		System.out.println(expression);
		return expression;
	}

	private String addDeclaration(String line, BufferedReader jspIn)
			throws IOException {

		String code = "";
		while (line.indexOf("%>") == -1) {
			line = jspIn.readLine();
			code += line;
			code += "\r\n";
		}
		code = code.replace("<%", "");
		code = code.replace("%>", "");

		declaration.add(code);
//		System.out.println(code);
		return code;
	}
}
