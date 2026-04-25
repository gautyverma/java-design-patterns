package a1_Basics.a1_OOPS;

/**
 * PaymentMethod Interface
 * 
 * PRINCIPLE: Abstraction & Interface Segregation
 * 
 * This interface defines a contract for all payment methods in the system.
 * It allows both card-based (CreditCard, DebitCard) and non-card based 
 * (UPI, Wallet) payment methods to implement a common interface.
 * 
 * BENEFITS:
 * - Loose coupling between payment types
 * - Enables polymorphic behavior in PaymentService
 * - Easy to extend with new payment methods without modifying existing code
 * - Follows Open/Closed Principle: Open for extension, closed for modification
 */
public interface PaymentMethod {
    /**
     * Abstract method to execute payment
     * Each implementing class must provide its own implementation
     */
    void pay();
}
