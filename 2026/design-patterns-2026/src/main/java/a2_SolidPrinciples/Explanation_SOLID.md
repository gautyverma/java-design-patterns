# SOLID Principles

## Overview

| Principle | Full Name                             | Purpose                                     |
|-----------|---------------------------------------|---------------------------------------------|
| **S**     | Single Responsibility Principle (SRP) | One reason to change                        |
| **O**     | Open/Closed Principle (OCP)           | Open for extension, closed for modification |
| **L**     | Liskov Substitution Principle (LSP)   | Subtypes must be substitutable              |
| **I**     | Interface Segregation Principle (ISP) | Many client-specific interfaces             |
| **D**     | Dependency Inversion Principle (DIP)  | Depend on abstractions, not concretions     |

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
processor.

processPayment(payment, 1000);
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

---

## **Liskov Substitution Principle (LSP)**

### What is LSP?

Objects of a superclass should be replaceable with objects of its subclasses **without breaking the application**.

In simple terms:  
👉 If class **B** is a subtype of class **A**, then we should be able to use **B** anywhere we use **A** without
unexpected behavior.

---

### Bad Implementation (Violates LSP)

**BadImpl/Bird.java**

```java
public class Bird {
    public void fly() {
        System.out.println("Bird is flying");
    }
}
```

**BadImpl/Ostrich.java**

```java
public class Ostrich extends Bird {
    @Override
    public void fly() {
        throw new UnsupportedOperationException("Ostrich can't fly");
    }
}
```

**Usage**

```java
public class Main {
    public static void main(String[] args) {
        Bird bird = new Ostrich();
        bird.fly(); // Runtime error ❌
    }
}
```

**Problems:**

- Subclass changes expected behavior
- Breaks polymorphism
- Causes runtime errors
- Violates trust in inheritance

---

### Good Implementation (Follows LSP)

### Class Hierarchy (Good Design)

```
Bird (abstract)
├── FlyingBird (abstract)
│     └── Sparrow
└── Ostrich
```

**GoodImpl/Bird.java**

```java
public abstract class Bird {
}
```

**GoodImpl/FlyingBird.java**

```java
public abstract class FlyingBird extends Bird {
    public void fly() {
        System.out.println("Flying...");
    }
}
```

**GoodImpl/Sparrow.java**

```java
public class Sparrow extends FlyingBird {
}
```

**GoodImpl/Ostrich.java**

```java
public class Ostrich extends Bird {
    // No fly method ✅
}
```

**Usage**

```java
public class Main {
    public static void main(String[] args) {
        FlyingBird bird = new Sparrow();
        bird.fly(); // Works fine ✅
    }
}
```

---

## **Interface Segregation Principle (ISP)**

### What is ISP?

Clients should not be forced to depend on methods they do not use. Prefer many specific interfaces over one general-purpose interface.

---

### Bad Implementation (Violates ISP)

**Problem:** A single interface with multiple responsibilities forces all implementers to provide implementations for methods they don't need.

**BadImpl/Worker.java**

```java
public interface Worker {
    void work();
    void eat();
}
```

**BadImpl/HumanWorker.java**

```java
public class HumanWorker implements Worker {
    @Override
    public void work() {
        System.out.println("Human is working");
    }

    @Override
    public void eat() {
        System.out.println("Human is eating");
    }
}
```

**BadImpl/RobotWorker.java**

```java
public class RobotWorker implements Worker {
    @Override
    public void work() {
        System.out.println("Robot is working");
    }

    @Override
    public void eat() {
        throw new UnsupportedOperationException("Robot can't eat");
    }
}
```

**Problems:**

- Robots are forced to implement eat(), leading to runtime errors or empty implementations
- Violates ISP by making interfaces too broad
- Hard to maintain and extend

---

### Good Implementation (Follows ISP)

### 💡 Approach

- Split the interface into smaller, more specific interfaces
- Clients implement only the interfaces they need

---

**GoodImpl/Workable.java**

```java
public interface Workable {
    void work();
}
```

**GoodImpl/Eatable.java**

```java
public interface Eatable {
    void eat();
}
```

**GoodImpl/HumanWorker.java**

```java
public class HumanWorker implements Workable, Eatable {
    @Override
    public void work() {
        System.out.println("Human is working");
    }

    @Override
    public void eat() {
        System.out.println("Human is eating");
    }
}
```

**GoodImpl/RobotWorker.java**

```java
public class RobotWorker implements Workable {
    @Override
    public void work() {
        System.out.println("Robot is working");
    }
}
```

**Usage**

```java
public class Main {
    public static void main(String[] args) {
        Workable human = new HumanWorker();
        human.work(); // Human is working

        Workable robot = new RobotWorker();
        robot.work(); // Robot is working

        Eatable eater = new HumanWorker();
        eater.eat(); // Human is eating
    }
}
```

### Key Idea

Separate interfaces → Clients depend only on what they use
Achieves better decoupling and flexibility

---

## **Dependency Inversion Principle (DIP)**

### What is DIP?

High-level modules should not depend on low-level modules. Both should depend on abstractions (interfaces).

Abstractions should not depend on details. Details should depend on abstractions.

---

### Bad Implementation (Violates DIP)

**Problem:** High-level module (NotificationManager) directly depends on low-level module (EmailService)

**BadImpl/EmailService.java**

```java
public class EmailService {
    public void sendEmail(String message) {
        System.out.println("Sending email: " + message);
    }
}
```

**BadImpl/NotificationManager.java**

```java
public class NotificationManager {
    private EmailService emailService;

    public NotificationManager() {
        this.emailService = new EmailService(); // Direct dependency
    }

    public void notifyUser(String message) {
        emailService.sendEmail(message);
    }
}
```

**Problems:**

- Tight coupling between NotificationManager and EmailService
- Hard to test NotificationManager without EmailService
- Difficult to change notification method (e.g., add SMS)


---

### Good Implementation (Follows DIP)

### 💡 Approach

- Introduce abstraction (interface)
- Both high-level and low-level modules depend on the abstraction

---

**GoodImpl/NotificationService.java**

```java
public interface NotificationService {
    void sendNotification(String message);
}
```

**GoodImpl/EmailNotification.java**

```java
public class EmailNotification implements NotificationService {
    @Override
    public void sendNotification(String message) {
        System.out.println("Sending email: " + message);
    }
}
```

**GoodImpl/SMSNotification.java**

```java
public class SMSNotification implements NotificationService {
    @Override
    public void sendNotification(String message) {
        System.out.println("Sending SMS: " + message);
    }
}
```

**GoodImpl/NotificationManager.java**

```java
public class NotificationManager {
    private NotificationService notificationService;

    public NotificationManager(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public void notifyUser(String message) {
        notificationService.sendNotification(message);
    }
}
```

**Usage**

```java
public class Main {
    public static void main(String[] args) {
        NotificationService emailService = new EmailNotification();
        NotificationManager manager = new NotificationManager(emailService);
        manager.notifyUser("Hello via Email"); // Sending email: Hello via Email

        NotificationService smsService = new SMSNotification();
        NotificationManager smsManager = new NotificationManager(smsService);
        smsManager.notifyUser("Hello via SMS"); // Sending SMS: Hello via SMS
    }
}
```


### Key Idea

Depend on abstractions → Loose coupling, easy to test and extend
