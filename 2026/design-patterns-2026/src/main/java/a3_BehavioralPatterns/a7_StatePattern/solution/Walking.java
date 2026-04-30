package a3_BehavioralPatterns.a7_StatePattern.solution;

public class Walking implements TransportationMode {
  @Override
  public int calcETA() {
    System.out.println("Calculating ETA for walking...");
    return 30; // Example ETA in minutes
  }

  @Override
  public String getDirection() {
    System.out.println("Getting directions for walking...");
    return "Head north on Main St, then turn right onto 1st Ave.";
  }
}
