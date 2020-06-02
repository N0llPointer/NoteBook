import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        printMenu(); // Выводится меню
        String insertLine = scanner.nextLine(); // Ввод пользователя

        while (!checkForTheEnd(insertLine)) { // Если введено не end или 0, цикл продолжает работу
            int userCode = getUserCode(insertLine); // Обработка ввода пользователя в числовой код
            switch (userCode) { // Выбор, что будет делать программа
                case 1:
                    addTaskToList(list, scanner);
                    break;
                case 2:
                    printTasks(list);
                    break;
                case 3:
                    deleteTasks(list, scanner);
                    break;
                case 0:
                    break;
                default:
                    printErrorMessage();
            }

            printMenu();
            insertLine = scanner.nextLine();

        }

        printTasks(list); // Вывод списка задач под конец работы программы.

    }

    /*
        Вывод меню
     */
    private static void printMenu() {
        System.out.println("Выберите действие: (введите end для выхода из программы)\n" +
                "1. Добавить задачу\n" +
                "2. Вывести список задач\n" +
                "3. Удалить задачу\n" +
                "0. Выход\n");
    }

    /*
        Сообщение об ошибке
     */
    private static void printErrorMessage() {
        System.out.println("Ошибка ввода, попробуйте еще раз!");
    }

    /*
        Проверка, ввел ли пользователь end или 0
        toLowerCase() - переводит все символы в строчные,
        чтобы программа работала при любом вводе слова end
     */
    private static boolean checkForTheEnd(String line) {
        return line.toLowerCase().equals("end") || line.equals("0");
    }

    /*
        Проверка, ввел ли пользователь finish
     */
    private static boolean checkForTheFinish(String line) {
        return line.toLowerCase().equals("finish");
    }

    /*
        Преобразует ввод в числовой код.
        Также присутствует защита от неправльного ввода.
        Если введено слово, тогда функция вернет -1,
        а программа продолжит работу.
     */
    private static int getUserCode(String line) {
        Scanner scanner = new Scanner(line);
        int code = -1;
        if (scanner.hasNextInt()) // Если в введенной строчке есть число, то это число и будет обработано.
            code = scanner.nextInt();
        return code;
    }

    /*
        Добавление введенной строки в список.
     */
    private static void addTaskToList(ArrayList<String> list, Scanner scanner) {
        System.out.println("Введите задачу для добавления в список:");
        String task = scanner.nextLine();
        list.add(task);
        System.out.println("Задача успещно добавлена!\n");
    }

    /*
        Вывод всех задач
     */
    private static void printTasks(ArrayList<String> list) {
        System.out.println("Список задач");
        int i = 0;
        for (String task : list) {
            System.out.println((i + 1) + ". " + task);
            i++;
        }
        System.out.println();
    }

    /*
        Удаление задачи из списка.
     */
    private static void deleteTasks(ArrayList<String> list, Scanner scanner) {
        String input;

        printTasks(list);
        System.out.println("Введите номер задачи для удаления: (введите finish для выхода из удаления)");
        input = scanner.nextLine();
        while (!checkForTheFinish(input)) { // Проверка на ввод слова "finish"
            int index = getUserCode(input); // Обработка ввода пользователя
            if (index < 0 || index > list.size()) {
                printErrorMessage();
            } else
                list.remove(index - 1); // Если введен существующий номер задачи, то только тогда происходит удаление.

            printTasks(list);
            System.out.println("Введите номер задачи для удаления: (введите finish для выхода из удаления)");
            input = scanner.nextLine();
        }
    }
}
