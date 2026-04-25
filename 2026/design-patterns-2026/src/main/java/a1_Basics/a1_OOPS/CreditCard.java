package a1_Basics.a1_OOPS;

public class CreditCard extends Card {
  public CreditCard(String cardNumber, String cardHolderName) {
    super(cardNumber, cardHolderName);
  }

  @Override
  public void pay() {
    System.out.println(
        "Payment made using Credit Card"
            + "\nCard Number: "
            + getCardNumber()
            + "\nCard Holder Name: "
            + getCardHolderName());
  }
}
