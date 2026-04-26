# Strategy Pattern

## Overview

The **Strategy Pattern** is a behavioral design pattern that defines a family of algorithms, encapsulates each one, and makes them interchangeable. It lets the algorithm vary independently from clients that use it.

---

## Why Use Strategy Pattern?

- **Algorithm Encapsulation**: Different algorithms are encapsulated in separate classes
- **Runtime Flexibility**: Change algorithm behavior at runtime without modifying client code
- **Single Responsibility**: Each strategy class has one specific algorithm
- **Open/Closed Principle**: Add new strategies without changing existing code
- **Eliminate Conditional Logic**: Replace complex if-else chains with strategy objects

---

## Use Cases

- Payment processing (credit card, PayPal, bank transfer)
- Sorting algorithms (quick sort, merge sort, bubble sort)
- Compression algorithms (ZIP, RAR, GZIP)
- Travel route planning (fastest, shortest, scenic routes)
- Text formatting (plain text, HTML, Markdown)

---

## Key Components

| Component | Responsibility |
|-----------|-----------------|
| **Strategy** | Interface/common contract for all concrete strategies |
| **Concrete Strategy** | Implements specific algorithm/strategy |
| **Context** | Maintains reference to strategy and delegates work to it |

---

## Implementation

### 1. Strategy Interface

**PaymentStrategy.java** - Defines the strategy contract:

```java
// interface for strategy
interface PaymentStrategy {
  void pay(int amount);
}
```

**Key Points:**
- Defines `pay()` method that all payment strategies must implement
- Takes `amount` parameter for payment processing
- Provides common interface for different payment methods

---

### 2. Concrete Strategies

**CreditCardPayment.java** - Credit card payment strategy:

```java
class CreditCardPayment implements PaymentStrategy {
  private String cardNumber;

  public CreditCardPayment(String cardNumber) {
    this.cardNumber = cardNumber;
  }

  @Override
  public void pay(int amount) {
    System.out.println("Paid " + amount + " using credit card: " + cardNumber);
  }
}
```

**DebitCardPayment.java** - Debit card payment strategy:

```java
class DebitCardPayment implements PaymentStrategy {
  private String cardNumber;

  public DebitCardPayment(String cardNumber) {
    this.cardNumber = cardNumber;
  }

  @Override
  public void pay(int amount) {
    System.out.println("Paid " + amount + " using debit card: " + cardNumber);
  }
}
```

**Key Points:**
- Both implement `PaymentStrategy` interface
- Each encapsulates specific payment logic
- Maintain their own state (card number)
- Provide different payment implementations

---

### 3. Context Class

**PaymentService.java** - Context that uses strategies:

```java
class PaymentService {
  private PaymentStrategy paymentStrategy;

  public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
    this.paymentStrategy = paymentStrategy;
  }

  public void processPayment(int amount) {
    if (paymentStrategy == null) {
      throw new IllegalStateException("Payment strategy not set");
    }
    paymentStrategy.pay(amount);
  }
}
```

**Key Points:**
- Maintains reference to current `PaymentStrategy`
- `setPaymentStrategy()` allows changing strategy at runtime
- `processPayment()` delegates to current strategy
- Includes validation to ensure strategy is set

---

## Usage Example

**StrategyPattern.java** - Demonstrates strategy pattern:

```java
public class StrategyPattern {
  public static void main(String[] args) {
    PaymentService paymentService = new PaymentService();

    // Using credit card payment strategy
    paymentService.setPaymentStrategy(new CreditCardPayment("1234-5678-9012-3456"));
    paymentService.processPayment(100);

    // Using debit card payment strategy
    paymentService.setPaymentStrategy(new DebitCardPayment("9876-5432-1098-7654"));
    paymentService.processPayment(200);
  }
}
```

### Output

```
Paid 100 using credit card: 1234-5678-9012-3456
Paid 200 using debit card: 9876-5432-1098-7654
```

---

## Code Flow

1. Create PaymentService context
2. Set CreditCardPayment strategy with card number
3. processPayment() delegates to strategy's pay() method
4. Change to DebitCardPayment strategy
5. processPayment() uses new strategy for payment

---

## Advantages

✅ **Flexibility**: Change algorithms at runtime without modifying context  
✅ **Extensibility**: Add new strategies without changing existing code  
✅ **Testability**: Test strategies independently  
✅ **Clean Code**: Eliminates complex conditional statements  
✅ **Reusability**: Strategies can be reused across different contexts  

---

## Disadvantages

❌ **Increased Complexity**: More classes and interfaces to manage  
❌ **Client Knowledge**: Client must know which strategy to use  
❌ **Communication Overhead**: Strategy selection requires understanding of options  
❌ **Runtime Errors**: Wrong strategy selection can cause issues  

---

## Real-World Analogies

- **Navigation Apps**: Choose route type (fastest, shortest, avoid tolls)
- **Text Editors**: Select sorting algorithm (alphabetical, date, size)
- **Media Players**: Choose compression format (MP3, AAC, FLAC)
- **E-commerce**: Select payment method (credit card, PayPal, bank transfer)
- **Travel Booking**: Choose transportation mode (flight, train, bus)

---
