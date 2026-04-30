package a4_CreationalPatterns.a2_FactoryDesignPattern.solution;

public class Bike implements Transport {
  @Override
  public void deliver() {
    System.out.println("Delivering by bike");
  }
}
