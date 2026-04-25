package a1_Basics.a1_OOPS;

/**
 * Wallet Class - Digital Wallet Payment Method
 * 
 * PRINCIPLE: Interface Implementation & Polymorphism
 * 
 * Concrete implementation of PaymentMethod interface for Digital Wallet payments.
 * Like UPI, Wallet is NOT a card-based payment method.
 * It directly implements PaymentMethod interface.
 * 
 * WHY NOT EXTEND CARD?
 * - Wallet is not a card
 * - It operates on a wallet ID/account, not card credentials
 * - Extending Card would add unnecessary attributes (cardNumber, cardHolderName)
 * - This is a design principle: "Composition over Inheritance"
 * 
 * RELATIONSHIP:
 * - Implements PaymentMethod interface
 * - No inheritance from Card (correct design)
 * 
 * EXAMPLE WALLETS:
 * - Google Pay, Apple Pay, Samsung Pay
 * - PayPal, Paytm, PhonePe
 * - Amazon Pay
 * 
 * EXTENSIBILITY:
 * The system easily supports adding more wallet types:
 * public class GooglePay implements PaymentMethod { ... }
 * public class AmazonPay implements PaymentMethod { ... }
 * No changes needed to existing code!
 */
public class Wallet implements PaymentMethod {
  /**
   * Wallet ID - Unique identifier for the wallet account
   * Example: "user@paymentapp" or account number
   */
  private String walletId;

  /**
   * Constructor for Wallet
   * Initializes with wallet ID
   * 
   * @param walletId - Unique wallet identifier
   */
  public Wallet(String walletId) {
    this.walletId = walletId;
  }

  /**
   * Implementation of pay() for Wallet
   * 
   * POLYMORPHISM: This method is selected at runtime based on object type
   * Provides wallet-specific payment logic
   */
  @Override
  public void pay() {
    System.out.println("Payment made using Wallet" + "\nWallet ID: " + walletId);
  }
}
