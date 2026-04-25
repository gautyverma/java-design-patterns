package a2_SolidPrinciples.a2_OCP.badImpl;

public class PaymentProcessor {
  /**
   * Issue with this implementation is that if we want to add new payment types [UPI...], we need to
   * modify the processPayment method, which violates the Open/Closed Principle. This can lead to
   * bugs and maintenance issues as the codebase grows.
   */
  public void processPayment(String paymentType, double amount) {
    if (paymentType.equals("CreditCard")) {
      // Code to process credit card payment
      System.out.println("Processing credit card payment of amount: " + amount);

    } else if (paymentType.equals("DebitCard")) {
      // Code to process DebitCard payment
      System.out.println("Processing DebitCard payment of amount: " + amount);
    } else if (paymentType.equals("PayPal")) {
      // Code to process PayPal payment
      System.out.println("Processing PayPal payment of amount: " + amount);
    } else {
      throw new IllegalArgumentException("Unsupported payment type: " + paymentType);
    }
  }
}
