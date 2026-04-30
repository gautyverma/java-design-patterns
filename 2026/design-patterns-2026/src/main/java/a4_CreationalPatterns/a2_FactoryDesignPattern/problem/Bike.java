package a4_CreationalPatterns.a2_FactoryDesignPattern.problem;

public class Bike implements Transport {
  @Override
  public void deliver() {
    System.out.println("Delivering by bike");
  }
}
