package a3_BehavioralPatterns.a6_IteratorPattern;

public class ClientV1 {
  public static void main(String[] args) {

    BookCollection bookCollection = new BookCollection();
    bookCollection.addBook(new Book("The Great Gatsby"));
    bookCollection.addBook(new Book("To Kill a Mockingbird"));
    bookCollection.addBook(new Book("1984"));

    // Problem: No way to traverse the collection without exposing its internal structure
    for (int i = 0; i < bookCollection.getBooks().size(); i++) {
      System.out.println(bookCollection.getBooks().get(i));
    }
  }
}
