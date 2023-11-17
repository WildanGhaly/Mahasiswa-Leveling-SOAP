
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
import java.sql.SQLException;
import java.sql.Statement;


@WebService
public class buyProductServiceImpl implements buyProductService {

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
    public int buyProduct(int restId, int productId, int quantity, int balance) {
        if (!checkApiKey()) {
            return 0;
        }
        Database db = new Database();
        Connection connection = db.getConnection();
        System.out.println(restId);
        System.out.println(balance);
        try {
            connection.setAutoCommit(false);
            String balanceQuery = "SELECT point FROM soap_connector WHERE user_id_Rest = ?";
            PreparedStatement balanceStatement = connection.prepareStatement(balanceQuery);
            balanceStatement.setInt(1, restId);
            ResultSet balanceResult = balanceStatement.executeQuery();

            int currentBalance = 0;
            if (balanceResult.next()) {
                currentBalance = balanceResult.getInt("point");
            }
            if (currentBalance < balance) {
                balanceResult.close();
                balanceStatement.close();
                connection.close();
                return 0;
            }
            
            String updateQuery = "UPDATE soap_connector SET point = point - ? WHERE user_id_Rest = ?";
            PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
            updateStatement.setInt(1, balance);
            updateStatement.setInt(2, restId);
            int rowsAffected = updateStatement.executeUpdate();


            String historyInsertQuery = "INSERT INTO history (user_id, product_id, quantity) VALUES (?, ?, ?)";
            PreparedStatement historyInsertStatement = connection.prepareStatement(historyInsertQuery);
            historyInsertStatement.setInt(1, restId); 
            historyInsertStatement.setInt(2, productId); 
            historyInsertStatement.setInt(3, quantity); 

            int rowsInserted = historyInsertStatement.executeUpdate();
            historyInsertStatement.close();

            balanceResult.close();
            balanceStatement.close();
            updateStatement.close();
            connection.commit();
            // connection.close();
            
            log("buying product " + productId + " user id " + restId + " quantity " + quantity + " and total " + balance);
            
            return 1;
            
        } catch (SQLException e) {
            e.printStackTrace();
            log("Error when buying product " + productId + " user id " + restId + " quantity " + quantity + " and total " + balance);
            return -1;
        } 
    }

    
}