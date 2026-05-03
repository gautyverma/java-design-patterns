# Decorator Design Pattern

## Overview

The **Decorator Pattern** is a structural design pattern that allows you to attach additional responsibilities to an object dynamically, providing a flexible alternative to subclassing for extending functionality. It wraps an object with a decorator object that provides additional features while maintaining the same interface as the original object.

---

## Why Use Decorator Pattern?

- **Dynamic Feature Addition**: Add features to objects at runtime without modifying source
- **Avoid Subclass Explosion**: Alternative to creating many subclasses for combinations
- **Single Responsibility**: Each decorator handles one specific enhancement
- **Flexible Composition**: Combine multiple decorators in any order
- **Open/Closed Principle**: Open for extension, closed for modification
- **Transparency**: Decorators are transparent to clients using the component
- **Runtime Behavior**: Change object behavior dynamically
- **Easy to Add/Remove**: Add or remove decorators without affecting other code

---

## Use Cases

- Adding UI features (scrollbars, borders, decorations)
- Extending component functionality (compression, encryption, caching)
- Pizza ordering system with toppings (your use case!)
- Stream wrapping (BufferedInputStream, DataInputStream)
- Logger enhancements (timestamps, formatting, severity levels)
- HTTP request/response interceptors
- Window manager effects (shadows, transparency)
- File I/O wrappers (encryption, compression, formatting)

---

## Key Components

| Component | Responsibility |
|-----------|-----------------|
| **Component Interface** | Defines interface for objects and decorators |
| **ConcreteComponent** | Base object that decorators wrap |
| **Decorator** | Abstract base for all decorators |
| **ConcreteDecorator** | Specific decorator that adds new behavior |
| **Client** | Works with decorated objects through component interface |

---

## Implementation

### 1. Decorator Pattern with Pizza Example

**Component Interface - Pizza.java:**

```java
// Interface that both component and decorators implement
public interface Pizza {
  String getDescription();
  double getCost();
}
```

**Concrete Component - BasicPizza.java:**

```java
// Base object that can be decorated
public class BasicPizza implements Pizza {
  @Override
  public String getDescription() {
    return "Basic Pizza";
  }
  
  @Override
  public double getCost() {
    return 5.0;
  }
}
```

**Abstract Decorator - PizzaDecorator.java:**

```java
// Abstract base class for decorators
abstract class PizzaDecorator implements Pizza {
  // Holds reference to wrapped object
  protected Pizza decoratedPizza;
  
  // Accept wrapped object in constructor
  public PizzaDecorator(Pizza decoratedPizza) {
    this.decoratedPizza = decoratedPizza;
  }
  
  // Default implementation delegates to wrapped object
  @Override
  public String getDescription() {
    return decoratedPizza.getDescription();
  }
  
  @Override
  public double getCost() {
    return decoratedPizza.getCost();
  }
}
```

**Concrete Decorators - CheeseDecorator.java:**

```java
// Specific decorator that adds cheese
public class CheeseDecorator extends PizzaDecorator {
  public CheeseDecorator(Pizza pizza) {
    super(pizza);
  }
  
  @Override
  public String getDescription() {
    return decoratedPizza.getDescription() + ", cheese";
  }
  
  @Override
  public double getCost() {
    return decoratedPizza.getCost() + 1.5;
  }
}
```

**More Concrete Decorators - PaneerDecorator.java:**

```java
public class PaneerDecorator extends PizzaDecorator {
  public PaneerDecorator(Pizza pizza) {
    super(pizza);
  }
  
  @Override
  public String getDescription() {
    return decoratedPizza.getDescription() + ", paneer";
  }
  
  @Override
  public double getCost() {
    return decoratedPizza.getCost() + 3.0;
  }
}
```

**MushroomDecorator.java:**

```java
public class MushroomDecorator extends PizzaDecorator {
  public MushroomDecorator(Pizza pizza) {
    super(pizza);
  }
  
  @Override
  public String getDescription() {
    return decoratedPizza.getDescription() + ", mushrooms";
  }
  
  @Override
  public double getCost() {
    return decoratedPizza.getCost() + 2.0;
  }
}
```

**Key Points:**
- All decorators implement same interface as component
- Decorators wrap component (composition)
- Each decorator adds specific functionality
- Decorators can be stacked in any order
- Client sees single unified interface

---

## Decorator Variants

