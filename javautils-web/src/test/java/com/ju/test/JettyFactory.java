package com.ju.test;


import com.google.common.collect.Lists;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.Configuration;
import org.eclipse.jetty.webapp.WebAppClassLoader;
import org.eclipse.jetty.webapp.WebAppContext;

import java.util.List;

/**
 * 创建Jetty Server的工厂类.
 * 
 * @author calvin
 */
public class JettyFactory {

	private static final String DEFAULT_WEBAPP_PATH = "src/main/webapp";
	private static final String WINDOWS_WEBDEFAULT_PATH = "jetty/webdefault-windows.xml";

	/**
	 * 创建用于开发运行调试的Jetty Server, 以src/main/webapp为Web应用目录.
	 */
	public static Server createServerInSource(int port, String contextPath) {
		Server server = new Server(port);
		Configuration.ClassList classlist = Configuration.ClassList.setServerDefault(server);
		classlist.addAfter("org.eclipse.jetty.webapp.FragmentConfiguration", "org.eclipse.jetty.plus.webapp.EnvConfiguration", "org.eclipse.jetty.plus.webapp.PlusConfiguration");
		// 设置在JVM退出时关闭Jetty的钩子。
		server.setStopAtShutdown(true);
		WebAppContext webContext = new WebAppContext(DEFAULT_WEBAPP_PATH, contextPath);
		// 修改webdefault.xml，解决Windows下Jetty Lock住静态文件的问题.
		webContext.setDefaultsDescriptor(WINDOWS_WEBDEFAULT_PATH);
		server.setHandler(webContext);

		return server;
	}

	/**
	 * 设置除jstl-*.jar外其他含tld文件的jar包的名称.
	 * jar名称不需要版本号，如sitemesh, shiro-web
	 */
	public static void setTldJarNames(Server server, String... jarNames) {
		WebAppContext context = (WebAppContext) server.getHandler();
		List<String> jarNameExprssions = Lists.newArrayList(".*/jstl-[^/]*\\.jar$", ".*/.*taglibs[^/]*\\.jar$");
		for (String jarName : jarNames) {
			jarNameExprssions.add(".*/" + jarName + "-[^/]*\\.jar$");
		}

		context.setAttribute("org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern",
				StringUtils.join(jarNameExprssions, '|'));

	}

	/**
	 * 快速重新启动application，重载target/classes与target/test-classes.
	 */
	public static void reloadContext(Server server) throws Exception {
		WebAppContext context = (WebAppContext) server.getHandler();

		System.out.println("[INFO] Application reloading");
		context.stop();

		WebAppClassLoader classLoader = new WebAppClassLoader(context);
		classLoader.addClassPath("target/classes");
		classLoader.addClassPath("target/test-classes");
		context.setClassLoader(classLoader);

		context.start();

		System.out.println("[INFO] Application reloaded");
	}
}
