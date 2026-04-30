# Factory Design Pattern

## Overview
The Factory Design Pattern is a **Creational Design Pattern** that provides an interface for creating objects without specifying the exact classes to create. Instead of directly instantiating objects using the `new` keyword, we use a factory method or factory class to create objects.

---

## Types of Factory Patterns

### 1. Simple Factory Pattern
A single factory class that creates objects based on input parameters.

**Characteristics:**
- Not considered a true design pattern (more of a programming idiom)
- Uses a switch/if-else statement to determine object type
- Simple and straightforward implementation
- Violates Open/Closed Principle (OCP)

**Example:**
```java
class ShapeFactory {
    public static Shape createShape(String type) {
        if (type.equals("CIRCLE")) {
            return new Circle();
        } else if (type.equals("RECTANGLE")) {
            return new Rectangle();
        }
        return null;
    }
}
```

---

### 2. Factory Method Pattern (Virtual Constructor Pattern)
Uses abstract methods in a base class to define the interface for creating objects. Subclasses override these methods to create specific object types.

**Characteristics:**
- Considered a true design pattern (part of Gang of Four patterns)
- Uses inheritance and method overriding
- Follows the Open/Closed Principle
- Each subclass is responsible for creating its own products

**Key Components:**
- **Creator (Abstract Class):** Declares the factory method
- **ConcreteCreator:** Implements the factory method
- **Product (Interface/Abstract):** Defines the interface for objects
- **ConcreteProduct:** Implements the product interface

**Benefits:**
- Eliminates dependency on concrete classes
- Easy to extend with new product types
- Follows Single Responsibility Principle (SRP)
- Leverages polymorphism

**Example:**
```java
abstract class DatabaseConnection {
    abstract Connection createConnection();
    
    public void connectToDatabase() {
        Connection conn = createConnection();
        conn.connect();
    }
}

class MySQLConnection extends DatabaseConnection {
    @Override
    Connection createConnection() {
        return new MySQLConnectionImpl();
    }
}
```


## When to Use Factory Patterns

**Use Simple Factory when:**
- You have a few, unchanging object types
- The creation logic is simple
- Extensibility is not a concern

**Use Factory Method when:**
- You want subclasses to decide which class to instantiate
- The number of product types may increase in future
- You want to avoid tight coupling to concrete classes

---

## Advantages & Disadvantages

### Advantages ✅
1. **Loose Coupling:** Decouples client code from concrete classes
2. **Flexibility:** Easy to add new product types without modifying existing code
3. **Centralized Creation:** All object creation logic is in one place
4. **Consistency:** Ensures related objects are created together
5. **Scalability:** Easy to manage complex object creation scenarios

### Disadvantages ❌
1. **Complexity:** Can introduce unnecessary complexity for simple scenarios
2. **Over-Engineering:** May be overkill for small projects
3. **Additional Classes:** Requires creating multiple factory and product classes
4. **Learning Curve:** Developers need to understand the pattern

---

## Real-World Examples

### Example 1: Database Connection Factory
```java
interface Database {
    void connect();
}

class MySQLDatabase implements Database {
    public void connect() {
        System.out.println("Connecting to MySQL");
    }
}

class PostgreSQLDatabase implements Database {
    public void connect() {
        System.out.println("Connecting to PostgreSQL");
    }
}

class DatabaseFactory {
    public static Database createDatabase(String type) {
        if (type.equals("MYSQL")) return new MySQLDatabase();
        if (type.equals("POSTGRESQL")) return new PostgreSQLDatabase();
        return null;
    }
}
```

### Example 2: Transport Delivery System (Similar to Bike.java)
```java
interface Transport {
    void deliver();
}

class Bike implements Transport {
    @Override
    public void deliver() {
        System.out.println("Delivering by bike");
    }
}

class Truck implements Transport {
    @Override
    public void deliver() {
        System.out.println("Delivering by truck");
    }
}

class Plane implements Transport {
    @Override
    public void deliver() {
        System.out.println("Delivering by plane");
    }
}

class TransportFactory {
    public static Transport createTransport(String type) {
        if (type.equals("BIKE")) return new Bike();
        if (type.equals("TRUCK")) return new Truck();
        if (type.equals("PLANE")) return new Plane();
        return null;
    }
}
```

## Best Practices

1. **Use Interfaces:** Define contracts for factories and products using interfaces
2. **Single Responsibility:** Each factory should be responsible for one family of products
3. **Naming Convention:** Use clear names like `Factory`, `Creator`, or `Builder` in class names
4. **Configuration:** Use configuration files or properties to specify factory implementation
5. **Registry Pattern:** Combine with Registry pattern for more flexible object creation
6. **Dependency Injection:** Consider using DI frameworks for automatic factory selection

---