package a2_SolidPrinciples.a5_DIP.goodImpl;

public class UserMain {
  public static void main(String[] args) {
    // Using Email Service
    NotificationChannel emailChannel = new EmailService();
    NotificationService notificationService = new NotificationService(emailChannel);
    notificationService.sendNotification("Hello via Email!");

    // Using SMS Service
    NotificationChannel smsChannel = new SmsService();
    notificationService = new NotificationService(smsChannel);
    notificationService.sendNotification("Hello via SMS!");

    // WhatsApp Service can be added without modifying existing code
    NotificationChannel whatsappChannel = new WhatsAppService();
    notificationService = new NotificationService(whatsappChannel);
    notificationService.sendNotification("Hello via WhatsApp!");
  }
}
