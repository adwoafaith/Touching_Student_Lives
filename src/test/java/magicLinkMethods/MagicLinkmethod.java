package magicLinkMethods;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.mail.*;
import javax.mail.internet.MimeMultipart;

import javax.mail.search.SubjectTerm;


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
        props.put("mail.store.protocol", "imaps");
        props.put("mail.imaps.host", "imap.gmail.com");
        props.put("mail.imaps.port", "993");
        props.put("mail.imap.ssl.enable", "true");

        Session session = Session.getDefaultInstance(props, null);
        Store store = session.getStore("imaps");
        store.connect("imap.gmail.com", username, password);

        Folder inbox = store.getFolder("INBOX");
        inbox.open(Folder.READ_ONLY);

        String magicLink = null;
        int retries = 5;
        int waitTime = 10000;

        for (int attempt = 1; attempt <= retries; attempt++) {
            System.out.println("⏳ Attempt " + attempt + ": Checking inbox for the magic link...");

            // Search for emails containing "Magic Link" or "Sign In"
            Message[] messages = inbox.search(new SubjectTerm("Sign In Link"));


            if (messages.length == 0) {
                System.out.println("⚠️ No new unread emails found. Retrying in " + (waitTime / 1000) + " seconds...");
                Thread.sleep(waitTime);
                continue;
            }

            Message message = messages[messages.length - 1];
            System.out.println("✅ Fetched email subject: " + message.getSubject());

            String content = getTextFromMessage(message);

            if (content == null || content.isEmpty()) {
                System.out.println("⚠️ Email content is empty, retrying...");
                Thread.sleep(waitTime);
                continue;
            }

            // 🔥 Extract the magic link from the HTML content
            magicLink = extractMagicLinkFromHtml(content);

            if (magicLink != null) {
                System.out.println("🔗 Extracted Magic Link: " + magicLink);
                break;
            } else {
                System.out.println("⚠️ Magic link not found in the email, retrying...");
                Thread.sleep(waitTime);
            }
        }

        inbox.close(false);
        store.close();

        if (magicLink == null) {
            throw new Exception("❌ Magic link not found after " + retries + " attempts.");
        }

        return magicLink;
    }

    public static String extractMagicLinkFromHtml(String htmlContent) {
        try {
            Document doc = Jsoup.parse(htmlContent);
            Elements links = doc.select("a[href]");

            for (Element link : links) {
                String href = link.attr("href");

                if (href.contains("auth/verify-magic-link")) { // Ensure it's the magic link
                    return href;
                }
            }
        } catch (Exception e) {
            System.err.println("Error extracting magic link: " + e.getMessage());
        }

        return null;
    }



    //let's extract text from the email
    public static String getTextFromMessage(Message message) throws MessagingException, IOException {
        System.out.println("📩 Email MIME Type: " + message.getContentType());

        if (message.isMimeType("text/plain")) {
            System.out.println("✅ Email is plain text!");
            return message.getContent().toString();
        } else if (message.isMimeType("text/html")) {
            System.out.println("✅ Email is HTML!");
            return message.getContent().toString();
        } else if (message.isMimeType("multipart/*")) {
            System.out.println("✅ Email is Multipart!");
            MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
            return getTextFromMimeMultipart(mimeMultipart);
        }

        return ""; // Return empty string instead of null
    }



    //method to extract text from a mimemultipart

    public static String getTextFromMimeMultipart(MimeMultipart mimeMultipart) throws MessagingException, IOException {
        StringBuilder result = new StringBuilder();

        int count = mimeMultipart.getCount();
        for (int i = 0; i < count; i++) {
            BodyPart bodyPart = mimeMultipart.getBodyPart(i);

            if (bodyPart.isMimeType("text/plain")) {
                System.out.println("✅ Found text/plain content!");
                result.append("\n").append(bodyPart.getContent().toString());
            } else if (bodyPart.isMimeType("text/html")) {
                System.out.println("✅ Found text/html content!");
                result.append("\n").append(bodyPart.getContent().toString()); // Extract HTML directly
            } else if (bodyPart.getContent() instanceof MimeMultipart) {
                result.append(getTextFromMimeMultipart((MimeMultipart) bodyPart.getContent()));
            }
        }

        return result.toString().trim();
    }





}
