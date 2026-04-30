package a4_CreationalPatterns.a2_FactoryDesignPattern.solution;

public class TransportService {
  public static void main(String[] args) {

    Transport vehicle = TransportFactory.createTransport("bike");
    vehicle.deliver();

    vehicle = TransportFactory.createTransport("truck");
    vehicle.deliver();
  }
}
