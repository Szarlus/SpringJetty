package pl.szarlus.h2;

import org.h2.tools.Server;

import java.sql.SQLException;

/**
 * Created by karol on 27.11.2017.
 */
public class H2Starter {

    private Server server;

    public H2Starter(String[] args) {
        try {
            server = Server.createTcpServer(args).start();
        } catch (SQLException e) {
            e.printStackTrace();
            server.stop();
        }
    }

}
