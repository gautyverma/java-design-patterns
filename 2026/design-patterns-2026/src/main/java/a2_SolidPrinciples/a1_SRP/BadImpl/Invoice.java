package a2_SolidPrinciples.a1_SRP.BadImpl;

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

  public void saveToDatabase() {
    // Code to save invoice to database
    System.out.println("Saving invoice to database for amount: " + amount);
  }

  public void sendEmail() {
    // Code to send invoice via email
    System.out.println("Sending invoice email for amount: " + amount);
  }
}

