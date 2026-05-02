package a5_StructuralPatterns.a3_ProxyPattern.problem;

public class Client {
  public static void main(String[] args) {
    Image image = new RealImage("photo.jpg");
    image.display();
    Image image2 = new RealImage("photo.jpg");

    // This will load the image again, which is inefficient
  }
}
