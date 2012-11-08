package dev.foursix.http.test;

//import static org.junit.Assert.*;
//
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//
//import dev.foursix.http.server.HttpServer;
//
//public class ServerTest {
//	
//	private static HttpServer	server;
//	
//	@BeforeClass
//	public static void setUpBeforeClass() throws Exception {
//		server = new HttpServer();
//	}
//
//	@AfterClass
//	public static void tearDownAfterClass() throws Exception {
//	}
//
//	@Before
//	public void setUp() throws Exception {
//	}
//
//	@After
//	public void tearDown() throws Exception {
//	}
//	
//	@Test
//	public void testServer() {		
//		assertNotNull(server);		
//	}
//
//	@Test
//	public void testSetPort() {
//		int port = 80;
//		server.setPort(port);
//		assertEquals(port, server.getPort());
//	}
//	
//	@Test
//	public void testStart() {
//		server.setPort(80);
//		
//		assertNotNull(server.start());	
//	}
//
//}
