package a4_CreationalPatterns.a1_SingletonPattern.problem;

public class WithoutSingleton {
  public static void main(String[] args) {
    AppSetings setting1 = new AppSetings();
    AppSetings setting2 = new AppSetings();

    System.out.println("Settings 1 Database URL: " + setting1.getDataBaseUrl());
    System.out.println("Settings 1 API Key: " + setting1.getApiKey());

    System.out.println("Settings 2 Database URL: " + setting2.getDataBaseUrl());
    System.out.println("Settings 2 API Key: " + setting2.getApiKey());
  }
}
