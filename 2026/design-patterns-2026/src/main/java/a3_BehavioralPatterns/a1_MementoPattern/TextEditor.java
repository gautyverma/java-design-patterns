package a3_BehavioralPatterns.a1_MementoPattern;

/**
 * A text editor that allows users to undo changes such as text addition,deletion or formating . The
 * text editor maintains a history of changes using the Memento design pattern, allowing users to
 * revert to previous states of the document.
 */
public class TextEditor {

  private StringBuilder content = new StringBuilder();

  // Append text
  public void append(String text) {
    content.append(text);
  }

  // Save current state
  public EditorMemento save() {
    String snapshot = content.toString();
    System.out.println(" Saving: " + snapshot);
    return new EditorMemento(snapshot);
  }

  // Restore previous state
  public void restore(EditorMemento memento) {
    if (memento == null) {
      System.out.println("Nothing to restore!");
      return;
    }
    content = new StringBuilder(memento.getContent());
    System.out.println("Restored to: " + content);
  }

  public String getContent() {
    return content.toString();
  }
}
