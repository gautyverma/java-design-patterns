package a3_BehavioralPatterns.a1_MementoPattern;

public class TextEditorMain {

  public static void main(String[] args) {

    TextEditor editor = new TextEditor();
    Caretaker caretaker = new Caretaker();

    // Initial state
    editor.append("Hello");
    caretaker.saveState(editor);

    editor.append(" World");
    caretaker.saveState(editor);

    editor.append("!!!");
    caretaker.saveState(editor);

    System.out.println("\nCurrent: " + editor.getContent());

    // Undo operations
    System.out.println("\n--- Undo 1 ---");
    caretaker.undo(editor);
    System.out.println("Current: " + editor.getContent());

    System.out.println("\n--- Undo 2 ---");
    caretaker.undo(editor);
    System.out.println("Current: " + editor.getContent());
  }
}
