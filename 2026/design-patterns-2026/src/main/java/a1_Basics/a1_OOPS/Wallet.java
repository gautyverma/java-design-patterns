package a1_Basics.a1_OOPS;

public class Wallet implements PaymentMethod {
  private String walletId;

  public Wallet(String walletId) {
    this.walletId = walletId;
  }

  @Override
  public void pay() {
    System.out.println("Payment made using Wallet" + "\nWallet ID: " + walletId);
  }
}
