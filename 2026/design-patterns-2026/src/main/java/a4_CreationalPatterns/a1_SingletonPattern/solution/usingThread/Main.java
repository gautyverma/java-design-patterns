package a4_CreationalPatterns.a1_SingletonPattern.solution.usingThread;

class Singleton {
  // 1. Create static obj
  static Singleton singletonObject;

  // 2. Make private Constructor - using this we can't create outside its own class using new
  private Singleton() {
    System.out.println("Instance Created");
  }

  // 3. Make method to access singleton object created
  public static synchronized Singleton getInstance() {
    if (singletonObject == null) {
      System.out.println("1st thread is creating instance");
      singletonObject = new Singleton();
    } else {
      System.out.println("Instance already created by 1st thread, returning existing instance");
    }
    return singletonObject;
  }
}

public class Main {
  public static void main(String[] args) {

    Thread t1 =
        new Thread(
            new Runnable() {
              @Override
              public void run() {
                Singleton obj = Singleton.getInstance();
              }
            });

    Thread t2 =
        new Thread(
            new Runnable() {
              @Override
              public void run() {
                Singleton obj = Singleton.getInstance();
              }
            });
    t1.start();
    t2.start();
  }
}
