package com.sqlworks.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionToDB {

    /**
     * Make connection to my PostgreSQL Server on port 5432.
     * @return {@code java.sql.Connection}
     */
    public static Connection connect() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new DaoException("Can't find org.postgresql.Driver.", e);
        }
        String url = "jdbc:postgresql://localhost:5432/crud";
        Properties props = new Properties();
        props.setProperty("user","cruder");
        props.setProperty("password","p@ssw0rd");
        try{
            return  DriverManager.getConnection(url, props);
        } catch (SQLException e) {
            throw new DaoException("Error while connecting to database: ", e);
        }
    }
}
