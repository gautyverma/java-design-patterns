package a5_StructuralPatterns.a4_CompositePattern.solution;

public class File implements FileSystemComponent {

  private String name;

  public File(String name) {
    this.name = name;
  }

  public void showDetails() {
    System.out.println("File: " + name);
  }
}
