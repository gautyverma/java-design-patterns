# Prototype Design Pattern

## Overview

The **Prototype Pattern** is a creational design pattern that creates new objects by copying an existing object (prototype) rather than creating from scratch. It allows you to clone objects, enabling fast object creation when instantiation is expensive or complex. It's based on the principle of cloning an existing object to get a new instance efficiently.

---

## Why Use Prototype Pattern?

- **Performance**: Faster than creating objects from scratch (especially for complex objects)
- **Deep Cloning**: Create independent copies without inheritance complications
- **Lazy Initialization**: Defer expensive initialization to later cloning
- **Circular Reference Handling**: Clone objects with complex relationships
- **Reduce Subclassing**: Avoid creating multiple subclasses for variations
- **Dynamic Object Cloning**: Create objects dynamically at runtime
- **System Independence**: Clone objects without knowing their concrete classes
- **State Snapshots**: Easy checkpoint/rollback mechanisms

---

## Use Cases

- Graphics objects cloning (shapes, images, drawings)
- Document cloning (templates, reports)
- Game objects duplication (enemies, power-ups)
- Configuration cloning with modifications
- UI component cloning
- Data entity cloning for versioning
- Cache implementations
- Undo/Redo functionality
- Database connection pool cloning
- Thread pool creation from prototype

---

## Key Components

| Component | Responsibility |
|-----------|-----------------|
| **Prototype Interface** | Declares clone method (e.g., Cloneable) |
| **ConcretePrototype** | Implements clone method to create copies |
| **Client** | Creates objects by cloning existing prototypes |
| **PrototypeRegistry** (Optional) | Manages and provides prototypes |

---

## Implementation

### 1. Basic Prototype Pattern (Shallow Copy)

**Prototype Interface:**

```java
// Using Java's Cloneable interface
interface GamePiece extends Cloneable {
  GamePiece clone();
  void display();
}
```

**ConcretePrototype - GamePiece.java:**

```java
class GamePiece implements Cloneable {
  private String color;
  private int id;
  
  public GamePiece(String color, int id) {
    this.color = color;
    this.id = id;
  }
  
  @Override
  public GamePiece clone() {
    try {
      return (GamePiece) super.clone();  // Shallow copy
    } catch (CloneNotSupportedException e) {
      throw new RuntimeException("Clone not supported", e);
    }
  }
  
  @Override
  public String toString() {
    return "GamePiece{" +
      "color='" + color + '\'' +
      ", id=" + id +
      '}';
  }
  
  public void setColor(String color) {
    this.color = color;
  }
  
  public void setId(int id) {
    this.id = id;
  }
}
```

**ConcretePrototype - GameBoard.java:**

```java
class GameBoard implements Cloneable {
  private List<GamePiece> pieces = new ArrayList<>();
  
  public void addPiece(GamePiece piece) {
    pieces.add(piece);
  }
  
  @Override
  public GameBoard clone() {
    try {
      GameBoard cloned = (GameBoard) super.clone();
      cloned.pieces = new ArrayList<>(this.pieces);  // Deep copy list
      return cloned;
    } catch (CloneNotSupportedException e) {
      throw new RuntimeException("Clone failed", e);
    }
  }
  
  public void showBoardState() {
    System.out.println("Board State:");
    pieces.forEach(System.out::println);
  }
}
```

**Key Points:**
- Implement `Cloneable` interface
- Override `clone()` method using `super.clone()`
- Handle `CloneNotSupportedException`
- Shallow copy - only copies primitive values and object references
- For complex objects, implement deep copy in clone method

---

## Prototype Variants

### **Approach 1: Shallow Copy (Shown Above)**
- Pros: Simple, fast, minimal overhead
- Cons: Shared mutable objects between original and clone
- Best for: Objects with primitive fields or immutable objects

### **Approach 2: Deep Copy**
```java
@Override
public Employee clone() {
  try {
    Employee cloned = (Employee) super.clone();
    cloned.address = new Address(address.getCity(), address.getCountry());
    cloned.projects = new ArrayList<>(this.projects);
    return cloned;
  } catch (CloneNotSupportedException e) {
    throw new RuntimeException("Clone failed", e);
  }
}
```

### **Approach 3: Prototype Registry (Object Pool)**
```java
class ShapeRegistry {
  private Map<String, Shape> shapes = new HashMap<>();
  
  public void addShape(String name, Shape shape) {
    shapes.put(name, shape);
  }
  
  public Shape getShape(String name) {
    Shape shape = shapes.get(name);
    return shape != null ? shape.clone() : null;
  }
}
```

### **Approach 4: Copy Constructor Alternative**
```java
public Document(Document original) {
  this.title = original.title;
  this.content = new ArrayList<>(original.content);
  this.metadata = new HashMap<>(original.metadata);
}
```

---

## Usage Example

**GameClientWithPrototypePattern.java** - Demonstrates prototype pattern:

```java
public class GameClientWithPrototypePattern {
  public static void main(String[] args) {
    
    // Create original board
    GameBoard gameBoard = new GameBoard();
    gameBoard.addPiece(new GamePiece("Red", 1));
    gameBoard.addPiece(new GamePiece("Blue", 5));
    gameBoard.showBoardState();
    
    // CheckPoint state: Clone for backup
    GameBoard copiedBoard = gameBoard.clone();
    
    System.out.println("\nCopy Game Board State:");
    copiedBoard.showBoardState();
    
    // Modify original without affecting clone
    gameBoard.addPiece(new GamePiece("Green", 10));
    
    System.out.println("\nOriginal after modification:");
    gameBoard.showBoardState();
    
    System.out.println("\nClone (unchanged):");
    copiedBoard.showBoardState();
  }
}
```

