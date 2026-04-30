# Abstract Factory Design Pattern

## Overview
The Abstract Factory Pattern is a **Creational Design Pattern** that provides an interface for creating **families of related or dependent objects** without specifying their concrete classes. It's used when systems need to work with multiple families of products independently.

---

## Key Components

1. **AbstractFactory:** Interface defining methods to create abstract products
2. **ConcreteFactory:** Implements factory methods for specific product families
3. **AbstractProduct:** Interface for product families
4. **ConcreteProduct:** Specific product implementations

---

## Code Example (Under 50 Lines)

```java
// Product Interfaces
interface Button { void render(); }
interface ScrollBar { void scroll(); }

// Concrete Products
class WindowsButton implements Button { 
  public void render() { System.out.println("Windows Button"); } 
}
class WindowsScrollBar implements ScrollBar { 
  public void scroll() { System.out.println("Windows ScrollBar"); } 
}
class MacButton implements Button { 
  public void render() { System.out.println("Mac Button"); } 
}
class MacScrollBar implements ScrollBar { 
  public void scroll() { System.out.println("Mac ScrollBar"); } 
}

// Abstract Factory
interface UIFactory {
  Button createButton();
  ScrollBar createScrollBar();
}

// Concrete Factories
class WindowsFactory implements UIFactory {
  public Button createButton() { return new WindowsButton(); }
  public ScrollBar createScrollBar() { return new WindowsScrollBar(); }
}
class MacFactory implements UIFactory {
  public Button createButton() { return new MacButton(); }
  public ScrollBar createScrollBar() { return new MacScrollBar(); }
}

// Client Code
class Application {
  UIFactory factory;
  Application(UIFactory factory) { this.factory = factory; }
  void renderUI() {
    factory.createButton().render();
    factory.createScrollBar().scroll();
  }
}
```

---

## Problem It Solves

**Before (Tight Coupling):**
```java
if (OS.equals("Windows")) {
  button = new WindowsButton();
  scrollBar = new WindowsScrollBar();
} else if (OS.equals("Mac")) {
  button = new MacButton();
  scrollBar = new MacScrollBar();
}
```
❌ Violates Open/Closed Principle
❌ Hard to maintain and extend

**After (Abstract Factory):**
```java
UIFactory factory = OSFactory.getFactory(OS);
button = factory.createButton();
scrollBar = factory.createScrollBar();
```
✅ Clean and maintainable
✅ Easy to add new OS families

---

## Benefits

✅ **Loose Coupling:** Isolates concrete classes
✅ **Consistency:** Ensures related objects work together
✅ **Easy Extension:** Add new product families easily
✅ **Centralized:** All creation logic in factory classes
✅ **SOLID:** Follows Open/Closed and Dependency Inversion

---

## Comparison Table: Factory Method vs Abstract Factory

| Feature | Factory Method | Abstract Factory |
|---------|---|---|
| **Purpose** | Create single product objects | Create families of related objects |
| **Interface** | Single factory method | Multiple factory methods |
| **Products** | One type of product | Multiple related product types |
| **Extensibility** | Add new products via subclasses | Add new product families via new factories |
| **Coupling** | Moderate | Low |
| **Complexity** | Low-Medium | Medium-High |
| **Use Case** | Single object creation | Coordinated multi-object creation |
| **Example** | `ShapeFactory.create(type)` | `UIFactory.createButton() & .createScrollBar()` |
| **Inheritance** | Typically uses inheritance | Uses composition/interfaces |
| **Product Consistency** | Not enforced | Enforced (same family) |

---

## When to Use

**Use Abstract Factory when:**
- ✅ You have multiple families of related products
- ✅ System must work with different product families independently
- ✅ You want to ensure products from the same family work together
- ✅ You need to provide a product library (only interfaces)

**Don't use when:**
- ❌ You only have one product family
- ❌ Object creation is simple and straightforward
- ❌ Extensibility is unlikely

---

## Real-World Applications

- **UI Frameworks:** Buttons, scrollbars, menus for different OS
- **Database Systems:** Connection objects, statement objects for different DBs
- **Document Processing:** Word, Excel, PDF document families
- **Game Development:** Character, weapon, and item families for different game themes
- **Theme Systems:** Dark mode, light mode, high-contrast mode families

---

---

## Comparison: Abstract Factory vs Simple Factory

| Aspect | Simple Factory | Abstract Factory |
|--------|---|---|
| Object Creation | Single method | Multiple methods |
| Families | None | Multiple |
| Design Pattern | No (Idiom) | Yes (GoF) |
| Coupling | Medium | Low |
| Extensibility | Poor | Excellent |
| Code Size | Small | Large |

---

## Conclusion

The Abstract Factory Pattern is powerful for managing multiple families of related objects. It ensures consistency between related products and provides a clean way to extend the system with new product families without modifying existing code.

**Choose wisely:** Use it when you genuinely have product families; otherwise, Factory Method or Simple Factory may be more appropriate.

