package a3_BehavioralPatterns.a7_StatePattern.solution;

public class DirectionService {
  TransportationMode transportationMode;

  public DirectionService(TransportationMode transportationMode) {
    this.transportationMode = transportationMode;
  }

  public void setTransportationMode(TransportationMode transportationMode) {
    this.transportationMode = transportationMode;
  }

  // delegating the work current state's concrete class impl
  public int getETA() {
    return transportationMode.calcETA();
  }

  public String getDirection() {
    return transportationMode.getDirection();
  }
}
