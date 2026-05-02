package a5_StructuralPatterns.a2_DecoratorPattern.solution;

import a5_StructuralPatterns.a2_DecoratorPattern.solution.Pizza;

abstract class PizzaDecorator implements Pizza {

  protected Pizza decoratedPizza;

  public PizzaDecorator(Pizza decoratedPizza) {
    this.decoratedPizza = decoratedPizza;
  }

  @Override
  public String getDescription() {
    return decoratedPizza.getDescription();
  }

  @Override
  public double getCost() {
    return decoratedPizza.getCost();
  }
}
