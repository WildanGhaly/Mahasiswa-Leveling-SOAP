package org.example;

import javax.xml.ws.Endpoint;

public class Main {
    public static void main(String[] args) {
        try {
            Endpoint.publish("http://localhost:8080/ws/testing", new TestingService());
            System.out.println("Server started");
        } catch (Exception e) {
            System.out.println("Something Wrong");
        }
    }
}