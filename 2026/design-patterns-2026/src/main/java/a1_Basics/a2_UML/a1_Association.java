package a1_Basics.a2_UML;

/**
 * Association: Two classes are related, but independent
 *
 * <p>Understanding: Teacher uses Student Both can exist independently
 *
 * <p>Real-world: Teacher ↔ Student
 */
public class a1_Association {
  public static void main(String[] args) {
    Teacher teacher = new Teacher("Mr. Smith");
    Student student = new Student("Alice");

    teacher.teach(student);
  }
}

class Teacher {
  String name;

  public Teacher(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void teach(Student student) {
    System.out.println(name + " is teaching " + student.getName());
  }
}

class Student {
  String name;

  public Student(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
