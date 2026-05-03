package a5_StructuralPatterns.a5_FacadePattern.solution;

public class OrderService {
    public String getOrderDetails(String orderId) {
        // Simulate fetching order details from a database
        return "Order ID: " + orderId + ", Product: Laptop, Quantity: 1, Price: $1000";
    }
}
