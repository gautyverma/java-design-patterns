package org.example;

import java.util.ArrayList;
import java.util.List;

// Component
interface Task {
  String getTitle();

  void setTitle(String title);

  void display();
}

// Leaf - represent independent operation
class SimpleTask implements Task {
  private String title;

  public SimpleTask(String title) {
    this.title = title;
  }

  @Override
  public String getTitle() {
    return title;
  }

  @Override
  public void setTitle(String title) {
    this.title = title;
  }

  @Override
  public void display() {
    System.out.println("Simple Task : " + title);
  }
}

// Composite -> Has-a relation with Task
class TaskList implements Task {
  private String title;
  private List<Task> tasks;

  public TaskList(String title) {
    this.title = title;
    this.tasks = new ArrayList<>();
  }

  @Override
  public String getTitle() {
    return title;
  }

  @Override
  public void setTitle(String title) {
    this.title = title;
  }

  @Override
  public void display() {
    System.out.println("Task List: " + title);
    for (Task task : tasks) {
      task.display();
    }
  }

  // Add
  public void addTask(Task task) {
    tasks.add(task);
  }

  // Remove
  public void removeTask(Task task) {
    tasks.remove(task);
  }
}

public class Main {
  public static void main(String[] args) {
    // Creating simple tasks
    Task simpleTask1 = new SimpleTask("Java Coding");
    Task simpleTask2 = new SimpleTask("Design Patterns");

    // Creating a task List
    TaskList projectTasks = new TaskList("Tasks to do");
    projectTasks.addTask(simpleTask1);
    projectTasks.addTask(simpleTask2);

    projectTasks.display();
  }
}
