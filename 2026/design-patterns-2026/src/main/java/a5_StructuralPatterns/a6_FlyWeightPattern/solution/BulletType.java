package a5_StructuralPatterns.a6_FlyWeightPattern.solution;

public class BulletType {
    private String color; // Intrinsic state

    public BulletType(String color) {
        this.color = color;
        System.out.println("Creating bullet type with color " + color);
    }

    public String getColor() {
        return color;
    }

}
