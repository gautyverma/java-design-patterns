package a1_Basics.a1_OOPS;

/**
 * UPI Class - Unified Payments Interface
 * 
 * PRINCIPLE: Interface Implementation & Polymorphism
 * 
 * Concrete implementation of PaymentMethod interface for UPI payments.
 * Unlike CreditCard and DebitCard which extend Card, UPI is NOT a card.
 * It directly implements PaymentMethod interface.
 * 
 * WHY NOT EXTEND CARD?
 * - UPI is not a card-based payment method
 * - It only has a UPI ID, not cardNumber or cardHolderName
 * - Card class attributes are not applicable to UPI
 * - This demonstrates composition over inheritance
 * 
 * RELATIONSHIP:
 * - Implements PaymentMethod interface
 * - No inheritance from Card (correct design decision)
 * 
 * POLYMORPHISM:
 * PaymentMethod method = new UPI("user@upi");
 * method.pay(); // Calls UPI's pay() method at runtime
 * 
 * FLEXIBILITY:
 * PaymentService can handle UPI, CreditCard, DebitCard, and any future
 * PaymentMethod implementations uniformly through the interface.
 */
public class UPI implements PaymentMethod {
  /**
   * UPI ID - Unique identifier for UPI payment
   * Example: "username@bankname" (e.g., "chirag@okhdfcbank")
   */
  private String upiId;

  /**
   * Constructor for UPI
   * Initializes with UPI ID only (no card attributes needed)
   * 
   * @param id - UPI ID for payment
   */
  public UPI(String id) {
    this.upiId = id;
  }

  /**
   * Implementation of pay() for UPI
   * 
   * POLYMORPHISM: This method is selected at runtime based on object type
   * Provides UPI-specific payment logic
   */
  @Override
  public void pay() {
    System.out.println("Payment made using UPI" + "\nUPI ID: " + upiId);
  }
}
