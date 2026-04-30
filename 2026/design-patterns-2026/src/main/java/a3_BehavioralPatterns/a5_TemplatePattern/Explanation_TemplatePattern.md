# Template Pattern

## Overview

The **Template Pattern** is a behavioral design pattern that defines the skeleton of an algorithm in a base class but lets subclasses override specific steps without changing the algorithm's structure. It follows the "Don't call us, we'll call you" principle.

---

## Why Use Template Pattern?

- **Code Reuse**: Common algorithm steps are defined once in base class
- **Consistency**: Ensures consistent algorithm flow across implementations
- **Flexibility**: Allow subclasses to override only specific steps
- **Inversion of Control**: Subclasses implement specific behavior while base controls flow
- **Maintenance**: Changes to common steps affect all subclasses automatically
- **Variability**: Extract common behavior from similar algorithms

---

## Use Cases

- Data parsing (CSV, JSON, XML parsers)
- Document generation (PDF, HTML, Word formats)
- Database access patterns (JDBC templates)
- Game development (game loops)
- Build processes (maven, gradle)
- Cooking recipes (consistent steps, different ingredients)

---

## Key Components

| Component | Responsibility |
|-----------|-----------------|
| **Abstract Class** | Defines template method and abstract methods for subclasses |
| **Template Method** | Defines algorithm skeleton with fixed and variable steps |
| **Primitive Operations** | Abstract methods that subclasses must implement |
| **Hook Methods** | Optional methods subclasses can override (default implementations provided) |
| **Concrete Classes** | Implement specific variations of template steps |

---

## Implementation

### 1. Abstract Template Class

**DataPraser.java** - Defines algorithm skeleton:

```java
abstract class DataPraser {

  public void parse(String data) {
    openFile();
    parseData();
    closeFile();
  }

  protected void openFile() {
    System.out.println("Opening file...");
  }

  protected void closeFile() {
    System.out.println("Closing file...");
  }

  protected abstract void parseData();
}
```

**Key Points:**
- `parse()` is the template method defining algorithm steps
- `openFile()` and `closeFile()` are concrete methods (hook methods)
- `parseData()` is abstract - must be implemented by subclasses
- Template method calls these methods in specific order

---

### 2. Concrete Implementations

**CSVDataParser.java** - CSV parsing implementation:

```java
class CSVDataParser extends DataPraser {

  @Override
  protected void parseData() {
    System.out.println("Parsing CSV data...");
  }
}
```

**JSONDataParser.java** - JSON parsing implementation:

```java
class JSONDataParser extends DataPraser {

  @Override
  protected void parseData() {
    System.out.println("Parsing JSON data...");
  }
}
```

**Key Points:**
- Both extend DataPraser abstract class
- Override only `parseData()` method
- Inherit common `openFile()` and `closeFile()` behavior
- Each provides specific parsing logic

---

## Usage Example

**WithTemplatePattern.java** - Demonstrates template pattern:

```java
public class WithTemplatePattern {
  public static void main(String[] args) {
    DataPraser csvParser = new CSVDataParser();
    csvParser.parse("data.csv");

    System.out.println();

    DataPraser jsonParser = new JSONDataParser();
    jsonParser.parse("data.json");
  }
}
```

### Output

```
Opening file...
Parsing CSV data...
Closing file...

Opening file...
Parsing JSON data...
Closing file...
```

---

## Code Flow

1. Create CSVDataParser instance
2. Call parse() template method with file path
3. Template executes: openFile() → parseData() → closeFile()
4. openFile() and closeFile() use inherited implementations
5. parseData() calls CSV-specific implementation
6. Repeat for JSONDataParser with JSON-specific implementation

---

## Advantages

✅ **Code Reuse**: Common algorithm steps in base class  
✅ **Consistency**: Ensures uniform algorithm flow across all implementations  
✅ **Extensibility**: Easy to add new variations without modifying template  
✅ **Reduced Duplication**: DRY principle - avoid repeating common code  
✅ **Maintenance**: Changes to template affect all implementations  
✅ **Inversion of Control**: Subclasses focus only on variable parts  

---

## Disadvantages

❌ **Rigidity**: Algorithm structure cannot be changed in subclasses  
❌ **Confusion**: Developers must understand inheritance hierarchy  
❌ **Abstract Class Requirement**: Cannot use with interfaces (though default methods help)  
❌ **Limited Flexibility**: Only specific methods can be overridden  
❌ **Tight Coupling**: Subclasses tightly coupled to abstract class  

---

## Real-World Analogies

- **Recipe**: Template is the cooking process (read ingredients, mix, bake, serve), variations are different recipes
- **Manufacturing**: Assembly line (template) with station-specific tasks (variations)
- **Software Build Process**: Maven/Gradle define build lifecycle, plugins customize specific phases
- **Gaming**: Game loop (initialize, update, render) - same for all games, specific logic varies
- **Document Generation**: Same structure (header, body, footer), different content and formatting

---

## Related Patterns

- **Strategy Pattern**: Both define families of algorithms, but Template uses inheritance while Strategy uses composition
- **Factory Pattern**: Often used together to create appropriate template instances
- **Hook Pattern**: Uses template hooks for optional customization points
- **Decorator Pattern**: Adds functionality to existing objects vs extending algorithms
- **Observer Pattern**: Can notify observers at template method steps
