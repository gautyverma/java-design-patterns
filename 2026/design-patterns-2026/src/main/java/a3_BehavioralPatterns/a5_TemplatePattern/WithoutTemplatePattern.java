package a3_BehavioralPatterns.a5_TemplatePattern;

class CSVParser {

  public void parseCSV() {
    openFile();
    // CSV parsing logic
    System.out.println("Parsing CSV file...");
    closeFile();
  }

  private void openFile() {
    System.out.println("Opening CSV file...");
  }

  private void closeFile() {
    System.out.println("Closing CSV file...");
  }
}

class JsonParser {

  public void parseJSON() {
    openFile();
    // JSON parsing logic
    System.out.println("Parsing JSON file...");
    closeFile();
  }

  private void openFile() {
    System.out.println("Opening JSON file...");
  }

  private void closeFile() {
    System.out.println("Closing JSON file...");
  }
}

public class WithoutTemplatePattern {
  public static void main(String[] args) {
    CSVParser csvParser = new CSVParser();
    csvParser.parseCSV();

    System.out.println();

    JsonParser jsonParser = new JsonParser();
    jsonParser.parseJSON();
  }
}
