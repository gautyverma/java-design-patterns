package a1_Basics.a1_OOPS;

public abstract class Card implements PaymentMethod {
  private String cardNumber;
  private String cardHolderName;

  public Card(String cardNumber, String cardHolderName) {
    this.cardNumber = cardNumber;
    this.cardHolderName = cardHolderName;
  }

  public String getCardNumber() {
    return cardNumber;
  }

  public void setCardNumber(String cardNumber) {
    this.cardNumber = cardNumber;
  }

  public String getCardHolderName() {
    return cardHolderName;
  }

  public void setCardHolderName(String cardHolderName) {
    this.cardHolderName = cardHolderName;
  }

}
