
package org.example.service;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.ws.EndpointReference;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import javax.jws.WebMethod;

import com.sun.net.httpserver.HttpExchange;
import com.sun.xml.internal.ws.developer.JAXWSProperties;
import org.example.model.Challenge;
import org.example.model.ChallengeDAOImpl;
import org.example.core.Database;
import org.example.model.Log;
import org.w3c.dom.Element;
import java.security.Principal;

@WebService
public class ChallengeServiceImpl implements ChallengeService {

    @Resource
    public WebServiceContext wsContext;

    @Override
    @WebMethod
    public Challenge getChallengeById(int id) {
        ChallengeDAOImpl challengeDAO = new ChallengeDAOImpl();
        log("Get challenge by id");
        return challengeDAO.findById(id);
    }

    public void log(String description) {
        try {
            MessageContext msgContext = wsContext.getMessageContext();
            HttpExchange httpExchange = (HttpExchange) msgContext.get("com.sun.xml.internal.ws.http.exchange");
            String ip = httpExchange.getRemoteAddress().getAddress().getHostAddress();
            String endpoint = httpExchange.getRequestURI().toString();
            System.out.println(ip);
            System.out.println(endpoint);
            Log log = new Log();
            String apiKey = httpExchange.getRequestHeaders().getFirst("X-API-Key");
            String desc = apiKey + ": " + description;
           log.InsertLog(desc, ip, endpoint);
        } catch (Exception e) {
           System.out.println("Internal Server Error: " + e.getMessage());
        }
    }

}