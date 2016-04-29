package servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import service.Connector;

public class Initializator implements ServletContextListener {
	@Override
	public void contextInitialized(ServletContextEvent event) {
		ServletContext context = event.getServletContext();
		String jdbcDriver = context.getInitParameter("jdbc-driver");
		String jdbcUrl = context.getInitParameter("jdbc-url");
		String jdbcUser = context.getInitParameter("jdbc-user");
		String jdbcPassword = context.getInitParameter("jdbc-password");
		Connector.init(jdbcDriver, jdbcUrl, jdbcUser, jdbcPassword);
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {}
}
