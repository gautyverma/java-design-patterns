package a4_CreationalPatterns.a2_FactoryDesignPattern.problem;

public class TransportService {
  public static void main(String[] args) {

    // Directly creating instances of transport types
    Transport car = new Car();
    Transport bike = new Bike();

    car.deliver();
    bike.deliver();

    // This approach can lead to tight coupling and makes it harder to add new transport types in
    // the future
  }
}
