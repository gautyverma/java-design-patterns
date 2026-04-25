package a2_SolidPrinciples.a2_OCP.goodImpl;

public class UPI implements PaymentMode {
  @Override
  public void pay(double amount) {
    // Code to process UPI payment
    System.out.println("Processing UPI payment of amount: " + amount);
  }
}
