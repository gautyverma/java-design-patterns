package a1_Basics.a2_UML;

class Document {
  private String content;

  public Document(String content) {
    this.content = content;
  }

  public String getContent() {
    return content;
  }
}

class Printer {
  public void print(Document document) {
    System.out.println("Printing: " + document.getContent());
  }
}

public class a5_Dependency {
  public static void main(String[] args) {
    Document doc =
        new Document(
            "Dependency Example : As Document is created, Printer can print it. But Document does not need to know about Printer.");
    Printer printer = new Printer();
    printer.print(doc);
  }
}
