package a3_BehavioralPatterns.a8_MediatorPattern;

class User {
  private String name;

  public User(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void sendMessage(String message, User recipient) {
    System.out.println(this.name + " sends message: '" + message + "' to " + recipient.getName());
  }
}

public class WithoutMediatorPattern {
  public static void main(String[] args) {

    User alice = new User("Alice");
    User bob = new User("Bob");
    User charlie = new User("Charlie");

    alice.sendMessage("Hello Bob!", bob);
    alice.sendMessage("Hi Charlie!", charlie);
  }
}
