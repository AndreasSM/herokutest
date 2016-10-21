package no.herokutest;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

import java.io.File;
import java.time.LocalDateTime;

public class WebServer {
    private static Server server;

    public static void main(String[] argv) throws Exception {
        WebServer webServer = new WebServer();
        webServer.start(webServer.getPort());
    }

    private int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 8080;
    }

    protected void start(int port) throws Exception {
        server = new Server(port);
        server.setHandler(getHandler());
        server.start();

        System.out.println(server.getURI() + " at " + LocalDateTime.now());
        System.out.println("Path=" + new File(".").getAbsolutePath());
    }

    protected WebAppContext getHandler() {
        WebAppContext webAppContext = new WebAppContext();
        webAppContext.getInitParams().put("org.eclipse.jetty.servlet.Default.useFileMappedBuffer", "false");
        webAppContext.setContextPath("/");
        webAppContext.setResourceBase("src/main/resources/webapp");
        return webAppContext;
    }

    @SuppressWarnings("UnusedDeclaration")
    protected void stop() throws Exception {
        server.stop();
    }
}
