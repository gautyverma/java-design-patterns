package a4_CreationalPatterns.a4_BuilderDesignPattern.solution;

public class WithBuilderPattern {
  public static void main(String[] args) {
    House house =
        new House.HouseBuilder("Concrete", "Brick", "Tile")
            .setHasGarage(true)
            .setHasSwimmingPool(true)
            .build(); // builder pattern, more readable and maintainable
    System.out.println(house);
  }
}
