# Proxy Design Pattern

## Overview

The **Proxy Pattern** is a structural design pattern that provides a surrogate or placeholder for another object to control access to it. A proxy is an object that acts as an intermediary between the client and the real object, allowing you to control access, delay initialization, log calls, or add security checks before reaching the actual object.

---

## Why Use Proxy Pattern?

- **Lazy Initialization**: Defer expensive object creation until actually needed
- **Access Control**: Restrict access to sensitive objects
- **Logging & Monitoring**: Log method calls and monitor usage
- **Caching**: Cache expensive computations or data
- **Security**: Add authentication/authorization before accessing real object
- **Remote Access**: Represent remote objects locally
- **Performance Optimization**: Reduce load by controlling when objects are created
- **Transparency**: Client unaware of proxy - sees uniform interface

---

## Use Cases

- Image loading systems (lazy load images on demand)
- Database connection pools
- Remote Method Invocation (RMI)
- Web services and API calls
- Authentication/Authorization systems
- Caching systems (Redis, Memcached)
- Virtual proxies (defer expensive operations)
- Smart references (protection proxies)
- Logging and monitoring
- Transaction management

---

## Key Components

| Component | Responsibility |
|-----------|-----------------|
| **Subject Interface** | Defines common interface for real & proxy objects |
| **RealSubject** | Actual object that proxy represents |
| **Proxy** | Surrogate that wraps real subject |
| **Client** | Works with proxy through subject interface |

---

## Types of Proxy Pattern

### **1. Virtual Proxy (Lazy Initialization)**
- Defers creation of expensive objects until needed
- Most common type
- Used in your image loading example

### **2. Remote Proxy**
- Represents object in different address space
- Used for RMI, web services
- Handles network communication

### **3. Protection Proxy**
- Controls access to real object
- Implements security/authorization
- Restricts who can access object

### **4. Smart Reference**
- Provides additional functionality
- Logging, caching, reference counting
- Transparent to client

---

## Implementation

### 1. Virtual Proxy Pattern (Lazy Initialization)

**Subject Interface - Image.java:**

```java
// Interface that both real and proxy objects implement
public interface Image {
  void display();
}
```

**Real Subject - RealImage.java:**

```java
// Actual object that performs expensive operations
public class RealImage implements Image {
  private String fileName;
  
  public RealImage(String fileName) {
    this.fileName = fileName;
    loadImageFromDisk();  // Expensive operation
  }
  
  private void loadImageFromDisk() {
    System.out.println("Loading image from disk: " + fileName);
    try {
      Thread.sleep(2000);  // Simulate time-consuming loading
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
  
  @Override
  public void display() {
    System.out.println("Displaying image: " + fileName);
  }
}
```

**Proxy - ProxyImage.java:**

```java
// Proxy that controls access and defers real object creation
public class ProxyImage implements Image {
  private String fileName;
  private RealImage realImage;  // Holds reference to real object
  
  public ProxyImage(String fileName) {
    this.fileName = fileName;
    // Don't create real object yet - lazy initialization
  }
  
  @Override
  public void display() {
    // Create real object only when first needed
    if (realImage == null) {
      realImage = new RealImage(fileName);
    }
    // Delegate to real object
    realImage.display();
  }
}
```

**Key Points:**
- Proxy implements same interface as real object
- Proxy holds reference to real object
- Real object created only when first used
- Subsequent calls reuse cached real object
- Client unaware of proxy - sees uniform interface

---

## Proxy Variants

### **Approach 1: Virtual Proxy (Shown Above)**
- Pros: Defers expensive object creation, saves resources
- Cons: First call has initialization overhead
- Best for: Expensive objects that might not be used

### **Approach 2: Remote Proxy**
```java
public class RemoteImageProxy implements Image {
  private String imageUrl;
  
  public RemoteImageProxy(String imageUrl) {
    this.imageUrl = imageUrl;
  }
  
  @Override
  public void display() {
    // Fetch from remote server
    byte[] imageData = fetchFromServer(imageUrl);
    // Display locally
    displayImage(imageData);
  }
}
```

### **Approach 3: Protection Proxy**
```java
public class ProtectionProxy implements Image {
  private RealImage realImage;
  private User currentUser;
  
  public ProtectionProxy(RealImage realImage, User user) {
    this.realImage = realImage;
    this.currentUser = user;
  }
  
  @Override
  public void display() {
    if (currentUser.hasPermission("VIEW_IMAGE")) {
      realImage.display();
    } else {
      System.out.println("Access Denied!");
    }
  }
}
```

### **Approach 4: Caching Proxy**
```java
public class CachingProxy implements Image {
  private RealImage realImage;
  private static Map<String, RealImage> cache = new HashMap<>();
  
  public CachingProxy(String fileName) {
    if (!cache.containsKey(fileName)) {
      cache.put(fileName, new RealImage(fileName));
    }
    this.realImage = cache.get(fileName);
  }
  
  @Override
  public void display() {
    realImage.display();
  }
}
```

---

## Usage Example

**Client.java** - Demonstrates proxy pattern:

```java
public class Client {
  public static void main(String[] args) {
    
    // Create proxy objects (doesn't load images yet)
    Image image1 = new ProxyImage("photo1.jpg");
    Image image2 = new ProxyImage("photo2.jpg");
    
    System.out.println("Proxies created, but images not loaded");
    System.out.println();
    
    // First display - loads image (takes 2 seconds)
    System.out.println("First display call:");
    image1.display();
    System.out.println();
    
    // Second display - uses cached real image (instant)
    System.out.println("Second display call:");
    image1.display();
    System.out.println();
    
    // Different image - loads new image (takes 2 seconds)
    System.out.println("Display different image:");
    image2.display();
  }
}
```

