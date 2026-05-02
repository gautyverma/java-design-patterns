package a5_StructuralPatterns.a3_ProxyPattern.solution;

public class ProxyImage implements Image {

  private String fileName;
  private RealImage realImage; // proxy holds a reference to the real object

  public ProxyImage(String fileName) {
    this.fileName = fileName;
  }

  @Override
  public void display() {
    if (realImage == null) { // lazy initialization
      realImage = new RealImage(fileName); // create the real object only when needed
    }
    realImage.display(); // delegate the call to the real object
  }
}
