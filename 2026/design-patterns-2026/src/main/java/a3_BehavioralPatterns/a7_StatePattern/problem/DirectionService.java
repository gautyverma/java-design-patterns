package a3_BehavioralPatterns.a7_StatePattern.problem;

enum TransportationMode {
  WALKING,
  BIKE,
  CAR,
  TRAIN
}

public class DirectionService {
  private TransportationMode mode;

  public DirectionService(TransportationMode mode) {
    this.mode = mode;
  }

  public void setMode(TransportationMode mode) {
    this.mode = mode;
  }

  // Calculate ETA based on the current transportation mode
  public int getETA() {
    switch (mode) {
      case WALKING:
        System.out.println("Calculating ETA for walking : 30 minutes");
        return 30; // 30 minutes
      case BIKE:
        System.out.println("Calculating ETA for biking : 15 minutes");
        return 15; // 15 minutes
      case CAR:
        System.out.println("Calculating ETA for driving : 10 minutes");
        return 10; // 10 minutes
      case TRAIN:
        System.out.println("Calculating ETA for train : 5 minutes");
        return 5; // 5 minutes
      default:
        throw new IllegalStateException("Unknown transportation mode: " + mode);
    }
  }

  public int getDirections() {
    switch (mode) {
      case WALKING:
        System.out.println("Getting directions for walking : 3 turns");
        return 3; // 3 turns
      case BIKE:
        System.out.println("Getting directions for biking : 2 turns");
        return 2; // 2 turns
      case CAR:
        System.out.println("Getting directions for driving : 1 turn");
        return 1; // 1 turn
      case TRAIN:
        System.out.println("Getting directions for train : No turns, just go to station");
        return 0; // No turns
      default:
        throw new IllegalStateException("Unknown transportation mode: " + mode);
    }
  }
}
