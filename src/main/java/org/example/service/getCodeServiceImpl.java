
package org.example.service;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import com.sun.net.httpserver.HttpExchange;
import java.sql.PreparedStatement;

import com.sun.xml.internal.ws.developer.JAXWSProperties;
import org.example.model.Challenge;
import org.example.model.ChallengeDAOImpl;
import org.example.core.Database;
import org.example.model.Log;
import org.w3c.dom.Element;
import java.security.Principal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


@WebService
public class getCodeServiceImpl implements getCodeService {

    @Resource
    public WebServiceContext wsContext;

   public Boolean checkApiKey() {
       String[] API_KEYS = { "RestClient" , "PHPClient" };
       MessageContext msgContext = wsContext.getMessageContext();
       HttpExchange httpExchange = (HttpExchange) msgContext.get("com.sun.xml.internal.ws.http.exchange");
       String apiKey = httpExchange.getRequestHeaders().getFirst("X-API-Key");
       if (apiKey == null) {
         return false;
       } else if (apiKey.equals(API_KEYS[0]) || apiKey.equals(API_KEYS[1])) {
         return true;
       } else {
         return false;
       }
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

    @WebMethod
    @Override
    public String getCode(int phpId) {
        if (!checkApiKey()) {
            return "";
        }
        Database db = new Database();
        Connection connection = db.getConnection();
        System.out.println(phpId);
        String code;
        try {
            String query = "SELECT code FROM soap_connector WHERE user_id_php = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, phpId); // Set the PHP user ID
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                code = resultSet.getString("code");
            } else {
                return"";
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();

            log("getting rest code with user id php " + phpId);
            return code;

        } catch (Exception e) {
            e.printStackTrace();
            log("Error when getting rest code with user id php " + phpId);
            return "";
        }
    }

    
}
