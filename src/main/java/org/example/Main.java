package org.example;

import javax.xml.ws.Endpoint;

import org.example.service.*;

public class Main {
    public static void main(String[] args) {
        try {
            Endpoint.publish("http://0.0.0.0:8081/topup", new TopupServiceImpl());
            Endpoint.publish("http://0.0.0.0:8081/code", new restCodeServiceImpl());
            Endpoint.publish("http://0.0.0.0:8081/buyProduct", new buyProductServiceImpl());
            Endpoint.publish("http://0.0.0.0:8081/checkCode", new checkCodeServiceImpl());
            
            System.out.println("Server started");
        } catch (Exception e) {
            System.out.println("Something Wrong, error is " + e);
        }
    }
}