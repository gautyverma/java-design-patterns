package a3_BehavioralPatterns.a7_StatePattern.solution;

public class WithStatePattern {
  public static void main(String[] args) {
    DirectionService service = new DirectionService(new Car());
    service.setTransportationMode(new Train());

    System.out.println(service.getETA());
    System.out.println(service.getDirection());
  }
}
