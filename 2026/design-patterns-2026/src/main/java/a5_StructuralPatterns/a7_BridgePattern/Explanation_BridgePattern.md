# Bridge Design Pattern

## Overview

The **Bridge Pattern** is a structural design pattern that decouples an abstraction from its implementation so that the two can vary independently. It bridges the gap between abstract and concrete by introducing an interface (bridge) between them, allowing either side to change without affecting the other.

---

## Why Use Bridge Pattern?

- **Decouple Abstraction**: Separate abstract concepts from implementations
- **Independent Variation**: Change abstraction and implementation independently
- **Avoid Subclass Explosion**: Prevent combinatorial class hierarchy explosion
- **Runtime Selection**: Choose implementation at runtime
- **Multiple Implementations**: Support multiple implementations of same abstraction
- **Share Implementation**: Multiple abstractions can share same implementation
- **Clean Separation**: Clear separation of concerns
- **Flexible Design**: Easy to extend both sides

---

## Use Cases

- Device drivers (display drivers for different devices)
- Graphics systems (shapes with different rendering engines)
- Database access layers (multiple database implementations)
- Communication protocols (multiple transport implementations)
- Payment systems (multiple payment gateways)
- Logging frameworks (multiple logging implementations)
- UI themes (multiple theme implementations)
- Remote access (multiple connection types)

---

## Key Components

| Component | Responsibility |
|-----------|-----------------|
| **Abstraction** | Defines high-level interface |
| **RefinedAbstraction** | Extends abstraction with specific functionality |
| **Implementor Interface** | Defines implementation interface |
| **ConcreteImplementor** | Provides specific implementation |
| **Client** | Works with abstraction |

---

## The Problem: Subclass Explosion

**Without Bridge Pattern (Causes Class Explosion):**
```
Shape
├── Circle
│   ├── CircleRaster
│   └── CircleVector
├── Square
│   ├── SquareRaster
│   └── SquareVector
└── ... more combinations
```

**With Bridge Pattern (Clean Design):**
```
Shape (uses Renderer)
├── Circle
└── Square

Renderer
├── RasterRenderer
└── VectorRenderer
```

---

## Implementation

### 1. Bridge Pattern with Shape Rendering System

**Implementor Interface - Renderer.java:**

```java
// Implementation interface (varies independently)
public interface Renderer {
  void renderCircle(double radius);
  void renderSquare(double side);
}
```

**Concrete Implementor 1 - RasterRenderer.java:**

```java
// Specific implementation 1
public class RasterRenderer implements Renderer {
  @Override
  public void renderCircle(double radius) {
    System.out.println("Drawing circle with radius " + radius + " using raster");
  }
  
  @Override
  public void renderSquare(double side) {
    System.out.println("Drawing square with side " + side + " using raster");
  }
}
```

**Concrete Implementor 2 - VectorRenderer.java:**

```java
// Specific implementation 2
public class VectorRenderer implements Renderer {
  @Override
  public void renderCircle(double radius) {
    System.out.println("Drawing circle with radius " + radius + " using vector");
  }
  
  @Override
  public void renderSquare(double side) {
    System.out.println("Drawing square with side " + side + " using vector");
  }
}
```

**Abstraction - Shape.java:**

```java
// Abstraction that uses implementor (bridge)
public abstract class Shape {
  protected Renderer renderer;  // Bridge to implementation
  
  public Shape(Renderer renderer) {
    this.renderer = renderer;
  }
  
  public abstract void draw();
}
```

**Refined Abstraction 1 - Circle.java:**

```java
// Specific abstraction 1
public class Circle extends Shape {
  private double radius;
  
  public Circle(Renderer renderer, double radius) {
    super(renderer);
    this.radius = radius;
  }
  
  @Override
  public void draw() {
    renderer.renderCircle(radius);
  }
}
```

**Refined Abstraction 2 - Square.java:**

```java
// Specific abstraction 2
public class Square extends Shape {
  private double side;
  
  public Square(Renderer renderer, double side) {
    super(renderer);
    this.side = side;
  }
  
  @Override
  public void draw() {
    renderer.renderSquare(side);
  }
}
```

**Key Points:**
- Renderer interface (bridge) separates abstraction from implementation
- Shape abstract class uses bridge to delegate rendering
- Circle and Square are different abstractions
- RasterRenderer and VectorRenderer are different implementations
- Can combine any abstraction with any implementation
- Changes to one side don't affect the other

---

## Bridge Variants

### **Approach 1: Simple Bridge (Shown Above)**
- Pros: Clear separation, easy to understand
- Cons: Requires multiple classes
- Best for: Clear abstraction/implementation separation

### **Approach 2: Abstract Bridge**
```java
public abstract class Bridge {
  protected Implementor implementor;
  
  public abstract void operation();
}

public class ConcreteBridge extends Bridge {
  @Override
  public void operation() {
    implementor.implementationOperation();
  }
}
```

### **Approach 3: Bridge with Factory**
```java
public class BridgeFactory {
  public static Shape createShape(ShapeType type, RendererType renderer) {
    Renderer impl = RendererFactory.createRenderer(renderer);
    switch(type) {
      case CIRCLE: return new Circle(impl, 5);
      case SQUARE: return new Square(impl, 10);
      default: return null;
    }
  }
}
```

### **Approach 4: Dynamic Bridge**
```java
public class Shape {
  private Renderer renderer;
  
  public void setRenderer(Renderer renderer) {
    this.renderer = renderer;  // Change implementation at runtime
  }
  
  public void draw() {
    renderer.render();
  }
}
```

---

## Usage Example