### Output

```
Proxies created, but images not loaded

First display call:
Loading image from disk: photo1.jpg
Displaying image: photo1.jpg

Second display call:
Displaying image: photo1.jpg

Display different image:
Loading image from disk: photo2.jpg
Displaying image: photo2.jpg
```

---

## Code Flow

1. Client creates proxy instead of real object (lightweight)
2. Client calls method on proxy interface
3. Proxy checks if real object exists
4. If not, proxy creates real object (lazy initialization)
5. Proxy delegates method call to real object
6. Real object executes actual operation
7. Subsequent calls reuse existing real object
8. No initialization overhead on second call

---

## Proxy Types Comparison

| Type | Purpose | When to Use |
|------|---------|------------|
| **Virtual** | Lazy initialization | Heavy objects |
| **Remote** | Remote representation | Network objects |
| **Protection** | Access control | Secured objects |
| **Smart Reference** | Additional features | Logging, caching |

---

## Advantages

✅ **Lazy Initialization**: Defer expensive operations until needed  
✅ **Access Control**: Restrict access to real object  
✅ **Performance**: Reduce resource usage through caching  
✅ **Transparency**: Client sees unified interface  
✅ **Security**: Add authentication/authorization  
✅ **Logging**: Monitor and track object access  
✅ **Remote Access**: Handle network communication  
✅ **Decoupling**: Client unaware of real implementation  

---

## Disadvantages

❌ **Complexity**: Additional layer adds code complexity  
❌ **Performance Overhead**: First call slower due to initialization  
❌ **Debugging Difficulty**: Multiple object layers to trace  
❌ **Memory Usage**: Extra proxy object in memory  
❌ **Thread Safety**: Must handle concurrent access carefully  
❌ **Maintenance**: Proxy must mirror real object interface  
❌ **Type Checking**: `instanceof` checks may fail  
❌ **Serialization**: Proxies may have serialization issues  

---

## Real-World Applications

| Application | Real Object | Proxy | Purpose |
|-------------|-------------|-------|---------|
| **Image Loading** | RealImage | ProxyImage | Lazy load on demand |
| **Database** | DatabaseConnection | ConnectionPool | Control access, pool |
| **Remote Server** | ServerObject | RemoteProxy | Network access |
| **File System** | LargeFile | VirtualFile | Defer loading |
| **Authentication** | SecureResource | AuthProxy | Check permissions |
| **Cache** | ExpensiveQuery | CacheProxy | Store results |
| **Web Service** | RESTService | ServiceProxy | Monitor calls |
| **Thread Pool** | Worker | ThreadProxy | Manage execution |

---

## When to Use Proxy Pattern

**Use Proxy when:**
- ✅ Object creation is expensive (virtual proxy)
- ✅ Need to control access to object
- ✅ Want to add security/authentication
- ✅ Need remote object representation
- ✅ Want to implement caching
- ✅ Need to log/monitor access
- ✅ Object might not always be used
- ✅ Want lazy initialization

**Don't use when:**
- ❌ Simple direct access is sufficient
- ❌ Performance overhead matters greatly
- ❌ Object creation is trivial
- ❌ Adding unnecessary complexity
- ❌ Type flexibility is critical
- ❌ Runtime performance is critical

---

## Implementation Guidelines

### **Creating an Effective Proxy**

1. **Define Subject**: Create interface for real and proxy
2. **Understand Purpose**: Determine proxy type needed
3. **Implement Controls**: Add necessary access/lazy logic
4. **Delegate Calls**: Forward to real object properly
5. **Handle State**: Manage initialization and caching
6. **Maintain Interface**: Keep same interface as real object

### **Best Practices**

- Keep proxy lightweight
- Document proxy type clearly
- Handle thread safety if needed
- Consider performance implications
- Test real object separately from proxy
- Don't add business logic to proxy
- Keep interface consistent

---

## Proxy vs Lazy Initialization Pattern

**Lazy Initialization without Proxy:**
```java
public class Service {
  private ExpensiveResource resource;
  
  public ExpensiveResource getResource() {
    if (resource == null) {
      resource = new ExpensiveResource();
    }
    return resource;
  }
}
```

**Lazy Initialization with Proxy:**
```java
public class ServiceProxy extends Service {
  @Override
  public ExpensiveResource getResource() {
    if (super.getResource() == null) {
      return new ExpensiveResource();
    }
    return super.getResource();
  }
}
```

---

## Common Pitfalls to Avoid

❌ **Over-Engineering**: Don't use proxy for trivial access control  
❌ **Not Mirroring Interface**: Proxy must have all real object methods  
❌ **Ignoring Thread Safety**: Consider concurrent access  
❌ **Performance Negligence**: Measure overhead impact  
❌ **Complex Logic**: Keep proxy logic simple  
❌ **Poor Documentation**: Document proxy type and purpose  
❌ **Type Visibility**: Avoid exposing proxy type to client  

---

## Real-World Analogy

**Bank Account:**
- **Real Subject**: Actual bank account with money
- **Proxy**: ATM card/interface
- **Access Control**: PIN authentication before withdrawals
- **Security**: Checks balance, limits, fraud detection
- **Result**: You don't handle real account directly, use proxy (card) instead!

---

## Conclusion

The Proxy Pattern is invaluable for controlling access to objects, deferring expensive operations, and adding cross-cutting concerns like security and logging. The image loading example perfectly demonstrates how proxy can improve performance by loading images only when needed, while maintaining a transparent interface to the client.

**Remember**: Use proxy when you need to control access or defer operations, but keep the proxy lightweight and focused. The goal is to provide transparent control without exposing the proxy's existence to the client!

