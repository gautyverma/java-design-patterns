package a3_BehavioralPatterns.a7_StatePattern.solution;

public class Car implements TransportationMode {
  @Override
  public int calcETA() {
    System.out.println("Calculating ETA for Car");
    return 15; // Example ETA in minutes
  }

  @Override
  public String getDirection() {
    System.out.println("Getting direction for Car");
    return "Head north on Main St, then turn right onto 1st Ave.";
  }
}
