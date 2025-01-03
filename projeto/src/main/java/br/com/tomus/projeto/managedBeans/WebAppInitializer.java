package br.com.tomus.projeto.managedBeans;



import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.request.*;
import org.springframework.web.servlet.*;

import br.com.tomus.projeto.config.AppConfig;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import javax.servlet.ServletRegistration.Dynamic;
public class WebAppInitializer implements WebApplicationInitializer{

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
		ctx.register(AppConfig.class);
		ctx.setServletContext(servletContext);
		servletContext.addListener(new ContextLoaderListener(ctx));
		servletContext.addListener(new RequestContextListener());
		
		Dynamic dynamic = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));
		dynamic.addMapping("/");
		dynamic.setLoadOnStartup(1);
		
		
	}

	
}