### **Approach 1: Class Decorator (Shown Above)**
- Pros: Simple inheritance hierarchy, clear structure
- Cons: Cannot decorate subclasses of wrapped object
- Best for: Decorating specific classes

### **Approach 2: Generic Decorator**
```java
public class GenericDecorator<T> implements Decorator<T> {
  private T component;
  private String decoration;
  
  public GenericDecorator(T component, String decoration) {
    this.component = component;
    this.decoration = decoration;
  }
  
  public T getComponent() { return component; }
  public String getDecoration() { return decoration; }
}
```

### **Approach 3: Stream Decorators (java.io)**
```java
// Reader stream wrapped with decorators
BufferedReader in = new BufferedReader(
  new InputStreamReader(
    new FileInputStream("file.txt")
  )
);
```

### **Approach 4: Functional Decorator (Lambda)**
```java
interface Decorator {
  String decorate(String input);
}

Decorator bold = s -> "<b>" + s + "</b>";
Decorator italic = s -> "<i>" + s + "</i>";
Decorator combined = bold.compose(italic);
```

---

## Usage Example

**PizzaApp.java** - Demonstrates decorator pattern:

```java
public class PizzaApp {
  public static void main(String[] args) {
    // Start with basic pizza
    Pizza pizza = new BasicPizza();
    System.out.println("Pizza: " + pizza.getDescription());
    System.out.println("Cost: $" + pizza.getCost());
    System.out.println();
    
    // Wrap with cheese decorator
    pizza = new CheeseDecorator(pizza);
    System.out.println("Pizza: " + pizza.getDescription());
    System.out.println("Cost: $" + pizza.getCost());
    System.out.println();
    
    // Wrap with paneer decorator
    pizza = new PaneerDecorator(pizza);
    System.out.println("Pizza: " + pizza.getDescription());
    System.out.println("Cost: $" + pizza.getCost());
    System.out.println();
    
    // Wrap with mushroom decorator
    pizza = new MushroomDecorator(pizza);
    System.out.println("Pizza: " + pizza.getDescription());
    System.out.println("Cost: $" + pizza.getCost());
  }
}
```

### Output

```
Pizza: Basic Pizza
Cost: $5.0

Pizza: Basic Pizza, cheese
Cost: $6.5

Pizza: Basic Pizza, cheese, paneer
Cost: $9.5

Pizza: Basic Pizza, cheese, paneer, mushrooms
Cost: $11.5
```

---

## Code Flow

1. Client instantiates base component (BasicPizza)
2. Client wraps component with first decorator (CheeseDecorator)
3. Decorator stores reference to wrapped object
4. Client can nest multiple decorators
5. Each level calls previous decorator's method
6. Behavior accumulates through decorator chain
7. Final object has all decorations applied

### Decorator Chain Visualization

```
MushroomDecorator
    ↓
PaneerDecorator
    ↓
CheeseDecorator
    ↓
BasicPizza (5.0)
```

---

## Advantages

✅ **Dynamic Behavior**: Add features at runtime  
✅ **Avoid Subclass Explosion**: No need for countless subclasses  
✅ **Single Responsibility**: Each decorator handles one concern  
✅ **Flexible Composition**: Mix and match decorators freely  
✅ **Open/Closed Principle**: Extend without modifying existing code  
✅ **Transparency**: Clients unaware of decoration  
✅ **Easy to Test**: Each decorator independently testable  
✅ **Runtime Configuration**: Change behavior without recompilation  

---

## Disadvantages

❌ **Complexity**: Decorator chain can be hard to trace/debug  
❌ **Performance Overhead**: Extra method call overhead  
❌ **Debugging Difficulty**: Stack traces include decorator layers  
❌ **Order Dependency**: Decorator order may affect results  
❌ **Memory Usage**: Each decorator adds object overhead  
❌ **Learning Curve**: Design pattern complexity  
❌ **Maintenance**: Many small decorator classes  
❌ **Order Matters**: Decorators applied in specific sequence  

---

## Decorator Chain Examples

### **Example 1: Simple Single Decorator**
```java
Pizza myPizza = new CheeseDecorator(new BasicPizza());
```

### **Example 2: Multiple Decorators Stacked**
```java
Pizza myPizza = 
  new MushroomDecorator(
    new PaneerDecorator(
      new CheeseDecorator(
        new BasicPizza()
      )
    )
  );
```

