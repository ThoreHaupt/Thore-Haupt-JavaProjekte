package Projects.EmailBombe;

import javax.mail.MessagingException;

public class EmailBombe {

    final static String target = "hwr51320@omeie.com";

    public static void main(String[] args) {
        User u = new User();

        try {
            for (int i = 0; i < 20; i++) {
                MailService.sendMail(u, target, EmailTextObject.header, EmailTextObject.body);
                Thread.sleep(5000);
            }
        } catch (MessagingException e) {
            System.out.println("problemm");
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
