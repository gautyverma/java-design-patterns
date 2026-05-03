package a5_StructuralPatterns.a4_CompositePattern.problem;

public class FileSystemApp {
  public static void main(String[] args) {
    File f1 = new File("file1.txt");
    File f2 = new File("file2.txt");

    Folder folder = new Folder("Documents");
    folder.addFile(f1);
    folder.addFile(f2);

    folder.showDetails();
  }
}
