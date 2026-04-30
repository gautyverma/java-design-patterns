package a4_CreationalPatterns.a1_SingletonPattern.problem;

public class AppSetings {

  private String dataBaseUrl;
  private String apiKey;

  public AppSetings() {
    dataBaseUrl = "jdbc:mysql://localhost:3306/mydb";
    apiKey = "12345-ABCDE";
  }

  public String getDataBaseUrl() {
    return dataBaseUrl;
  }

  public String getApiKey() {
    return apiKey;
  }
}
