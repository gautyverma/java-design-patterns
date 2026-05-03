# Builder Design Pattern

## Overview

The **Builder Pattern** is a creational design pattern that separates the construction of a complex object from its representation, allowing step-by-step object creation. It enables flexible and readable construction of objects with many optional parameters, making code more maintainable and less error-prone.

---

## Why Use Builder Pattern?

- **Readable Code**: Fluent API makes object construction clear and self-documenting
- **Flexible Construction**: Build objects step-by-step with optional parameters
- **Avoid Telescoping Constructors**: Eliminate constructor overloading hell
- **Immutability**: Create immutable objects safely without huge constructor parameters
- **Object Consistency**: Ensure all required fields are set before object creation
- **Separation of Concerns**: Separates construction logic from business logic
- **Easier Maintenance**: Changes to object structure don't affect client code

---

## Use Cases

- Building configuration objects with many optional parameters
- Creating complex domain objects (House, Computer, Resume)
- UI components with multiple customization options
- Database query builders
- Request/Response builders in REST APIs
- HTML/XML document builders
- Report builders with various options
- Game object creation with many attributes

---

## Key Components

| Component | Responsibility |
|-----------|-----------------|
| **Product** | Complex object being constructed |
| **Builder** | Abstract builder interface with build methods |
| **ConcreteBuilder** | Implements builder methods to construct product |
| **Director** (Optional) | Controls construction sequence |

---

## Implementation

### 1. Basic Builder Pattern

**House.java** - Product class with inner builder:

```java
class House {
  // Required parameters
  private String foundation;
  private String walls;
  private String roof;
  
  // Optional parameters
  private boolean hasGarage;
  private boolean hasSwimmingPool;
  private boolean hasGarden;
  
  // Private constructor - only accessible via builder
  private House(HouseBuilder builder) {
    this.foundation = builder.foundation;
    this.walls = builder.walls;
    this.roof = builder.roof;
    this.hasGarage = builder.hasGarage;
    this.hasSwimmingPool = builder.hasSwimmingPool;
    this.hasGarden = builder.hasGarden;
  }
  
  @Override
  public String toString() {
    return "House{" +
      "foundation='" + foundation + '\'' +
      ", walls='" + walls + '\'' +
      ", roof='" + roof + '\'' +
      ", hasGarage=" + hasGarage +
      ", hasSwimmingPool=" + hasSwimmingPool +
      ", hasGarden=" + hasGarden +
      '}';
  }
  
  // Inner Builder class
  static class HouseBuilder {
    // Required
    private String foundation;
    private String walls;
    private String roof;
    
    // Optional
    private boolean hasGarage = false;
    private boolean hasSwimmingPool = false;
    private boolean hasGarden = false;
    
    // Constructor with required parameters
    public HouseBuilder(String foundation, String walls, String roof) {
      this.foundation = foundation;
      this.walls = walls;
      this.roof = roof;
    }
    
    // Fluent builder methods for optional parameters
    public HouseBuilder setHasGarage(boolean hasGarage) {
      this.hasGarage = hasGarage;
      return this;
    }
    
    public HouseBuilder setHasSwimmingPool(boolean hasSwimmingPool) {
      this.hasSwimmingPool = hasSwimmingPool;
      return this;
    }
    
    public HouseBuilder setHasGarden(boolean hasGarden) {
      this.hasGarden = hasGarden;
      return this;
    }
    
    // Build method returns the constructed House
    public House build() {
      return new House(this);
    }
  }
}
```

**Key Points:**
- Product constructor is `private` - only builder can instantiate
- Builder provides fluent interface (methods return `this`)
- Required parameters in builder constructor
- Optional parameters have setter methods
- `build()` method creates final product

---

## Builder Variants

### **Approach 1: Inner Builder (Shown Above)**
- Pros: Simple, encapsulated, fluent API
- Cons: Tight coupling between product and builder
- Best for: Single product types

### **Approach 2: Separate Builder Class**
```java
interface Builder {
  Builder setFoundation(String foundation);
  Builder setWalls(String walls);
  Builder setRoof(String roof);
  House build();
}

class HouseBuilder implements Builder {
  private String foundation, walls, roof;
  private boolean hasGarage, hasSwimmingPool;
  
  @Override
  public Builder setFoundation(String foundation) {
    this.foundation = foundation;
    return this;
  }
  
  @Override
  public House build() {
    return new House(foundation, walls, roof, hasGarage, hasSwimmingPool);
  }
}
```

