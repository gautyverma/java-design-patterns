package a3_BehavioralPatterns.a1_Memento;

/**
 * A text editor that allows users to undo changes such as text addition,deletion or formating . The
 * text editor maintains a history of changes using the Memento design pattern, allowing users to
 * revert to previous states of the document.
 */
public class TextEditor {

  private String content;

  public void write(String text) {
    this.content = text;
  }

  public String getContent() {
    return content;
  }
}
