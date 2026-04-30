package a3_BehavioralPatterns.a6_IteratorPattern;

import java.util.Iterator;

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
