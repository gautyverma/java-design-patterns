package a5_StructuralPatterns.a2_DecoratorPattern.problem;

public class BasicPizza implements Pizza {
  @Override
  public String getDescription() {
    return "Basic Pizza";
  }

  @Override
  public double getCost() {
    return 5;
  }
}
