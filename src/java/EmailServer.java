
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Coldwellaj
 */
public class EmailServer {
    
    public static void LoginNotifyEmail(String email){
        try{

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); // for gmail use smtp.gmail.com
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "true"); 
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");

        Session mailSession = Session.getInstance(props, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("sagests310@gmail", "219907aJ1");
            }
        });

        mailSession.setDebug(true); // Enable the debug mode

        Message msg = new MimeMessage( mailSession );

        //--[ Set the FROM, TO, DATE and SUBJECT fields
        msg.setFrom( new InternetAddress( "admin@sage.com" ) );
        msg.setRecipients( Message.RecipientType.TO,InternetAddress.parse(email) );
        msg.setSentDate( new Date());
        msg.setSubject( "Login Alert!" );

        //--[ Create the body of the mail
        msg.setText( "Hello this email is to inform you that an attempt to login" 
        + " using your account has been made");

        //--[ Ask the Transport class to send our mail message
        Transport.send( msg );

    }catch(Exception E){
        System.out.println( "Oops something has gone pearshaped!");
        System.out.println( E );
    }
    }
}
