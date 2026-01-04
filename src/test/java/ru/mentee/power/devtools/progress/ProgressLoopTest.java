package ru.mentee.power.devtools.progress;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Тестирование ProgressTracker")
class ProgressLoopTest {
  @Test
  @DisplayName("Должен корректно вычислить суммарный прогресс когда передан массив mentee")
  void shouldCalculateTotalProgressWhenMultipleMentees() {
    // given - подготовка данных
    ProgressTracker tracker = new ProgressTracker();
    Mentee[] mentees = {
        new Mentee("Иван", "Москва", "Backend разработка", 5, 12),
        new Mentee("Мария", "Санкт-Петербург", "Fullstack", 8, 12),
        new Mentee("Пётр", "Казань", "Java Backend", 12, 12)
    };

    // when - выполнение действия
    String result = tracker.calculateTotalProgress(mentees);

    // then - проверка результата с assertJ
    assertThat(result)
            .contains("пройдено 25 из 36 уроков")
            .contains("осталось 11 уроков");
  }

  @Test
  @DisplayName("Должен корректно обработать массив когда все mentee завершили курс")
  void shouldCalculateTotalProgressWhenAllMenteesCompleted() {
  // given
    ProgressTracker tracker = new ProgressTracker();
    Mentee[] mentees = {
        new Mentee("Иван", "Москва", "Backend", 12, 12),
        new Mentee("Мария", "СПб", "Fullstack", 12, 12)
    };

    // when
    String result = tracker.calculateTotalProgress(mentees);

    // then
    assertThat(result)
            .contains("пройдено 24 из 24 уроков")
            .contains("осталось 0 уроков");
  }

  @Test
  @DisplayName("main должен корректно выводить суммарный прогресс для заданного массива mentee")
  void mainShouldPrintCorrectProgressForGivenMentees() {
    // given - перехватываем вывод в консоль
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream originalOut = System.out;
    System.setOut(new PrintStream(outputStream));

    // when - вызываем метод main
    ProgressTracker.main(new String[]{});

    // then - восстанавливаем оригинальный вывод и проверяем результат
    System.setOut(originalOut);
    String consoleOutput = outputStream.toString().trim();

    assertThat(consoleOutput)
            .contains("пройдено 25 из 36 уроков")
            .contains("осталось 11 уроков");
  }

  @Test
  @DisplayName("main должен корректно работать без аргументов")
  void mainShouldWorkWithoutArguments() {
    // given
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream originalOut = System.out;
    System.setOut(new PrintStream(outputStream));

    // when
    try {
      ProgressTracker.main(new String[]{});
    } finally {
      // then - всегда восстанавливаем System.out
      System.setOut(originalOut);
      String output = outputStream.toString().trim();
      assertThat(output).isNotEmpty();
      assertThat(output).contains("Суммарно:");
    }
  }
}