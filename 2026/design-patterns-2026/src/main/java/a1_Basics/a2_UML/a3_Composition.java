package a1_Basics.a2_UML;

import java.util.ArrayList;
import java.util.List;

/**
 * Composition → “strong HAS-A”
 *
 * Meaning: One class fully owns another (lifecycle dependent)
 *
 * Example:
 *        class House { private List<Room> rooms = new ArrayList<>(); }
 *        class Room { }
 *
 * Understanding: House HAS-A Rooms If house is destroyed → rooms are also
 * destroyed
 *
 * Real-world: House → Rooms
 */
class Room {
  private String name;

  public Room(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}

class House {
  private String address;
  private List<Room> rooms;

  public House(String address) {
    this.address = address;
    this.rooms = new ArrayList<>();
  }

  public void addRoom(Room room) {
    rooms.add(room);
  }

  public void showRooms() {
    System.out.println("House at: " + address);
    System.out.println("Rooms:");
    for (Room room : rooms) {
      System.out.println("- " + room.getName());
    }
  }
}

public class a3_Composition {
  public static void main(String[] args) {
    House house = new House("123 Main St");
    Room livingRoom = new Room("Living Room");
    Room kitchen = new Room("Kitchen");
    Room bedroom = new Room("Bedroom");

    house.addRoom(livingRoom);
    house.addRoom(kitchen);
    house.addRoom(bedroom);

    house.showRooms();
  }
}
