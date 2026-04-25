package org.basic1;

class Singleton {
  // 1. Create static obj
  static Singleton singletonObject;

  // 2. Make private Constructor - using this we can't create outside its own class using new
  private Singleton() {}

  // 3. Make method to access singleton object created
  public static Singleton getInstance() {
    if(singletonObject==null){
      singletonObject = new Singleton();
    }
    return singletonObject;
  }

  public void display() {
    System.out.println("Singleton object accessed");
  }
}

public class Main {
  public static void main(String[] args) {
    Singleton obj = Singleton.getInstance();
    obj.display();
  }
}
