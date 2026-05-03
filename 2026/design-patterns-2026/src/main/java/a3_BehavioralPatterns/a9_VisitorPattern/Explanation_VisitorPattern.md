# Visitor Design Pattern

## Overview

The **Visitor Pattern** is a behavioral design pattern that represents an operation to be performed on elements of an object structure. It allows you to define new operations without changing the classes of the elements on which it operates. The pattern lets you separate data structures from algorithms that operate on them.

---

## Why Use Visitor Pattern?

- **Add Operations**: Add new operations without modifying element classes
- **Separate Concerns**: Separate data structures from algorithms
- **Avoid Cluttering**: Keep element classes clean and focused
- **Complex Operations**: Handle complex operations spanning multiple classes
- **Single Responsibility**: Each visitor handles one specific operation
- **Easy to Extend**: Add new visitors for new operations easily
- **Clean Code**: Avoid if-else chains and type checking
- **Flexible Design**: Apply multiple operations to same structure

---

## Use Cases

- Compiler symbol table traversal
- AST (Abstract Syntax Tree) analysis and transformation
- File system operations (copy, move, delete, compress)
- Report generation from complex object hierarchies
- Document processing and formatting
- UI component rendering and event handling
- Game object interactions and behaviors
- Database query builders and executors
- XML/JSON parsing and transformation
- Object serialization/deserialization

---

## Key Components

| Component | Responsibility |
|-----------|-----------------|
| **Element Interface** | Defines accept() method for visitors |
| **ConcreteElement** | Implements accept(), represents object |
| **Visitor Interface** | Defines visit methods for each element type |
| **ConcreteVisitor** | Implements visit methods for specific operation |
| **ObjectStructure** | Aggregates elements and lets visitors access |

---

## The Double Dispatch Mechanism

Visitor pattern uses **double dispatch** to determine the correct method to call:
1. First dispatch: Call `accept()` on element (element type determines method)
2. Second dispatch: Call `visit()` on visitor (visitor type determines method)

This achieves polymorphism on both the element and visitor types.

---

## Implementation

### 1. Visitor Pattern with Document Structure

**Element Interface - DocumentElement.java:**

```java
// Interface that elements implement
public interface DocumentElement {
  void accept(Visitor visitor);  // Allow visitor to visit this element
}
```

**Concrete Elements:**

**Text.java:**
```java
public class Text implements DocumentElement {
  private String content;
  
  public Text(String content) {
    this.content = content;
  }
  
  public String getContent() {
    return content;
  }
  
  @Override
  public void accept(Visitor visitor) {
    visitor.visit(this);  // Double dispatch
  }
}
```

**Paragraph.java:**
```java
public class Paragraph implements DocumentElement {
  private List<DocumentElement> elements = new ArrayList<>();
  
  public void add(DocumentElement element) {
    elements.add(element);
  }
  
  public List<DocumentElement> getElements() {
    return elements;
  }
  
  @Override
  public void accept(Visitor visitor) {
    visitor.visit(this);  // Double dispatch
    elements.forEach(e -> e.accept(visitor));  // Visit children
  }
}
```

**Image.java:**
```java
public class Image implements DocumentElement {
  private String filePath;
  
  public Image(String filePath) {
    this.filePath = filePath;
  }
  
  public String getFilePath() {
    return filePath;
  }
  
  @Override
  public void accept(Visitor visitor) {
    visitor.visit(this);  // Double dispatch
  }
}
```

**Visitor Interface - Visitor.java:**

```java
// Visitor interface with methods for each element type
public interface Visitor {
  void visit(Text text);
  void visit(Paragraph paragraph);
  void visit(Image image);
}
```

**Concrete Visitor 1 - RenderingVisitor.java:**

```java
// Specific operation: Rendering
public class RenderingVisitor implements Visitor {
  private StringBuilder result = new StringBuilder();
  
  @Override
  public void visit(Text text) {
    result.append(text.getContent());
  }
  
  @Override
  public void visit(Paragraph paragraph) {
    result.append("<p>");
  }
  
  @Override
  public void visit(Image image) {
    result.append("<img src='").append(image.getFilePath()).append("'/>");
  }
  
  public String getResult() {
    return result.toString();
  }
}
```

**Concrete Visitor 2 - CountingVisitor.java:**

```java
// Specific operation: Counting elements
public class CountingVisitor implements Visitor {
  private int textCount = 0;
  private int paragraphCount = 0;
  private int imageCount = 0;
  
  @Override
  public void visit(Text text) {
    textCount++;
  }
  
  @Override
  public void visit(Paragraph paragraph) {
    paragraphCount++;
  }
  
  @Override
  public void visit(Image image) {
    imageCount++;
  }
  
  public void printStats() {
    System.out.println("Text elements: " + textCount);
    System.out.println("Paragraph elements: " + paragraphCount);
    System.out.println("Image elements: " + imageCount);
  }
}
```

