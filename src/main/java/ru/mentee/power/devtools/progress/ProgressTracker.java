package ru.mentee.power.devtools.progress;

public class ProgressTracker {

    /**
     * Вычисляет суммарный прогресс группы mentee.
     *
     * @param mentees массив mentee
     * @return строка с информацией о суммарном прогрессе (пройдено/осталось уроков)
     */
    public String calculateTotalProgress(Mentee[] mentees) {
        if (mentees == null || mentees.length == 0) {
            return "Массив невалиден";
        }

        // Инициализация аккумулятора и счётчика
        int totalCompleted = 0;
        int totalTotal = 0;
        int index = 0;
        // Цикл while: проходим по всем элементам массива
        while (index < mentees.length) {
            totalCompleted += mentees[index].completedLessons(); // Используем геттер record'а
            totalTotal += mentees[index].totalLessons();
            index++;
        }
        int totalRemaining = totalTotal - totalCompleted;
        return "Суммарно: пройдено "+totalCompleted+" из "+totalTotal+" уроков, осталось "+totalRemaining+" уроков";
    }

    public static void main(String[] args) {
        ProgressTracker tracker = new ProgressTracker();

        // Создаём массив mentee (продолжение DVT-2: добавляем прогресс к личной карточке)
        Mentee[] mentees = {
                new Mentee("Иван", "Москва", "Backend разработка", 5, 12),
                new Mentee("Мария", "Санкт-Петербург", "Fullstack", 8, 12),
                new Mentee("Пётр", "Казань", "Java Backend", 12, 12)
        };

        String progress = tracker.calculateTotalProgress(mentees);
        System.out.println(progress);
    }
}
