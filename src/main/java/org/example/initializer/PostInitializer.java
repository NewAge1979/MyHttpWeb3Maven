package org.example.initializer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;

public class PostInitializer implements WebApplicationInitializer {
    private static final Logger myLogger = LogManager.getLogger(PostInitializer.class);
    @Override
    public void onStartup(ServletContext servletContext) {
        myLogger.info("Started execute PostInitializer.onStartup...");
        final var context = new AnnotationConfigWebApplicationContext();
        context.scan("org.example");
        context.refresh();

        final var servlet = new DispatcherServlet(context);
        final var registration = servletContext.addServlet("app", servlet);
        registration.setLoadOnStartup(1);
        registration.addMapping("/");
    }
}
