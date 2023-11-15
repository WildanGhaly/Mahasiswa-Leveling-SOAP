package org.example;

import javax.xml.ws.Endpoint;

import org.example.service.ChallengeServiceImpl;
import org.example.service.TopupServiceImpl;
import org.example.service.restCodeServiceImpl;

public class Main {
    public static void main(String[] args) {
        try {
            Endpoint.publish("http://0.0.0.0:8081/topup", new TopupServiceImpl());
            Endpoint.publish("http://0.0.0.0:8081/code", new restCodeServiceImpl());
            
            System.out.println("Server started");
        } catch (Exception e) {
            System.out.println("Something Wrong, error is " + e);
        }
    }
}