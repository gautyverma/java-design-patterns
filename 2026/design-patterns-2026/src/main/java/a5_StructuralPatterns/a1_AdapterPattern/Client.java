package a5_StructuralPatterns.a1_AdapterPattern;

public class Client {
  public static void main(String[] args) {
    NotificationService emailService = new EmailNotificationService();
    emailService.send("customer@ab.com", "Your order has been shipped!", "Order details are ....");

    //      NotificationService sendGridService = new SendGridService();
    // issue is that SendGridService does not implement NotificationService interface, as it is 3rd
    // party service.

    System.out.println("------------------------------");
    NotificationService sendGridAdapter = new SendGridAdapter(new SendGridService());
    sendGridAdapter.send(
        "customer@ab.com", "Your order has been shipped!", "Order details are ....");
  }
}
