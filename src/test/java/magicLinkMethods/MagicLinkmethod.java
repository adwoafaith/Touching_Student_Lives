package magicLinkMethods;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.mail.*;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MagicLinkmethod {

    // Extract the token from a magic link
    public static String extractToken(String magicLink) {
        Pattern pattern = Pattern.compile("token=([^&]+)");
        Matcher matcher = pattern.matcher(magicLink);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    // Fetch the token directly from the latest sign-in email
    public static String fetchTokenFromEmail(String username, String password,long earliestAcceptableTime) throws Exception {
        // Add this at the start of fetchTokenFromEmail
        System.out.println("Test started looking for emails after: " + new Date(earliestAcceptableTime));
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
            System.out.println("⏳ Attempt " + attempt + " at: " + new Date());

            SearchTerm searchTerm = new AndTerm(
                    new SubjectTerm("Sign In Link"),
                    new FlagTerm(new Flags(Flags.Flag.SEEN), false)
            );
            Message[] messages = inbox.search(searchTerm);


            System.out.println("Found " + messages.length + " matching emails");
            // If no unseen emails, fall back to recent emails
            if (messages.length == 0) {
                searchTerm = new AndTerm(
                        new SubjectTerm("Sign In Link"),
                        new SentDateTerm(ComparisonTerm.GT, new Date(earliestAcceptableTime - 30000)) // 30s buffer
                );
                messages = inbox.search(searchTerm);
            }
            // Sort messages by most recent first
            Arrays.sort(messages, (m1, m2) -> {
                try {
                    return m2.getSentDate().compareTo(m1.getSentDate());
                } catch (MessagingException e) {
                    throw new RuntimeException(e);
                }
            });

            for (Message message : messages) {
                System.out.println("✅ Checking email subject: " + message.getSubject());
                String content = getTextFromMessage(message);
                magicLink = extractMagicLinkFromHtml(content);


                if (magicLink != null) {
                    System.out.println("🔗 Found valid magic link: " + magicLink);
                    break;
                } else {
                    System.out.println("⚠️ Magic link not found in this email, checking next...");
                }
            }

            if (magicLink != null) break;

            System.out.println("⚠️ Magic link not found in any email, retrying in " + (waitTime / 1000) + " seconds...");
            Thread.sleep(waitTime);
        }

        inbox.close(false);
        store.close();

        if (magicLink == null) {
            throw new Exception("❌ Magic link not found after " + retries + " attempts.");
        }

        // ✅ Extract the token from the magic link
        String token = extractToken(magicLink);
        if (token == null) {
            throw new Exception("❌ Token could not be extracted from the magic link.");
        }

        System.out.println("🔐 Extracted Token: " + token);
        return token;
    }

    // Extract magic link from HTML content
    public static String extractMagicLinkFromHtml(String htmlContent) {
        try {
            Document doc = Jsoup.parse(htmlContent);
            Elements links = doc.select("a[href]");

            for (Element link : links) {
                String href = link.attr("href");
                if (href.contains("auth/verify-magic-link")) {
                    return href;
                }
            }
        } catch (Exception e) {
            System.err.println("Error extracting magic link: " + e.getMessage());
        }

        return null;
    }

    // Extract text from a message (plain, html, or multipart)
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

        return "";
    }

    // Extract text from multipart content
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
                result.append("\n").append(bodyPart.getContent().toString());
            } else if (bodyPart.getContent() instanceof MimeMultipart) {
                result.append(getTextFromMimeMultipart((MimeMultipart) bodyPart.getContent()));
            }
        }

        return result.toString().trim();
    }
}
