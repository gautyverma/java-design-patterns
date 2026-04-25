package a2_SolidPrinciples.a2_OCP.goodImpl;

public class CreditCard implements PaymentMode {
  @Override
  public void pay(double amount) {
    // Code to process credit card payment
    System.out.println("Processing credit card payment of amount: " + amount);
  }
}
