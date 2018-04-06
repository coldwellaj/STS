
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
    private static Properties props = System.getProperties();
    
    public static void LoginNotifyEmail(String email){
        try{
        props.put("mail.smtp.host", "smtp.gmail.com"); // for gmail use smtp.gmail.com
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", "587");
//        props.put("mail.smtp.user", "sagests310@gmail");
//        props.put("mail.smtp.password", "219907aJ1");
        

        Session mailSession = Session.getInstance(props);

        Message msg = new MimeMessage( mailSession );

        //--[ Set the FROM, TO, DATE and SUBJECT fields
        msg.setFrom( new InternetAddress( "admin@sage.com" ) );
        msg.setRecipients( Message.RecipientType.TO,InternetAddress.parse(email) );
        msg.setSentDate( new Date());
        msg.setSubject( "Login Alert!" );

        //--[ Create the body of the mail
        msg.setText( "Hello this email is to inform you that an attempt to login" 
        + " using your account has been made");

        //--[ Create transport object
        Transport transport = mailSession.getTransport("smtp");
        transport.connect("sagests310", "Hell0w0rld");
        transport.sendMessage(msg, msg.getAllRecipients());
        transport.close();
        

    }catch(Exception E){
        System.out.println( "Oops something has gone pearshaped!");
        System.out.println( E );
    }
    }
    
    public static boolean ContactClient(String clientEmail, String subject, String message){
        try{
        props.put("mail.smtp.host", "smtp.gmail.com"); // for gmail use smtp.gmail.com
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", "587");
//        props.put("mail.smtp.user", "sagests310@gmail");
//        props.put("mail.smtp.password", "219907aJ1");
        

        Session mailSession = Session.getInstance(props);

        Message msg = new MimeMessage( mailSession );

        //--[ Set the FROM, TO, DATE and SUBJECT fields
        msg.setFrom( new InternetAddress( "admin@sage.com" ) );
        msg.setRecipients( Message.RecipientType.TO,InternetAddress.parse(clientEmail) );
        msg.setSentDate( new Date());
        msg.setSubject( subject );

        //--[ Create the body of the mail
        msg.setText(message);

        //--[ Create transport object
        Transport transport = mailSession.getTransport("smtp");
        transport.connect("sagests310", "Hell0w0rld");
        transport.sendMessage(msg, msg.getAllRecipients());
        transport.close();
        return true;
        

    }catch(Exception E){
        System.out.println( "Oops something has gone pearshaped!");
        System.out.println( E );
        return false;
    }
    }
    
    public static boolean SendSurvey(Client client, Ticket ticket){
        try{
        props.put("mail.smtp.host", "smtp.gmail.com"); // for gmail use smtp.gmail.com
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", "587");
//        props.put("mail.smtp.user", "sagests310@gmail");
//        props.put("mail.smtp.password", "219907aJ1");
        

        Session mailSession = Session.getInstance(props);

        Message msg = new MimeMessage( mailSession );

        //--[ Set the FROM, TO, DATE and SUBJECT fields
        msg.setFrom( new InternetAddress( "admin@sage.com" ) );
        msg.setRecipients( Message.RecipientType.TO,InternetAddress.parse(client.getEmail()) );
        msg.setSentDate( new Date());
        msg.setSubject( "Closing of ticket: " + ticket.getTicketTitle());

        //--[ Create the body of the mail
        msg.setText("Your ticket titled: " + ticket.getTicketTitle() + " has been completed."
        + " Please feel free to fill out the survey below. /n ");

        //--[ Create transport object
        Transport transport = mailSession.getTransport("smtp");
        transport.connect("sagests310", "Hell0w0rld");
        transport.sendMessage(msg, msg.getAllRecipients());
        transport.close();
        return true;
        

    }catch(Exception E){
        System.out.println( "Oops something has gone pearshaped!");
        System.out.println( E );
        return false;
    }
    }
}
