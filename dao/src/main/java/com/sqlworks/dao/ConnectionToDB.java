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
    public static Connection connect(){
        String url = "jdbc:postgresql://localhost:5432/postgres";
        Properties props = new Properties();
        props.setProperty("user","postgres");
        props.setProperty("password","danil");
        props.setProperty("insert_returning", "true");
        try {
            return DriverManager.getConnection(url, props);
        } catch (SQLException e) {
            throw new DaoException("Error while connecting to database: ", e);
        }
    }
}
