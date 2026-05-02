package a5_StructuralPatterns.a2_DecoratorPattern.problem;

public class CheesePizza extends BasicPizza {
  @Override
  public String getDescription() {
    return super.getDescription() + " , cheese";
  }

  @Override
  public double getCost() {
    return super.getCost() + 2.0; // Adding cost of cheese
  }
}
