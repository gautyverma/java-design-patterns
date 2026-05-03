# Composite Design Pattern

## Overview

The **Composite Pattern** is a structural design pattern that allows you to compose objects into tree structures to represent part-whole hierarchies. It lets clients treat individual objects and compositions of objects uniformly. This pattern is perfect for representing hierarchical structures like file systems, organizational charts, UI components, menus, and more.

---

## Why Use Composite Pattern?

- **Tree Structures**: Naturally represent hierarchical structures
- **Uniform Interface**: Treat individual and composite objects the same way
- **Recursive Composition**: Easily build nested structures
- **Simplify Client Code**: No need to distinguish between leaf and composite
- **Flexibility**: Add new component types without changing existing code
- **Easy to Extend**: Add new operations to existing structure
- **Natural Hierarchy**: Reflects real-world hierarchies perfectly
- **Recursive Processing**: Automatically handle nested structures

---

## Use Cases

- File system representation (files and folders)
- UI component rendering (panels, buttons, layouts)
- Organizational hierarchies (employees, departments)
- Menu systems (menus with submenus)
- Document structures (chapters, sections, subsections)
- Graphics rendering (shapes, groups, layers)
- Expression trees (mathematical expressions)
- Game object hierarchies (game entities with children)

---

## Key Components

| Component | Responsibility |
|-----------|-----------------|
| **Component Interface** | Defines common interface for all objects |
| **Leaf** | Represents terminal objects with no children |
| **Composite** | Represents objects that can have children |
| **Client** | Works with objects through component interface |

---

## When to Use Composite vs Other Patterns

Composite is different from other structural patterns:

| Aspect | Composite | Decorator | Facade |
|--------|-----------|-----------|---------|
| **Purpose** | Part-whole hierarchy | Add behavior | Simplify interface |
| **Structure** | Tree of objects | Single wrapper | Multiple wrappers |
| **Children** | Multiple | Single | Multiple |
| **Interface** | Uniform | Same | Different |
| **Use Case** | Hierarchies | Enhancement | Simplification |

---

## Implementation

### 1. Composite Pattern with File System

**Component Interface - FileSystemComponent.java:**

```java
// Common interface for all components
public interface FileSystemComponent {
  void showDetails();
}
```

**Leaf Component - File.java:**

```java
// Leaf object - cannot have children
public class File implements FileSystemComponent {
  private String name;
  
  public File(String name) {
    this.name = name;
  }
  
  @Override
  public void showDetails() {
    System.out.println("File: " + name);
  }
}
```

**Composite Component - Folder.java:**

```java
// Composite object - can have children
public class Folder implements FileSystemComponent {
  private String name;
  private List<FileSystemComponent> components = new ArrayList<>();
  
  public Folder(String name) {
    this.name = name;
  }
  
  // Add method to add child components
  public void addComponent(FileSystemComponent component) {
    components.add(component);
  }
  
  // Remove method to remove child components
  public void removeComponent(FileSystemComponent component) {
    components.remove(component);
  }
  
  @Override
  public void showDetails() {
    System.out.println("Folder: " + name);
    // Recursively process all children
    for (FileSystemComponent component : components) {
      component.showDetails();
    }
  }
}
```

**Key Points:**
- Component interface defines common operations
- Leaf (File) implements interface but has no children
- Composite (Folder) implements interface and manages children
- Recursive processing through tree structure
- Client treats both the same way through interface

---

## Composite Variants

### **Approach 1: Simple Composite (Shown Above)**
- Pros: Simple, clear structure, easy to understand
- Cons: Operations for leaves and composites similar
- Best for: Basic hierarchies

### **Approach 2: Generic Composite**
```java
public abstract class Component<T> {
  protected String name;
  
  public abstract void operation();
}

public class Leaf<T> extends Component<T> {
  @Override
  public void operation() {
    // Leaf implementation
  }
}

public class Composite<T> extends Component<T> {
  private List<Component<T>> children = new ArrayList<>();
}
```

### **Approach 3: Safe Composite (Type Checking)**
```java
public interface Component {
  void operation();
  List<Component> getChildren();  // May throw exception for leaves
  void add(Component component);  // May throw exception for leaves
}
```

