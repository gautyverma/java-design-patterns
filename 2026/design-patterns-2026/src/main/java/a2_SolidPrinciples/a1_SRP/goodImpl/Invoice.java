package a2_SolidPrinciples.a1_SRP.goodImpl;

public class Invoice {

  private double amount;

  public Invoice(double amount) {
    this.amount = amount;
  }

  public double getAmount() {
    return amount;
  }

  public void generateInvoice() {
    // Code to generate invoice
    System.out.println("Generating invoice for amount: " + amount);
  }
}
