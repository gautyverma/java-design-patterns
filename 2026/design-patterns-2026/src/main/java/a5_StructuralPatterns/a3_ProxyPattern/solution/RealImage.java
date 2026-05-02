package a5_StructuralPatterns.a3_ProxyPattern.solution;

public class RealImage implements Image {
  private String fileName;

  public RealImage(String fileName) {
    this.fileName = fileName;
    loadImageFromDisk(); // Simulate expensive operation
  }

  private void loadImageFromDisk() {
    System.out.println("Loading image from disk: " + fileName);
    try {
      Thread.sleep(2000); // Simulate time-consuming loading
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void display() {
    System.out.println("Displaying image: " + fileName);
  }
}
