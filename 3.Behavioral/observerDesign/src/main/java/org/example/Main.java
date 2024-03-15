package org.example;

import java.util.ArrayList;
import java.util.List;

class Subscriber {
  private String name;
  private Channel channel = new Channel();

  public Subscriber(String name) {
    this.name = name;
  }

  public void update() {
    System.out.println(name+" - Video uploaded!");
  }

  public void subscribeChannel(Channel ch) {
    channel = ch;
  }
}

class Channel {
  private List<Subscriber> subscriberList = new ArrayList<>();
  private String title;

  public void subscribe(Subscriber sub) {
    subscriberList.add(sub);
  }

  public void unSubscribe(Subscriber sub) {
    subscriberList.remove(sub);
  }

  public void notifySubscriber() {
    for (Subscriber sub : subscriberList) {
      sub.update();
    }
  }

  public void upload(String title) {
    this.title = title;
    notifySubscriber();
  }
}

public class Main {
  public static void main(String[] args) {
    Channel matuga = new Channel();
    Subscriber s1 = new Subscriber("gauty");
    Subscriber s2 = new Subscriber("gautam");
    Subscriber s3 = new Subscriber("gaurav");

    matuga.subscribe(s1);
    matuga.subscribe(s2);
    matuga.subscribe(s3);

    matuga.unSubscribe(s3);

    s1.subscribeChannel(matuga);
    s2.subscribeChannel(matuga);
    s3.subscribeChannel(matuga);


    matuga.upload("Learn Java");
  }
}
