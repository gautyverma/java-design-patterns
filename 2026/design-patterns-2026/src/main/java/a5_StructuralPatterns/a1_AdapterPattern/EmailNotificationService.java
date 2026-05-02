package a5_StructuralPatterns.a1_AdapterPattern;

public class EmailNotificationService implements NotificationService {
  @Override
  public void send(String to, String subject, String body) {
    // Simulate sending an email
    System.out.println("Sending Email to: " + to);
    System.out.println("Subject: " + subject);
    System.out.println("Body: " + body);
  }
}
