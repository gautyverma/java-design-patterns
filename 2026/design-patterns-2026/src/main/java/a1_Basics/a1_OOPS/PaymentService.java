package a1_Basics.a1_OOPS;

import java.util.HashMap;

/**
 * PaymentService Class - Core Payment Processing Service
 * 
 * PRINCIPLES: Polymorphism, Single Responsibility, Strategy Pattern
 * 
 * This class demonstrates RUNTIME POLYMORPHISM in action.
 * It manages and processes payments for different payment methods without
 * knowing the concrete type of each payment method.
 * 
 * KEY FEATURES:
 * 1. Stores multiple payment methods in a HashMap
 * 2. Associates each payment method with a unique name/identifier
 * 3. Processes payments polymorphically through PaymentMethod interface
 * 4. No casting or type checking required
 * 
 * DESIGN PATTERN:
 * This implements the Strategy Pattern where:
 * - PaymentMethod is the Strategy interface
 * - CreditCard, DebitCard, UPI, Wallet are concrete strategies
 * - PaymentService is the Context that uses different strategies
 * 
 * BENEFITS:
 * - Decoupled from specific payment implementations
 * - Easy to add new payment methods
 * - Single Responsibility: Only manages payment storage and execution
 * - Open/Closed Principle: Open for extension (new payment types), closed for modification
 */
public class PaymentService {
  /**
   * HashMap to store payment methods
   * Key: Unique identifier/name for the payment method (e.g., "gv-CreditCard")
   * Value: The actual PaymentMethod implementation (CreditCard, UPI, Wallet, etc.)
   * 
   * Note: Uses PaymentMethod interface as type, not concrete classes
   * This enables polymorphic behavior
   */
  HashMap<String, PaymentMethod> paymentMethods;

  /**
   * Constructor - Initializes the payment methods storage
   * Creates an empty HashMap to store payment methods
   */
  public PaymentService() {
    paymentMethods = new HashMap<>();
  }

  /**
   * Add a payment method to the service
   * 
   * POLYMORPHISM: Accepts any implementation of PaymentMethod
   * Can be CreditCard, DebitCard, UPI, Wallet, or any future payment type
   * 
   * @param name - Unique identifier for this payment method (e.g., "gv-CreditCard")
   * @param payMethod - The PaymentMethod implementation to register
   * 
   * EXAMPLE:
   * service.addPaymentMethod("card1", new CreditCard("1234", "Chirag"));
   * service.addPaymentMethod("upi1", new UPI("chirag@upi"));
   * Both work the same way!
   */
  public void addPaymentMethod(String name, PaymentMethod payMethod) {
    paymentMethods.put(name, payMethod);
  }

  /**
   * Make a payment using a registered payment method
   * 
   * RUNTIME POLYMORPHISM: THE CORE OF THIS DESIGN
   * - This method calls pay() on the PaymentMethod interface
   * - At runtime, Java determines the ACTUAL type of the object
   * - The correct pay() implementation is called (CreditCard, UPI, etc.)
   * - This happens WITHOUT any casting or type checking!
   * 
   * @param name - Identifier of the payment method to use
   * 
   * EXAMPLE EXECUTION:
   * service.makePayment("card1");
   * - Retrieves CreditCard object from HashMap
   * - Calls method.pay() -> CreditCard.pay() executes
   * 
   * service.makePayment("upi1");
   * - Retrieves UPI object from HashMap
   * - Calls method.pay() -> UPI.pay() executes
   * 
   * Same code, different behavior based on runtime type!
   */
  public void makePayment(String name) {
    // Retrieve payment method from storage
    PaymentMethod method = paymentMethods.get(name);
    
    // Check if payment method exists
    if (method != null) {
      // 🔑 RUNTIME POLYMORPHISM IN ACTION
      // This single line demonstrates the power of polymorphism
      // The correct pay() method is called based on actual object type
      method.pay();
    } else {
      System.out.println("Payment method not found");
    }
  }
}