### **Approach 3: Director Pattern**
```java
class HouseDirector {
  private Builder builder;
  
  public HouseDirector(Builder builder) {
    this.builder = builder;
  }
  
  public House buildStandardHouse() {
    return builder
      .setFoundation("Concrete")
      .setWalls("Brick")
      .setRoof("Tile")
      .build();
  }
  
  public House buildLuxuryHouse() {
    return builder
      .setFoundation("Marble")
      .setWalls("Stone")
      .setRoof("Slate")
      .setHasGarage(true)
      .setHasSwimmingPool(true)
      .build();
  }
}
```

---

## Usage Example

**Client.java** - Demonstrates builder pattern usage:

```java
public class Client {
  public static void main(String[] args) {
    
    // Simple house
    House BasicHouse = new House.HouseBuilder("Concrete", "Brick", "Tile")
      .build();
    System.out.println("Basic House: " + BasicHouse);
    
    // House with garage
    House HouseWithGarage = new House.HouseBuilder("Concrete", "Brick", "Tile")
      .setHasGarage(true)
      .build();
    System.out.println("House with Garage: " + HouseWithGarage);
    
    // Luxury house
    House LuxuryHouse = new House.HouseBuilder("Marble", "Stone", "Slate")
      .setHasGarage(true)
      .setHasSwimmingPool(true)
      .setHasGarden(true)
      .build();
    System.out.println("Luxury House: " + LuxuryHouse);
  }
}
```

### Output

```
Basic House: House{foundation='Concrete', walls='Brick', roof='Tile', hasGarage=false, hasSwimmingPool=false, hasGarden=false}
House with Garage: House{foundation='Concrete', walls='Brick', roof='Tile', hasGarage=true, hasSwimmingPool=false, hasGarden=false}
Luxury House: House{foundation='Marble', walls='Stone', roof='Slate', hasGarage=true, hasSwimmingPool=true, hasGarden=true}
```

---

## Code Flow

1. Client creates builder with required parameters
2. Client calls optional setter methods in any order
3. Each setter method returns builder instance (fluent interface)
4. Client calls `build()` method to create product
5. Builder validates and constructs final product
6. Client receives complete, consistent object

---

## Advantages

✅ **Readable Code**: Fluent, self-documenting API  
✅ **Flexible Construction**: Add or skip optional parameters easily  
✅ **No Telescoping Constructors**: Avoid constructor overloading hell  
✅ **Immutability**: Create truly immutable objects  
✅ **Consistency**: Ensures valid object state before creation  
✅ **Separation of Concerns**: Construction logic separate from product  
✅ **Easy to Extend**: Add new parameters without affecting existing code  

---

## Disadvantages

❌ **Verbose**: More code than simple constructors  
❌ **Performance**: Slight overhead in object creation  
❌ **Complexity**: Overkill for simple objects  
❌ **Memory Overhead**: Builder instance must be kept in memory  
❌ **Thread Safety**: Builder itself not thread-safe (needs synchronization)  
❌ **Learning Curve**: Requires understanding fluent API pattern  

---

## Comparison: Constructor vs Builder

| Aspect | Traditional Constructor | Builder Pattern |
|--------|---|---|
| **Readability** | Poor with many params | Excellent, self-documenting |
| **Optional Params** | Telescoping constructors | Fluent setter methods |
| **Flexibility** | Low | High |
| **Immutability** | Easy but verbose | Better balance |
| **Code Lines** | Few | Many |
| **Maintainability** | Moderate | Good |
| **Validation** | In constructor | In builder |

---

## Real-World Analogies

- **House Construction**: Foundation→Walls→Roof→Finishing (step-by-step)
- **Car Manufacturing**: Frame→Engine→Interior→Paint (assembly line)
- **Meal Preparation**: Base→Protein→Vegetables→Sauce→Seasoning
- **Computer Assembly**: Case→Motherboard→CPU→Memory→Storage
- **Document Creation**: Title→Content→Formatting→Metadata
- **API Request Building**: Method→URL→Headers→Body→Query Params

---

## When to Use Builder Pattern

**Use Builder when:**
- ✅ Object has many (5+) optional parameters
- ✅ You want immutable objects
- ✅ Object construction is complex
- ✅ You need multiple ways to build similar objects
- ✅ You value code readability over simplicity

**Don't use when:**
- ❌ Object has few parameters
- ❌ Constructor is simple and straightforward
- ❌ Performance is critical (overhead matters)
- ❌ You need thread-safe parallel construction

---

## Conclusion

The Builder Pattern is a powerful tool for constructing complex objects with optional parameters. It provides a clean, readable alternative to telescoping constructors while maintaining immutability and consistency. Use it wisely when object complexity warrants the additional code structure.

**Remember**: The Builder Pattern trades simplicity for flexibility and readability. Choose it when the cost is justified by improved code clarity and maintainability.

