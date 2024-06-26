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
            Endpoint.publish("http://0.0.0.0:8081/getCurrency", new getCurrencyServiceImpl());
            Endpoint.publish("http://0.0.0.0:8081/getHistory", new getHistoryServiceImpl());
            Endpoint.publish("http://0.0.0.0:8081/uangConverter", new uangConverterServiceImpl());
            Endpoint.publish("http://0.0.0.0:8081/getCode", new getCodeServiceImpl());
            Endpoint.publish("http://0.0.0.0:8081/updatePoint", new updatePointServiceImpl());
            
            
            System.out.println("Server started");
        } catch (Exception e) {
            System.out.println("Something Wrong, error is " + e);
        }
    }
}