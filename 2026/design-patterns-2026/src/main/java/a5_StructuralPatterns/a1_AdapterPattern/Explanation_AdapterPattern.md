# Adapter Design Pattern

## Overview

The **Adapter Pattern** is a structural design pattern that allows incompatible interfaces to work together by wrapping an existing class with a new interface. It acts as a bridge between two incompatible classes/interfaces, enabling them to collaborate without modifying their source code. This pattern is particularly useful when integrating third-party libraries or legacy code.

---

## Why Use Adapter Pattern?

- **Legacy Code Integration**: Use old code with new interfaces without modification
- **Third-Party Integration**: Adapt third-party libraries to your system's interface
- **Interface Mismatch**: Bridge incompatible interfaces between components
- **Code Reusability**: Reuse existing classes even if interfaces don't match
- **Reduce Code Duplication**: Avoid rewriting existing functionality
- **Single Responsibility**: Keep classes focused on their core responsibility
- **Open/Closed Principle**: Add new functionality without modifying existing code
- **Loose Coupling**: Decouple client code from specific implementations

---

## Use Cases

- Integrating third-party services (payment gateways, email providers)
- Using legacy database drivers with new code
- Connecting different logging frameworks
- Adapting UI components from different libraries
- Converting data formats between systems
- Using incompatible API responses
- Integrating hardware interfaces
- Converting between file formats

---

## Key Components

| Component | Responsibility |
|-----------|-----------------|
| **Target Interface** | Interface expected by the client |
| **Adaptee/Adapter Source** | Existing class with incompatible interface |
| **Adapter** | Converts adaptee interface to target interface |
| **Client** | Uses target interface without knowing about adaptee |

---

## Two Types of Adapter Pattern

