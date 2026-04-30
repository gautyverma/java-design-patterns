# State Pattern

## Overview

The **State Pattern** is a behavioral design pattern that allows an object to alter its behavior when its internal state changes. The object will appear to change its class. It encapsulates state-specific behavior and delegates to the current state object.

---

## Why Use State Pattern?

- **State-Specific Behavior**: Different behavior for different states
- **Clean Code**: Eliminates complex conditional statements
- **Extensibility**: Add new states without modifying existing code
- **Single Responsibility**: Each state class handles one specific behavior
- **Runtime Flexibility**: Change object behavior at runtime
- **Maintainability**: State logic is localized and easy to modify

---

## Use Cases

- Document states (draft, review, published, archived)
- Game character states (idle, walking, running, jumping)
- Network connection states (connecting, connected, disconnected, error)
- Order processing states (pending, processing, shipped, delivered)
- UI component states (enabled, disabled, hover, pressed)
- Vending machine states (idle, has coin, dispensing, out of stock)

---

## Key Components

| Component | Responsibility |
|-----------|-----------------|
| **Context** | Maintains reference to current state, delegates behavior to state |
| **State** | Interface/abstract class defining state-specific methods |
| **Concrete State** | Implements behavior for specific state |

---

## Implementation

### 1. State Interface

**TransportationMode.java** - Defines state contract:

```java
public interface TransportationMode {
    int calcETA();
    String getDirection();
}
```

**Key Points:**
- Defines methods that all states must implement
- `calcETA()`: Calculate estimated time of arrival
- `getDirection()`: Get navigation directions
- Common interface for all transportation modes

---

### 2. Context Class

**DirectionService.java** - Context that maintains current state:

```java
public class DirectionService {
  TransportationMode transportationMode;

  public DirectionService(TransportationMode transportationMode) {
    this.transportationMode = transportationMode;
  }

  public void setTransportationMode(TransportationMode transportationMode) {
    this.transportationMode = transportationMode;
  }

  // delegating the work current state's concrete class impl
  public int getETA() {
    return transportationMode.calcETA();
  }

  public String getDirection() {
    return transportationMode.getDirection();
  }
}
```

**Key Points:**
- Maintains reference to current `TransportationMode` state
- Constructor initializes with specific transportation mode
- `setTransportationMode()` allows runtime state changes
- `getETA()` and `getDirection()` delegate to current state
- Client interacts with context, unaware of specific states

---

### 3. Concrete States

**Car.java** - Car transportation state:

```java
public class Car implements TransportationMode {
  @Override
  public int calcETA() {
    System.out.println("Calculating ETA for Car");
    return 15; // Example ETA in minutes
  }

  @Override
  public String getDirection() {
    System.out.println("Getting direction for Car");
    return "Head north on Main St, then turn right onto 1st Ave.";
  }
}
```

**Train.java** - Train transportation state:

```java
public class Train implements TransportationMode {
  @Override
  public int calcETA() {
    System.out.println("Calculating ETA for Train");
    return 120; // Example ETA in minutes
  }

  @Override
  public String getDirection() {
    System.out.println("Getting direction for Train");
    return "Train direction: North";
  }
}
```

**Cycling.java** - Cycling transportation state:

```java
public class Cycling implements TransportationMode {

  @Override
  public int calcETA() {
    System.out.println("Calculating ETA for cycling...");
    return 20; // Example ETA in minutes
  }

  @Override
  public String getDirection() {
    System.out.println("Getting directions for cycling...");
    return "Head north on Main St, then turn right onto 1st Ave.";
  }
}
```

**Walking.java** - Walking transportation state:

```java
public class Walking implements TransportationMode {
  @Override
  public int calcETA() {
    System.out.println("Calculating ETA for walking...");
    return 30; // Example ETA in minutes
  }

  @Override
  public String getDirection() {
    System.out.println("Getting directions for walking...");
    return "Head north on Main St, then turn right onto 1st Ave.";
  }
}
```

**Key Points:**
- Each implements `TransportationMode` interface
- Provide state-specific ETA calculations and directions
- Car: Fastest (15 min), detailed street directions
- Train: Slowest (120 min), simple directional info
- Cycling: Medium speed (20 min), pedestrian-friendly routes
- Walking: Slow (30 min), detailed walking directions

---

## Usage Example

**WithStatePattern.java** - Demonstrates state pattern:

```java
public class WithStatePattern {
  public static void main(String[] args) {
    DirectionService service = new DirectionService(new Car());
    service.setTransportationMode(new Train());

    System.out.println(service.getETA());
    System.out.println(service.getDirection());
  }
}
```

### Output

```
Calculating ETA for Train
120
Getting direction for Train
Train direction: North
```

---

## Code Flow

1. Create DirectionService with initial Car state
2. Change state to Train using setTransportationMode()
3. Call getETA() - delegates to Train's calcETA() method
4. Call getDirection() - delegates to Train's getDirection() method
5. Each state provides different ETA and direction logic
6. Context remains unchanged, behavior changes with state

---

## Advantages

✅ **Eliminates Conditionals**: No complex if-else chains for state logic  
✅ **Extensibility**: Add new states without modifying context  
✅ **Single Responsibility**: Each state handles one specific behavior  
✅ **Runtime Flexibility**: Change behavior dynamically  
✅ **Clean Code**: State logic is encapsulated and organized  
✅ **Testability**: Each state can be tested independently  

---

## Disadvantages

❌ **Class Explosion**: Many state classes for complex state machines  
❌ **Increased Complexity**: More classes and interfaces to manage  
❌ **State Transitions**: Need to manage valid state transitions  
❌ **Memory Overhead**: Each state object consumes memory  
❌ **Setup Complexity**: Initial state configuration can be complex  

---

## Real-World Analogies

- **Traffic Light**: Red, yellow, green states with different behaviors
- **Vending Machine**: Idle, has coin, dispensing, out of stock states
- **Document Workflow**: Draft, review, approved, published states
- **Game Character**: Idle, walking, running, jumping states
- **Order Processing**: Pending, processing, shipped, delivered states

---

## Related Patterns

- **Strategy Pattern**: Similar structure but different purpose (algorithms vs states)
- **Template Method**: Defines algorithm skeleton, states implement variations
- **Observer Pattern**: States can notify observers of state changes
- **Command Pattern**: Commands can trigger state transitions
- **Flyweight Pattern**: Share state objects to reduce memory usage
