package a4_CreationalPatterns.a1_SingletonPattern.problem;

public class WithoutSingleton {
  public static void main(String[] args) {
    AppSetings setting1 = new AppSetings();
    AppSetings setting2 = new AppSetings();

    System.out.println("Settings 1 Database URL: " + setting1.getDataBaseUrl());
    System.out.println("Settings 1 API Key: " + setting1.getApiKey());

    System.out.println("Settings 2 Database URL: " + setting2.getDataBaseUrl());
    System.out.println("Settings 2 API Key: " + setting2.getApiKey());

    // more memory usage and potential inconsistency if settings are modified in one instance but
    // not the other
    System.out.println("Are both settings instances the same? " + (setting1 == setting2));
    // Output - FALSE will show that setting1 and setting2 are different instances, demonstrating the problem of not using the Singleton pattern
  }
}
