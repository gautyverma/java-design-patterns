package a2_SolidPrinciples.a2_OCP.goodImpl;

public class DebitCard implements PaymentMode {
  @Override
  public void pay(double amount) {
    // Code to process Debit card payment
    System.out.println("Processing Debit card payment of amount: " + amount);
  }
}
