# Flyweight Design Pattern

## Overview

The **Flyweight Pattern** is a structural design pattern that uses sharing to support large numbers of fine-grained objects efficiently by sharing common state. It minimizes memory usage by sharing data between multiple objects when those objects have immutable parts. The pattern separates data into intrinsic state (shared) and extrinsic state (unique per object).

---

## Why Use Flyweight Pattern?

- **Memory Optimization**: Drastically reduce memory consumption for many similar objects
- **Performance**: Improve performance in memory-constrained environments
- **Resource Sharing**: Share immutable state among many objects
- **Scalability**: Support thousands/millions of objects efficiently
- **Reduced Object Creation**: Create fewer actual objects
- **Cache Reuse**: Reuse cached objects instead of creating new ones
- **Large-Scale Applications**: Essential for games, simulations, graphics
- **Efficient Collections**: Store large collections efficiently

---

## Use Cases

- Game development (bullets, particles, enemies, trees in forest)
- Text editors (character formatting, font objects)
- Graphics systems (shapes, colors, textures)
- Web browsers (font caches, icon caches)
- Connection pooling in databases
- Thread pools in concurrent applications
- Particle systems in games/simulations
- Cache management systems
- String interning in Java
- Icon/image caches in UI applications

---

## Key Components

| Component | Responsibility |
|-----------|-----------------|
| **Flyweight Interface** | Defines interface for flyweight objects |
| **ConcreteFlyweight** | Implements interface, stores intrinsic state |
| **FlyweightFactory** | Creates and manages flyweight pool |
| **Client** | Works with flyweights, provides extrinsic state |

---

## Intrinsic vs Extrinsic State

| Aspect | Intrinsic State | Extrinsic State |
|--------|-----------------|-----------------|
| **Definition** | Shared, immutable data | Unique, context-dependent data |
| **Storage** | Stored in flyweight | Stored in client/context |
| **Shared** | Yes (across multiple objects) | No (unique per instance) |
| **Changeable** | No (immutable) | Yes (can change) |
| **Memory** | Saved by sharing | Not shared |
| **Example (Bullet)** | Color (shared) | Position, velocity (unique) |

---

## Implementation

### 1. Flyweight Pattern with Game Bullets

**Flyweight Interface & Implementation - BulletType.java:**

```java
// Flyweight - stores intrinsic (shared) state
public class BulletType {
  private String color;  // Intrinsic state - shared
  
  public BulletType(String color) {
    this.color = color;
    System.out.println("Creating bullet type with color " + color);
  }
  
  public String getColor() {
    return color;
  }
}
```

**Flyweight Factory - BulletTypeFactory.java:**

```java
// Factory that manages the flyweight pool
public class BulletTypeFactory {
  // Pool of shared flyweight objects
  private static final HashMap<String, BulletType> bulletTypes = new HashMap<>();
  
  public static BulletType getBulletType(String color) {
    BulletType type = bulletTypes.get(color);
    
    // Return existing flyweight if found
    if (type == null) {
      // Create new flyweight only if not found
      type = new BulletType(color);
      bulletTypes.put(color, type);
    }
    return type;
  }
}
```

**Context/Client - Bullet.java:**

```java
// Client that holds extrinsic state and references flyweight
public class Bullet {
  private BulletType type;  // Reference to shared flyweight
  private int x, y;         // Extrinsic state - unique per bullet
  private int velocity;     // Extrinsic state - unique per bullet
  
  public Bullet(String color, int x, int y, int velocity) {
    // Get shared flyweight from factory
    this.type = BulletTypeFactory.getBulletType(color);
    // Store unique extrinsic state
    this.x = x;
    this.y = y;
    this.velocity = velocity;
    System.out.println("Creating bullet at (" + x + ", " + y + 
                       ") with velocity " + velocity);
  }
  
  public void display() {
    // Use both intrinsic (from type) and extrinsic state
    System.out.println("Bullet (" + type.getColor() + ") at (" + 
                       x + ", " + y + ") moving with velocity " + velocity);
  }
}
```

**Key Points:**
- BulletType stores only immutable intrinsic state (color)
- Factory maintains pool of BulletType objects
- Bullet stores references to shared flyweight + unique extrinsic state
- Multiple bullets can share same BulletType object
- Significant memory savings with many objects

---

## Flyweight Variants

### **Approach 1: Simple Flyweight (Shown Above)**
- Pros: Simple to understand and implement
- Cons: Manual extrinsic state management
- Best for: Straightforward shared state scenarios

### **Approach 2: Generic Flyweight Factory**
```java
public class FlyweightFactory<T> {
  private Map<String, T> pool = new HashMap<>();
  
  public T getFlyweight(String key, FlyweightCreator<T> creator) {
    if (!pool.containsKey(key)) {
      pool.put(key, creator.create(key));
    }
    return pool.get(key);
  }
  
  @FunctionalInterface
  public interface FlyweightCreator<T> {
    T create(String key);
  }
}
```

