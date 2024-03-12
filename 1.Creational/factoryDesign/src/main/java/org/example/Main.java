package org.example;

interface Shape {
  void draw();
}

class Rectangle implements Shape {
  @Override
  public void draw() {
    System.out.println("Rectangle is drawn!");
  }
}

class Square implements Shape {

  @Override
  public void draw() {
    System.out.println("Square is drawn!");
  }
}

class Triangle implements Shape {

  @Override
  public void draw() {
    System.out.println("Triangle is drawn!");
  }
}

class ShapeFactory {
  public Shape getShapeInstance(String type) {
    if (type == null) {
      return null;
    } else if (type.equalsIgnoreCase("Rectangle")) {
      return new Rectangle();
    } else if (type.equalsIgnoreCase("Triangle")) {
      return new Triangle();
    } else if (type.equalsIgnoreCase("Square")) {
      return new Square();
    }
    return null;
  }
}

public class Main {
  public static void main(String[] args) {
    // Calling as default case - No design pattern followed
    // Class is exposed to client
    Shape triangle1 = new Triangle();
    triangle1.draw();
    Shape rec1 = new Rectangle();
    rec1.draw();
    Shape square1 = new Square();
    square1.draw();

    System.out.println("-------------------- Factory Design Pattern ------------------------");
    // Factory Design patten - no logic class is exposed directly
    ShapeFactory shapeFactory = new ShapeFactory();
    Shape triangle = shapeFactory.getShapeInstance("Triangle");
    triangle.draw();
    Shape rectangle = shapeFactory.getShapeInstance("Rectangle");
    rectangle.draw();
    Shape square = shapeFactory.getShapeInstance("Square");
    square.draw();
  }
}
