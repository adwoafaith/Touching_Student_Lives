package magicLinkMethods;

import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;

import javax.mail.*;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.FlagTerm;
import java.io.File;
import java.io.IOException;

import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MagicLinkmethod {
    public static String extractToken(String magicLink){
        Pattern pattern = Pattern.compile("token=([^&]+)");
        Matcher matcher = pattern.matcher(magicLink);
        if(matcher.find()){
            return matcher.group(1);
        }
        return null;
    }

    //let's fetch the lastest unread message from our email
    public static String fetchMagicLinkFromEmail(String username, String password) throws Exception {
        Properties props = new Properties();
        props.put("mail.store.protocol","imaps");
        props.put("mail.imaps.host","imap.gmail.com");
        props.put("mail.imaps.port","993");
        props.put("mail.imap.ssl.enable","true");

        Session session = Session.getDefaultInstance(props,null);
        Store store = session.getStore("imaps");
        store.connect("imap.gmail.com","womensahfaith@gmail.com","");

        Folder inbox = store.getFolder("INBOX");
        inbox.open(Folder.READ_ONLY);

        Message[] messages = inbox.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));

        if (messages.length == 0) {
            throw new Exception("No new emails found");
        }

        Message message = messages[messages.length - 1];
        String content = getTextFromMessage(message);

        //use regex fo find the magic link

        Pattern linkPattern = Pattern.compile("(https://tslp-frontend\\.amalitech-dev\\.net/auth/verify-magic-link\\?token=[^\\s]+)");
        Matcher linkMatcher = linkPattern.matcher(content);
        String magicLink = null;

        if (linkMatcher.find()){
            magicLink = linkMatcher.group(1);
        }
        inbox.close(false);
        store.close();
        return magicLink;
    }

    //let's extract text from the email
    public static String getTextFromMessage(Message message) throws MessagingException, IOException, TikaException {
        String result = "";
        if (message.isMimeType("text/plain")) {
            result = message.getContent().toString();
        } else if (message.isMimeType("multipart/*")){
            MimeMultipart mineMultipart = (MimeMultipart) message.getContent();
            result = getTextFromMineMultipart(mineMultipart);
    }
        return result;
    }

    //method to extract text from a mimemultipart
    public static String getTextFromMineMultipart(MimeMultipart mimeMultipart) throws MessagingException, IOException, TikaException {
        String result = "";

        int count = mimeMultipart.getCount();
        for (int i = 0; i < count; i++) {
            BodyPart bodyPart = mimeMultipart.getBodyPart(i);
            if (bodyPart.isMimeType("text/plain")) {
                result = result + "\n" + bodyPart.getContent();
            } else if (bodyPart.isMimeType("text/html")) {

                String html = (String) bodyPart.getContent();

                // Convert HTML to plain text using apache tika.
                Tika tika = new Tika();
                result = result + "\n" + tika.parseToString(new File(html));

            } else if (bodyPart.getContent() instanceof MimeMultipart) {
                result = result + getTextFromMineMultipart((MimeMultipart) bodyPart.getContent());
            }
        }
        return result;
    }



}
