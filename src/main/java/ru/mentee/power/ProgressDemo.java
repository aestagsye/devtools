package ru.mentee.power;

public class ProgressDemo {
  public static void main(String[] args) {
  // напиши new MenteeProgress( "Имя студента", 1, 6)  затем выдели и
  // набери Ctrl + Alt + V (Windows/Linux) или Option +
  // Command + V (macOS) выделяет выражение в переменную.
    MenteeProgress heshegto = new MenteeProgress(
        "Heshegto", // возьми значение из своего плана DVT-0
        1,               // номер спринта
        6                // запланированные часы на спринт
    );
    var progress = heshegto;
    System.out.println("Debug: starting loop") //2.Забыт отладочный вывод

    //System.out.println(progress.summary()); 3. Закомментированный код
    if (progress.readyForSprint()) {
      System.out.println("Status: sprint ready");
    } else {
      System.out.println("Status: backlog first");
    }
    System.out.println("feature/DVT-3");
  }
}