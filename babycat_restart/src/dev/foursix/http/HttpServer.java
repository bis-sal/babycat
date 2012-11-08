package dev.foursix.http;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {

	private ServerSocket	server;
	private int				port;
	private HttpServerInfo	serverInfo;

	public HttpServer() {

	}

	public void config(HttpServerInfo serverInfo) {
		/*
		 * test
		 */
		System.out.println("test");
		
		this.serverInfo = serverInfo;
		
		this.serverInfo.infoSetting();
		int port = this.serverInfo.getPort();
		this.setPort(port);
	}

	public boolean start() {
		try {
			if (port <= 0) {
				throw new IllegalArgumentException();
			}
			server = new ServerSocket(port);
			
			System.out.println(
			"46\'s Web Server start at port [" + port + "]\n");

			listening();

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} catch (IllegalArgumentException e) {
			System.err.println("wrong port");
			return false;
		} finally {
			close();
		}
		return true;
	}

	private void listening() {
		try {
			while (true) {				
				Socket socket = server.accept();
				
				System.out.println("connected from ["
									+ socket.getInetAddress()
									+ "]");
				
				Transfer transfer = new Transfer(socket, serverInfo);
				transfer.start();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	private void close() {
		try {
			server.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
