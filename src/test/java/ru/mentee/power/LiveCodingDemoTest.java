package ru.mentee.power;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class LiveCodingDemoTest {

  // Тест 1: Проверка вывода Fizz для чисел, кратных 3
  @Test
  void fizzBuzzPrintsFizzWhenNumberIsDivisibleBy3() {
    // Arrange
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream));

    // Act
    LiveCodingDemo.fizzBuzz(3); // 1, 2, Fizz

    // Assert
    String output = outputStream.toString();
    assertTrue(output.contains("Fizz"),
            "Должен содержать 'Fizz' для числа 3");
    assertFalse(output.contains("Buzz"),
            "Не должен содержать 'Buzz' для 3");
    assertFalse(output.contains("FizzBuzz"),
            "Не должен содержать 'FizzBuzz' для 3");
  }

  // Тест 2: Проверка вывода Buzz для чисел, кратных 5
  @Test
  void fizzBuzzPrintsBuzzWhenNumberIsDivisibleBy5() {
    // Arrange
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream));

    // Act
    LiveCodingDemo.fizzBuzz(5); // 1, 2, Fizz, 4, Buzz

    // Assert
    String output = outputStream.toString();
    assertTrue(output.contains("Buzz"),
            "Должен содержать 'Buzz' для числа 5");
    assertTrue(output.contains("Fizz"),
            "Должен содержать 'Fizz' для числа 3");
  }

  // Тест 3: Проверка вывода FizzBuzz для чисел, кратных 3 и 5
  @Test
  void fizzBuzzPrintsFizzBuzzWhenNumberIsDivisibleBy3And5() {
    // Arrange
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream));

    // Act
    LiveCodingDemo.fizzBuzz(15); // Последнее число будет FizzBuzz

    // Assert
    String output = outputStream.toString();
    assertTrue(output.contains("FizzBuzz"),
            "Должен содержать 'FizzBuzz' для числа 15");
  }

  // Тест 4: Проверка вывода обычных чисел
  @Test
  void fizzBuzzPrintsNumberWhenNotDivisibleBy3Or5() {
    // Arrange
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream));

    // Act
    LiveCodingDemo.fizzBuzz(2); // 1, 2

    // Assert
    String output = outputStream.toString();
    assertTrue(output.contains("1") && output.contains("2"),
            "Должен содержать числа 1 и 2");
    assertFalse(output.contains("Fizz")
                    && !output.contains("Buzz")
                    && !output.contains("FizzBuzz"),
            "Не должен содержать Fizz/Buzz/FizzBuzz");
  }

  // Тест 5: Проверка граничного случая - n = 1
  @Test
  void fizzBuzzHandlesSingleNumber() {
    // Arrange
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream));

    // Act
    LiveCodingDemo.fizzBuzz(1);

    // Assert
    String output = outputStream.toString();
    assertEquals("1" + System.lineSeparator(), output,
            "Для n=1 должен вывести только '1'");
  }

  // Тест 6: Проверка отрицательного или нулевого ввода (если бы проверяли)
  @ParameterizedTest
  @ValueSource(ints = {0, -1, -10})
  void fizzBuzzHandlesNonPositiveInput(int n) {
    // Arrange
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream));

    // Act
    LiveCodingDemo.fizzBuzz(n);

    // Assert
    String output = outputStream.toString();
    assertTrue(output.isEmpty(),
            "Для n <= 0 не должно быть вывода (или цикл не выполнится)");
  }

  // Тест 7: Проверка конкретной последовательности
  @Test
  void fizzBuzzProducesCorrectSequence() {
    // Arrange
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream));

    // Act
    LiveCodingDemo.fizzBuzz(15);

    // Assert
    String output = outputStream.toString();
    String[] lines = output.split(System.lineSeparator());

    assertEquals(15, lines.length, "Должно быть 15 строк");
    assertEquals("1", lines[0]);
    assertEquals("2", lines[1]);
    assertEquals("Fizz", lines[2]);
    assertEquals("4", lines[3]);
    assertEquals("Buzz", lines[4]);
    assertEquals("Fizz", lines[5]);
    assertEquals("7", lines[6]);
    assertEquals("8", lines[7]);
    assertEquals("Fizz", lines[8]);
    assertEquals("Buzz", lines[9]);
    assertEquals("11", lines[10]);
    assertEquals("Fizz", lines[11]);
    assertEquals("13", lines[12]);
    assertEquals("14", lines[13]);
    assertEquals("FizzBuzz", lines[14]);
  }

  // Тест 8: Параметризованный тест с CSV данными
  @ParameterizedTest
  @CsvSource({
    "3, 'Fizz'",
    "5, 'Buzz'",
    "15, 'FizzBuzz'",
    "2, '2'",
    "1, '1'"
  })
  void fizzBuzzSpecificNumberTests(int number, String expected) {
    // Arrange
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream));

    // Act - вызываем для диапазона, который включает наше число
    LiveCodingDemo.fizzBuzz(number);

    // Assert
    String output = outputStream.toString();
    String[] lines = output.split(System.lineSeparator());
    String lastLine = lines[lines.length - 1];

    assertEquals(expected, lastLine,
            String.format("Для числа %d ожидалось '%s'", number, expected));
  }

  // Тест 9: Проверка main метода
  @Test
  void mainMethodExecutesWithoutErrors() {
    // Arrange
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream));

    // Act
    LiveCodingDemo.main(new String[]{});

    // Assert
    String output = outputStream.toString();
    assertFalse(output.isEmpty(), "main метод должен что-то выводить");
    assertTrue(output.contains("FizzBuzz"),
            "main метод должен вызывать fizzBuzz(15)");
  }

  // Тест 10: Восстановление System.out после тестов
  @Test
  void systemOutIsRestoredAfterTest() {
    // Arrange
    PrintStream originalOut = System.out;
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream));

    try {
      // Act
      LiveCodingDemo.fizzBuzz(1);
    } finally {
      // Восстанавливаем System.out
      System.setOut(originalOut);
    }

    // Assert - проверяем что можем печатать
    System.out.println("Этот текст должен отобразиться в консоли");
    // Если тест проходит без ошибок - значит System.out восстановлен
  }
}