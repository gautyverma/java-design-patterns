package a2_SolidPrinciples.a3_ISP.goodImpl;

public class UserMain {
  public static void main(String[] args) {
    ReadableFile readableFile = new ReadOnlyFile();
    readableFile.read(); // Works fine

    WritableFile writableFile = new WritableFile();
    writableFile.write(); // Works fine
    writableFile.read(); // Works fine

    System.out.println(" --- Using readAnyFile method: --- ");
    readAnyFile(readableFile);
    readAnyFile(writableFile);
  }

  public static void readAnyFile(ReadableFile file) {
    file.read();
  }
}
