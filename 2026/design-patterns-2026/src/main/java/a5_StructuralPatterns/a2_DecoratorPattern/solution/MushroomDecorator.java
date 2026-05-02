package a5_StructuralPatterns.a2_DecoratorPattern.solution;

public class MushroomDecorator extends PizzaDecorator {
  public MushroomDecorator(Pizza decoratedPizza) {
    super(decoratedPizza);
  }

  public String getDescription() {
    return decoratedPizza.getDescription() + ", mushrooms";
  }

  public double getCost() {
    return decoratedPizza.getCost() + 2.0;
  }
}
