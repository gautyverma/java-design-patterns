package a3_BehavioralPatterns.a6_IteratorPattern;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BookCollectionV2 {

  private List<Book> books;

  public BookCollectionV2() {
    this.books = new ArrayList<>();
  }

  public void addBook(Book book) {
    books.add(book);
  }

  public List<Book> getBooks() {
    return books;
  }

  public Iterator<Book> createIterator() {
    return new BookIterator(this.books);
  }

  // Another class (Nested class) that implements the Iterator interface for Book
  private class BookIterator implements Iterator<Book> {

    private List<Book> books; // List of books to iterate over
    private int index = 0; // Current index in the list

    public BookIterator(List<Book> books) {
      this.books = books;
    }

    @Override
    public boolean hasNext() {
      return index < books.size();
    }

    @Override
    public Book next() {
      return books.get(index++);
    }
  }
}
