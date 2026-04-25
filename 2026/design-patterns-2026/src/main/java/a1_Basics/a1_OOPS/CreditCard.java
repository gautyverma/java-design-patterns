package a1_Basics.a1_OOPS;

public class CreditCard extends Card {
  public CreditCard(String cardNumber, String cardHolderName) {
    super(cardNumber, cardHolderName);
  }

  @Override
  void pay() {
    System.out.println("Payment done using Credit Card");

  }
}