**Object Structure - Document.java:**

```java
// Contains and manages collection of elements
public class Document {
  private List<DocumentElement> elements = new ArrayList<>();
  
  public void add(DocumentElement element) {
    elements.add(element);
  }
  
  public void accept(Visitor visitor) {
    for (DocumentElement element : elements) {
      element.accept(visitor);
    }
  }
}
```

**Key Points:**
- Elements accept visitors using accept() method
- Visitors implement visit() methods for each element type
- Double dispatch determines correct method to call
- New operations added by creating new visitors
- Element classes remain unchanged

---

## Visitor Variants

### **Approach 1: Simple Visitor (Shown Above)**
- Pros: Clear separation, easy to understand
- Cons: Requires new visitor class for each operation
- Best for: Operations that clearly separate logically

### **Approach 2: Visitor with Arguments**
```java
public interface Visitor {
  void visit(Text text, Object... args);
  void visit(Paragraph paragraph, Object... args);
}
```

### **Approach 3: Visitor with Return Value**
```java
public interface Visitor {
  Object visit(Text text);
  Object visit(Paragraph paragraph);
}
```

### **Approach 4: Generic Visitor**
```java
public interface Element<T> {
  <R> R accept(GenericVisitor<R> visitor);
}

public interface GenericVisitor<R> {
  R visitText(Text text);
  R visitParagraph(Paragraph paragraph);
}
```

### **Approach 5: Acyclic Visitor (No Type Casting)**
```java
public interface Visitor { }

public interface TextVisitor extends Visitor {
  void visit(Text text);
}

public interface ParagraphVisitor extends Visitor {
  void visit(Paragraph paragraph);
}
```

---

## Usage Example

```java
public class DocumentApp {
  public static void main(String[] args) {
    // Build document structure
    Document document = new Document();
    
    Paragraph paragraph1 = new Paragraph();
    paragraph1.add(new Text("Hello "));
    paragraph1.add(new Text("World"));
    paragraph1.add(new Image("logo.png"));
    
    Paragraph paragraph2 = new Paragraph();
    paragraph2.add(new Text("This is a second paragraph"));
    
    document.add(paragraph1);
    document.add(paragraph2);
    
    // Apply different visitors
    System.out.println("=== Rendering ===");
    RenderingVisitor renderer = new RenderingVisitor();
    document.accept(renderer);
    System.out.println(renderer.getResult());
    
    System.out.println("\n=== Statistics ===");
    CountingVisitor counter = new CountingVisitor();
    document.accept(counter);
    counter.printStats();
  }
}
```

### Output

```
=== Rendering ===
<p>Hello World<img src='logo.png'/></p><p>This is a second paragraph</p>

=== Statistics ===
Text elements: 3
Paragraph elements: 2
Image elements: 1
```

---

## Code Flow

1. Client creates visitor with specific operation
2. Client calls accept(visitor) on root structure
3. Element calls visitor.visit(this) in accept method (double dispatch)
4. Visitor executes specific operation for this element type
5. For composite elements, recursively accept visitor for children
6. Visitor performs operation on entire structure
7. Client receives results from visitor

### Double Dispatch Flow

```
Client calls: element.accept(visitor)
  ↓
Element calls: visitor.visit(this)
  ↓
Visitor method determined by:
  1. Element type (first dispatch)
  2. Visitor type (second dispatch)
  ↓
Correct visitor method executed
```

---

## Visitor vs Strategy Pattern

| Aspect | Visitor | Strategy |
|--------|---------|----------|
| **Purpose** | Operations on object structure | Encapsulate algorithm |
| **What Varies** | Operations | Algorithm implementation |
| **Structure** | Multiple elements | Single object |
| **Operations** | Multiple operations | Single algorithm |
| **Complexity** | Higher | Lower |
| **When** | Many operations on structure | Multiple way to do one thing |

---

## Visitor vs Observer Pattern

| Aspect | Visitor | Observer |
|--------|---------|----------|
| **Purpose** | Perform operations | React to changes |
| **When** | Active (pull) operation | Passive (push) notification |
| **Structure** | Tree/collection | Subject-Observer |
| **Coupling** | Loose (visitors) | Tight (subject-observer) |
| **Use Case** | Algorithm execution | Event notification |

---

## Advantages

