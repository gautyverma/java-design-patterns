package a4_CreationalPatterns.a1_SingletonPattern.solution;

public class AppSetings {

  // Step 1: Create a private static instance of the class
  private static AppSetings instance;
  private String dataBaseUrl;
  private String apiKey;

  // Step 2: Make the constructor private to prevent instantiation from outside the class
  private AppSetings() {
    dataBaseUrl = "jdbc:mysql://localhost:3306/mydb";
    apiKey = "12345-ABCDE";
  }

  // Step 3: Provide a public static method to access the instance
  public static AppSetings getInstance() {
    if (instance == null) {
      instance = new AppSetings();
    }
    return instance;
  }

  public String getDataBaseUrl() {
    return dataBaseUrl;
  }

  public String getApiKey() {
    return apiKey;
  }
}
