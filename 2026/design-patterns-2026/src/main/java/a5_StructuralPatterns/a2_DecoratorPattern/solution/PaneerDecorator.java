package a5_StructuralPatterns.a2_DecoratorPattern.solution;

public class PaneerDecorator extends PizzaDecorator {
  public PaneerDecorator(Pizza decoratedPizza) {
    super(decoratedPizza);
  }

  public String getDescription() {
    return decoratedPizza.getDescription() + ", paneer";
  }

  public double getCost() {
    return decoratedPizza.getCost() + 3.0;
  }
}
