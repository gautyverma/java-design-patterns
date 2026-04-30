package a3_BehavioralPatterns.a7_StatePattern.solution;

public class Train implements TransportationMode {
  @Override
  public int calcETA() {
    System.out.println("Calculating ETA for Train");
    return 120; // Example ETA in minutes
  }

  @Override
  public String getDirection() {
    System.out.println("Getting direction for Train");
    return "Train direction: North";
  }
}
