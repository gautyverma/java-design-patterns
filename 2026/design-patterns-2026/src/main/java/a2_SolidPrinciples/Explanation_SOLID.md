# SOLID Principles

## Overview

| Principle | Full Name | Purpose |
|-----------|-----------|---------|
| **S** | Single Responsibility Principle (SRP) | One reason to change |
| **O** | Open/Closed Principle (OCP) | Open for extension, closed for modification |
| **L** | Liskov Substitution Principle (LSP) | Subtypes must be substitutable |
| **I** | Interface Segregation Principle (ISP) | Many client-specific interfaces |
| **D** | Dependency Inversion Principle (DIP) | Depend on abstractions, not concretions |


---
## **Single Responsibility Principle (SRP)**
### What is SRP?
A class should have **only one reason to change** - meaning only one responsibility or job.

### Bad Implementation (Violates SRP)

**BadImpl/Invoice.java** - Multiple responsibilities in one class:

```java
public class Invoice {
    private double amount;

    public void generateInvoice() {
    }      // Responsibility 1: Generate

    public void saveToDatabase() {
    }       // Responsibility 2: Persist

    public void sendEmail() {
    }            // Responsibility 3: Email
}
```

**Problems:**

- 3 reasons to change the class
- Hard to test
- Tightly coupled
- Low reusability

---

### Good Implementation (Follows SRP)

**GoodImpl/Invoice.java** - Only business logic:

```java
public class Invoice {
    private double amount;

    public void generateInvoice() {
    }      // Single Responsibility
}
```

**GoodImpl/InvoiceRepository.java** - Database responsibility:

```java
public class InvoiceRepository {
    public void saveToDatabase() {
    }       // Single Responsibility
}
```

**GoodImpl/EmailService.java** - Email responsibility:

```java
public class EmailService {
    public void sendEmail() {
    }            // Single Responsibility
}
```

### Golden Rule ??

> **If you can describe a class in one sentence without "and/or", it follows SRP.**

---

## 2. Open/Closed Principle (OCP)

### Definition
> A class should be **open for extension** but **closed for modification**. 
> Add new functionality through extension (inheritance/composition) without modifying existing code.

### ❌ Bad Implementation (Violates OCP)

**Problem:** Must modify the existing method to add new payment types

**PaymentProcessor.java** — Hard-coded conditionals:
```java
public class PaymentProcessor {
  /**
   * Issue: Adding new payment types [UPI...] requires modifying this method,
   * which violates OCP. This can lead to bugs and maintenance issues.
   */
  public void processPayment(String paymentType, double amount) {
    if (paymentType.equals("CreditCard")) {
      System.out.println("Processing credit card payment of amount: " + amount);
    } else if (paymentType.equals("DebitCard")) {
      System.out.println("Processing DebitCard payment of amount: " + amount);
    } else if (paymentType.equals("PayPal")) {
      System.out.println("Processing PayPal payment of amount: " + amount);
    } else {
      throw new IllegalArgumentException("Unsupported payment type: " + paymentType);
    }
  }
}
```

## ✅ Good Implementation (Follows OCP)

### 💡 Approach
- Use **interface (abstraction)**
- Add new behavior via **new classes**
- No modification to existing code

---

### Step 1: Create Interface
```java
public interface Payment {
    void pay(double amount);
}
```

### Step 2: Implement Payment Types
```java 
public class CreditCardPayment implements Payment {
public void pay(double amount) {
System.out.println("Processing credit card payment: " + amount);
}
}

public class DebitCardPayment implements Payment {
public void pay(double amount) {
System.out.println("Processing debit card payment: " + amount);
}
}

public class PayPalPayment implements Payment {
public void pay(double amount) {
System.out.println("Processing PayPal payment: " + amount);
}
}
```
### Step 3: Use Abstraction in Processor
```java
public class PaymentProcessor {

    public void processPayment(Payment payment, double amount) {
        payment.pay(amount);
    }
}
```
#### Usage
```java 
Payment payment = new CreditCardPayment();
PaymentProcessor processor = new PaymentProcessor();
processor.processPayment(payment, 1000);
```
### Extend Without Modification
```java
public class UpiPayment implements Payment {
public void pay(double amount) {
System.out.println("Processing UPI payment: " + amount);
}
}
```
### Key Idea
Add new classes → NO change in existing code
Achieves true Open for Extension, Closed for Modification