package a2_SolidPrinciples.a2_OCP.goodImpl;

public class PaymentProcessor {
  public void processPayment(PaymentMode paymentMode, double amount) {
    paymentMode.pay(amount); // Run-time polymorphism to call the appropriate pay method
  }
}
