package AIFB.Programmierprüfung;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.MessagingException;

public class MailService {

    public static void main(String[] args) {
        try {
            sendMail(new User(), "uhrga@student.kit.edu", "test");
        } catch (MessagingException e) {
            System.out.println("problemm");
            e.printStackTrace();
        }
    }

    public static void sendMail(User from, String to, String msg) throws AddressException, MessagingException {
        Properties props = System.getProperties();

        // Setup mail server
        props.put("mail.smtp.host", "eas.kit.edu");
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "true");
        props.put("mail.smtp.port", "25");

        // Get session
        //Session session = Session.getDefaultInstance(props, null);
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(from.getEmail(), from.getPassword());
                    }
                });

        // Define message
        MimeMessage message = new MimeMessage(session);

        // Set the from address
        message.setFrom(new InternetAddress(from.getEmail()));

        // Set the to address
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

        // Set the subject
        message.setSubject("Hello JavaMail");

        // Set the content
        message.setText("Welcome to JavaMail");

        // Send message
        Transport.send(message);
    }
}
