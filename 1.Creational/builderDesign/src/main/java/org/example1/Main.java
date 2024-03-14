package org.example1;

class Phone {
  private String os;
  private int ram;
  private String processor;
  private double screenSize;
  private int battery;

  public Phone(String os, int ram, String processor, double screenSize, int battery) {
    this.os = os;
    this.ram = ram;
    this.processor = processor;
    this.screenSize = screenSize;
    this.battery = battery;
  }

  @Override
  public String toString() {
    return "Phone{"
        + "os='"
        + os
        + '\''
        + ", ram="
        + ram
        + ", processor='"
        + processor
        + '\''
        + ", screenSize="
        + screenSize
        + ", battery="
        + battery
        + '}';
  }
}

class PhoneBuilder {
  private String os;
  private int ram;
  private String processor;
  private double screenSize;
  private int battery;

  public PhoneBuilder setOs(String os) {
    this.os = os;
    return this;
  }

  public PhoneBuilder setRam(int ram) {
    this.ram = ram;
    return this;
  }

  public PhoneBuilder setProcessor(String processor) {
    this.processor = processor;
    return this;
  }

  public PhoneBuilder setScreenSize(double screenSize) {
    this.screenSize = screenSize;
    return this;
  }

  public PhoneBuilder setBattery(int battery) {
    this.battery = battery;
    return this;
  }

  public Phone getPhone() {
    return new Phone(os, ram, processor, screenSize, battery);
  }
}

public class Main {
  public static void main(String[] args) {
    // Naive Approach
    Phone phone = new Phone("IOS", 2, "apple", 5.1, 4500);
    System.out.println(phone.toString());

    // Builder Design pattern
    Phone phone1 =
        new PhoneBuilder().setOs("IOS").setProcessor("apple").setBattery(4500).getPhone();
    System.out.println(
        phone1.toString()); // O/p - Phone{os='IOS', ram=0, processor='apple', screenSize=0.0,
    // battery=4500}
  }
}
