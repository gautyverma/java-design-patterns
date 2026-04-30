package a4_CreationalPatterns.a1_SingletonPattern.solution;

public class SingletonPattern {
  public static void main(String[] args) {
    AppSetings setting1 = AppSetings.getInstance();
    AppSetings setting2 = AppSetings.getInstance();

    System.out.println("Settings 1 API Key: " + setting1.getApiKey());
    System.out.println("Settings 2 API Key: " + setting2.getApiKey());

    // more memory usage and potential inconsistency if settings are modified in one instance but
    // not the other
    System.out.println("Are both settings instances the same? " + (setting1 == setting2));
    // Output - TRUE will show that both setting1 and setting2 are the same instance, demonstrating
    // the Singleton pattern
  }
}
