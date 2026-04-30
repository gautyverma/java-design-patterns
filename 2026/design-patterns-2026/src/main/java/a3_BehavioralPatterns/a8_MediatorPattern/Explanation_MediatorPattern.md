# Mediator Pattern

## Overview

The **Mediator Pattern** is a behavioral design pattern that defines how a set of objects interact with each other. Instead of objects communicating directly, they communicate through a mediator object. This promotes loose coupling by keeping objects from referring to each other explicitly.

---

## Why Use Mediator Pattern?

- **Loose Coupling**: Objects don't need to know about each other
- **Centralized Control**: Communication logic is centralized in mediator
- **Maintainability**: Changes to interaction logic affect only the mediator
- **Reusability**: Colleague objects can be reused with different mediators
- **Simplified Dependencies**: Reduces complex many-to-many relationships
- **Encapsulation**: Interaction rules are encapsulated in one place

---

## Use Cases

- Chat applications and messaging systems
- GUI dialog boxes with multiple controls
- Air traffic control systems
- Workflow management systems
- Event handling in complex UIs
- Multiplayer game coordination
- Smart home automation systems

---

## Key Components

| Component | Responsibility |
|-----------|-----------------|
| **Mediator** | Interface defining communication contract |
| **Concrete Mediator** | Implements communication logic, coordinates colleagues |
| **Colleague** | Objects that communicate through mediator |

---

## Implementation

### 1. Mediator Interface

**ChatMediator.java** - Defines mediator contract:

```java
interface ChatMediator {
  void sendMessage(String message, ChatUser user);

  void addUser(ChatUser user);
}
```

**Key Points:**
- `sendMessage()`: Broadcast message to other users
- `addUser()`: Register new users with mediator
- Defines communication protocol for chat system

---

### 2. Concrete Mediator

**ChatRoom.java** - Implements chat coordination:

```java
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
```

**Key Points:**
- Maintains list of all chat users (colleagues)
- `sendMessage()` broadcasts to all users except sender
- `addUser()` registers new participants
- Centralizes all communication logic

---

### 3. Colleague Class

**ChatUser.java** - Chat participants:

```java
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
```

**Key Points:**
- Each user has reference to mediator (not other users)
- `sendMessage()` delegates to mediator instead of direct communication
- `receiveMessage()` called by mediator when messages arrive
- Users communicate only through mediator

---

## Usage Example

**WithMediatorPattern.java** - Demonstrates mediator pattern:

```java
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
```

### Output

```
Alice sends message: 'Hello everyone!'
Bob receives message: 'Hello everyone!' from Alice
Charlie receives message: 'Hello everyone!' from Alice
```

---

## Code Flow

1. Create ChatRoom mediator instance
2. Create ChatUser instances with reference to mediator
3. Register all users with mediator using addUser()
4. Alice calls sendMessage() which delegates to mediator
5. Mediator broadcasts message to all users except sender
6. Bob and Charlie receive the message through mediator

---

## Advantages

✅ **Loose Coupling**: Colleagues don't reference each other directly  
✅ **Centralized Logic**: All communication rules in one place  
✅ **Maintainability**: Easy to modify interaction behavior  
✅ **Reusability**: Colleagues can work with different mediators  
✅ **Simplified Dependencies**: No complex object relationships  
✅ **Extensibility**: Add new colleagues without changing existing ones  

---

## Disadvantages

❌ **Mediator Complexity**: Mediator can become complex with many colleagues  
❌ **Single Point of Failure**: System depends on mediator availability  
❌ **Performance**: All communication goes through mediator bottleneck  
❌ **Tight Coupling to Mediator**: Colleagues depend on specific mediator interface  
❌ **Debugging Difficulty**: Communication flow is indirect  

---

## Real-World Analogies

- **Air Traffic Control**: Planes communicate through control tower
- **Chat Room**: Users communicate through chat server
- **UN Security Council**: Countries communicate through mediator
- **Event Bus**: Components communicate through central event system
- **Smart Home Hub**: Devices communicate through central controller

---
