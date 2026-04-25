package a2_SolidPrinciples.a3_ISP.badImpl;

public class ReadOnlyFile extends File {
  public void write() {
    throw new UnsupportedOperationException("Write operation is not supported for ReadOnlyFile.");
  }
}
