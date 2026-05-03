package a5_StructuralPatterns.a6_FlyWeightPattern.solution;

public class Game {
  public static void main(String[] args) {
    // 5- Red Simulate creating multiple bullets in a game
    for (int i = 0; i < 5; i++) {
      Bullet bullet = new Bullet("red", i * 10, i * 10, 100);
      bullet.display();
    }

    System.out.println("---------------------------------------------");
    // 5- Blue Simulate creating multiple bullets in a game
    for (int i = 0; i < 5; i++) {
      Bullet bullet = new Bullet("blue", i * 10, i * 12, 200);
      bullet.display();
    }
  }
}
