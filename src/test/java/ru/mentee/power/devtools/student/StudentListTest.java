package ru.mentee.power.devtools.student;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Тестирование Student и StudentList")
class StudentListTest {
  private StudentList studentList;

  @BeforeEach
  void setUp() {
    studentList = new StudentList();
  }

  // ========== Тесты для Student record ==========
  @Test
  @DisplayName("Student record должен корректно создаваться с name и city")
  void studentRecordShouldBeCreatedWithNameAndCity() {
    // given
    String name = "Иван";
    String city = "Москва";

    // when
    Student student = new Student(name, city);

    // then
    assertThat(student.name()).isEqualTo(name);
    assertThat(student.city()).isEqualTo(city);
  }

  @Test
  @DisplayName("Student record должен корректно работать с equals и hashCode")
  void studentRecordShouldHaveProperEqualsAndHashCode() {
    // given
    Student student1 = new Student("Мария", "Санкт-Петербург");
    Student student2 = new Student("Мария", "Санкт-Петербург");
    Student student3 = new Student("Пётр", "Казань");

    // when & then
    assertThat(student1).isEqualTo(student2);
    assertThat(student1.hashCode()).isEqualTo(student2.hashCode());
    assertThat(student1).isNotEqualTo(student3);
  }

  @Test
  @DisplayName("Student record toString должен содержать name и city")
  void studentRecordToStringShouldContainNameAndCity() {
    // given
    Student student = new Student("Анна", "Новосибирск");

    // when
    String result = student.toString();

    // then
    assertThat(result)
        .contains("Анна")
        .contains("Новосибирск")
        .contains("Student");
  }

  // ========== Тесты для StudentList class ==========
  @Test
  @DisplayName("StudentList конструктор должен инициализировать пустой список")
  void studentListConstructorShouldInitializeEmptyList() {
    // given & when
    StudentList newList = new StudentList();

    // then
    // Вместо getStudents(null) проверяем через рефлексию или создаем метод getter
    assertThat(newList.getStudents("anyCity")).isEmpty();
  }

  @Test
  @DisplayName("addStudent должен добавлять студента когда передан не-null объект")
  void addStudentShouldAddStudentWhenNotNull() {
    // given
    Student student = new Student("Иван", "Москва");

    // when
    studentList.addStudent(student);

    // then
    // Используем конкретный город для проверки
    List<Student> moscowStudents = studentList.getStudents("Москва");
    assertThat(moscowStudents).hasSize(1);
    assertThat(moscowStudents.get(0)).isEqualTo(student);
  }

  @Test
  @DisplayName("addStudent не должен добавлять студента когда передан null")
  void addStudentShouldNotAddStudentWhenNull() {
    // given
    Student student = null;

    // when
    studentList.addStudent(student);

    // then
    // Проверяем что список пуст для любого города
    assertThat(studentList.getStudents("Москва")).isEmpty();
    assertThat(studentList.getStudents("Санкт-Петербург")).isEmpty();
  }

  @Test
  @DisplayName("getStudents должен фильтровать студентов по городу")
  void getStudentsShouldFilterStudentsByCity() {
    // given
    Student student1 = new Student("Иван", "Москва");
    Student student2 = new Student("Мария", "Санкт-Петербург");
    Student student3 = new Student("Пётр", "Москва");
    studentList.addStudent(student1);
    studentList.addStudent(student2);
    studentList.addStudent(student3);

    // when
    List<Student> result = studentList.getStudents("Москва");

    // then
    assertThat(result).hasSize(2);
    assertThat(result).containsExactlyInAnyOrder(student1, student3);
  }

  @Test
  @DisplayName("метод должен возвращать пустой список когда нет студентов из указанного города")
  void getStudentsShouldReturnEmptyListWhenNoStudentsFromCity() {
    // given
    Student student1 = new Student("Иван", "Москва");
    Student student2 = new Student("Мария", "Санкт-Петербург");
    studentList.addStudent(student1);
    studentList.addStudent(student2);

    // when
    List<Student> result = studentList.getStudents("Казань");

    // then
    assertThat(result).isEmpty();
  }

  @Test
  @DisplayName("getStudents должен возвращать пустой список для пустой коллекции")
  void getStudentsShouldReturnEmptyListForEmptyCollection() {
    // given & when
    List<Student> result = studentList.getStudents("Москва");

    // then
    assertThat(result).isEmpty();
  }

  @Test
  @DisplayName("getStudents должен учитывать регистр при фильтрации по городу")
  void getStudentsShouldBeCaseSensitiveWhenFilteringByCity() {
    // given
    Student student = new Student("Иван", "Москва");
    studentList.addStudent(student);

    // when
    List<Student> resultLowercase = studentList.getStudents("москва");
    List<Student> resultUppercase = studentList.getStudents("МОСКВА");

    // then
    assertThat(resultLowercase).isEmpty();
    assertThat(resultUppercase).isEmpty();
    // Только точное совпадение должно работать
    assertThat(studentList.getStudents("Москва")).hasSize(1);
  }

  @Test
  @DisplayName("Множественные добавления студентов должны работать корректно")
  void multipleStudentAdditionsShouldWorkCorrectly() {
    // given
    Student student1 = new Student("Студент1", "Город1");
    Student student2 = new Student("Студент2", "Город2");
    Student student3 = new Student("Студент3", "Город1");

    // when
    studentList.addStudent(student1);
    studentList.addStudent(student2);
    studentList.addStudent(student3);
    studentList.addStudent(student1); // дубликат

        // then
    List<Student> city1Students = studentList.getStudents("Город1");
    List<Student> city2Students = studentList.getStudents("Город2");

    assertThat(city1Students).hasSize(3); // student1, student3, student1(дубль)
    assertThat(city2Students).hasSize(1); // student2
  }

  @Test
  @DisplayName("getStudents должен возвращать пустой список при null city")
  void getStudentsShouldReturnEmptyListWhenCityIsNull() {
    // given
    Student student = new Student("Иван", "Москва");
    studentList.addStudent(student);

    // when
    List<Student> result = studentList.getStudents(null);

    // then
    assertThat(result).isEmpty();
  }

  @Test
  @DisplayName("getStudents должен возвращать студентов из разных городов по отдельности")
  void getStudentsShouldReturnStudentsFromDifferentCitiesSeparately() {
    // given
    Student student1 = new Student("Иван", "Москва");
    Student student2 = new Student("Мария", "Санкт-Петербург");
    Student student3 = new Student("Пётр", "Москва");
    Student student4 = new Student("Анна", "Санкт-Петербург");

    studentList.addStudent(student1);
    studentList.addStudent(student2);
    studentList.addStudent(student3);
    studentList.addStudent(student4);

    // when
    List<Student> moscowStudents = studentList.getStudents("Москва");
    List<Student> spbStudents = studentList.getStudents("Санкт-Петербург");

    // then
    assertThat(moscowStudents).hasSize(2);
    assertThat(moscowStudents).containsExactlyInAnyOrder(student1, student3);

    assertThat(spbStudents).hasSize(2);
    assertThat(spbStudents).containsExactlyInAnyOrder(student2, student4);
  }
}