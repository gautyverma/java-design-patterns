# Creational Design Patterns

## Overview

Creational Design Patterns focus on **how objects are created and instantiated**.  
They help in defining **clear object creation mechanisms**, improving flexibility and minimizing direct dependencies.

The key idea is to **abstract the instantiation process** so that systems become independent of how their objects are created, composed, and represented.

---

## **1. Singleton Pattern**

**Purpose:**  
Ensure a class has **only one instance** and provide a global point of access to it.

**Key Characteristics:**
- Single instance throughout application lifetime
- Global access point
- Thread-safe initialization (often required)

**Use Cases:**
- Database connection pools
- Logger instances
- Configuration managers
- Thread pool managers
- Application settings holder

**Benefits:**
✅ Controlled access to single instance  
✅ Lazy initialization possible  
✅ Global point of access  

**Drawbacks:**
❌ Can mask poor design  
❌ Hard to test (global state)  
❌ Thread-safety overhead  

---

## **2. Factory Pattern**

**Purpose:**  
Define a **family of classes** and provide interfaces to create objects **without specifying exact classes**.

**Key Types:**
- **Simple Factory**: Single factory method handles creation
- **Factory Method**: Subclasses decide which class to instantiate
- **Abstract Factory**: Create families of related objects

**Use Cases:**
- UI element creation (buttons, dialogs for different OS)
- Document creation (PDF, Word, Excel)
- Payment processor creation (Credit card, PayPal, UPI)
- Database connection creation
- Animal/vehicle creation systems

**Benefits:**
✅ Decouples client from concrete classes  
✅ Centralizes object creation logic  
✅ Supports polymorphic object creation  

**Drawbacks:**
❌ Additional classes needed  
❌ Increased code complexity  
❌ Harder to extend for simple cases  

---

## **3. Builder Pattern**

**Purpose:**  
Construct **complex objects step by step** by separating construction logic from representation.

**Key Characteristics:**
- Separates construction from representation
- Allows step-by-step object creation
- Supports optional parameters
- Fluent interface (method chaining)

**Use Cases:**
- Complex object creation (House, Pizza, Car)
- Objects with many optional parameters
- Immutable object construction
- Configuration objects
- SQL query builders
- XML/JSON builders

**Benefits:**
✅ Clear and readable construction  
✅ Handles optional parameters elegantly  
✅ Produces immutable objects  
✅ Step-by-step control over construction  

**Drawbacks:**
❌ More code than direct instantiation  
❌ Constructor overloading may be simpler for simple cases  
❌ Memory overhead for builder object  

---

## **4. Prototype Pattern**

**Purpose:**  
Create **new objects by cloning an existing object** rather than creating from scratch.

**Key Characteristics:**
- Clone method implementation (shallow vs deep copy)
- Avoids expensive object creation
- Runtime object type determination
- Prototype registry for cloning

**Use Cases:**
- Graphical element cloning (shapes, images)
- Game object creation (enemies, projectiles)
- Database record copying
- Configuration cloning
- Expensive-to-create object duplication
- Undo/Redo systems

**Benefits:**
✅ Faster than creating from scratch  
✅ Avoids subclassing complexity  
✅ Runtime type flexibility  
✅ Useful for expensive initialization  

**Drawbacks:**
❌ Cloning complex object graphs is tricky  
❌ Circular references complicate cloning  
❌ Deep copy implementation overhead  

---

## **5. Abstract Factory Pattern**

**Purpose:**  
Provide interface to create **families of related or dependent objects** without specifying concrete classes.

**Key Characteristics:**
- Creates families of related objects
- Each factory handles a product family
- Ensures compatible products
- Decouples client from concrete classes

**Use Cases:**
- Cross-platform GUI creation (Windows, macOS, Linux UI elements)
- Theme creation (light theme, dark theme components)
- Database abstraction (SQL Server, MySQL, Oracle)
- Operating system abstraction
- Furniture store (Modern, Victorian, Art Deco styles)

**Benefits:**
✅ Ensures product family compatibility  
✅ Isolates concrete class creation  
✅ Supports product family switching  

**Drawbacks:**
❌ Complex implementation  
❌ Many classes required  
❌ Harder to extend with new product types  

---

## **Comparison Matrix**

| Pattern | Focus | Complexity | When to Use |
|---------|-------|-----------|------------|
| **Singleton** | One instance | Low | Single global resource |
| **Factory** | Polymorphic creation | Medium | Multiple subtypes |
| **Builder** | Step-by-step construction | Medium | Complex multi-step objects |
| **Prototype** | Clone-based creation | Medium | Expensive object creation |
| **Abstract Factory** | Product family | High | Multiple related objects |

---

## **Selection Guide**

🔍 **Choose Singleton if:**
- You need exactly one instance
- Need global access point
- Resource is expensive to create

🔍 **Choose Factory if:**
- Objects have multiple subtypes
- Client shouldn't know concrete types
- Object creation logic is complex

🔍 **Choose Builder if:**
- Object has many constructor parameters
- Want step-by-step construction
- Need immutable objects
- Want fluent/readable interface

🔍 **Choose Prototype if:**
- Object creation is expensive
- Need object copies
- Runtime type determination needed
- Similar objects with minor variations

🔍 **Choose Abstract Factory if:**
- Need families of related objects
- Want to ensure product compatibility
- Need to switch between product families
- Platform-specific creation needed

---

## **Design Principles Covered**

| Principle | Patterns Using It |
|-----------|------------------|
| **Encapsulation** | All patterns hide creation details |
| **DRY** | Factory, Builder centralize creation logic |
| **Open/Closed** | Factory, Abstract Factory extensible for new types |
| **Single Responsibility** | Builder, Factory separate concerns |
| **Dependency Inversion** | Factory, Abstract Factory depend on abstractions |

---

## **Combination Patterns**

- **Singleton + Factory**: Factory ensures single instance creation
- **Factory + Strategy**: Factory chooses algorithm strategy
- **Builder + Prototype**: Builder creates prototypes
- **Abstract Factory + Singleton**: Single factory for each product family

---

## **Golden Insight 🚀**

> **Creational patterns are about "lazy instantiation, abstraction, and flexibility".**  
> They provide mechanisms to create objects **without coupling to their concrete implementations**,  
> enabling systems to be independent of how their objects are created.

---

## **Common Pitfalls**

❌ Over-engineering with patterns for simple cases  
❌ Mixing too many creation patterns  
❌ Not considering thread-safety (especially Singleton)  
❌ Ignoring performance implications (cloning, factory overhead)  
❌ Complex inheritance hierarchies in Abstract Factory  

---

## **Best Practices**

✅ Start simple, add patterns as complexity grows  
✅ Consider thread-safety from the beginning  
✅ Document creation process clearly  
✅ Test creation logic thoroughly  
✅ Consider immutability (especially with Builder)  
✅ Use composition over inheritance  
✅ Cache created objects when appropriate  

---

## **Related Topics**

- **Lazy Initialization**: Creating objects on demand
- **Object Pooling**: Reusing created objects
- **Dependency Injection**: Alternative to Factory/Builder
- **Immutability**: Often paired with Builder
- **Clone Semantics**: Deep vs shallow copies in Prototype
