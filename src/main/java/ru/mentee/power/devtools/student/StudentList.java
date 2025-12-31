package ru.mentee.power.devtools.student;

import java.util.ArrayList;
import java.util.List;  // порядок импортов нарушен

public class StudentList { //нарушение: неправильное имя класса camelCase
  private List<Student> studentList;  // нарушение: snake_case

  public StudentList() { // нарушение: 4 пробела вместо 2х
    studentList = new ArrayList<>();
  }

  // нарушение: имя метода
  public void addStudent(Student student) {
    if (student != null) {  // нарушение: нет пробела после if, и также нет фигурных скобок
      studentList.add(student);

    }
  }
  // нарушение: длинная строка (>120 символов)

  public List<Student> getStudents(String city) {
    return studentList.stream().filter(s -> s.city().equals(city)).toList();
  }
}