package org.example1;

import org.example1.pen.Pen;
import org.example1.pen.PenAdapter;

class AssignmentWork {
  private Pen pen;

  public Pen getPen() {
    return pen;
  }

  public void setPen(Pen pen) {
    this.pen = pen;
  }

  public void writeAssignment(String str) {
    pen.write(str);
  }
}

public class School {
  public static void main(String[] args) {
    //    // Naive Approach - We haven't given impl to pen interface
    //    AssignmentWork aw = new AssignmentWork();
    //    aw.writeAssignment("Assignment Completed");
    // ----------------------------------------------------
    //    PilotPen pp = new PilotPen(); // -> Can't pass the object of pilotPen we need a adapter to
    // make connection.
    Pen adapter = new PenAdapter();
    AssignmentWork aw = new AssignmentWork();
    aw.setPen(adapter);
    aw.writeAssignment("Assignment Completed");
  }
}
