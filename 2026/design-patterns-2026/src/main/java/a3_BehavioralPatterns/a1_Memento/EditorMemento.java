package a3_BehavioralPatterns.a1_Memento;

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
