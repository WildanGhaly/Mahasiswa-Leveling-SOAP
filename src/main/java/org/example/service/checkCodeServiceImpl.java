
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
public class checkCodeServiceImpl implements checkCodeService {

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
    public int checkCode(String code, int restId) {
        if (!checkApiKey()) {
            return 0;
        }
        Database db = new Database();
        Connection connection = db.getConnection();
        System.out.println(restId);
        System.out.println(code);
        try {
            connection.setAutoCommit(false);
            
            String codeCheckQuery = "SELECT * FROM soap_connector WHERE code = ? AND user_id_Rest IS NULL";
            PreparedStatement codeCheckStatement = connection.prepareStatement(codeCheckQuery);
            codeCheckStatement.setString(1, code);
            ResultSet codeCheckResult = codeCheckStatement.executeQuery();

            if (!codeCheckResult.next()) {
                codeCheckResult.close();
                codeCheckStatement.close();
                connection.close();
                log("Invalid code: " + code);
                return 0;
            }
            String updateRestCodeQuery = "UPDATE soap_connector SET user_id_Rest = ? WHERE code = ? AND user_id_Rest IS NULL";
            PreparedStatement updateStatement = connection.prepareStatement(updateRestCodeQuery);
            updateStatement.setInt(1, restId);
            updateStatement.setString(2, code);
            int rowsAffected = updateStatement.executeUpdate();
            updateStatement.close();
            codeCheckResult.close();
            codeCheckStatement.close();
            connection.commit();
            connection.close();
            log("check code with code " + code + " success");
            return 1;

        } catch (Exception e) {
            e.printStackTrace();
            log("Error when check code with code " + code);
            return 0;
        }
    }

    
}
