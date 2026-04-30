package a3_BehavioralPatterns.a1_MementoPattern;

import java.util.Stack;

// Caretaker class: Manage mementos (snapshots of textEditor's state)
public class Caretaker {

  private final Stack<EditorMemento> history = new Stack<>();

  // Save state
  public void saveState(TextEditor editor) {
    history.push(editor.save());
  }

  // Undo
  public void undo(TextEditor editor) {
    if (history.isEmpty()) {
      System.out.println(" Nothing to undo!");
      return;
    }

    history.pop(); // remove current
    editor.restore(history.peek()); // restore previous
  }
}
