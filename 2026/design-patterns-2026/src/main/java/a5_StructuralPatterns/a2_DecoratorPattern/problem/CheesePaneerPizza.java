package a5_StructuralPatterns.a2_DecoratorPattern.problem;

public class CheesePaneerPizza extends CheesePizza {
  @Override
  public String getDescription() {
    return super.getDescription() + " , paneer";
  }

  @Override
  public double getCost() {
    return super.getCost() + 2.5; // Adding cost of paneer
  }
}