```java
public class DrawingApp {
  public static void main(String[] args) {
    
    // Create different implementations
    Renderer rasterRenderer = new RasterRenderer();
    Renderer vectorRenderer = new VectorRenderer();
    
    // Bridge: Same abstraction, different implementations
    Shape rasterCircle = new Circle(rasterRenderer, 5);
    Shape vectorCircle = new Circle(vectorRenderer, 5);
    
    Shape rasterSquare = new Square(rasterRenderer, 10);
    Shape vectorSquare = new Square(vectorRenderer, 10);
    
    // Draw using different implementations
    rasterCircle.draw();  // Output: Drawing circle ... using raster
    vectorCircle.draw();  // Output: Drawing circle ... using vector
    
    rasterSquare.draw();  // Output: Drawing square ... using raster
    vectorSquare.draw();  // Output: Drawing square ... using vector
  }
}
```

---

## Code Flow

1. Client creates abstraction with specific implementation
2. Abstraction stores reference to implementation (bridge)
3. Client calls operation on abstraction
4. Abstraction delegates to implementation through bridge
5. Implementation performs specific operation
6. Result returned to client through abstraction

### Interaction Structure

```
Client
  ↓
Shape (Abstraction) uses→ Renderer (Implementor)
├── Circle           ├── RasterRenderer
└── Square           └── VectorRenderer
```

---

## Bridge vs Adapter Pattern

| Aspect | Bridge | Adapter |
|--------|--------|---------|
| **Purpose** | Decouple abstraction/impl | Make incompatible compatible |
| **Design Intent** | To vary independently | To make existing work together |
| **Time** | Designed before implementation | Applied after the fact |
| **Structure** | Two hierarchies | Wraps single object |
| **Flexibility** | High - vary both sides | Medium - adapts one side |

---

## Bridge vs Strategy Pattern

| Aspect | Bridge | Strategy |
|--------|--------|----------|
| **Purpose** | Decouple abstraction/impl | Vary algorithm/strategy |
| **Timing** | At design time | At runtime |
| **Flexibility** | Both sides vary | One side varies |
| **Complexity** | More complex | Simpler |
| **When** | Prevent subclass explosion | Interchangeable algorithms |

---

## Advantages

✅ **Decouple Abstraction**: Clear separation between abstract and concrete  
✅ **Independent Variation**: Both sides can change independently  
✅ **Avoid Explosion**: Prevent exponential class hierarchy  
✅ **Runtime Selection**: Choose implementation at runtime  
✅ **Flexibility**: Easy to add new abstractions or implementations  
✅ **Reusability**: Implementations can be reused across abstractions  
✅ **Modularity**: Clean module boundaries  
✅ **Open/Closed Principle**: Open for extension, closed for modification  

---

## Disadvantages

❌ **Complexity**: More classes and indirection needed  
❌ **Performance**: Extra indirection layer adds overhead  
❌ **Overkill**: Unnecessary for simple cases  
❌ **Learning Curve**: Developers need to understand pattern  
❌ **Maintenance**: More code to maintain  
❌ **Debugging**: Extra layers complicate debugging  
❌ **Overhead**: May be premature optimization  

---

## Real-World Applications

| System | Abstraction | Implementor | Benefit |
|--------|------------|-------------|---------|
| **Graphics** | Shape | Renderer | Multi-renderer support |
| **Databases** | Query | Connection | Multiple databases |
| **UI Themes** | Theme | Renderer | Multiple themes |
| **Devices** | Device | Driver | Multiple drivers |
| **Payments** | PaymentService | Gateway | Multiple payment systems |
| **Logging** | Logger | Output | Multiple output targets |
| **Communication** | Messenger | Transport | Multiple protocols |
| **Rendering** | Document | Formatter | Multiple output formats |

---

## When to Use Bridge Pattern

**Use Bridge when:**
- ✅ Want to avoid permanent binding between abstraction and implementation
- ✅ Changes to implementation shouldn't affect clients
- ✅ Different implementations exist for abstraction
- ✅ Wish to share abstraction across multiple implementations
- ✅ Subclass explosion problem exists
- ✅ Variation exists on both abstraction and implementation
- ✅ Object composition is preferred over inheritance

**Don't use when:**
- ❌ No clear abstraction/implementation separation
- ❌ Only one implementation exists
- ❌ Simple inheritance works fine
- ❌ Adding unnecessary complexity
- ❌ Performance overhead matters greatly

---

## Common Implementation Scenarios

### **Scenario 1: Graphics Rendering**
```
Shape (abstraction)
├── Circle
└── Rectangle

GraphicsAPI (implementor)
├── OpenGL
└── DirectX
```

### **Scenario 2: Database Access**
```
DataAccess (abstraction)
├── UserAccess
└── ProductAccess

Database (implementor)
├── MySQL
└── MongoDB
```

### **Scenario 3: Remote Control**
```
RemoteControl (abstraction)
├── TVRemote
└── ACRemote

Device (implementor)
├── TV
└── AirConditioner
```

---

## Real-World Analogy

**Video Player Hardware**
- **Abstraction**: Video Player interface (Play, Pause, Stop)
- **Refined Abstraction**: YouTube Player, Netflix Player
- **Implementor**: Codec interface (H.264, VP9, AV1)
- **Concrete Implementor**: Different codec implementations
- **Bridge**: Player uses codec independently

The player doesn't care which codec, codec doesn't care which player!

---

## Conclusion

The Bridge Pattern is powerful for scenarios where you need to decouple abstraction from implementation. It solves the subclass explosion problem and allows both sides to vary independently, making systems more flexible and maintainable. Use it when you have multiple abstractions and multiple implementations that need to work together.

**Remember**: Bridge is about building two orthogonal (independent) hierarchies. If you don't need independent variation on both sides, Bridge adds unnecessary complexity!

