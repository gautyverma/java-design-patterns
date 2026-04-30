# Iterator Pattern

## Overview

The **Iterator Pattern** is a behavioral design pattern that provides a way to access the elements of an aggregate object sequentially without exposing its underlying representation. It allows traversal of collections without knowing their internal structure.

---

## Why Use Iterator Pattern?

- **Encapsulation**: Access collection elements without exposing internal structure
- **Uniform Interface**: Same traversal interface for different collection types
- **Multiple Traversals**: Support multiple simultaneous traversals of same collection
- **Decoupling**: Separate collection storage from traversal logic
- **Extensibility**: Add new collection types without changing client code
- **Fail-Safe Iteration**: Prevent concurrent modification issues

---

## Use Cases

- Collection traversal (List, Set, Map, custom collections)
- Database result sets
- File system directory traversal
- Tree/graph traversals
- Menu system navigation
- Playlist iteration in media players

---

## Key Components

| Component | Responsibility |
|-----------|-----------------|
| **Iterator** | Interface defining traversal methods (hasNext, next, remove) |
| **Concrete Iterator** | Implements specific traversal logic for collection |
| **Aggregate** | Interface defining method to create iterator |
| **Concrete Aggregate** | Implements collection that creates appropriate iterator |

---

## Implementation

### 1. Element Class

**Book.java** - The element being stored in collection:

```java
public class Book {

  private String title;

  public Book(String title) {
    this.title = title;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  @Override
  public String toString() {
    return "Book{" + "title='" + title + '\'' + '}';
  }
}
```

**Key Points:**
- Simple data class representing collection elements
- Contains book title and getter/setter methods
- Unaware of Iterator pattern

---

### 2. Aggregate Interface

**Iterable.java** - Java's built-in interface for collections that can be iterated:

```java
public interface Iterable<T> {
    Iterator<T> iterator();
}
```

**Key Points:**
- Defines `iterator()` method that returns Iterator
- Generic interface allowing type-safe iteration
- Part of Java's standard library

---

### 3. Iterator Interface

**Iterator.java** - Java's built-in interface for iteration:

```java
public interface Iterator<E> {
    boolean hasNext();
    E next();
    default void remove() {
        throw new UnsupportedOperationException("remove");
    }
}
```

**Key Points:**
- `hasNext()`: Checks if more elements exist
- `next()`: Returns next element and advances position
- `remove()`: Optional removal of current element
- Generic interface for type safety

---

### 4. Concrete Aggregate

**BookCollectionV3.java** - Collection implementing Iterable:

```java
public class BookCollectionV3 implements Iterable<Book>{

  private List<Book> books;

  public BookCollectionV3() {
    this.books = new ArrayList<>();
  }

  public void addBook(Book book) {
    books.add(book);
  }


  @Override
  public Iterator<Book> iterator() {
    return books.iterator();
  }
}
```

**Key Points:**
- Implements `Iterable<Book>` interface
- Uses `ArrayList<Book>` for internal storage
- `iterator()` returns ArrayList's built-in iterator
- Delegates iteration to underlying collection

---

## Usage Example

**ClientV3.java** - Demonstrates iterator pattern:

```java
public class ClientV3 {
  public static void main(String[] args) {

    BookCollectionV3 bookCollection = new BookCollectionV3();
    bookCollection.addBook(new Book("The Great Gatsby"));
    bookCollection.addBook(new Book("To Kill a Mockingbird"));
    bookCollection.addBook(new Book("1984"));

    Iterator<Book> iterator = bookCollection.iterator();
    while (iterator.hasNext()) {
      Book book = iterator.next();
      System.out.println(book.getTitle());
    }
  }
}
```

### Output

```
The Great Gatsby
To Kill a Mockingbird
1984
```

---

## Code Flow

1. Create BookCollectionV3 and add Book instances
2. Call iterator() to get Iterator<Book> instance
3. Use hasNext() to check for more elements
4. Call next() to retrieve current element and advance
5. Process element (print title)
6. Repeat until hasNext() returns false

---

## Advantages

✅ **Encapsulation**: Collection internals hidden from clients  
✅ **Uniform Access**: Same interface for all collection types  
✅ **Multiple Iterators**: Support concurrent traversals  
✅ **Fail-Safe**: Prevent concurrent modification exceptions  
✅ **Extensibility**: Add new collections without changing clients  
✅ **Type Safety**: Generic iterators prevent type errors  

---

## Disadvantages

❌ **Performance**: Iterator creation has overhead  
❌ **Memory**: Iterator objects consume memory  
❌ **Complexity**: Extra abstraction layer for simple traversals  
❌ **Limited Operations**: Only sequential access supported  
❌ **State Management**: Iterator state must be maintained  

---

## Real-World Analogies

- **TV Remote**: Channel up/down buttons iterate through channels
- **Music Playlist**: Next/previous buttons traverse song list
- **File Explorer**: Navigate through directory structure
- **Database Cursor**: Move through query result rows
- **Social Media Feed**: Scroll through posts sequentially

---

## Related Patterns

- **Composite Pattern**: Iterator can traverse composite structures
- **Visitor Pattern**: Iterator provides access, visitor performs operations
- **Observer Pattern**: Iterator changes can notify observers
- **Factory Pattern**: Create appropriate iterators for collections
- **Memento Pattern**: Iterator state can be saved/restored
