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
