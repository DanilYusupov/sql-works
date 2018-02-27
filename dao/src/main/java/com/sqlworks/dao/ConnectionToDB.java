package com.sqlworks.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

@Deprecated
public class ConnectionToDB {

    private String host;
    private String port;
    private String database;
    private String user;
    private String password;

    public ConnectionToDB() {
        initProps();
    }

    /**
     * Make connection to my PostgreSQL Server on port 5432.
     *
     * @return {@code java.sql.Connection}
     */
    public Connection connect() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new DaoException("Can't find org.postgresql.Driver.", e);
        }
        try {
            return DriverManager.getConnection("jdbc:postgresql://" + host + ":" + port + "/" + database, user, password);
        } catch (SQLException e) {
            throw new DaoException("Error while connecting to database: ", e);
        }
    }

    private void initProps() {
        Properties props = new Properties();
        try {
            props.load(getClass().getResourceAsStream("/props.properties"));
            host = props.getProperty("host");
            port = props.getProperty("port");
            database = props.getProperty("database");
            user = props.getProperty("user");
            password = props.getProperty("password");
        } catch (IOException e) {
            throw new DaoException("Cannot load properties from file 'props.properties': ", e);
        }
    }
}
