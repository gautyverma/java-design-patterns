package org.example2;

import java.util.ArrayList;
import java.util.List;

/*
 Consider computer sytem - cabinet - MB,Ram, Peripheral -> mouse,keyboard
*/

// Component
interface Component {
  void showPrice();
}

class Leaf implements Component {
  private String name;
  private int price;

  public Leaf(String name, int price) {
    this.name = name;
    this.price = price;
  }

  @Override
  public void showPrice() {
    System.out.println(name + " : " + price);
  }
}

class Composite implements Component {
  private String name;
  private List<Component> components;

  public Composite(String name) {
    this.name = name;
    this.components = new ArrayList<>();
  }

  @Override
  public void showPrice() {
    System.out.println(name);
    for (Component com : components) {
      com.showPrice();
    }
  }

  public void addComponent(Component com) {
    components.add(com);
  }

  public void removeComponent(Component com) {
    components.remove(com);
  }
}

public class Main {
  public static void main(String[] args) {

    // Leaf - peripheral device
    Component mouse = new Leaf("mouse", 500);
    Component screen = new Leaf("screen", 8000);
    Component keyBoard = new Leaf("keyBoard", 900);

    Component ram = new Leaf("Ram", 5000);
    Component cpu = new Leaf("cpu", 11000);
    Component drive = new Leaf("HDD", 3500);

    // Composite items
    Composite peripheral = new Composite("peripheral");
    peripheral.addComponent(mouse);
    peripheral.addComponent(screen);
    peripheral.addComponent(keyBoard);

    Composite cabinet = new Composite("cabient");
    cabinet.addComponent(ram);
    cabinet.addComponent(cpu);
    cabinet.addComponent(drive);

    Composite computer = new Composite("computer");
    computer.addComponent(peripheral);
    computer.addComponent(cabinet);

    computer.showPrice();
  }
}