### Output

```
Board State:
GamePiece{color='Red', id=1}
GamePiece{color='Blue', id=5}

Copy Game Board State:
GamePiece{color='Red', id=1}
GamePiece{color='Blue', id=5}

Original after modification:
GamePiece{color='Red', id=1}
GamePiece{color='Blue', id=5}
GamePiece{color='Green', id=10}

Clone (unchanged):
GamePiece{color='Red', id=1}
GamePiece{color='Blue', id=5}
```

---

## Code Flow

1. Prototype object is created (expensive operation)
2. When clone needed, client calls `clone()` method
3. `super.clone()` creates new instance with copied state
4. Shallow copy: primitives copied, references shared
5. Deep copy: nested objects also cloned
6. Client receives independent copy
7. Modifications to clone don't affect original

---

## Shallow Copy vs Deep Copy

| Aspect | Shallow Copy | Deep Copy |
|--------|---|---|
| **Primitive Fields** | Copied as new values | Copied as new values |
| **Object References** | Reference same object | Separate new objects |
| **Nested Objects** | Shared between copies | Independent copies |
| **Modifications** | Affect both original and clone | Only affects clone |
| **Performance** | Very fast | Slower (more work) |
| **Complexity** | Simple implementation | Complex implementation |
| **Memory** | Lower usage | Higher usage |
| **Best For** | Immutable/simple objects | Complex nested objects |

---

## Advantages

✅ **Performance**: Faster than creating from scratch  
✅ **Reduces Subclassing**: Avoid multiple subclass variants  
✅ **Flexibility**: Clone objects dynamically at runtime  
✅ **Simplicity**: Simple to implement and use  
✅ **Independence**: Clones are independent from originals  
✅ **Abstracts Creation**: Client doesn't need concrete classes  
✅ **Supports Undo/Redo**: Easy checkpoint/rollback mechanisms  
✅ **Avoids Expensive Initialization**: Copy already-initialized state  

---

## Disadvantages

❌ **Shallow Copy Issues**: Unintended sharing of mutable objects  
❌ **Deep Copy Complexity**: Difficult to implement correctly  
❌ **Performance Trade-off**: May lose benefit if deep copy needed  
❌ **Circular References**: Problematic with circular object graphs  
❌ **Cloneable Limitations**: Interface only, not enforced at compile-time  
❌ **Testing Complexity**: Cloned objects may behave unexpectedly  
❌ **Maintenance Burden**: Must update clone() when adding fields  

---

## Prototype vs Factory Pattern

| Aspect | Prototype | Factory |
|--------|---|---|
| **Object Creation** | Clone existing object | Create new from scratch |
| **Existing Object** | Required (template) | Not needed |
| **Cost** | Fast (copy) | Variable |
| **Object Variation** | Modify clone after creation | Use inheritance/parameters |
| **Registration** | Often uses registry | Not typically |
| **Flexibility** | Add objects at runtime | Fixed at compile-time |
| **Dependency** | Requires prototype instance | Only needs type info |
| **Performance** | Excellent (pre-initialized) | Good (creation overhead) |

---

## Real-World Analogies

- **Photo Copying**: Original document → Photocopy (Xerox)
- **DNA Cloning**: Template DNA → Clone organism
- **Blueprint Replication**: Master plans → Copies for buildings
- **Email Templates**: Template email → Clone and customize
- **Game Sprites**: Master sprite → Clone for multiple instances
- **Database Snapshots**: Backup database → Clone for testing
- **Computer Image**: Master installation → Cloned to multiple machines
- **Video Streaming**: Master content → Cloned across servers

---

## When to Use Prototype Pattern

**Use Prototype when:**
- ✅ Object creation is expensive or complex
- ✅ You need multiple independent copies of objects
- ✅ System requires object state snapshots
- ✅ You want to avoid deep class hierarchies
- ✅ You need undo/redo or checkpoint functionality
- ✅ Object type is determined at runtime
- ✅ Initialization is costly (complex state setup)

**Don't use when:**
- ❌ Object creation is simple and fast
- ❌ Only a few fixed object types exist
- ❌ Deep copying is impossible or too complex
- ❌ Circular references are common and problematic
- ❌ You prefer explicit object creation
- ❌ Cloneable interface overhead is unacceptable

---

## Important Considerations

### **Implementing Cloneable**
- `Cloneable` is a marker interface (no methods to implement)
- Must explicitly override `clone()` method
- Must handle `CloneNotSupportedException` 
- `super.clone()` performs shallow copy

### **Deep vs Shallow Copy Decision**
- Choose based on object complexity
- Shallow copy easier, but shared mutable objects risky
- Deep copy safer but more complex implementation
- Hybrid approach: partial deep copy for critical fields

### **Thread Safety**
- Cloning itself is not inherently thread-safe
- Prototype registry should be synchronized for multi-threading
- Clone operations generally thread-safe if deep copy done properly
- Consider synchronization when cloning shared prototypes

### **Clone Method Conventions**
- Always override `clone()` even if interface doesn't require it
- Return type should match actual class (covariant return type)
- Document shallow vs deep copy behavior
- Ensure copied object is completely independent

---

## Conclusion

The Prototype Pattern is powerful for efficient object creation when instantiation is expensive or complex. It trades upfront implementation complexity for improved runtime performance and flexibility. It's especially valuable for checkpoint/rollback scenarios and when creating many similar objects dynamically.

**Remember**: Always consider whether shallow or deep copying is appropriate for your use case. Incorrect copy strategy can lead to unexpected shared state issues between original and clones. Test thoroughly, especially with nested objects.
