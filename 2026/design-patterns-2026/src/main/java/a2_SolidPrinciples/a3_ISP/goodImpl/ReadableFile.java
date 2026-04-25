package a2_SolidPrinciples.a3_ISP.goodImpl;

public class ReadableFile implements Readable {

  @Override
  public void read() {
    // Code to read from file
    System.out.println("Reading from file");
  }
}
