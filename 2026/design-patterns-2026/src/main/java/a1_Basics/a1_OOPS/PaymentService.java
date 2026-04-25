package a1_Basics.a1_OOPS;

import java.util.HashMap;

public class PaymentService {
  // Storing + Making Payment
  HashMap<String, PaymentMethod> paymentMethods;

  public PaymentService() {
    paymentMethods = new HashMap<>();
  }

  public void addPaymentMethod(String name, PaymentMethod payMethod) {
    paymentMethods.put(name, payMethod);
  }

  public void makePayment(String name) {
    PaymentMethod method = paymentMethods.get(name);
    if (method != null) {
      method.pay(); // Run-time polymorphism
    } else {
      System.out.println("Payment method not found");
    }
  }
}
