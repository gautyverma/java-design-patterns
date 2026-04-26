package a3_BehavioralPatterns.a1_Memento;

public class TextEditorMain {
  public static void main(String[] args) {
    TextEditor editor = new TextEditor();
    editor.write("Hello, World!");
    editor.write("Welcome to the Memento Design Pattern.");

    // Problem: No way to undo changes or revert to previous states
    System.out.println("Current Content: " + editor.getContent());
  }
}
