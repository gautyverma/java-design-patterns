package a3_BehavioralPatterns.a2_Observer;

class DisplayDevice {
  public void showTemp(float temp) {
    System.out.println("Current temperature: " + temp + "c");
  }
}

class WeatherStation {
  private float temperature;
  private DisplayDevice displayDevice; // can be multiple devices in a real implementation

  public WeatherStation(DisplayDevice displayDevice) {
    this.displayDevice = displayDevice;
  }

  public void setTemperature(float temperature) {
    this.temperature = temperature;
    notifyDevice();
  }

  public void notifyDevice() {
    displayDevice.showTemp(temperature);
  }
}

public class WithoutObserverPattern {
  public static void main(String[] args) {
    DisplayDevice device = new DisplayDevice();
    WeatherStation station = new WeatherStation(device);

    // tight coupling: WeatherStation directly calls DisplayDevice to update the temperature
    station.setTemperature(26);
    station.setTemperature(30);
  }
}
