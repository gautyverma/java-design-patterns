package a3_BehavioralPatterns.a6_IteratorPattern;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