### **Approach 3: With Reference Counting**
```java
public class CountedFlyweight {
  private int referenceCount = 0;
  
  public void incrementReference() {
    referenceCount++;
  }
  
  public void decrementReference() {
    referenceCount--;
    if (referenceCount <= 0) {
      // Can be removed from pool
    }
  }
}
```

### **Approach 4: Thread-Safe Flyweight Factory**
```java
public class ThreadSafeFlyweightFactory {
  private static final Map<String, BulletType> pool = 
    Collections.synchronizedMap(new HashMap<>());
  
  public static synchronized BulletType getBulletType(String color) {
    return pool.computeIfAbsent(color, k -> new BulletType(k));
  }
}
```

---

## Usage Example

**Game.java** - Demonstrates flyweight pattern:

```java
public class Game {
  public static void main(String[] args) {
    
    System.out.println("Creating Red Bullets:");
    // 5 red bullets - all reuse same BulletType("red")
    for (int i = 0; i < 5; i++) {
      Bullet bullet = new Bullet("red", i * 10, i * 10, 100);
      bullet.display();
    }
    
    System.out.println("\n---------------------------------------------\n");
    
    System.out.println("Creating Blue Bullets:");
    // 5 blue bullets - all reuse same BulletType("blue")
    for (int i = 0; i < 5; i++) {
      Bullet bullet = new Bullet("blue", i * 10, i * 12, 200);
      bullet.display();
    }
    
    System.out.println("\n---------------------------------------------\n");
    
    System.out.println("Creating Red Bullets Again:");
    // 5 more red bullets - reuse existing BulletType("red")
    for (int i = 0; i < 5; i++) {
      Bullet bullet = new Bullet("red", i * 20, i * 20, 150);
      bullet.display();
    }
  }
}
```

### Output

```
Creating Red Bullets:
Creating bullet type with color red
Creating bullet at (0, 0) with velocity 100
Bullet (red) at (0, 0) moving with velocity 100
Creating bullet at (10, 10) with velocity 100
Bullet (red) at (10, 10) moving with velocity 100
Creating bullet at (20, 20) with velocity 100
Bullet (red) at (20, 20) moving with velocity 100
... more red bullets without creating new type ...

---------------------------------------------

Creating Blue Bullets:
Creating bullet type with color blue
Creating bullet at (0, 0) with velocity 200
Bullet (blue) at (0, 0) moving with velocity 200
... blue bullets reusing shared BulletType ...

---------------------------------------------

Creating Red Bullets Again:
Creating bullet at (0, 0) with velocity 150
Bullet (red) at (0, 0) moving with velocity 150
... red bullets reusing existing BulletType without creating new one ...
```

---

## Code Flow

1. Client requests flyweight with key (e.g., "red")
2. Factory checks if flyweight already exists in pool
3. If exists, return existing flyweight from pool
4. If not, factory creates new flyweight and stores in pool
5. Client combines flyweight with extrinsic state
6. Multiple clients share same flyweight object
7. Massive memory savings with thousands of objects

### Memory Footprint Visualization

**Without Flyweight (1000 red bullets):**
```
1000 Bullet objects × (color string "red" + x + y + velocity)
= 1000 copies of "red" string + position + velocity
≈ Very large memory usage
```

**With Flyweight (1000 red bullets):**
```
1000 Bullet objects × (reference to shared BulletType + x + y + velocity)
+ 1 shared BulletType("red")
= Much smaller memory usage
```

---

## Flyweight vs Related Patterns

| Aspect | Flyweight | Object Pool | Cache | Singleton |
|--------|-----------|-------------|-------|-----------|
| **Purpose** | Share intrinsic state | Reuse objects | Store results | Single instance |
| **Objects** | Many shared | Reusable | Results only | One |
| **State** | Intrinsic/Extrinsic | Stateful | Cached data | Single state |
| **Sharing** | Automatic via factory | Manual checkout | Key-based | N/A |
| **Memory** | Minimal | Moderate | Selective | Minimal |

---

## Advantages

✅ **Massive Memory Savings**: Dramatically reduce memory for many similar objects  
✅ **Performance**: Fewer object allocations means better performance  
✅ **Scalability**: Handle large numbers of objects efficiently  
✅ **Sharing**: Shared state reduces redundancy  
✅ **GC Pressure**: Fewer objects means less garbage collection  
✅ **Cache Efficiency**: Better CPU cache utilization  
✅ **Support Scale**: Enable large-scale applications  
✅ **Real-world**: Matches natural object models  

---

## Disadvantages

❌ **Complexity**: More complex than simple object creation  
❌ **Thread Safety**: Requires synchronization if multi-threaded  
❌ **CPU Cost**: Lookup overhead in factory  
❌ **Maintenance**: Must carefully separate intrinsic/extrinsic state  
❌ **Debugging**: Shared state harder to trace  
❌ **Mutation Issues**: Intrinsic state must never change  
❌ **Learning Curve**: Developers need to understand pattern  
❌ **Cache Invalidation**: Removing items from pool is tricky  

---

## Real-World Applications

