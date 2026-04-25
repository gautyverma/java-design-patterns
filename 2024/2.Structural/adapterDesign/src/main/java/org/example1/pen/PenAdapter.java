package org.example1.pen;

public class PenAdapter implements Pen {
  PilotPen pp = new PilotPen();

  @Override
  public void write(String str) {
    pp.draw(str);
  }
}
