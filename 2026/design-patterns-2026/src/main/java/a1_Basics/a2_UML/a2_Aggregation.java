package a1_Basics.a2_UML;

import java.util.List;

/**
 * Aggregation → “weak HAS-A”
 *
 *Meaning: One class has another, but both can exist separately
 * Department HAS-A Professors
 * If department is deleted → professors still exist
 *
 * Real-world: Department → Professors
 */
class Professor {
  private String name;

  public Professor(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}

class Department {
  private String name;
  private List<Professor> professors;

  public Department(String name, List<Professor> professors) {
    this.name = name;
    this.professors = professors;
  }

  public void showProfessors() {
    System.out.println("Department: " + name);
    System.out.println("Professors:");
    for (Professor professor : professors) {
      System.out.println("- " + professor.getName());
    }
  }
}

public class a2_Aggregation {
  public static void main(String[] args) {
    Professor prof1 = new Professor("Dr. Smith");
    Professor prof2 = new Professor("Dr. Johnson");

    Department csDepartment = new Department("Computer Science", List.of(prof1, prof2));
    csDepartment.showProfessors();
    // output:Department: Computer Science
    // Professors:
    // - Dr. Smith
    // - Dr. Johnson

    System.out.println("-----------------------------");
    // Professors can exist independently of the department
    System.out.println("Professor 1: " + prof1.getName());
    System.out.println("Professor 2: " + prof2.getName());
  }
}
