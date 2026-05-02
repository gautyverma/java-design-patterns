package a5_StructuralPatterns.a3_ProxyPattern.solution;

public class Client {
  public static void main(String[] args) {
    Image image = new ProxyImage("photo.jpg");
    Image image2 = new ProxyImage("photo.jpg");

    image.display();
    image.display();
  }
}
