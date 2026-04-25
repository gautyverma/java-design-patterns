package a1_Basics.a2_UML;

public class a4_Inheritance {
  public static void main(String[] args) {
    Dog dog = new Dog();
    dog.eat(); // Inherited method from Animal
    dog.bark(); // Dog's own method
  }
}

class Animal {
  void eat() {
    System.out.println("Animal is eating");
  }
}

class Dog extends Animal {
  void bark() {
    System.out.println("Dog is barking");
  }
}
