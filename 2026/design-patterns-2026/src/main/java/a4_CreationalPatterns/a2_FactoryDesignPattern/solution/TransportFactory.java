package a4_CreationalPatterns.a2_FactoryDesignPattern.solution;

public class TransportFactory {
  public static Transport createTransport(String type) {
    switch (type.toLowerCase()) {
      case "car":
        return new Car();
      case "bike":
        return new Bike();
      default:
        throw new IllegalArgumentException("Unknown transport type: " + type);
    }
  }
}
