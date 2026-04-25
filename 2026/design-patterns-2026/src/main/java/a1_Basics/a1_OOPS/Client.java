package a1_Basics.a1_OOPS;

public class Client {
  public static void main(String[] args) {
    PaymentService service = new PaymentService();
    service.addPaymentMethod("gv-CreditCard", new CreditCard("1234", "Chirag"));
    service.addPaymentMethod("gv-DebitCard", new DebitCard("5678", "Deepak"));
    service.addPaymentMethod("gv-UPI", new UPI("udapi@0011"));
    service.addPaymentMethod("gv-wallet", new Wallet("wallet@00w12"));

    System.out.println("-----------------------------");
    service.makePayment("gv-CreditCard");
    System.out.println("-----------------------------");
    service.makePayment("gv-DebitCard");
    System.out.println("-----------------------------");
    service.makePayment("gv-UPI");
    System.out.println("-----------------------------");
    service.makePayment("gv-wallet");
    System.out.println("-----------------------------");
  }
}
