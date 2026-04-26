package a3_BehavioralPatterns.a2_Observer;

import java.util.ArrayList;
import java.util.List;

// observer interface
interface Observer {
  void update(float temp);
}

// subject interface
interface Subject {
  void registerObserver(Observer o);

  void removeObserver(Observer o);

  void notifyObservers();
}

// concrete subject = weather station
class WeatherStn implements Subject {

  private float temp;
  private List<Observer> observersList;

  public WeatherStn() {
    observersList = new ArrayList<>();
  }

  public void setTemp(float temp) {
    this.temp = temp;
    // notify all observers about the change in temperature
    notifyObservers();
  }

  @Override
  public void registerObserver(Observer o) {
    observersList.add(o);
  }

  @Override
  public void removeObserver(Observer o) {
    observersList.remove(o);
  }

  @Override
  public void notifyObservers() {
    for (Observer obs : observersList) {
      obs.update(temp); // Run-time polymorphism
    }
  }
}

class DisplayDeviceIn implements Observer {

  @Override
  public void update(float temp) {
    System.out.println("Temperature updated on displayDevice: " + temp);
  }
}

class MobileApp implements Observer {

  @Override
  public void update(float temp) {
    System.out.println("Temperature updated on mobile: " + temp);
  }
}

public class ObserverPatternSol {
  public static void main(String[] args) {
    // Create a publisher
    WeatherStn station = new WeatherStn();

    // create subscribers
    DisplayDeviceIn display = new DisplayDeviceIn();
    MobileApp mobileApp = new MobileApp();

    // register subscribers to publisher
    station.registerObserver(display);
    station.registerObserver(mobileApp);

    // update temperature
    station.setTemp(30.5f);
    station.setTemp(22.5f);

    // Unregister a subscriber
    station.removeObserver(display);
    station.setTemp(27.0f);
  }
}
