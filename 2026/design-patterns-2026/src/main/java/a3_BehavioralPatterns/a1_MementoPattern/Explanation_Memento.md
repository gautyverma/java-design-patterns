# Memento Pattern

## Overview

The **Memento Pattern** is a behavioral design pattern that captures and externalizes an object's internal state without violating encapsulation, allowing the object to be restored to this state later.

---

## Why Use Memento Pattern?

- **Undo/Redo Functionality**: Save snapshots of object state for reverting to previous states
- **Encapsulation**: Capture state without exposing internal structure
- **History Management**: Maintain a history of changes without modifying the original object
- **Separation of Concerns**: Separate state management from the main object logic

---

## Use Cases

- Text editors (Undo/Redo operations)
- Database transaction rollbacks
- Game checkpoints and save states
- Document version control
- Snapshot-based state management

---

## Key Components

| Component | Responsibility |
|-----------|-----------------|
| **Originator** | Object whose state needs to be saved (TextEditor) |
| **Memento** | Stores the snapshot of the Originator's state (EditorMemento) |
| **Caretaker** | Manages the history of mementos (Caretaker) |

---

## Implementation

### 1. Memento Class (Store State)

**EditorMemento.java** - Immutable snapshot of state:

```java
// Memento clas : Stores the internal state of the TextEditor.
// Immutable snapshot
public class EditorMemento {

    private final String content;

    public EditorMemento(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
```

**Key Points:**
- Immutable: State cannot be changed after creation
- Stores the snapshot of editor content
- Only accessible through getter method

---

### 2. Originator Class (Create State)

**TextEditor.java** - Creates and restores mementos:

```java
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
```

**Key Points:**
- `save()`: Creates a memento with current state
- `restore()`: Restores from a memento
- Encapsulates the content management

---

### 3. Caretaker Class (Manage History)

**Caretaker.java** - Manages memento history:

```java
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
```

**Key Points:**
- Maintains a history stack of mementos
- `saveState()`: Saves current state to history
- `undo()`: Reverts to previous state
- No direct access to editor's internal state

---

## Usage Example

**TextEditorMain.java** - Demonstrates undo functionality:

```java
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
```

### Output

```
 Saving: Hello
 Saving: Hello World
 Saving: Hello World!!!

Current: Hello World!!!

--- Undo 1 ---
 Saving: Hello World
Restored to: Hello World
Current: Hello World

--- Undo 2 ---
 Saving: Hello
Restored to: Hello
Current: Hello
```

---

## Advantages

✅ **Encapsulation**: State captured without exposing internal structure  
✅ **Undo/Redo**: Easy to implement history management  
✅ **Single Responsibility**: Separation between state management and application logic  
✅ **State Isolation**: Each memento is independent  

---

## Disadvantages

❌ **Memory Usage**: Storing multiple mementos consumes memory  
❌ **Performance**: Creating snapshots can be expensive for large objects  
❌ **Cleanup**: Old mementos must be manually removed  

---

## Real-World Analogies

- **Chess Game**: Save position after each move, restore previous position
- **Photo Editor**: Save layer states to enable undo operations
- **IDE Code Editor**: Track file history for undo/redo
- **Database**: Transaction rollback functionality