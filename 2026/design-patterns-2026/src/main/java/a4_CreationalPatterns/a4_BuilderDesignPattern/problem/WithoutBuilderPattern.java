package a4_CreationalPatterns.a4_BuilderDesignPattern.problem;

public class WithoutBuilderPattern {
  public static void main(String[] args) {
    House house =
        new House(
            "Concrete",
            "Brick",
            "Tile",
            true,
            false,
            true); // constructor with many parameters, hard to read and maintain
    //        House house1 = new House( "Wood","Wood","Single");
    // Problem: The constructor has too many parameters, making it difficult to read and maintain
    // constructor -> explosion
    System.out.println(house);
  }
}
