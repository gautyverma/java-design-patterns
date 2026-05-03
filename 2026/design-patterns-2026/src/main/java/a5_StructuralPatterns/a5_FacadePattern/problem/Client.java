package a5_StructuralPatterns.a5_FacadePattern.problem;

public class Client {
  public static void main(String[] args) {

    // Client code interacting with multiple services directly, leading to tight coupling and
    // complexity
    UserService userService = new UserService();
    OrderService orderService = new OrderService();
    PaymentService paymentService = new PaymentService();

    // task
    System.out.println(userService.getUserInfo("U-007"));
    System.out.println(orderService.getOrderDetails("O-12345"));
    System.out.println(paymentService.processPayment("P-123"));
  }
}
