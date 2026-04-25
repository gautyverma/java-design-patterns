package a2_SolidPrinciples.a5_DIP.badImpl;

public class EmailService {
  public void sendEmail(String recipient, String message) {
    // Code to send email
    System.out.println("Sending email to " + recipient + ": " + message);
  }
}
