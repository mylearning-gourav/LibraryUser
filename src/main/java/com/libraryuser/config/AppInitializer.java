package com.libraryuser.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/*
 * Application Initializer Configuration class
 */
public class AppInitializer implements WebApplicationInitializer {

	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
		appContext.register(AppConfiguration.class);
		appContext.setServletContext(servletContext);
		
		DispatcherServlet dispatchServlet = new DispatcherServlet(appContext);
		dispatchServlet.setThrowExceptionIfNoHandlerFound(true);
		ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher", dispatchServlet);
		servlet.setLoadOnStartup(1);
		servlet.addMapping("/");
	}
	
}

/*public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] {AppConfiguration.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}

}*/
