package a5_StructuralPatterns.a1_AdapterPattern;

public interface NotificationService {
  void send(String to, String subject, String body);
}
