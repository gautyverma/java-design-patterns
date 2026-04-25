package a2_SolidPrinciples.a5_DIP.goodImpl;

public class WhatsAppService implements NotificationChannel {

  @Override
  public void sendNotification(String message) {
    // Code to send email
    System.out.println("Sending SMS: " + message);
  }
}
