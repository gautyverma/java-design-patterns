package a2_SolidPrinciples.a5_DIP.badImpl;

public class NotificationService {
    private EmailService emailService;
    private SmsService smsService;

    public NotificationService() {
        this.emailService = new EmailService();
        this.smsService = new SmsService();
    }

    public void sendEmailNotification(String recipient, String message) {
        emailService.sendEmail(recipient, message);
    }

    public void sendSmsNotification(String recipient, String message) {
        smsService.sendSms(recipient, message);
    }
}
