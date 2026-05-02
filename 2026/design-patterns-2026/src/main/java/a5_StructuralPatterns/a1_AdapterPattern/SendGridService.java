package a5_StructuralPatterns.a1_AdapterPattern;

public class SendGridService {
  public void sendEmail(String recipient, String title, String content) {
    // Simulate sending an email using SendGrid's API
    System.out.println("Sending email via SendGrid:");
    System.out.println("To: " + recipient);
    System.out.println("Title: " + title);
    System.out.println("Content: " + content);
  }
}
