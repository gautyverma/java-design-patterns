package a1_Basics.a1_OOPS;

public class DebitCard extends Card {
  public DebitCard(String cardNumber, String cardHolderName) {
    super(cardNumber, cardHolderName);
  }

  @Override
  void pay() {
    System.out.println("Payment done using Debit Card");
  }
}
