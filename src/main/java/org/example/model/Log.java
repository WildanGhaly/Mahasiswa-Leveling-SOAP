package org.example.model;

import org.example.core.Database;
import java.sql.Statement;


public class Log extends Database {
    public Log() {
        super();
      }

    public String InsertLog(String deskripsi, String ipaddr, String endpoint) {
    try {
        Statement statement = this.connection.createStatement();
        String query = "INSERT INTO soap_log (description, ip_address, endpoint) VALUES ('" + deskripsi + "', '" + ipaddr + "', '" + endpoint + "')";
        statement.executeUpdate(query);
    } catch (Exception e) {
        e.printStackTrace();
        return "Failed inserting log";
    }
    return "Successfully inserting log";
    }
    
}
