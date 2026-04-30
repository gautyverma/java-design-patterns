package a3_BehavioralPatterns.a8_MediatorPattern;

import java.util.ArrayList;
import java.util.List;

interface ChatMediator {
  void sendMessage(String message, ChatUser user);

  void addUser(ChatUser user);
}

class ChatRoom implements ChatMediator {
  private List<ChatUser> users;

  public ChatRoom() {
    this.users = new ArrayList<>();
  }

  @Override
  public void sendMessage(String message, ChatUser sender) {
    for (ChatUser u : users) {
      if (u != sender) {
        u.receiveMessage(message, sender);
      }
    }
  }

  @Override
  public void addUser(ChatUser user) {
    users.add(user);
  }
}

class ChatUser {
  private String name;
  private ChatMediator chatMediator;

  public ChatUser(String name, ChatMediator chatMediator) {
    this.name = name;
    this.chatMediator = chatMediator;
  }

  public String getName() {
    return name;
  }

  public void sendMessage(String message) {
    System.out.println(this.name + " sends message: '" + message + "'");
    chatMediator.sendMessage(message, this);
  }

  public void receiveMessage(String message, ChatUser sender) {
    System.out.println(this.name + " receives message: '" + message + "' from " + sender.getName());
  }
}

public class WithMediatorPattern {
  public static void main(String[] args) {

    ChatMediator chatRoom = new ChatRoom();

    ChatUser alice = new ChatUser("Alice", chatRoom);
    ChatUser bob = new ChatUser("Bob", chatRoom);
    ChatUser charlie = new ChatUser("Charlie", chatRoom);

    chatRoom.addUser(alice);
    chatRoom.addUser(bob);
    chatRoom.addUser(charlie);

    alice.sendMessage("Hello everyone!");
  }
}