| Application | Flyweight | Pool Size Reduction | Example |
|-------------|-----------|-------------------|---------|
| **Game Bullets** | BulletType/Color | 100x | 10K bullets, 100 colors |
| **Text Editor** | Character/Font | 50x | 1M characters, 20 fonts |
| **Browser** | Icon/Image | 10x | Thousands of pages, icons reused |
| **Forest Scene** | Tree Type | 100x | 1M trees, 10 types |
| **Database Pool** | Connection | 10x | 10 actual, 100 logical |
| **Thread Pool** | Worker Thread | 20x | 20 workers, 400 tasks |
| **String Interning** | String Object | 100x | Common strings cached |
| **Particle System** | Particle Type | 50x | 100K particles, 20 types |

---

## When to Use Flyweight Pattern

**Use Flyweight when:**
- ✅ Application creates many similar objects
- ✅ Memory usage is significant concern
- ✅ Objects have intrinsic (immutable) state that can be shared
- ✅ Object creation is expensive
- ✅ Extrinsic state can be separated from intrinsic
- ✅ Many objects have same intrinsic values
- ✅ Application performance is memory-bound
- ✅ Garbage collection overhead is high

**Don't use when:**
- ❌ Few objects needed (overkill)
- ❌ Immutable state cannot be identified
- ❌ Intrinsic state changes frequently
- ❌ Factory overhead exceeds savings
- ❌ Simplicity is more important than performance
- ❌ Thread safety overhead too high
- ❌ Objects need independent identity

---

## Implementation Guidelines

### **Separating Intrinsic and Extrinsic State**

**Good Example (Bullet):**
```java
// Intrinsic (shared, immutable)
color: "red"           // Same for all red bullets

// Extrinsic (unique per bullet)
position: (100, 200)   // Differs for each bullet
velocity: 50           // Differs for each bullet
```

**Bad Example (Don't do this):**
```java
// Don't store unique data as intrinsic
// This defeats the purpose of flyweight
color: "red"
position: (100, 200)   // This is extrinsic, not intrinsic!
velocity: 50
```

### **Best Practices**

1. **Identify Shared State**: Clearly identify what can be shared
2. **Make Immutable**: Ensure intrinsic state never changes
3. **Factory Pattern**: Always use factory to manage pool
4. **Document State**: Document which state is intrinsic
5. **Avoid Modification**: Never modify intrinsic state
6. **Thread Safety**: Synchronize factory if multi-threaded
7. **Test Sharing**: Verify objects are actually shared
8. **Monitor Pool**: Track pool size and performance

---

## Intrinsic State Examples

| Scenario | Shared (Intrinsic) | Unique (Extrinsic) |
|----------|-------------------|--------------------|
| **Game Bullets** | Color, damage, type | Position, velocity |
| **Text Editor** | Font, size, color | Position in text |
| **Graphics** | Texture, material | Transform, scale |
| **UI Buttons** | Style, icon, label | Position, state |
| **Database Connections** | Driver, credentials | Current query |

---

## Common Pitfalls to Avoid

❌ **Mutable Intrinsic State**: Intrinsic state must be immutable  
❌ **Unclear Separation**: Don't mix intrinsic and extrinsic  
❌ **Missing Factory**: Always use factory, never share manually  
❌ **No Thread Safety**: Synchronize factory in multi-threaded apps  
❌ **Pool Congestion**: Don't create unlimited flyweights  
❌ **Performance Assumption**: Measure actual gains  
❌ **Over-Engineering**: Don't use for simple scenarios  
❌ **Cache Invalidation**: Plan removal strategy carefully  

---

## String Interning as Flyweight

Java's String class uses Flyweight principles:

```java
String s1 = "Hello";
String s2 = "Hello";
String s3 = new String("Hello");
String s4 = s3.intern();

s1 == s2;  // true - same reference (flyweight)
s1 == s3;  // false - different objects
s1 == s4;  // true - intern() gets flyweight from pool
```

---

## Performance Comparison

**Without Flyweight:**
```
1,000,000 bullets × 100 bytes each = 100 MB
```

**With Flyweight:**
```
1,000,000 bullets × 20 bytes each + 100 types × 50 bytes = 20 MB + 5 KB
≈ 20 MB (80% reduction!)
```

---

## Real-World Analogy

**Movie Theater Seats:**
- **Without Flyweight**: Each seat is full object with all properties
- **With Flyweight**: Seats share same type object (color, material)
- **Extrinsic**: Seat location (row, number)
- **Intrinsic**: Seat type (color, material - shared)
- **Result**: Save memory by sharing seat type across 1000s of seats!

---

## Conclusion

The Flyweight Pattern is essential for applications dealing with large numbers of similar objects. By sharing immutable state and storing unique state externally, it achieves dramatic memory savings. The game bullet example perfectly demonstrates how thousands of bullets can be created efficiently by reusing bullet types.

**Remember**: The key to Flyweight is correctly identifying and sharing intrinsic state while maintaining clear separation from extrinsic state. Use it when you need to create many similar objects in memory-constrained or performance-critical scenarios!

