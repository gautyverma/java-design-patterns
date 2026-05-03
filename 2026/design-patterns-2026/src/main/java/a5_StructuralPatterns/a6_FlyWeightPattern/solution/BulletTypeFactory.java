package a5_StructuralPatterns.a6_FlyWeightPattern.solution;

import java.util.HashMap;

public class BulletTypeFactory {

    private static final HashMap<String, BulletType> bulletTypes = new HashMap<>();

    public static BulletType getBulletType(String color) {
        BulletType type = bulletTypes.get(color);
        if (type == null) {
            type = new BulletType(color);
            bulletTypes.put(color, type);
        }
        return type;
    }
}