### **Approach 4: Transparent Composite (No Type Checking)**
```java
public abstract class Component {
  protected List<Component> children = new ArrayList<>();
  
  public void add(Component component) {
    children.add(component);
  }
  
  public abstract void operation();
}
```

---

## Usage Example

**FileSystemApp.java** - Demonstrates composite pattern:

```java
public class FileSystemApp {
  public static void main(String[] args) {
    
    // Create leaf components
    FileSystemComponent f1 = new File("file1.txt");
    FileSystemComponent f2 = new File("file2.txt");
    
    // Create composite (folder)
    Folder folder = new Folder("Documents");
    folder.addComponent(f1);
    folder.addComponent(f2);
    
    // Create nested subfolder
    Folder subFolder = new Folder("Subfolder");
    FileSystemComponent f3 = new File("file3.txt");
    subFolder.addComponent(f3);
    
    // Add subfolder to main folder
    folder.addComponent(subFolder);
    
    // Show entire tree structure
    folder.showDetails();
  }
}
```

### Output

```
Folder: Documents
File: file1.txt
File: file2.txt
Folder: Subfolder
File: file3.txt
```

---

## Code Flow

1. Create leaf components (files)
2. Create composite component (folder)
3. Add leaf components to composite
4. Create nested composite (subfolder)
5. Add nested composite to parent composite
6. Call operation on root composite
7. Operation recursively propagates through tree
8. Each node performs its own operation
9. Entire tree structure processed uniformly

### Tree Structure Visualization

```
Folder: Documents
├── File: file1.txt
├── File: file2.txt
└── Folder: Subfolder
    └── File: file3.txt
```

---

## Safe vs Transparent Composite

### **Safe Composite: Type Checking**
```java
public interface Component {
  void operation();
  // Only in Composite
  void add(Component component);
  void remove(Component component);
  Component getChild(int index);
}

public class Leaf implements Component {
  @Override
  public void add(Component component) {
    throw new UnsupportedOperationException();
  }
}
```
- Pros: Type safety, clear what operations are valid
- Cons: Client must check type before adding

### **Transparent Composite: No Type Checking**
```java
public interface Component {
  void operation();
  void add(Component component);
  void remove(Component component);
  Component getChild(int index);
}

public class Leaf implements Component {
  private List<Component> children = new ArrayList<>();
  
  @Override
  public void add(Component component) {
    children.add(component);  // Allowed but meaningless
  }
}
```
- Pros: Uniform interface, client doesn't check types
- Cons: Less type safe, all objects have all operations

---

## Advantages

✅ **Tree Structures**: Naturally represent hierarchies  
✅ **Uniform Interface**: Treat individual and composite objects uniformly  
✅ **Recursive Composition**: Build deeply nested structures easily  
✅ **Simplified Client Code**: No type checking needed  
✅ **Open/Closed Principle**: Add new component types without changes  
✅ **Flexible Operations**: Easy to add new operations  
✅ **Natural Representation**: Reflects real-world hierarchies  
✅ **Automatic Recursion**: Tree traversal handled automatically  

---

## Disadvantages

❌ **Complexity**: Adds extra layer of abstraction  
❌ **Type Safety**: Hard to prevent invalid operations  
❌ **Memory Overhead**: More objects in tree  
❌ **Performance**: Extra traversal overhead  
❌ **Debugging Difficulty**: Tracing recursive calls complex  
❌ **Design Constraints**: Forces hierarchical structure  
❌ **Testing Complexity**: Testing large trees difficult  
❌ **Modification Overhead**: Adding/removing nodes impacts whole tree  

---

## Real-World Examples

### **File System**
```
Drive: C:\
├── Folder: Documents
│   ├── File: resume.pdf
│   └── Folder: Projects
│       └── File: project.java
├── Folder: Pictures
│   ├── File: photo1.jpg
│   └── File: photo2.jpg
└── File: readme.txt
```

### **Organization Structure**
```
Company
├── Department: Engineering
│   ├── Employee: John (Senior)
│   ├── Employee: Jane (Jr)
│   └── Team: Backend
│       ├── Employee: Bob
│       └── Employee: Alice
└── Department: Sales
    ├── Employee: Tom
    └── Employee: Jerry
```

