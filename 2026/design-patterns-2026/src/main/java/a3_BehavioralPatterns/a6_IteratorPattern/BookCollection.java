package a3_BehavioralPatterns.a6_IteratorPattern;

import java.util.ArrayList;
import java.util.List;

public class BookCollection {

  private List<Book> books;

  public BookCollection() {
    this.books = new ArrayList<>();
  }

  public void addBook(Book book) {
    books.add(book);
  }

  public List<Book> getBooks() {
    return books;
  }
}
