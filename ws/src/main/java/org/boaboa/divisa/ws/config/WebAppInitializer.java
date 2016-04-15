package org.boaboa.divisa.ws.config;

import org.apache.cxf.transport.servlet.CXFServlet;
import org.boaboa.divisa.config.ApplicationConfig;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * Created by pperez on 15-04-16.
 */
public class WebAppInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
        appContext.register(ApplicationConfig.class);
        servletContext.addListener(new ContextLoaderListener(appContext));

        CXFServlet cxfServlet = new CXFServlet();
        ServletRegistration.Dynamic appServlet = servletContext.addServlet("CXFServlet", cxfServlet);
        appServlet.setLoadOnStartup(1);
        appServlet.addMapping("/api/*");
    }
}
