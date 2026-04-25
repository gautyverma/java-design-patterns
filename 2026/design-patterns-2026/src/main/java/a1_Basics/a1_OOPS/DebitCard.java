package a1_Basics.a1_OOPS;

public class DebitCard extends Card {
  public DebitCard(String cardNumber, String cardHolderName) {
    super(cardNumber, cardHolderName);
  }

  @Override
  public void pay() {
    System.out.println("Payment made using Debit Card"+
        "\nCard Number: " + getCardNumber() +
        "\nCard Holder Name: " + getCardHolderName());
  }
}
