package com.sqlworks.service.migration;

import com.sqlworks.dao.DaoException;
import ru.yandex.qatools.embed.postgresql.EmbeddedPostgres;

import java.io.IOException;
import java.util.Properties;

import static ru.yandex.qatools.embed.postgresql.distribution.Version.Main.V10;

public class Executor {

    private static String host;
    private static int port;
    private static String dbName;
    private static String user;
    private static String password;
    private static EmbeddedPostgres postgres = new EmbeddedPostgres(V10);

    public static void main(String[] args) throws Exception {
        init();
        if (args[0].equals("0")){
            postgres.start(host, port, dbName, user, password);
        } else if (args[0].equals("1")){
            postgres.stop();
        } else {
            throw new Exception("No right arguments found!");
        }
    }

    private static void init(){
        Properties properties = new Properties();
        try {
            properties.load(Executor.class.getResourceAsStream("/hikari.properties"));
            host = properties.getProperty("dataSource.serverName");
            port = Integer.valueOf(properties.getProperty("dataSource.portNumber"));
            dbName = properties.getProperty("dataSource.databaseName");
            user = properties.getProperty("dataSource.user");
            password = properties.getProperty("dataSource.password");
        } catch (IOException e) {
            throw new DaoException("Cannot read table name from 'dao.project.tablename " +
                    "from file hikari.properties", e);
        }
    }

}