### **UI Components**
```
Frame
├── Panel (Main)
│   ├── Button (OK)
│   ├── Button (Cancel)
│   └── TextBox (Input)
└── Panel (Footer)
    ├── Label (Status)
    └── Button (Help)
```

---

## Applications of Composite

| Application | Leaf | Composite | Parent Container |
|-------------|------|-----------|------------------|
| **File System** | File | Folder | Drive/Root |
| **Graphics** | Shape | ShapeGroup | Canvas |
| **UI** | Button/Label | Panel | Window |
| **Menu** | MenuItem | Menu | MenuBar |
| **Organization** | Employee | Department | Company |
| **Document** | Paragraph | Section | Document |
| **Game** | Entity | EntityGroup | World |
| **Expression** | Operand | Operator | Expression |

---

## When to Use Composite Pattern

**Use Composite when:**
- ✅ You have hierarchical structures (part-whole)
- ✅ Clients should treat individual and composite objects uniformly
- ✅ You want to build trees of objects
- ✅ Adding new types shouldn't require client changes
- ✅ Recursive tree traversal needed
- ✅ You have containers and contents
- ✅ Easy navigation through hierarchy needed

**Don't use when:**
- ❌ Structure is not hierarchical
- ❌ Simple flat list works fine
- ❌ Type checking for operations is critical
- ❌ Performance overhead is unacceptable
- ❌ Hierarchy is rarely traversed
- ❌ Memory usage is critical
- ❌ Simple class hierarchy works better

---

## Implementation Guidelines

### **Best Practices**

1. **Define Clear Interface**: Unify operations in component interface
2. **Keep Operations Simple**: Each component performs one task
3. **Avoid Deep Nesting**: Limit practical tree depth
4. **Document Structure**: Clearly document hierarchy rules
5. **Consistent Behavior**: Composite should delegate recursively
6. **Handle Edge Cases**: Empty composites, single child, etc.
7. **Efficient Traversal**: Consider tree traversal performance

### **Common Patterns**

**Pre-order Traversal:**
```java
public void traverse(Component component) {
  component.operation();  // Process current
  if (component instanceof Composite) {
    for (Component child : ((Composite) component).getChildren()) {
      traverse(child);  // Process children
    }
  }
}
```

**Post-order Traversal:**
```java
public void traverse(Component component) {
  if (component instanceof Composite) {
    for (Component child : ((Composite) component).getChildren()) {
      traverse(child);  // Process children first
    }
  }
  component.operation();  // Process current after
}
```

---

## Common Pitfalls to Avoid

❌ **Over-Nesting**: Avoid creating unnecessarily deep trees  
❌ **Type Checking**: Don't force clients to check types  
❌ **Memory Waste**: Don't create unnecessary nodes  
❌ **Performance**: Don't ignore traversal costs  
❌ **Inconsistent Operations**: Keep interface consistent  
❌ **Missing Children**: Ensure composite always has container  
❌ **Poor Iteration**: Don't expose internal structure  

---

## Composite vs Iterator Pattern

**Composite**: Represents structure  
```java
root.operation();  // Automatically handles tree
```

**Iterator**: Accesses elements sequentially  
```java
while (iterator.hasNext()) {
  element = iterator.next();  // Manual traversal
}
```

Both can be combined for flexible tree traversal!

---

## Real-World Analogy

**Organization Hierarchy:**
- **CEO** (Root Composite) manages multiple departments
- **Department Manager** (Composite) manages multiple teams
- **Team Lead** (Composite) manages multiple employees
- **Individual Employee** (Leaf) performs work
- **Reports** (Operation) flow up the hierarchy
- **Decisions** (Operation) flow down the hierarchy

Each level can be uniform - asked to deliver results!

---

## Conclusion

The Composite Pattern is perfect for representing hierarchical structures and allowing uniform treatment of individual and composite objects. The file system example beautifully demonstrates how composite enables natural tree representation and recursive processing. Use it whenever you have part-whole hierarchies and want clients to work with them uniformly without knowing internal structure details.

**Remember**: The power of Composite lies in its recursive nature - each composite delegates to its children, which recursively handle their own children, until reaching leaves that perform the actual work!

