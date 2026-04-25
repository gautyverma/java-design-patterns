package a2_SolidPrinciples.a2_OCP.goodImpl;

public class ClientMain {
  public static void main(String[] args) {
    PaymentProcessor paymentProcessor = new PaymentProcessor();
    System.out.println("-----------------------------------");

    // Process Credit Card payment
    paymentProcessor.processPayment(new CreditCard(), 100.0);
    System.out.println("-----------------------------------");

    // Process Debit Card payment
    paymentProcessor.processPayment(new DebitCard(), 200.0);
    System.out.println("-----------------------------------");

    // Process UPI payment
    paymentProcessor.processPayment(new UPI(), 300.0);
    System.out.println("-----------------------------------");
  }
}
