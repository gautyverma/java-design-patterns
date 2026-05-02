package a5_StructuralPatterns.a2_DecoratorPattern.solution;

public class PizzaApp {
  public static void main(String[] args) {
    Pizza pizza = new BasicPizza();

    // Add Cheese
    pizza = new CheeseDecorator(pizza);

    // Add paneer
    pizza = new PaneerDecorator(pizza);

    // Add Mushroom
    pizza = new MushroomDecorator(pizza);

    System.out.println("Description: " + pizza.getDescription());
    System.out.println("Cost: " + pizza.getCost());
  }
}
