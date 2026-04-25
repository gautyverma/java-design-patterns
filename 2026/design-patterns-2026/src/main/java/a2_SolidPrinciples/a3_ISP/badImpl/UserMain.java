package a2_SolidPrinciples.a3_ISP.badImpl;

public class UserMain {
  public static void main(String[] args) {
    File file = new ReadOnlyFile();
    file.read(); // Works fine, as ReadOnlyFile supports read operation.
    file.write(); // This will throw UnsupportedOperationException at runtime, which is a violation
    // of the Interface Segregation Principle (ISP).
  }
}
