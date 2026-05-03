package a5_StructuralPatterns.a5_FacadePattern.solution;

public class Client {
  public static void main(String[] args) {

    // Client interacts with the Facade to get order details
    APIGateway apiGateway = new APIGateway();
    // Task
    System.out.println(apiGateway.getFullOrderDetails("U-007", "O-12345", "P-123"));
  }
}
