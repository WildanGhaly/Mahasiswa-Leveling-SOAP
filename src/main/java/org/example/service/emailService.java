package org.example.service;

import javax.mail.PasswordAuthentication;
import java.util.Properties;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;


public class emailService {
    public void sendEmailTopUpSuccess(String to, int amount) {
        String from = "Mahasiswaleveling@gmail.com";
        String host = "smtp.gmail.com";
        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
    
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("Mahasiswaleveling@gmail.com", "wjoqkblefofpcsgc");
            }
        });
    
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Top-Up Success Notification");
    
            String emailContent = "<html><head><meta charset='UTF-8'><meta name='viewport' content='width=device-width,initial-scale=1.0'><title>Top-Up Success Notification</title><style>.container{max-width:800px;margin:0 auto;background:#f3f1f1;font-family:Calibri;}.header{border-bottom:5px solid #000000;text-align:center;padding:3px;font-size:23px;}.content{padding:5px;margin:20px;}.title{text-align:center;color:#444;}.body{color:#060675;font-size:20px;}.footer{text-align:center;padding:15px;}.clear{clear:both;}</style></head><body><div class='container'><div class='header'><br><strong>Mahasiswaleveling</strong><br>Top-Up Success Notification<br><a href='Mahasiswaleveling@gmail.com'>Mahasiswaleveling@gmail.com</a></div><div class='content'><div class='title'><h2>Top-Up Success Notification</h2></div><div class='body'><p>Dear " + to + ",</p><p>Your top-up of $" + amount + " has been successfully processed.</p><p>We hope you enjoy using our services!</p><p style='text-align:right;'>Best Regards,<br><br>Mahasiswaleveling Admin<br></p></div></div><div class='clear'></div><div class='footer'><p>Copyright &copy; Mahasiswaleveling</p><p>All Rights Reserved</p></div></div></body></html>";
    
            message.setContent(emailContent, "text/html");
    
            System.out.println("Sending email...");
            Transport.send(message);
            System.out.println("Email sent!");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
    
    
}
