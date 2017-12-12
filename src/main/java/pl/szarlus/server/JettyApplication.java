package pl.szarlus.server;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import pl.szarlus.h2.H2Starter;

/**
 * Created by karol on 25.11.2017.
 */
public class JettyApplication {

    private static final Logger logger = LoggerFactory.getLogger(JettyApplication.class);
    private static final int DEFAULT_PORT = 9090;
    private static final String CONTEXT_PATH = "/";
    private static final String CONFIG_LOCATION = "pl.szarlus.config";
    private static final String MAPPING_URL = "/*";

    public static void main(String[] args) {
        int port = getPortFromArgs(args);
        try {
            startApplication(port);
            H2Starter h2Starter = new H2Starter(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int getPortFromArgs(String[] args) {
        if (args.length > 0) {
            int portFromArgs = Integer.valueOf(args[0]);
            logger.debug("Configuring port passed as arguments: {}", portFromArgs);
            return portFromArgs;
        }
        logger.debug("No server port configured, falling back to default {}", DEFAULT_PORT);
        return DEFAULT_PORT;
    }

    private static void startApplication(int port) throws Exception{
        logger.debug("Starting server at port {}", port);
        Server server = new Server(port);

        ServletContextHandler contextHandler = new ServletContextHandler();

        AnnotationConfigWebApplicationContext webApplicationContext = new AnnotationConfigWebApplicationContext();
        webApplicationContext.setConfigLocation(CONFIG_LOCATION);

        contextHandler.addServlet(new ServletHolder(new DispatcherServlet(webApplicationContext)), MAPPING_URL);
        contextHandler.setContextPath(CONTEXT_PATH);

        HandlerCollection handlers = new HandlerCollection();

        handlers.setHandlers(new Handler[] {
                contextHandler,
//                new DefaultHandler()
        });

        server.setHandler(handlers);

        server.start();
        server.dumpStdErr();
        logger.info("Server started at port {}", port);
        server.join();
    }
}
