/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.login.dao;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author shalini_w
 */
public class EmailDao {

    public static void sendPasswordEmail(String username, String pwd, String email) {
        // Recipient's email ID needs to be mentioned.
        String receiver = email;
        String receiver_name = username;
        String auto_password = pwd;

        // Sender's email ID needs to be mentioned
        final String sender = "loginsystemtesting@gmail.com";
        final String password = "shalini123";

        String host = "smtp.gmail.com";
        int d_port = 25;

        // Get new properties
        Properties props = new Properties();

        // Setup mail server
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", d_port);

        props.put("mail.smtp.EnableSSL.enable", "true");
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallbac k", "false");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");

        // Get the default Session object.
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sender, password);
            }
        });

        session.setDebug(true);

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(sender));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
            
            MimeBodyPart textPart = new MimeBodyPart();
            Multipart multipart = new MimeMultipart();
            
            String finalText = "\nDear "+receiver_name+",\n\nYour password is : "+auto_password+"\n\nGo to http://192.168.1.159:8080/Login and log by using this password. Then reset your account with your own password.\n\nRegards\nDeveloper Team\n";
            System.out.println(finalText);
            textPart.setText(finalText);
//            message.setSubject("Reset Password");
            multipart.addBodyPart(textPart);
            message.setContent(multipart);
            message.setSubject("Auto Generated Password");

            Transport.send(message);

            
        } catch (MessagingException mex) {
            mex.printStackTrace();
            
        }
    }
    
}
