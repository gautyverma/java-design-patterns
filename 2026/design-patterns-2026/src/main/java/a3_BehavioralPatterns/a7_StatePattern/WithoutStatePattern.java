package a3_BehavioralPatterns.a7_StatePattern;

public class WithoutStatePattern {
  public static void main(String[] args) {
    DirectionService service = new DirectionService(TransportationMode.TRAIN);
    service.setMode(TransportationMode.CAR);

    service.getDirections();
    service.getETA();
  }
}
