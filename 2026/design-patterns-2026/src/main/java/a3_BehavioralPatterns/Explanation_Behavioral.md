## **Overview of Behavioral Design Patterns**

Behavioral Design Patterns focus on **how objects communicate and interact with each other**.  
They help in defining **clear communication patterns**, improving flexibility and maintainability.

---

### **1. Memento Pattern**
**Purpose:**  
Capture and restore an object's internal state **without exposing its implementation**.

**Use Case:**  
Undo/Redo functionality (e.g., text editors)

---

### **2. Observer Pattern**
**Purpose:**  
Define a **one-to-many dependency** so that when one object changes state, all dependents are notified.

**Use Case:**  
Event systems, notifications, pub-sub systems

---

### **3. Strategy Pattern**
**Purpose:**  
Define a family of algorithms and make them **interchangeable at runtime**.

**Use Case:**  
Payment methods (Credit Card, UPI, PayPal)

---

### **4. Command Pattern**
**Purpose:**  
Encapsulate a request as an object, allowing **parameterization and queuing of requests**.

**Use Case:**  
Remote controls, task queues, undo operations

---

### **5. Template Method Pattern**
**Purpose:**  
Define the **skeleton of an algorithm** in a method and let subclasses override specific steps.

**Use Case:**  
Frameworks where workflow is fixed but steps vary

---

### **6. Iterator Pattern**
**Purpose:**  
Provide a way to **access elements of a collection sequentially** without exposing its structure.

**Use Case:**  
Traversing lists, trees, collections

---

### **7. State Pattern**
**Purpose:**  
Allow an object to change its behavior when its **internal state changes**.

**Use Case:**  
ATM machine states, media player states

---

### **8. Mediator Pattern**
**Purpose:**  
Define an object that **centralizes communication** between multiple objects.

**Use Case:**  
Chat systems, air traffic control systems

---

### **Golden Insight 🚀**
> Behavioral patterns are about **communication, responsibility, and flexibility in object interaction**.