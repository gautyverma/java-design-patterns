# **Object-Oriented Programming (OOP) - Payment System Design**

## **Overview**
This document explains the implementation of a flexible payment system using core OOP principles including **Inheritance**, **Abstraction**, **Interface Implementation**, and **Polymorphism**.

---

## **Design Problem & Solution**

### **Step 1: Initial Problem - Code Duplication**
**Problem:** Creating separate classes for CreditCard and DebitCard resulted in duplicate code for common attributes.

**Solution:** Create a parent `Card` class to hold shared attributes.

```java
// Parent Class - Card.java
public abstract class Card {
    private String cardNumber;
    private String cardHolderName;
    
    public Card(String cardNumber, String cardHolderName) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
    }
}
```

**Benefit:** 
- ✅ Eliminates code duplication
- ✅ Single source of truth for card attributes
- ✅ Easier maintenance and updates

---

### **Step 2: Adding Abstract Methods**
**Problem:** Different card types (CreditCard, DebitCard) need different payment implementations.

**Solution:** Define an abstract `pay()` method in the parent class and implement it differently in child classes.

```java
// Child Classes implementing pay() differently
public class CreditCard extends Card {
    @Override
    public void pay() {
        System.out.println("Payment made using Credit Card\n...");
    }
}

public class DebitCard extends Card {
    @Override
    public void pay() {
        System.out.println("Payment made using Debit Card\n...");
    }
}
```

**Benefits:**
- ✅ Enforces specific behavior in child classes
- ✅ Maintains contract through abstraction
- ✅ Supports **method overriding** for polymorphic behavior

---

### **Step 3: Extending Beyond Cards - Interface Implementation**
**Problem:** We need to support payment methods like UPI and Wallet that are NOT cards.

**Solution:** Create a `PaymentMethod` interface to define a contract for all payment types.

```java
// Interface - PaymentMethod.java
public interface PaymentMethod {
    void pay();
}
```

Now, both card and non-card payment methods implement this interface:

```java
// Card classes now implement the interface
public abstract class Card implements PaymentMethod { ... }

// Non-card payment methods
public class UPI implements PaymentMethod { ... }
public class Wallet implements PaymentMethod { ... }
```

**Benefits:**
- ✅ Supports multiple payment types without inheritance hierarchy
- ✅ Loose coupling - different implementations of same contract
- ✅ Single Responsibility Principle - each class handles one payment type

---

### **Step 4: Unified Payment Processing - Polymorphism**
**Problem:** Client needs a single way to process different payment types.

**Solution:** Use `PaymentService` with **Runtime Polymorphism** to handle all payment methods uniformly.

```java
// PaymentService.java - Uses Polymorphism
public class PaymentService {
    HashMap<String, PaymentMethod> paymentMethods;
    
    public void addPaymentMethod(String name, PaymentMethod payMethod) {
        paymentMethods.put(name, payMethod);
    }
    
    public void makePayment(String name) {
        PaymentMethod method = paymentMethods.get(name);
        if (method != null) {
            method.pay(); // 🔑 Runtime Polymorphism in action!
        }
    }
}
```

**How it works:**
- At runtime, the JVM determines which `pay()` method to call based on the actual object type
- Same interface, different behavior for each payment method
- No casting or type checking needed

**Benefits:**
- ✅ Clean, maintainable code
- ✅ Easy to extend with new payment methods
- ✅ Runtime flexibility

---

## **Class Structure Diagram**

```
┌──────────────────────┐
│  PaymentMethod       │  (Interface)
│  + pay()             │
└──────────────────────┘
        ▲
        │ implements
        │
    ┌───┴──────────┬────────────────┬─────────────┐
    │              │                │             │
    │              │                │             │
┌───────────┐  ┌──────────┐  ┌──────────┐  ┌──────────┐
│   Card    │  │   UPI    │  │ Wallet   │  │  Other   │
│(Abstract) │  │ (Concrete)│ │(Concrete)│  │ Methods  │
└─────┬─────┘  └──────────┘  └──────────┘  └──────────┘
      │ extends
      │
   ┌──┴──────────────┐
   │                 │
┌──────────┐  ┌──────────┐
│CreditCard│  │DebitCard │
│(Concrete)│  │(Concrete)│
└──────────┘  └──────────┘
```

---

## **Key OOP Principles Applied**

| Principle | Implementation | Benefit |
|-----------|----------------|---------|
| **Encapsulation** | Private attributes with getters/setters in `Card` | Data protection & controlled access |
| **Abstraction** | Abstract `Card` class & `PaymentMethod` interface | Hide implementation details |
| **Inheritance** | `CreditCard` and `DebitCard` extend `Card` | Code reuse, common behavior |
| **Polymorphism** | `PaymentService` handles all `PaymentMethod` types | Flexible, extensible design |
| **Single Responsibility** | Each class handles one payment type | Maintainability |
| **Open/Closed Principle** | Open for extension (new payment methods), closed for modification | Easy to add new payment types |

---

## **Real-World Example - Usage**

```java
// Client.java - How to use the system
PaymentService service = new PaymentService();

// Register multiple payment methods
service.addPaymentMethod("gv-CreditCard", new CreditCard("1234", "Chirag"));
service.addPaymentMethod("gv-DebitCard", new DebitCard("5678", "Deepak"));
service.addPaymentMethod("gv-UPI", new UPI("udapi@0011"));
service.addPaymentMethod("gv-wallet", new Wallet("wallet@00w12"));

// Process payments using polymorphism
service.makePayment("gv-CreditCard");  // Calls CreditCard.pay()
service.makePayment("gv-UPI");         // Calls UPI.pay()
service.makePayment("gv-wallet");      // Calls Wallet.pay()
```

---

## **Extensibility - Adding New Payment Methods**

To add a new payment method like **PhonePe**, simply:


**No modifications needed to existing classes!** This is the power of good OOP design.

---

## **Summary**

This payment system demonstrates how OOP principles create:
- ✅ **DRY Code** - No duplication through inheritance
- ✅ **Flexible Design** - Easy to extend with new payment methods
- ✅ **Clean Code** - Clear separation of concerns
- ✅ **Maintainable System** - Changes isolated to specific classes
- ✅ **Real-world Applicability** - Mimics actual payment processing systems

---
