import dev.foursix.http.HttpServer;
import dev.foursix.http.HttpServerInfo;


public class HttpServerTestMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HttpServerInfo serverInfo = new HttpServerInfo();
				
		HttpServer httpServer = new HttpServer();
		httpServer.config(serverInfo);
		httpServer.start();

	}
}