### **Type 1: Class Adapter (Using Inheritance)**
- Uses multiple inheritance (Java doesn't support, use interfaces)
- Adapter inherits from both target and adaptee
- Inflexible, rarely used in Java

### **Type 2: Object Adapter (Using Composition)**
- Adapter wraps adaptee object
- More flexible and commonly used
- Preferred approach in modern development

---

## Implementation

### 1. Object Adapter Pattern (Recommended)

**Target Interface - NotificationService.java:**

```java
// This is what the client expects
public interface NotificationService {
  void send(String to, String subject, String body);
}
```

**Adaptee - SendGridService.java:**

```java
// Third-party service with incompatible interface
public class SendGridService {
  // This method has different signature/behavior
  public void sendEmail(String recipient, String title, String message) {
    System.out.println("SendGrid: Sending email");
    System.out.println("To: " + recipient);
    System.out.println("Title: " + title);
    System.out.println("Message: " + message);
  }
}
```

**Existing Target Implementation - EmailNotificationService.java:**

```java
// Current implementation using target interface
public class EmailNotificationService implements NotificationService {
  @Override
  public void send(String to, String subject, String body) {
    System.out.println("Email: Sending notification");
    System.out.println("To: " + to);
    System.out.println("Subject: " + subject);
    System.out.println("Body: " + body);
  }
}
```

**Adapter - SendGridAdapter.java:**

```java
// Adapter that wraps SendGridService and implements NotificationService
public class SendGridAdapter implements NotificationService {
  // Compose the adaptee
  private SendGridService sendGridService;
  
  // Accept adaptee in constructor
  public SendGridAdapter(SendGridService sendGridService) {
    this.sendGridService = sendGridService;
  }
  
  // Implement target interface by delegating to adaptee
  @Override
  public void send(String to, String subject, String body) {
    // Convert interface (map method parameters)
    sendGridService.sendEmail(to, subject, body);
  }
}
```

**Key Points:**
- Adapter implements target interface
- Adapter wraps adaptee object (composition)
- Adapter maps target methods to adaptee methods
- Client uses adapter through target interface
- Adaptee interface remains unchanged

---

## Adapter Variants

### **Approach 1: Object Adapter (Shown Above)**
- Pros: Flexible, uses composition, avoids multiple inheritance issues
- Cons: Wrapper adds slight overhead
- Best for: Wrapping incompatible classes

### **Approach 2: Class Adapter (Inheritance)**
```java
// Java doesn't support multiple inheritance, but interface approach:
public class PayPalAdapter extends PayPalSDK implements PaymentService {
  @Override
  public void pay(double amount, String currency) {
    executePayment(amount, currency);
  }
}
```

### **Approach 3: Two-Way Adapter (Bidirectional)**
```java
public class BidirectionalAdapter implements OldInterface, NewInterface {
  private LegacySystem legacy;
  private ModernSystem modern;
  
  public BidirectionalAdapter(LegacySystem legacy, ModernSystem modern) {
    this.legacy = legacy;
    this.modern = modern;
  }
  
  // Implement both interfaces, converting between them
}
```

### **Approach 4: Adapter Chain (Multiple Adapters)**
```java
// Stack multiple adapters
NotificationService adapter1 = new EmailAdapter(emailService);
NotificationService adapter2 = new EncryptionAdapter(adapter1);
NotificationService adapter3 = new LoggingAdapter(adapter2);
adapter3.send(to, subject, body);
```

---

## Usage Example

**Client.java** - Demonstrates adapter pattern:

```java
public class Client {
  public static void main(String[] args) {
    
    // Using compatible email service directly
    NotificationService emailService = new EmailNotificationService();
    emailService.send(
      "customer@ab.com",
      "Your order has been shipped!",
      "Order details are ...."
    );
    
    System.out.println("------------------------------");
    
    // Using third-party SendGridService through adapter
    // SendGridService doesn't implement NotificationService
    // But SendGridAdapter bridges the gap
    NotificationService sendGridAdapter = 
      new SendGridAdapter(new SendGridService());
    
    sendGridAdapter.send(
      "customer@ab.com",
      "Your order has been shipped!",
      "Order details are ...."
    );
  }
}
```

### Output

```
Email: Sending notification
To: customer@ab.com
Subject: Your order has been shipped!
Body: Order details are ....
------------------------------
SendGrid: Sending email
To: customer@ab.com
Title: Your order has been shipped!
Message: Order details are ....
```

---

## Code Flow

1. Client calls method on target interface
2. Adapter receives the call
3. Adapter converts parameters if needed
4. Adapter delegates to adaptee method
5. Adaptee executes original functionality
6. Result returned through adapter to client
7. Client receives result in expected format

---

## Real-World Examples

| Scenario | Adaptee | Target | Adapter |
|----------|---------|--------|---------|
| **SendGrid Integration** | SendGridService | NotificationService | SendGridAdapter |
| **PayPal Payment** | PayPalSDK | PaymentProcessor | PayPalAdapter |
| **Database Driver** | LegacyOracleDriver | JDBCDriver | OracleJDBCAdapter |
| **Logging Framework** | Log4jLogger | SLF4JLogger | Log4jSLF4JAdapter |
| **HTTP Client** | OkHttpClient | HttpUrlConnection | OkHttpAdapter |
| **Graphics Rendering** | OpenGLRenderer | DirectXInterface | OpenGLDirectXAdapter |
| **Date Format** | LegacyDateFormat | JavaTimeFormat | DateFormatAdapter |
| **Payment Gateway** | StripeAPI | PaymentGateway | StripeAdapter |

---

## Advantages

✅ **Legacy Code Integration**: Use old code with new interfaces  
✅ **Third-Party Integration**: Adapt external libraries easily  
✅ **Code Reusability**: Reuse existing functionality  
✅ **Flexibility**: Swap implementations without client changes  
✅ **Loose Coupling**: Client unaware of adaptee details  
✅ **Open/Closed Principle**: Add adapters without modifying existing code  
✅ **Single Responsibility**: Separation of concerns  
✅ **Maintainability**: Centralized conversion logic  

---

## Disadvantages

❌ **Additional Code**: Adapter adds extra layer of complexity  
❌ **Runtime Overhead**: Extra method call through adapter  
❌ **Debugging Difficulty**: Stack trace includes adapter layer  
❌ **Over-Engineering**: May be overkill for simple cases  
❌ **Maintenance Overhead**: Must maintain adapter if adaptee changes  
❌ **Not a Fix**: Masks design issues rather than fixing them  
❌ **Performance Impact**: Additional object wrapping reduces performance  

---

## When to Use Adapter Pattern

**Use Adapter when:**
- ✅ You need to use a class with incompatible interface
- ✅ Integrating third-party libraries
- ✅ Using legacy code with new systems
- ✅ Multiple incompatible interfaces need to work together
- ✅ You can't modify the original classes
- ✅ You want unified interface for different implementations
- ✅ Avoiding code duplication in conversion logic

**Don't use when:**
- ❌ Interfaces are already compatible
- ❌ Simple type casting would work
- ❌ Adding unnecessary complexity
- ❌ You can modify source code of incompatible class
- ❌ Performance is critical (avoid overhead)
- ❌ Better to redesign system interfaces

---

## Implementation Guidelines

### **Creating an Effective Adapter**

1. **Identify Mismatch**: Clearly understand incompatible interfaces
2. **Design Mapping**: Plan parameter and method mappings
3. **Implement Target**: Adapter must fully implement target interface
4. **Wrap Adaptee**: Hold adaptee instance or reference
5. **Delegate Calls**: Implement methods by delegating to adaptee
6. **Handle Conversions**: Transform data between formats if needed

### **Best Practices**

- Keep adapter thin and focused
- Don't add extra functionality beyond adaptation
- Document mappings between interfaces
- Handle exceptions appropriately
- Consider two-way adapters if needed
- Use composition over inheritance
- Test adapter thoroughly

---

## Common Pitfalls to Avoid

❌ **Overloading Adapter**: Don't add business logic in adapter  
❌ **Ignoring Exception Handling**: Handle adaptee exceptions properly  
❌ **Creating Heavy Adapters**: Keep adapters lightweight  
❌ **Masking Design Issues**: Don't use adapters to hide bad design  
❌ **Poor Documentation**: Document interface mappings clearly  
❌ **Tight Coupling**: Don't make adapter depend on specific adaptee implementation  

---

## Real-World Analogy

**Power Adapter:**
- **Target Interface**: International power outlet (what device expects)
- **Adaptee**: Country-specific power outlet (different standard)
- **Adapter**: Physical power converter (bridges the gap)
- **Client**: Electrical device (works with adapter)

Just like a power adapter lets a US device work with European outlets, design adapters let incompatible software components work together!

---

## Conclusion

The Adapter Pattern is invaluable for integrating incompatible interfaces without modifying existing code. It promotes code reusability, maintains the Open/Closed Principle, and provides a clean solution for third-party library integration. Use it strategically when interface mismatches are unavoidable and modification is not an option.

**Remember**: The adapter is meant to be a temporary bridge between incompatible interfaces, not a permanent solution for poor design. If possible, improve interface design; if not, adapters are your solution!