### **Example 3: Dynamic Decorator Application**
```java
Pizza myPizza = new BasicPizza();
if (wantsCheese) myPizza = new CheeseDecorator(myPizza);
if (wantsPaneer) myPizza = new PaneerDecorator(myPizza);
if (wantsMushrooms) myPizza = new MushroomDecorator(myPizza);
```

---

## Real-World Applications

| Application | Base | Decorator 1 | Decorator 2 | Decorator 3 |
|-------------|------|-------------|-------------|------------|
| **File I/O** | FileInputStream | BufferedInputStream | DataInputStream | CompressedInputStream |
| **UI Components** | Panel | ScrollDecorator | BorderDecorator | ShadowDecorator |
| **HTTP Requests** | Request | AuthDecorator | CacheDecorator | LoggingDecorator |
| **Pizza Order** | BasicPizza | CheeseDecorator | PaneerDecorator | MushroomDecorator |
| **Text Formatting** | PlainText | BoldDecorator | ItalicDecorator | UnderlineDecorator |
| **Database Queries** | SQLQuery | CacheDecorator | LoggingDecorator | TimingDecorator |
| **Stream Processing** | InputStream | EncryptionDecorator | CompressionDecorator | ValidatingDecorator |

---

## When to Use Decorator Pattern

**Use Decorator when:**
- ✅ Need to add features to objects dynamically
- ✅ Subclassing would create too many classes
- ✅ Features can be applied independently
- ✅ Features need to be combined in many ways
- ✅ Object type is determined at runtime
- ✅ Need to add optional behavior without modifying original
- ✅ Want to avoid affecting other objects

**Don't use when:**
- ❌ Only one or two enhancements needed
- ❌ Decorator order doesn't matter but looks complex
- ❌ Performance is critical (overhead matters)
- ❌ Simple inheritance would be clearer
- ❌ Decorator chain becomes too deep/complex
- ❌ Difficult to understand composition for team

---

## Implementation Guidelines

### **Best Practices**

1. **Keep Decorators Focused**: Each should add one specific feature
2. **Maintain Interface**: Decorators must implement same interface
3. **Document Order**: Clearly document decorator dependency
4. **Avoid Logic Duplication**: Common logic in abstract decorator
5. **Handle Edge Cases**: Consider decorator combinations
6. **Test Independently**: Test each decorator alone and combined
7. **Support Removal**: Allow decorators to be removed if needed

### **Common Mistakes to Avoid**

❌ **Overloading Decorators**: Don't add too much functionality  
❌ **Tight Coupling**: Don't make decorators depend on each other  
❌ **Deep Nesting**: Limit decorator chain depth  
❌ **Ambiguous Order**: Make sure order doesn't matter or document it  
❌ **Performance Negligence**: Be aware of overhead  
❌ **Poor Documentation**: Document the decorator relationships  

---

## Comparison: Inheritance vs Decorator

**Using Inheritance (Bad for combinations):**
```java
class BasicPizza { }
class CheesePizza extends BasicPizza { }
class PaneerPizza extends BasicPizza { }
class CheesePaneerPizza extends CheesePizza { }
class MushroomPizza extends BasicPizza { }
class CheeseMushroomPizza extends CheesePizza { }
// ... explosion of classes!
```

**Using Decorator (Flexible combinations):**
```java
Pizza pizza = new BasicPizza();
pizza = new CheeseDecorator(pizza);
pizza = new PaneerDecorator(pizza);
pizza = new MushroomDecorator(pizza);
// Clean and flexible!
```

---

## Real-World Analogy

**Gift Wrapping:**
- **Base Object**: Gift (the actual item)
- **Decorators**: Gift wrap (basic), Ribbon (enhancement), Bow (another enhancement)
- **Combination**: You can apply wrap, ribbon, and bow in any order
- **Result**: Same gift, different visual appearance
- **Flexibility**: Wrap multiple times without creating new gift!

---

## Conclusion

The Decorator Pattern is a powerful tool for adding responsibilities to objects dynamically. It provides a cleaner and more flexible alternative to subclassing when you need to add features in various combinations. The pattern is especially useful for stream handling, UI components, and any system where behavior needs to be layered.

**Remember**: Decorators should be transparent to clients and focused on a single responsibility. Use them when subclassing would lead to class explosion, but avoid over-complicating simple scenarios. The Pizza example beautifully demonstrates how flexible and powerful this pattern can be!

