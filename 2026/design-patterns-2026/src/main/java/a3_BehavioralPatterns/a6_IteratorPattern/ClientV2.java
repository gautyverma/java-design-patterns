package a3_BehavioralPatterns.a6_IteratorPattern;

import java.util.Iterator;

public class ClientV2 {
  public static void main(String[] args) {

    BookCollectionV2 bookCollection = new BookCollectionV2();
    bookCollection.addBook(new Book("The Great Gatsby"));
    bookCollection.addBook(new Book("To Kill a Mockingbird"));
    bookCollection.addBook(new Book("1984"));

    Iterator<Book> iterator = bookCollection.createIterator();
    while (iterator.hasNext()) {
      Book book = iterator.next();
      System.out.println(book.getTitle());
    }
  }
}