✅ **Separate Concerns**: Operations separate from data structures  
✅ **Add Operations**: Add new operations without modifying elements  
✅ **Single Responsibility**: Each visitor handles one operation  
✅ **Easy Extension**: New visitors easily added  
✅ **Complex Operations**: Handle operations spanning multiple classes  
✅ **Related Operations**: Keep related operations together in visitor  
✅ **Clean Elements**: Element classes remain focused  
✅ **Flexible**: Apply multiple operations to same structure  

---

## Disadvantages

❌ **Complexity**: Pattern requires careful implementation  
❌ **Adding Elements**: Adding new element types requires updating all visitors  
❌ **Learning Curve**: Developers need to understand double dispatch  
❌ **Indirection**: Extra layer of indirection adds overhead  
❌ **Type Casting**: May need type checking despite pattern intent  
❌ **Inheritance Issues**: Works better with single inheritance  
❌ **Cycles**: Can create issues with circular dependencies  
❌ **Parallel Changes**: Elements and visitors must stay in sync  

---

## Real-World Applications

| Application | Structure | Operations | Benefit |
|-------------|-----------|-----------|---------|
| **Compiler** | AST nodes | Validate, Compile, Optimize | Multiple passes on AST |
| **IDE** | Code elements | Format, Analyze, Refactor | Multiple code operations |
| **Graphics** | Shape objects | Render, Export, Measure | Multiple graphics operations |
| **File System** | Files/Folders | Copy, Move, Compress | Multiple file operations |
| **Document** | Elements | Render, Export, Statistics | Multiple document operations |
| **Game** | Game objects | Update, Render, Collide | Multiple game operations |
| **Database** | Records | Validate, Serialize, Export | Multiple data operations |
| **XML Processing** | XML nodes | Parse, Validate, Transform | Multiple XML operations |

---

## When to Use Visitor Pattern

**Use Visitor when:**
- ✅ Many distinct operations needed on object structure
- ✅ Object structure classes rarely change
- ✅ Operations change or new operations often added
- ✅ Want to avoid polluting element classes
- ✅ Need to do similar operations across multiple element types
- ✅ Want to separate algorithms from object structure
- ✅ Multiple operations without inheritance explosion

**Don't use when:**
- ❌ Element types change frequently
- ✅ Only simple operations needed (overkill)
- ❌ Single operation works fine
- ❌ Adding unnecessary complexity
- ❌ Limited number of element types
- ❌ Operations tightly tied to specific elements

---

## Common Pitfalls to Avoid

❌ **Adding Elements**: Becomes painful to add new element types  
❌ **Type Casting**: Defeats pattern purpose if casting needed  
❌ **Tight Coupling**: Visitors coupled to element structure  
❌ **Performance**: Extra indirection adds overhead  
❌ **Mutable Structure**: Don't modify structure during traversal  
❌ **Complex Logic**: Keep each visitor focused  
❌ **Circular Dependencies**: Avoid cycles in visitor calls  
❌ **Over-Engineering**: Don't use for simple cases  

---

## Practical Implementation Tips

### **1. Symmetric Operations**
```java
// Operation that affects related elements
public class ValidationVisitor implements Visitor {
  public void visit(Text text) {
    // Validate text
  }
  
  public void visit(Paragraph paragraph) {
    // Validate paragraph structure
  }
}
```

### **2. Context in Visitor**
```java
public class ContextVisitor implements Visitor {
  private Stack<Element> context = new Stack<>();
  
  public void visit(Element element) {
    context.push(element);  // Track path
    // Use context for traversal
    context.pop();
  }
}
```

### **3. Visitor Composition**
```java
// Combine multiple visitors
visitor1.visit(element);
visitor2.visit(element);
visitor3.visit(element);
```

---

## Real-World Analogy

**Tax Auditor (Visitor)**
- **Elements**: Different business entities (Retail, Manufacturing, Service)
- **Visitor**: Tax auditor applying audit rules
- **Operation**: Audit each entity type differently
- **Result**: Each entity audited correctly for its type
- **Benefit**: Audit logic separate from business logic

---

## Visitor Pattern in Java Libraries

**Java Stream API**:
```java
list.forEach(System.out::println);  // Visitor pattern
```

**Swing Components**:
```java
component.accept(new UIVisitor());
```

**XML Processing**:
```java
// SAX parser uses visitor-like pattern
handler.startElement(...)
handler.characters(...)
handler.endElement(...)
```

---

## Conclusion

The Visitor Pattern is powerful for performing multiple operations on object structures without modifying the elements. It uses double dispatch to achieve polymorphism on both element and visitor types. While more complex than simpler patterns, it elegantly solves the problem of adding new operations to fixed element hierarchies.

**Remember**: Use Visitor when you have a stable object structure but need to add many different operations. If element types change frequently, the pattern becomes a maintenance burden!

