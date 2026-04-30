package a4_CreationalPatterns.a3_AbstractFactoryPattern.solution;

// Abstract Product interface
interface Button {
  void render();
}

interface ScrollBar {
  void scroll();
}

// Windows UI Factory
class WindowsButton implements Button {
  public void render() {
    System.out.println("Rendering Windows Button");
  }
}

class WindowsScrollBar implements ScrollBar {
  public void scroll() {
    System.out.println("Rendering Windows ScrollBar");
  }
}

// Mac UI Factory
class MacOSButton implements Button {
  public void render() {
    System.out.println("Rendering MacOS Button");
  }
}

class MacOSScrollBar implements ScrollBar {
  public void scroll() {
    System.out.println("Rendering MacOS ScrollBar");
  }
}

// Abstract Factory interface
interface UIFactory {
  Button createButton();

  ScrollBar createScrollBar();
}

// Windows Factory
class WindowsFactory implements UIFactory {

  @Override
  public Button createButton() {
    return new WindowsButton();
  }

  @Override
  public ScrollBar createScrollBar() {
    return new WindowsScrollBar();
  }
}

// Mac Factory
class MacOSFactory implements UIFactory {
  @Override
  public Button createButton() {
    return new MacOSButton();
  }

  @Override
  public ScrollBar createScrollBar() {
    return new MacOSScrollBar();
  }
}

public class Application {
  private Button button;
  private ScrollBar scrollBar;

  public Application(UIFactory factory) {
    this.button = factory.createButton();
    this.scrollBar = factory.createScrollBar();
  }

  public void renderUI() {
    button.render();
    scrollBar.scroll();
  }

  public static void main(String[] args) {
    // Use Windows UI
    UIFactory windowsFactory = new WindowsFactory();
    Application windowsApp = new Application(windowsFactory);
    windowsApp.renderUI();

    // Use MacOS UI
    UIFactory macFactory = new MacOSFactory();
    Application macApp = new Application(macFactory);
    macApp.renderUI();
  }
}
