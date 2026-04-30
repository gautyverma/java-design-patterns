package a3_BehavioralPatterns.a7_StatePattern.solution;

public class Cycling implements TransportationMode {

  @Override
  public int calcETA() {
    System.out.println("Calculating ETA for cycling...");
    return 20; // Example ETA in minutes
  }

  @Override
  public String getDirection() {
    System.out.println("Getting directions for cycling...");
    return "Head north on Main St, then turn right onto 1st Ave.";
  }
}
