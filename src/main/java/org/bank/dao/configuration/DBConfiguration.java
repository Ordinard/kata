package org.bank.dao.configuration;

import java.sql.*;

public class DBConfiguration {

    public static final String DB_URL = "jdbc:sqlite:bank.db";

    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Get the JDBC connection
     *
     * @return The JDBC connection
     * @throws SQLException Is throw for bad JDBC URL or unreachable host
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

}
