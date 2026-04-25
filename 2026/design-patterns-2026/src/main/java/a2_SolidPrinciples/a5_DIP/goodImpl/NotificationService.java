package a2_SolidPrinciples.a5_DIP.goodImpl;

public class NotificationService {
  private NotificationChannel notificationChannel;

  public NotificationService(NotificationChannel channel) {
    this.notificationChannel = channel;
  }

  public void sendNotification(String message) {
    notificationChannel.sendNotification(message); // Run-Time Polymorphism
  }
}
