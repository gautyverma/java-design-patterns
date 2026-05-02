package a5_StructuralPatterns.a2_DecoratorPattern.solution;

public class CheeseDecorator extends PizzaDecorator{
    public CheeseDecorator(Pizza pizza) {
        super(pizza);
    }

    public String getDescription() {
        return decoratedPizza.getDescription() + ", cheese";
    }

    public double getCost() {
        return decoratedPizza.getCost() + 1.5;
    }
}
