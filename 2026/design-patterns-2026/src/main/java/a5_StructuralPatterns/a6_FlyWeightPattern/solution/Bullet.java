package a5_StructuralPatterns.a6_FlyWeightPattern.solution;

public class Bullet {
    private BulletType type; // Intrinsic state
    private int x,y; // Extrinsic state
    private int velocity;

    public Bullet(String color, int x, int y, int velocity) {
        this.type = BulletTypeFactory.getBulletType(color);
        this.x = x;
        this.y = y;
        this.velocity = velocity;
    System.out.println("Creating bullet at (" + x + ", " + y + ") with velocity " + velocity);
    }

    public void display() {
        System.out.println("Bullet at (" + x + ", " + y + ") moving  with velocity " + velocity);
    }
}
