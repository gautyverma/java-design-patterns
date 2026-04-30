# Singleton Pattern

## Overview

The **Singleton Pattern** is a creational design pattern that ensures a class has **only one instance** and provides a global point of access to it. It restricts object instantiation to a single object, preventing multiple instances from being created.

---

## Why Use Singleton Pattern?

- **Single Instance**: Ensures only one object exists throughout application lifetime
- **Global Access Point**: Provides global point of access without parameter passing
- **Lazy Initialization**: Create instance only when first needed
- **Resource Control**: Manage expensive resources (database connections, thread pools)
- **Consistency**: Guarantee state consistency across application
- **Thread Safety**: Prevent race conditions in multi-threaded environments

---

## Use Cases

- Database connection pools
- Logger instances
- Configuration managers
- Thread pool managers
- Cache managers
- Application settings holder
- Session managers
- Text formatting utilities

---

## Key Components

| Component | Responsibility |
|-----------|-----------------|
| **Private Constructor** | Prevent external instantiation |
| **Static Instance** | Hold the single instance of the class |
| **Static Access Method** | Provide global access to instance |

---

## Implementation

### 1. Singleton Class with Thread Safety

**Singleton.java** - Thread-safe singleton pattern:

```java
class Singleton {
  // 1. Create static obj
  static Singleton singletonObject;

  // 2. Make private Constructor - using this we can't create outside its own class using new
  private Singleton() {
    System.out.println("Instance Created");
  }

  // 3. Make method to access singleton object created
  public static synchronized Singleton getInstance() {
    if (singletonObject == null) {
      System.out.println("1st thread is creating instance");
      singletonObject = new Singleton();
    } else {
      System.out.println("Instance already created by 1st thread, returning existing instance");
    }
    return singletonObject;
  }
}
```

**Key Points:**
- `static Singleton singletonObject`: Holds the single instance
- `private Singleton()`: Prevents instantiation from outside
- `synchronized getInstance()`: Ensures thread-safe lazy initialization
- `if (singletonObject == null)`: Creates instance only once
- Subsequent calls return existing instance

---

## Thread-Safe Variants

### **Approach 1: Synchronized Method (Shown Above)**
- Pros: Simple, lazy initialization
- Cons: Performance overhead on every call

### **Approach 2: Double-Checked Locking**
```java
public static Singleton getInstance() {
  if (singletonObject == null) {
    synchronized (Singleton.class) {
      if (singletonObject == null) {
        singletonObject = new Singleton();
      }
    }
  }
  return singletonObject;
}
```

### **Approach 3: Eager Initialization**
```java
static Singleton singletonObject = new Singleton();

public static Singleton getInstance() {
  return singletonObject;
}
```

### **Approach 4: Bill Pugh Singleton (Best Practice)**
```java
class Singleton {
  private Singleton() {}
  
  static class SingletonHolder {
    static Singleton instance = new Singleton();
  }
  
  public static Singleton getInstance() {
    return SingletonHolder.instance;
  }
}
```

---

## Usage Example

**Main.java** - Demonstrates thread-safe singleton:

```java
public class Main {
  public static void main(String[] args) {

    Thread t1 =
        new Thread(
            new Runnable() {
              @Override
              public void run() {
                Singleton obj = Singleton.getInstance();
              }
            });

    Thread t2 =
        new Thread(
            new Runnable() {
              @Override
              public void run() {
                Singleton obj = Singleton.getInstance();
              }
            });
    t1.start();
    t2.start();
  }
}
```

### Output

```
1st thread is creating instance
Instance Created
Instance already created by 1st thread, returning existing instance
```

---

## Code Flow

1. Two threads call getInstance() simultaneously
2. Synchronized lock prevents both from entering critical section
3. First thread creates instance and exits
4. Second thread checks condition, finds instance exists
5. Returns existing instance without creating new one
6. Both threads work with same singleton instance

---

## Advantages

✅ **Single Instance**: Guaranteed only one instance exists  
✅ **Global Access**: Easy global access without parameters  
✅ **Lazy Initialization**: Create only when needed  
✅ **Thread Safe**: Multiple implementations available  
✅ **Resource Control**: Manage expensive resources efficiently  
✅ **Consistency**: Centralized state management  

---

## Disadvantages

❌ **Hides Dependencies**: Hard to identify unneeded dependencies  
❌ **Testing Difficulty**: Hard to mock singletons in tests  
❌ **Global State**: Makes code less testable and maintainable  
❌ **Thread Safety Overhead**: Synchronization performance cost  
❌ **Hides Class Dependencies**: Can mask poor design decisions  
❌ **Violates SRP**: Often has multiple responsibilities  

---

## Real-World Analogies

- **Government**: Only one president of a country at a time
- **Database Connection Pool**: Single pool manages all connections
- **Logger**: Single logger instance logs all application events
- **Cache Manager**: Single cache instance serves entire application
- **Configuration Manager**: Single config holds application settings
- **Theme Manager**: Single theme manager controls app appearance
