package a1_Basics.a1_OOPS;

/**
 * Client Class - Demonstrates the Payment System Usage
 * 
 * PRINCIPLE: Demonstrates polymorphism and interface usage
 * 
 * This is the entry point of the application that shows:
 * 1. How to create different payment method instances
 * 2. How to register them with PaymentService
 * 3. How PaymentService handles them polymorphically
 * 
 * KEY OBSERVATION:
 * - PaymentService.makePayment() calls the same method for all payment types
 * - Different behaviors occur based on payment method type
 * - This is POLYMORPHISM in action
 * 
 * OUTPUT DEMONSTRATION:
 * The output shows how the same method processes different payment types:
 * - CreditCard.pay() outputs: "Payment made using Credit Card..."
 * - DebitCard.pay() outputs: "Payment made using Debit Card..."
 * - UPI.pay() outputs: "Payment made using UPI..."
 * - Wallet.pay() outputs: "Payment made using Wallet..."
 */
public class Client {
  /**
   * Main method - Entry point of the application
   * 
   * DEMONSTRATION OF OOP PRINCIPLES:
   * - Inheritance: CreditCard, DebitCard extend Card
   * - Abstraction: PaymentMethod interface hides implementation details
   * - Polymorphism: PaymentService uses all types uniformly
   * - Encapsulation: Private attributes with public getters/setters
   */
  public static void main(String[] args) {
    // ============ Step 1: Create PaymentService ============
    // PaymentService will manage all payment methods
    PaymentService service = new PaymentService();
    
    // ============ Step 2: Register Different Payment Methods ============
    // Each payment method is registered with a unique identifier
    
    // Card-based payment methods (both extend Card)
    service.addPaymentMethod("gv-CreditCard", new CreditCard("1234", "Chirag"));
    service.addPaymentMethod("gv-DebitCard", new DebitCard("5678", "Deepak"));
    
    // Non-card payment methods (implement PaymentMethod directly)
    service.addPaymentMethod("gv-UPI", new UPI("udapi@0011"));
    service.addPaymentMethod("gv-wallet", new Wallet("wallet@00w12"));

    // ============ Step 3: Make Payments ============
    // All payment processing goes through the same interface
    // But different pay() implementations are called at runtime
    
    System.out.println("-----------------------------");
    service.makePayment("gv-CreditCard");  // Calls CreditCard.pay()
    System.out.println("-----------------------------");
    
    service.makePayment("gv-DebitCard");   // Calls DebitCard.pay()
    System.out.println("-----------------------------");
    
    service.makePayment("gv-UPI");         // Calls UPI.pay()
    System.out.println("-----------------------------");
    
    service.makePayment("gv-wallet");      // Calls Wallet.pay()
    System.out.println("-----------------------------");
    
    // ============ Step 4: Extensibility Example ============
    // To add a new payment method (e.g., PhonePe):
    // 1. Create class: public class PhonePe implements PaymentMethod { ... }
    // 2. Register it: service.addPaymentMethod("gv-PhonePe", new PhonePe(...));
    // 3. Use it: service.makePayment("gv-PhonePe");
    // NO changes needed to PaymentService or Client!
  }
}
