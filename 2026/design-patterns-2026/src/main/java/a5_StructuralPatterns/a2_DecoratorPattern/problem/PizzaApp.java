package a5_StructuralPatterns.a2_DecoratorPattern.problem;

public class PizzaApp {
  public static void main(String[] args) {
    Pizza pizza = new CheesePaneerPizza();
    System.out.println(pizza.getDescription() + " with costs $ " + pizza.getCost());
  }
}
