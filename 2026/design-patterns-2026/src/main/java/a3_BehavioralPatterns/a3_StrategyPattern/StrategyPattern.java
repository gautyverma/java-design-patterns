package a3_BehavioralPatterns.a3_StrategyPattern;

// interface for strategy
interface PaymentStrategy {
  void pay(int amount);
}

class CreditCardPayment implements PaymentStrategy {
  private String cardNumber;

  public CreditCardPayment(String cardNumber) {
    this.cardNumber = cardNumber;
  }

  @Override
  public void pay(int amount) {
    System.out.println("Paid " + amount + " using credit card: " + cardNumber);
  }
}

class DebitCardPayment implements PaymentStrategy {
  private String cardNumber;

  public DebitCardPayment(String cardNumber) {
    this.cardNumber = cardNumber;
  }

  @Override
  public void pay(int amount) {
    System.out.println("Paid " + amount + " using debit card: " + cardNumber);
  }
}

class PaymentService {
  private PaymentStrategy paymentStrategy;

  public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
    this.paymentStrategy = paymentStrategy;
  }

  public void processPayment(int amount) {
    if (paymentStrategy == null) {
      throw new IllegalStateException("Payment strategy not set");
    }
    paymentStrategy.pay(amount);
  }
}

public class StrategyPattern {
  public static void main(String[] args) {
    PaymentService paymentService = new PaymentService();

    // Using credit card payment strategy
    paymentService.setPaymentStrategy(new CreditCardPayment("1234-5678-9012-3456"));
    paymentService.processPayment(100);

    // Using debit card payment strategy
    paymentService.setPaymentStrategy(new DebitCardPayment("9876-5432-1098-7654"));
    paymentService.processPayment(200);
  }
}
