package a1_Basics.a1_OOPS;

public class UPI implements PaymentMethod {
  private String upiId;

  public UPI(String id) {
    this.upiId = id;
  }

  @Override
  public void pay() {
    System.out.println("Payment made using UPI" + "\nUPI ID: " + upiId);
  }
}
