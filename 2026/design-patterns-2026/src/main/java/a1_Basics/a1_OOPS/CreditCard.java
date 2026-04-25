package a1_Basics.a1_OOPS;

/**
 * CreditCard Class
 * 
 * PRINCIPLE: Inheritance & Method Overriding (Polymorphism)
 * 
 * Concrete implementation of the Card abstract class for Credit Card payments.
 * Inherits card attributes (cardNumber, cardHolderName) from Card.
 * Implements the abstract pay() method specific to credit card payment logic.
 * 
 * RELATIONSHIP:
 * - Extends Card (parent class)
 * - Implements PaymentMethod interface (through Card)
 * 
 * BENEFITS:
 * - Reuses common card attributes from Card class (no duplication)
 * - Provides specific payment logic for credit cards
 * - Can be used polymorphically through PaymentMethod interface
 * 
 * POLYMORPHISM IN ACTION:
 * PaymentMethod method = new CreditCard("1234", "Chirag");
 * method.pay(); // Calls CreditCard's pay() method at runtime
 */
public class CreditCard extends Card {
  /**
   * Constructor for CreditCard
   * Calls parent Card constructor to initialize card details
   * 
   * @param cardNumber - Credit card number
   * @param cardHolderName - Name of the credit card holder
   */
  public CreditCard(String cardNumber, String cardHolderName) {
    super(cardNumber, cardHolderName);
  }

  /**
   * Implementation of pay() for Credit Card
   * Overrides the abstract method from Card class
   * 
   * POLYMORPHISM: This method is selected at runtime based on object type
   * Even if called as PaymentMethod.pay(), this implementation is executed
   */
  @Override
  public void pay() {
    // Credit card payment implementation
    System.out.println(
        "Payment made using Credit Card"
            + "\nCard Number: "
            + getCardNumber()  // Uses inherited getter from Card
            + "\nCard Holder Name: "
            + getCardHolderName());  // Uses inherited getter from Card
  }
}
