package a2_SolidPrinciples.a3_ISP.goodImpl;

public class WritableFile extends ReadableFile implements Writable {
  @Override
  public void write() {
    // Code to write to file
    System.out.println("Writing to file");
  }
}
