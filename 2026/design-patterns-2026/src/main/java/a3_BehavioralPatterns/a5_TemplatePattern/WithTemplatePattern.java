package a3_BehavioralPatterns.a5_TemplatePattern;

abstract class DataPraser {

  public void parse(String data) {
    openFile();
    parseData();
    closeFile();
  }

  protected void openFile() {
    System.out.println("Opening file...");
  }

  protected void closeFile() {
    System.out.println("Closing file...");
  }

  protected abstract void parseData();
}

class CSVDataParser extends DataPraser {

  @Override
  protected void parseData() {
    System.out.println("Parsing CSV data...");
  }
}

class JSONDataParser extends DataPraser {

  @Override
  protected void parseData() {
    System.out.println("Parsing JSON data...");
  }
}

public class WithTemplatePattern {
  public static void main(String[] args) {
    DataPraser csvParser = new CSVDataParser();
    csvParser.parse("data.csv");

    System.out.println();

    DataPraser jsonParser = new JSONDataParser();
    jsonParser.parse("data.json");
  }
}
