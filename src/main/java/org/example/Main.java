package org.example;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;

public class Main {
    private static final Logger myLogger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        myLogger.info("Program started.");
        final var tomcat = new Tomcat();
        try {
            final var baseDir = Files.createTempDirectory("tomcat");
            baseDir.toFile().deleteOnExit();
            tomcat.setBaseDir(baseDir.toAbsolutePath().toString());

            final var connector = new Connector();
            connector.setPort(8080);
            tomcat.setConnector(connector);

            tomcat.getHost().setAppBase(".");
            tomcat.addWebapp("", ".");

            try {
                tomcat.start();
                tomcat.getServer().await();
            } catch (LifecycleException e) {
                myLogger.error(e.getMessage());
            }
        } catch (IOException e) {
            myLogger.error(e.getMessage());
        }
        myLogger.info("Program finished.");
    }
}