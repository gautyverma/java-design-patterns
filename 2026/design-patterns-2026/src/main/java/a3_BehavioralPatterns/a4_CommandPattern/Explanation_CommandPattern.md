# Command Pattern

## Overview

The **Command Pattern** is a behavioral design pattern that encapsulates a request as an object, thereby allowing parameterization of clients with different requests, queuing of requests, and logging of the requests. It also enables undo/redo functionality.

---

## Why Use Command Pattern?

- **Decoupling**: Decouples the object that invokes operation from the object that performs it
- **Encapsulation**: Packages requests as objects that can be passed around
- **Queuing**: Commands can be queued and executed at different times
- **Undo/Redo**: Facilitates implementation of undo/redo mechanisms
- **Logging**: Commands can be logged and replayed
- **Macro Recording**: Supports recording and playback of command sequences

---

## Use Cases

- Text editor operations (bold, italic, underline, undo, redo)
- GUI button clicks and menu items
- Task scheduler and job queuing systems
- Macro recording and playback
- Transaction management and rollback
- Remote invocation and network communication

---

## Key Components

| Component | Responsibility |
|-----------|-----------------|
| **Command** | Interface defining the contract for command execution |
| **Concrete Command** | Implements specific operations and binds receiver |
| **Receiver** | Object that performs the actual work |
| **Invoker** | Object that triggers command execution |

---

## Implementation

### 1. Command Interface

**Command.java** - Defines command contract:

```java
interface Command {
  void execute();
}
```

**Key Points:**
- Simple interface with `execute()` method
- All commands must implement this interface
- Provides uniform way to invoke different operations

---

### 2. Receiver Class

**TextEditorCP.java** - The object that performs actual work:

```java
class TextEditorCP {
  public void bold() {
    System.out.println("Text is bolded");
  }

  public void italic() {
    System.out.println("Text is italicized");
  }

  public void underline() {
    System.out.println("Text is underlined");
  }

  public void changeColor() {
    System.out.println("Text color changed");
  }
}
```

**Key Points:**
- Contains the actual formatting operations
- Unaware of Command pattern
- Can be reused by different commands

---

### 3. Concrete Commands

**BoldCommand.java** - Encapsulates bold operation:

```java
class BoldCommand implements Command {
  private TextEditorCP textEditor;

  public BoldCommand(TextEditorCP textEditor) {
    this.textEditor = textEditor;
  }

  @Override
  public void execute() {
    textEditor.bold();
  }
}
```

**ChangeColorCommand.java** - Encapsulates color change operation:

```java
class ChangeColorCommand implements Command {
  private TextEditorCP textEditor;

  public ChangeColorCommand(TextEditorCP textEditor) {
    this.textEditor = textEditor;
  }

  @Override
  public void execute() {
    textEditor.changeColor();
  }
}
```

**Key Points:**
- Each command wraps a specific receiver operation
- Stores reference to receiver (TextEditorCP)
- Decouples invoker from receiver

---

### 4. Invoker Class

**Button.java** - Object that triggers commands:

```java
class Button {
  private Command command;

  public void setCommand(Command command) {
    this.command = command;
  }

  public void click() {
    command.execute();
  }
}
```

**Key Points:**
- Maintains reference to a command
- `setCommand()` allows changing command at runtime
- `click()` invokes the command without knowing details

---

## Usage Example

**TextEditorWithCommandPattern.java** - Demonstrates command pattern:

```java
public class TextEditorWithCommandPattern {
  public static void main(String[] args) {
    TextEditorCP textEditor = new TextEditorCP();

    // One Button can have multiple commands
    Button boldButton = new Button();
    boldButton.setCommand(new BoldCommand(textEditor));
    boldButton.setCommand(new ChangeColorCommand(textEditor));

    // Simulate button click
    boldButton.click();
  }
}
```

### Output

```
Text color changed
```

**Note:** Only the second command (ChangeColorCommand) is executed because setCommand() replaces the previous command.

---

## Code Flow

1. Create TextEditorCP receiver with formatting operations
2. Create Button invoker
3. Wrap BoldCommand around bold() operation
4. Set BoldCommand on button
5. Replace it with ChangeColorCommand (color change operation)
6. Button click invokes current command's execute()

---

## Advantages

✅ **Decoupling**: Invoker and receiver are decoupled  
✅ **Flexibility**: Change command behavior at runtime  
✅ **Queuing**: Commands can be queued and scheduled  
✅ **Undo/Redo**: Easy implementation for undo/redo functionality  
✅ **Logging**: Commands can be logged and replayed  
✅ **Macro Recording**: Support for recording command sequences  

---

## Disadvantages

❌ **Increased Classes**: Many command classes for many operations  
❌ **Memory Overhead**: Each command object consumes memory  
❌ **Complexity**: Extra layer of abstraction for simple requests  
❌ **Synchronization**: Queued commands need proper synchronization  

---

## Real-World Analogies

- **Restaurant**: Customer (invoker) orders food (command), chef (receiver) prepares it
- **Smart Home**: App button (invoker) sends commands to smart devices (receivers)
- **Video Game**: Controller buttons (invoker) trigger game actions (commands)
- **Transaction Management**: Database commands queued and executed in sequence
- **Macro Recording**: Video editors record user actions as command sequences

---
