package Projects.EmailBombe;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailService {

    public static void sendMail(User from, String to, String header, String msg)
            throws AddressException, MessagingException {
        Properties props = System.getProperties();

        // Setup mail server --add-modules java.activation
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "true");
        props.put("mail.smtp.port", "465");

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
        message.setSubject(header);

        // Set the content
        message.setText(msg);

        // Send message
        Transport.send(message);
    }
}
