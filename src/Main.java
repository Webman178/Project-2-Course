import Skypro.work.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Scanner;

public class Main {
    public static TaskService taskService = new TaskService();
    private static void printmenu() {
        System.out.println("1.Добавить задачу. 2. Удалить задачу 3.Получить задачу на указанный день. " +
                "4.Вывести все задачи 0. ВЫХОД");
    }
    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {
            boolean yes = true;
            while (yes) {
                printmenu();
                System.out.println("Выберете пункт меню:");
                if (scanner.hasNext()) {
                    int menu = scanner.nextInt();

                    switch (menu) {
                        case 1: // Добавить
                            try {
                                inputTask(scanner);
                            } catch (IncorrectArgumentException e) {
                                System.out.println("Ошибка: "+ e);
                            }
                            break;
                        case 2: // Удалить
                            removeTask(scanner);
                            break ;
                        case 3: // Получить задачи на указанный день
                            printAllByDate(scanner);
                            break ;
                        case 4: // Выести все задачи
                            taskService.printAllTasks();
                            break;
                        case 0:
                            yes=false;
                            break ;
                    }
                } else {
                    scanner.nextInt();
                    System.out.println("Выберете пунтк меню из СПИСКА!");
                }
            }
        }

    }

    private static void inputTask(Scanner scanner) throws IncorrectArgumentException {

        boolean isContinue = true;
        scanner.nextLine();

        String title ,description;
        do {
            System.out.println("Введите название задачи");
            title = scanner.nextLine();
            if (title.isBlank()) {
                System.out.println("Нет заголовка");
                continue;
            }else {
                isContinue=false;
                break;
            }
        }while (isContinue);
        isContinue=true;


        do {
            System.out.println("Введите описание задачи");
            description = scanner.nextLine();
            if (description.isBlank()){
                System.out.println("Нет описания");
                continue;
            }else {
                isContinue=false;
                break;
            }
        }while (isContinue);
        isContinue=true;

        String dateString;
        LocalDateTime localDateTime=LocalDateTime.now();


        LocalDate localDate = getLocalDateByScanner(scanner);
        isContinue=true;

        do {
            System.out.println("Введите время в формате: hh.mm");
            dateString=scanner.next();
            dateString = dateString.trim().replaceAll("\\s+", "");
            if (!dateString.isBlank() && dateString.length() >= 3 && dateString.length() <= 5) {
                String parts[] = dateString.split("[.:-]");
                if (parts.length != 2) {
                    System.out.println("Неверный ввод времени");
                    continue;
                }
                if (parts[0].matches("([0-1]?[0-9])|([2][0-3])") && parts[1].matches("([0-5]?[0-9])")) {
                    localDateTime = LocalDateTime.of(localDate, LocalTime.of(Integer.valueOf(parts[0]), Integer.valueOf(parts[1])));
                    if (localDate.isBefore(LocalDate.now()) && !localDate.isEqual(LocalDate.now())) {
                        System.out.println("Вы ввели неккоректное время!");
                        continue;
                    }
                    isContinue=false;
                    break;
                }else {
                    System.out.println("Неверный ввод времени");
                    continue;
                }
            }else {
                System.out.println("Неверный ввод времени");
                continue;
            }
        }while (isContinue);
        isContinue=true;
        //////////////////////////////////////////////// блок ввода типа заметки /////////////////////
        System.out.println("0-Личная заметка, 1-Рабочая заметка");
        Type type=Type.PERSONAL;
        System.out.println("Укажите тим рабочей заметки:");
        do {
            if (scanner.hasNextInt()) {
                int intType=scanner.nextInt();
                switch (intType) {
                    case 0:
                        type=Type.PERSONAL;
                        isContinue=false;
                        break;
                    case 1:
                        type= Type.WORK;
                        isContinue=false;
                        break;
                    default:
                        System.out.println("Ошибка, ввдите команду 0 или 1");
                        continue;
                }
            } else {
                System.out.println("Еще раз укажите номер заметки");
                continue;
            }
        } while (isContinue);
        isContinue=true;
        //////////////////////////////////////////////// блок ввода повторяемости ////////////////////
        System.out.println("0-однократно, 1-ежедневно, 2-еженедельно, 3-ежемесячно, 4- ежегодно");
        System.out.println("Введите признак повторяемости:");
        do {
            if (scanner.hasNextInt()) {
                int menu = scanner.nextInt();
                switch (menu) {

                    case 0:
                        taskService.add(new OneTimeTask(title,type,localDateTime,description));
                        isContinue=false;
                        break;
                    case 1:
                        taskService.add(new DailyTask(title,type,localDateTime,description));
                        isContinue=false;
                        break;
                    case 2:
                        taskService.add(new WeeklyTask(title,type,localDateTime,description));
                        isContinue=false;
                        break;
                    case 3:
                        taskService.add(new MonthlyTask(title,type,localDateTime,description));
                        isContinue=false;
                        break;
                    case 4:
                        taskService.add(new YearlyTask(title,type,localDateTime,description));
                        isContinue=false;
                        break;

                    default:
                        System.out.println("Ошибка, введите номер команды от 0 до 4");
                        break;
                }
            }else {
                scanner.next();
                System.out.println("Введите признак повторяемости ЕЩЕ раз");
            }
        }while (isContinue);
    } // inputTask

    static private LocalDate getLocalDateByScanner(Scanner scanner) {
        boolean isContinue=true;
        String dateString;
        LocalDate localDate=LocalDate.now();

        Integer day,month,year;
        do{
            System.out.println("Введите дату в формате dd.MM.yyyy:");
            dateString = scanner.next();
            dateString = dateString.trim().replaceAll("\\s+", "");
            if (dateString.length() >= 5 && dateString.length()<=10) {
                String parts[] = dateString.split("[.:-]");
                if (parts.length != 3) {
                    System.out.println("Неверный формат даты");
                    continue;
                }
                if (parts[0].matches("([0]?[1-9])|([12][0-9])|([3][01])") &&
                        parts[1].matches("([0]?[1-9])|([1][0-2])") &&
                        parts[2].matches("[0-9]?[0-9]?[0-9][0-9]")) {
                    day = Integer.valueOf(parts[0]);
                    month = Integer.valueOf(parts[1]);

                    if (parts[2].length() < 4) {
                        year = 2000 + Integer.valueOf(parts[2]);
                    } else {
                        year = Integer.valueOf(parts[2]);
                    }

                    localDate = LocalDate.of(year, month, 1);
                    int dayMax=localDate.getMonth().length(localDate.isLeapYear());
                    if (dayMax < day) {
                        System.out.println("Введенное количество дней больше, чем есть в данном месяце");
                        continue;
                    }
                    localDate=LocalDate.of(year,month,day);
                    if (localDate.isBefore(LocalDate.now()) && !localDate.isEqual(LocalDate.now())) {
                        System.out.println("Вы ввели устаревшую дату");
                        continue;
                    }
                    isContinue=false;
                    break;
                }else {
                    System.out.println("Некорретный ввод даты 2");
                    continue;
                }

            } else {
                System.out.println("Некорретный ввод даты 3");
                continue;
            }
        }while (isContinue);

        return localDate;
    }
    public static void removeTask(Scanner scanner) {
        System.out.println("Введите ID задачи для ее удаления:");
        Boolean isContinue=true;
        do{
            if (scanner.hasNextInt()) {
                int taskId=scanner.nextInt();
                try {
                    Task task=taskService.remove(taskId);
                    System.out.println("Задача успешно удалена: "+task);
                    isContinue=false;
                    break;
                } catch (TaskNotFoundException e) {
                    System.out.println("Задачи с таким ID не обнаружено");
                    break;
                }
            } else {
                System.out.println("Введите ID(число) для удаления задачи");
                scanner.hasNext();
                continue;
            }
        }while (isContinue);
    }

    public static void printAllByDate(Scanner scanner) {
        LocalDate localDate = getLocalDateByScanner(scanner);
        taskService.printTaskByDate(localDate);
    }

}