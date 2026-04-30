# Observer Pattern

## Overview

The **Observer Pattern** is a behavioral design pattern that defines a one-to-many dependency between objects so that when one object (subject) changes state, all its dependents (observers) are notified and updated automatically.

---

## Why Use Observer Pattern?

- **Loose Coupling**: Subject and observers are loosely coupled - subject doesn't need to know concrete observer classes
- **Dynamic Relationships**: Observers can be added or removed at runtime
- **Broadcast Communication**: One subject can notify multiple observers simultaneously
- **Separation of Concerns**: Subject focuses on its core logic, observers handle their own updates

---

## Use Cases

- GUI components (button clicks notify multiple listeners)
- Weather monitoring systems (weather station notifies multiple displays)
- Stock price updates (stock ticker notifies multiple investors)
- Event-driven systems (event publishers notify subscribers)
- Model-View architectures (model changes update multiple views)

---

## Key Components

| Component | Responsibility |
|-----------|-----------------|
| **Subject** | Interface/abstract class defining observer management methods |
| **Observer** | Interface defining update method for receiving notifications |
| **Concrete Subject** | Implements Subject, maintains state and notifies observers |
| **Concrete Observer** | Implements Observer, reacts to subject state changes |

---

## Implementation

### 1. Observer Interface

**Observer.java** - Defines the contract for observers:

```java
// observer interface
interface Observer {
  void update(float temp);
}
```

**Key Points:**
- Defines `update()` method that concrete observers must implement
- Receives state change notifications from subject
- Parameter `temp` represents the changed state data

---

### 2. Subject Interface

**Subject.java** - Defines observer management:

```java
// subject interface
interface Subject {
  void registerObserver(Observer o);

  void removeObserver(Observer o);

  void notifyObservers();
}
```

**Key Points:**
- `registerObserver()`: Adds observers to the notification list
- `removeObserver()`: Removes observers from the notification list
- `notifyObservers()`: Notifies all registered observers of state changes

---

### 3. Concrete Subject

**WeatherStn.java** - Weather station implementation:

```java
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
```

**Key Points:**
- Maintains temperature state (`temp`)
- Manages list of observers (`observersList`)
- `setTemp()` triggers notification when temperature changes
- `notifyObservers()` iterates through all observers and calls their `update()` method

---

### 4. Concrete Observers

**DisplayDeviceIn.java** - Display device observer:

```java
class DisplayDeviceIn implements Observer {

  @Override
  public void update(float temp) {
    System.out.println("Temperature updated on displayDevice: " + temp);
  }
}
```

**MobileApp.java** - Mobile application observer:

```java
class MobileApp implements Observer {

  @Override
  public void update(float temp) {
    System.out.println("Temperature updated on mobile: " + temp);
  }
}
```

**Key Points:**
- Both implement the `Observer` interface
- Each reacts differently to temperature updates
- Display device shows temperature on physical display
- Mobile app shows temperature in mobile interface

---

## Usage Example

**ObserverPatternSol.java** - Demonstrates observer pattern:

```java
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
```

### Output

```
Temperature updated on displayDevice: 30.5
Temperature updated on mobile: 30.5
Temperature updated on displayDevice: 22.5
Temperature updated on mobile: 22.5
Temperature updated on mobile: 27.0
```

---

## Code Flow

1. Create WeatherStn and observers (DisplayDeviceIn, MobileApp)
2. Register observers with station
3. setTemp() updates temperature and calls notifyObservers()
4. notifyObservers() loops through observersList, calling update() on each
5. Observers display temperature updates
6. removeObserver() unsubscribes display device
7. Subsequent updates only notify remaining observers

---

## Advantages

✅ **Loose Coupling**: Subject and observers are decoupled through interfaces  
✅ **Dynamic Relationships**: Observers can be added/removed at runtime  
✅ **Broadcast Communication**: One-to-many notifications  
✅ **Extensibility**: New observers can be added without modifying subject  
✅ **Separation of Concerns**: Subject focuses on state, observers on reactions  

---

## Disadvantages

❌ **Unpredictable Updates**: Observers may react in unexpected ways  
❌ **Memory Leaks**: Forgotten observers may prevent garbage collection  
❌ **Debugging Difficulty**: Hard to track which observers are notified  
❌ **Performance**: Notifying many observers can be expensive  
❌ **Spurious Updates**: Observers may be notified even when state hasn't meaningfully changed  

---

## Real-World Analogies

- **News Agency**: Newspaper subscribers get notified when news is published
- **YouTube Channel**: Subscribers get notified when new video is uploaded
- **Stock Market**: Investors get notified when stock prices change
- **Social Media**: Followers get notified when someone posts updates
- **Email Lists**: Subscribers receive emails when newsletters are sent

---

## Related Patterns

- **Mediator Pattern**: Centralizes complex communications between objects
- **Command Pattern**: Encapsulates requests as objects
- **Publish-Subscribe Pattern**: More sophisticated event system
- **Model-View-Controller (MVC)**: Uses Observer for model-view communication
