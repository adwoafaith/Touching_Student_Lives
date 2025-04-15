package magicLinkMethods;

import org.jsoup.Jsoup;

import javax.mail.*;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OtpFetcher {

    // ✅ Fetch OTP code from email (like "Your OTP is 123456")
    public static String fetchOtpFromEmail(String username, String password, long earliestAcceptableTime) throws Exception {
        System.out.println("📨 Looking for OTP email after: " + new Date(earliestAcceptableTime));

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

        String otpCode = null;
        int retries = 5;
        int waitTime = 10000;

        for (int attempt = 1; attempt <= retries; attempt++) {
            System.out.println("⏳ Attempt " + attempt + " at: " + new Date());

            SearchTerm searchTerm = new SubjectTerm("OTP Verification");
            Message[] messages = inbox.search(searchTerm);

            // Sort newest first
            Arrays.sort(messages, (m1, m2) -> {
                try {
                    return m2.getSentDate().compareTo(m1.getSentDate());
                } catch (MessagingException e) {
                    throw new RuntimeException(e);
                }
            });

            for (Message message : messages) {
                Date receivedDate = message.getReceivedDate();

                // ✅ Ignore emails received before the request time
                if (receivedDate == null || receivedDate.getTime() < earliestAcceptableTime) {
                    continue;
                }

                System.out.println("✅ Checking email: " + message.getSubject());
                String content = getTextFromMessage(message);


                Matcher matcher = Pattern.compile("\\b\\d{6}\\b").matcher(content);
                if (matcher.find()) {
                    otpCode = matcher.group();
                    System.out.println("🔐 OTP Found: " + otpCode);
                    break;
                } else {
                    System.out.println("⚠️ No OTP found in this email.");
                }

            }

            if (otpCode != null) break;

            System.out.println("🕐 OTP not found yet, retrying in " + (waitTime / 1000) + " seconds...");
            Thread.sleep(waitTime);
        }

        inbox.close(false);
        store.close();

        if (otpCode == null) {
            throw new Exception("❌ OTP not found after " + retries + " attempts.");
        }

        return otpCode;
    }

    // 🧽 Extract raw message content (plain or HTML)
    public static String getTextFromMessage(Message message) throws MessagingException, IOException {
        System.out.println("📩 Email MIME Type: " + message.getContentType());

        if (message.isMimeType("text/plain")) {
            return message.getContent().toString();
        } else if (message.isMimeType("text/html")) {
            return Jsoup.parse(message.getContent().toString()).text();  // Clean HTML to plain text
        } else if (message.isMimeType("multipart/*")) {
            MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
            return getTextFromMimeMultipart(mimeMultipart);
        }

        return "";
    }

    // 🧽 Extract from multipart email content
    public static String getTextFromMimeMultipart(MimeMultipart mimeMultipart) throws MessagingException, IOException {
        StringBuilder result = new StringBuilder();

        int count = mimeMultipart.getCount();
        for (int i = 0; i < count; i++) {
            BodyPart bodyPart = mimeMultipart.getBodyPart(i);

            if (bodyPart.isMimeType("text/plain")) {
                result.append("\n").append(bodyPart.getContent().toString());
            } else if (bodyPart.isMimeType("text/html")) {
                result.append("\n").append(Jsoup.parse(bodyPart.getContent().toString()).text());
            } else if (bodyPart.getContent() instanceof MimeMultipart) {
                result.append(getTextFromMimeMultipart((MimeMultipart) bodyPart.getContent()));
            }
        }

        return result.toString().trim();
    }
}
