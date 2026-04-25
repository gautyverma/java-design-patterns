package a1_Basics.a1_OOPS;

/**
 * DebitCard Class
 * 
 * PRINCIPLE: Inheritance & Method Overriding (Polymorphism)
 * 
 * Concrete implementation of the Card abstract class for Debit Card payments.
 * Inherits card attributes (cardNumber, cardHolderName) from Card.
 * Implements the abstract pay() method specific to debit card payment logic.
 * 
 * RELATIONSHIP:
 * - Extends Card (parent class)
 * - Implements PaymentMethod interface (through Card)
 * 
 * BENEFITS:
 * - Reuses common card attributes from Card class (no duplication)
 * - Provides specific payment logic for debit cards
 * - Can be used polymorphically through PaymentMethod interface
 * 
 * KEY DIFFERENCE FROM CREDITCARD:
 * While both CreditCard and DebitCard extend Card and implement pay(),
 * each provides different payment processing logic.
 * This demonstrates method overriding and runtime polymorphism.
 */
public class DebitCard extends Card {
  /**
   * Constructor for DebitCard
   * Calls parent Card constructor to initialize card details
   * 
   * @param cardNumber - Debit card number
   * @param cardHolderName - Name of the debit card holder
   */
  public DebitCard(String cardNumber, String cardHolderName) {
    super(cardNumber, cardHolderName);
  }

  /**
   * Implementation of pay() for Debit Card
   * Overrides the abstract method from Card class
   * 
   * POLYMORPHISM: This method is selected at runtime based on object type
   * Different from CreditCard.pay(), showing method overriding
   */
  @Override
  public void pay() {
    // Debit card payment implementation
    System.out.println("Payment made using Debit Card"+
        "\nCard Number: " + getCardNumber() +  // Uses inherited getter from Card
        "\nCard Holder Name: " + getCardHolderName());  // Uses inherited getter from Card
  }
}
