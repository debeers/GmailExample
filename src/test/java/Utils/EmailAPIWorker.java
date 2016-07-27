package Utils;

import com.sun.mail.imap.IMAPFolder;
import javax.mail.*;
import javax.mail.search.SearchTerm;
import java.io.*;
import java.util.Properties;

/**
 * Created by DeBeers on 24.07.2016.
 */
public class EmailAPIWorker {

    private IMAPFolder folder = null;
    Store store = null;

    public void getMails(String email, String password, String subjectToSearch) throws MessagingException, IOException, InterruptedException {
        try {
            Properties props = System.getProperties();
            props.setProperty("mail.store.protocol", "imaps");

            Session session = Session.getDefaultInstance(props, null);

            store = session.getStore("imaps");
            store.connect("imap.googlemail.com", email, password);
            folder = (IMAPFolder) store.getFolder("[Gmail]/Inbox"); //This works for both email account

            if (!folder.isOpen())
                folder.open(Folder.READ_WRITE);

            Thread.sleep(3000);
            SearchTerm searchCondition = new SearchTerm() {
                @Override
                public boolean match(Message message) {
                    try {
                        if (message.getFrom().toString().equalsIgnoreCase(subjectToSearch)) {
                            return true;
                        }
                    } catch (MessagingException ex) {
                        ex.printStackTrace();
                    }
                    return false;
                }
            };

            Message[] foundMessages = folder.search(searchCondition);

            for (int i = 0; i < foundMessages.length; i++) {
                Message message = foundMessages[i];
                String subject = message.getSubject();
                System.out.println("Found message #" + i + ": " + subject);
            }

            folder.close(false);
            store.close();
        } catch (NoSuchProviderException ex) {
            System.out.println("No provider.");
            ex.printStackTrace();
        } catch (MessagingException ex) {
            System.out.println("Could not connect to the message store.");
            ex.printStackTrace();
        }
    }
}
