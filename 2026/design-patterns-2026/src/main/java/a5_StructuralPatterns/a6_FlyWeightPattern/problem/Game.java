package a5_StructuralPatterns.a6_FlyWeightPattern.problem;

public class Game {
  public static void main(String[] args) {
    // 5- Red Simulate creating multiple bullets in a game
    for (int i = 0; i < 5; i++) {
      Bullet bullet = new Bullet("red", i * 10, i * 10, 100);
      bullet.display();
    }

    // 5- Blue Simulate creating multiple bullets in a game
    for (int i = 0; i < 5; i++) {
      Bullet bullet = new Bullet("blue", i * 10, i * 12, 200);
      bullet.display();
    }

    // problem:
    // 1. Memory Usage: Each bullet is a separate object, which can consume a lot of memory if there
    // are many bullets in the game.
    // 2. Performance: Creating and managing a large number of bullet objects can lead to
    // performance issues, especially if the game needs to create and destroy bullets frequently.
  }
}
