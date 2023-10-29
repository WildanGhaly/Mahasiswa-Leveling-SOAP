package org.example;

import javax.xml.ws.Endpoint;

import org.example.service.ChallengeServiceImpl;

public class Main {
    public static void main(String[] args) {
        try {
            Endpoint.publish("http://localhost:8081/ws/testing", new ChallengeServiceImpl());
            System.out.println("Server started");
        } catch (Exception e) {
            System.out.println("Something Wrong");
        }
    }
}