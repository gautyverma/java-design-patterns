package a2_SolidPrinciples.a4_ISP.goodImpl;

import a2_SolidPrinciples.a4_ISP.Document;

public class SimplePrinter implements Print, Scan {
  @Override
  public void print(Document document) {
    System.out.println("Printing document: " + document);
  }

  @Override
  public void scan(Document document) {
    System.out.println("Scanning document: " + document);
  }
}
