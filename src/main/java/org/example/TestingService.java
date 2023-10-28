package org.example;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class TestingService {
    @WebMethod
    public String HelloWorld(String name) {
        return "Hello " + name;
    }
}
