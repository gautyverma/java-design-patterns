package a4_CreationalPatterns.a3_AbstractFactoryPattern.problem;

// Windows UI Factory
class WindowsButton {
  public void render() {
    System.out.println("Rendering Windows Button");
  }
}

class WindowsScrollBar {
  public void render() {
    System.out.println("Rendering Windows ScrollBar");
  }
}

// Mac UI Factory
class MacOSButton {
  public void render() {
    System.out.println("Rendering MacOS Button");
  }
}

class MacOSScrollBar {
  public void render() {
    System.out.println("Rendering MacOS ScrollBar");
  }
}

public class Application {
  public static void main(String[] args) {
    // windows UI
    WindowsButton winButton = new WindowsButton();
    WindowsScrollBar winScrollBar = new WindowsScrollBar();

    winButton.render();
    winScrollBar.render();

    // mac UI
    MacOSButton macButton = new MacOSButton();
    MacOSScrollBar macScrollBar = new MacOSScrollBar();

    macButton.render();
    macScrollBar.render();
  }
}
