package a3_BehavioralPatterns.a4_CommandPattern;

class TextEditor {
  public void bold() {
    System.out.println("Text is bolded");
  }

  public void italic() {
    System.out.println("Text is italicized");
  }

  public void underline() {
    System.out.println("Text is underlined");
  }
}

// UI Buttons
class BoldButton {
  private TextEditor textEditor;

  public BoldButton(TextEditor textEditor) {
    this.textEditor = textEditor;
  }

  public void click() {
    textEditor.bold();
  }
}

class ItalicButton {
  private TextEditor textEditor;

  public ItalicButton(TextEditor textEditor) {
    this.textEditor = textEditor;
  }

  public void click() {
    textEditor.italic();
  }
}

public class TextEditorWIthoutCommandPattern {
  public static void main(String[] args){
    TextEditor textEditor = new TextEditor();
    // Create buttons and associate them with the text editor : Tightly coupled
    BoldButton boldButton = new BoldButton(textEditor);
    ItalicButton italicButton = new ItalicButton(textEditor);

    // User clicks buttons
    boldButton.click(); // Output: Text is bolded
    italicButton.click(); // Output: Text is italicized
  }
}
