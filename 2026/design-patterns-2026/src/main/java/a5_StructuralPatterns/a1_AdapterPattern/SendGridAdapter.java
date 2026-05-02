package a5_StructuralPatterns.a1_AdapterPattern;

public class SendGridAdapter implements NotificationService {
  private SendGridService sendGridService;

  public SendGridAdapter(SendGridService sendGridService) {
    this.sendGridService = sendGridService;
  }

  @Override
  public void send(String to, String subject, String body) {
    // Adapter Method -> convert parameters to SendGrid format and call SendGrid API
    sendGridService.sendEmail(to, subject, body);

  }
}
