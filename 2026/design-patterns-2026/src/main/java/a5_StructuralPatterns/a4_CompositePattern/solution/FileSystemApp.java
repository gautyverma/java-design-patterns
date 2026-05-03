package a5_StructuralPatterns.a4_CompositePattern.solution;

public class FileSystemApp {
  public static void main(String[] args) {
    FileSystemComponent f1 = new File("file1.txt");
    FileSystemComponent f2 = new File("file2.txt");

    Folder folder = new Folder("Documents");
    folder.addComponent(f1);
    folder.addComponent(f2);

    // subfolder
    Folder subFolder = new Folder("Subfolder");
    FileSystemComponent f3 = new File("file3.txt");
    subFolder.addComponent(f3);

    folder.addComponent(subFolder);

    folder.showDetails();
  }
}
