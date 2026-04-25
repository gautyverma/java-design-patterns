package a2_SolidPrinciples.a4_ISP.badImpl;

import a2_SolidPrinciples.a4_ISP.Document;

public class SimplePrinter implements Machine {
  @Override
  public void print(Document document) {
    System.out.println("Printing document: " + document);
  }

  @Override
  public void scan(Document document) {
    throw new UnsupportedOperationException("SimplePrinter cannot scan");
  }

  @Override
  public void fax(Document document) {
    throw new UnsupportedOperationException("SimplePrinter cannot fax");
  }

  @Override
  public void copy(Document document) {
    throw new UnsupportedOperationException("SimplePrinter cannot copy");
  }
}
