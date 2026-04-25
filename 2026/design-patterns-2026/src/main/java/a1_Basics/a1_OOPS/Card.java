package a1_Basics.a1_OOPS;

/**
 * Abstract Card Class
 * 
 * PRINCIPLE: Inheritance, Abstraction, and Encapsulation
 * 
 * This abstract base class represents a generic card with common attributes
 * shared by all card types (CreditCard, DebitCard, etc.).
 * 
 * WHY ABSTRACT?
 * - Cannot instantiate Card directly; must use a concrete implementation
 * - Defines abstract methods that child classes must implement
 * - Provides template for card-based payment methods
 * 
 * IMPLEMENTS PaymentMethod:
 * - Card itself implements the PaymentMethod interface
 * - This allows Card and its subclasses to be used polymorphically
 * 
 * BENEFITS:
 * - Eliminates code duplication of cardNumber and cardHolderName
 * - Centralizes common card attributes
 * - Forces child classes to implement pay() method
 * - Enables polymorphic behavior for all card types
 */
public abstract class Card implements PaymentMethod {
  // ============ Encapsulation: Private Attributes ============
  /**
   * Card number - Unique identifier for the card
   * Private: Accessed through getters/setters only
   */
  private String cardNumber;
  
  /**
   * Card holder name - Name of the person holding the card
   * Private: Accessed through getters/setters only
   */
  private String cardHolderName;

  // ============ Constructor ============
  /**
   * Initialize a Card with card number and holder name
   * 
   * @param cardNumber - Unique card identifier (e.g., "1234567890")
   * @param cardHolderName - Name of the card holder (e.g., "John Doe")
   */
  public Card(String cardNumber, String cardHolderName) {
    this.cardNumber = cardNumber;
    this.cardHolderName = cardHolderName;
  }

  // ============ Getters & Setters (Encapsulation) ============
  /**
   * Get the card number
   * @return the card number
   */
  public String getCardNumber() {
    return cardNumber;
  }

  /**
   * Set the card number
   * @param cardNumber - new card number
   */
  public void setCardNumber(String cardNumber) {
    this.cardNumber = cardNumber;
  }

  /**
   * Get the card holder name
   * @return the card holder name
   */
  public String getCardHolderName() {
    return cardHolderName;
  }

  /**
   * Set the card holder name
   * @param cardHolderName - new card holder name
   */
  public void setCardHolderName(String cardHolderName) {
    this.cardHolderName = cardHolderName;
  }

  /**
   * Abstract pay() method - MUST be implemented by subclasses
   * Different card types (Credit, Debit) implement payment differently
   * This is an example of the Template Method Pattern
   */
  @Override
  public abstract void pay();
}
