package a5_StructuralPatterns.a5_FacadePattern.solution;

public class APIGateway {
  UserService userService;
  OrderService orderService;
  PaymentService paymentService;

  public APIGateway() {
    this.userService = new UserService();
    this.orderService = new OrderService();
    this.paymentService = new PaymentService();
  }

  // Task: Implement a method to handle user registration, order placement, and payment processing
  // in a single method.
  public String getFullOrderDetails(String userId, String orderId, String amount) {
    String userDetails = userService.getUserDetails(userId);
    String orderDetails = orderService.getOrderDetails(orderId);
    String paymentStatus = paymentService.processPayment(amount);

    return "User Details: "
        + userDetails
        + "\n"
        + "Order Details: "
        + orderDetails
        + "\n"
        + "Payment Status: "
        + paymentStatus;
  }
}
