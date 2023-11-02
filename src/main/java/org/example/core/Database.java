package org.example.core;

import java.sql.*;

import io.github.cdimascio.dotenv.Dotenv;

public class Database {
    protected Connection connection;

    private Dotenv dotenv           = Dotenv.load();
    private String DB_URL           = dotenv.get("DB_URL");
    private String DB_USERNAME      = dotenv.get("MYSQL_USER");
    private String DB_PASSWORD      = dotenv.get("MYSQL_PASSWORD");

    public Database(){
        try {
            this.connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error connecting to database");
            System.out.println("DB_URL: " + DB_URL);
            System.out.println("DB_USERNAME: " + DB_USERNAME);
            System.out.println("DB_PASSWORD: " + DB_PASSWORD);
        }
    }

    public Connection getConnection(){
        return connection;
    }

}