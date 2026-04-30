package a3_BehavioralPatterns.a4_CommandPattern;

interface Command {
  void execute();
}

// Concrete class for the Command interface
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

// Button class that will trigger the command
class Button {
  private Command command;

  public void setCommand(Command command) {
    this.command = command;
  }

  public void click() {
    command.execute();
  }
}

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
