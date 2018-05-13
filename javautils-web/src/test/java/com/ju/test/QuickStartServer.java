package com.ju.test;


import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.eclipse.jetty.plus.jndi.Resource;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.springside.modules.test.spring.Profiles;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

/**
 * 使用Jetty运行调试Web应用, 在Console输入回车快速重新加载应用.
 * 
 * @author yanzhanghai
 */
public class QuickStartServer {
    
	

	public static final int PORT = 8080;
	public static final String CONTEXT = "/ju";
	public static final String[] TLD_JAR_NAMES = new String[] { "sitemesh",
			"spring-webmvc", "springside-core" };

	public static void main(String[] args) throws Exception {
		//
		Profiles.setProfileAsSystemProperty(Profiles.DEVELOPMENT);

		// Jetty
		Server server = JettyFactory.createServerInSource(PORT, CONTEXT);
		JettyFactory.setTldJarNames(server, TLD_JAR_NAMES);
		initJNDIDataSource(server) ;
		try {
			server.start();

			System.out.println("[INFO] Server running at http://localhost:"
					+ PORT + CONTEXT);
			System.out
					.println("[HINT] Hit Enter to reload the application quickly");

			//
			while (true) {
				char c = (char) System.in.read();
				if (c == '\n') {
					JettyFactory.reloadContext(server);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}

	private static Resource initJNDIDataSource(Server server) {
		Properties properties = new Properties();
		try {
			properties.load(Resource.class.getClassLoader()
					.getResourceAsStream("config/db.properties"));
		} catch (IOException e1) {
			System.out.print("config/db.properties is not exist");
			e1.printStackTrace();
		}
		;
		DataSource ds;
		try {
			WebAppContext context = (WebAppContext) server.getHandler();

			ds = BasicDataSourceFactory.createDataSource(properties);
			Resource resource = new Resource(context, "ju", ds);
			return resource;
		} catch (Exception e) {
			System.out.println("init the datasource error");
			e.printStackTrace();
		}
		return null;
	}
}
