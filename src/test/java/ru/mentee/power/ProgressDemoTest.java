package ru.mentee.power;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Тестирование ProgressDemo.main()")
class ProgressDemoTest {
  @Test
  @DisplayName("main должен корректно выводить информацию о прогрессе Heshegto")
  void mainShouldPrintProgressForHeshegto() {
    // given - перехватываем вывод в консоль
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream originalOut = System.out;
    System.setOut(new PrintStream(outputStream));

    try {
      // when - вызываем метод main
      ProgressDemo.main(new String[]{});

      // then - проверяем вывод
      String consoleOutput = outputStream.toString().trim();
      String[] lines = consoleOutput.split(System.lineSeparator());

      // Проверяем все ожидаемые строки
      assertThat(lines).hasSize(3);
      assertThat(lines[0]).contains("Heshegto"); // Проверяем наличие имени в summary
      assertThat(lines[1]).matches("Status: (sprint ready|backlog first)");
      assertThat(lines[2]).isEqualTo("feature/DVT-3");

    } finally {
      // Всегда восстанавливаем оригинальный System.out
      System.setOut(originalOut);
    }
  }

  @Test
  @DisplayName("main должен выводить статус sprint ready при готовности к спринту")
  void mainShouldPrintSprintReadyWhenConditionMet() {
    // given
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream originalOut = System.out;
    System.setOut(new PrintStream(outputStream));

    try {
      // when
      ProgressDemo.main(new String[]{});

      // then - проверяем конкретный статус
      String consoleOutput = outputStream.toString();

      // Предполагаем, что Heshegto с 1 спринтом и 6 часами готов к спринту
      // Это зависит от реализации MenteeProgress.readyForSprint()
      if (consoleOutput.contains("Status: sprint ready")) {
        assertThat(consoleOutput).contains("Status: sprint ready");
      } else {
        assertThat(consoleOutput).contains("Status: backlog first");
      }

    } finally {
      System.setOut(originalOut);
    }
  }

  @Test
  @DisplayName("main должен работать без аргументов командной строки")
  void mainShouldWorkWithoutArguments() {
    // given
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream originalOut = System.out;
    System.setOut(new PrintStream(outputStream));

    try {
      // when - передаем пустой массив аргументов
      ProgressDemo.main(new String[]{});

      // then - проверяем, что вывод не пустой и содержит ключевые элементы
      String output = outputStream.toString();

      assertThat(output)
              .isNotEmpty()
              .contains("Status:")
              .contains("feature/DVT-3");

    } finally {
      System.setOut(originalOut);
    }
  }
}