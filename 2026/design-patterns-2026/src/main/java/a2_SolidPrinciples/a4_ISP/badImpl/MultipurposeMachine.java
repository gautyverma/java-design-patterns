package a2_SolidPrinciples.a4_ISP.badImpl;

import a2_SolidPrinciples.a4_ISP.Document;

public class MultipurposeMachine implements Machine {
  @Override
  public void print(Document document) {
    System.out.println("Printing document: " + document);
  }

  @Override
  public void scan(Document document) {
    System.out.println("Scanning document: " + document);
  }

  @Override
  public void fax(Document document) {
    System.out.println("Faxing document: " + document);
  }

  @Override
  public void copy(Document document) {
    System.out.println("Copying document: " + document);
  }
}
