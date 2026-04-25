package a2_SolidPrinciples.a5_DIP.badImpl;

public class SmsService {
  public void sendSms(String recipient, String message) {
    // Code to send SMS
    System.out.println("Sending SMS to " + recipient + ": " + message);
  }
}
