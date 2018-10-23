package lesson1;

import kotlin.NotImplementedError;

import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public class JavaTasks {
    /**
     * Сортировка времён
     * <p>
     * Простая
     * (Модифицированная задача с сайта acmp.ru)
     * <p>
     * Во входном файле с именем inputName содержатся моменты времени в формате ЧЧ:ММ:СС,
     * каждый на отдельной строке. Пример:
     * <p>
     * 13:15:19
     * 07:26:57
     * 10:00:03
     * 19:56:14
     * 13:15:19
     * 00:40:31
     * <p>
     * Отсортировать моменты времени по возрастанию и вывести их в выходной файл с именем outputName,
     * сохраняя формат ЧЧ:ММ:СС. Одинаковые моменты времени выводить друг за другом. Пример:
     * <p>
     * 00:40:31
     * 07:26:57
     * 10:00:03
     * 13:15:19
     * 13:15:19
     * 19:56:14
     * <p>
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static public void sortTimes(String inputName, String outputName) throws IOException {
        ArrayList<Integer> list = new ArrayList<>();
        Scanner input = new Scanner(new File(inputName));
        while (input.hasNextLine()) {
            String line = input.nextLine();
            if (line.matches("")) {
                throw new IllegalAccessError();
            } else {
                list.add(timeSecond(line));
            }
        }
        input.close();

        Collections.sort(list);

        FileWriter output = new FileWriter(new File(outputName));
        for ( Integer o : list) {
            output.write(String.valueOf(StringSec(o)));
        }
    }

    public static int timeSecond(String str) {
        String[] parts = str.split(":");
        int res = 0;
        for (String part : parts) {
            int num = Integer.parseInt(part);
            res = res * 60 + num;
        }
        return res;
    }

    public static String StringSec(int res) {
        String result = "";
        int hour = res / 3600;
        int minute = (res % 3600) / 60;
        int second = res % 60;
        return result = String.format("%02d, %02d, %02d", hour, minute, second);
    }

    /**
     * Сортировка адресов
     * <p>
     * Средняя
     * <p>
     * Пример:
     * <p>
     * Петров Иван - Железнодорожная 3
     * Сидоров Петр - Садовая 5
     * Иванов Алексей - Железнодорожная 7
     * Сидорова Мария - Садовая 5
     * Иванов Михаил - Железнодорожная 7
     * <p>
     * Людей в городе может быть до миллиона.
     * <p>
     * Вывести записи в выходной файл outputName,
     * упорядоченными по названию улицы (по алфавиту) и номеру дома (по возрастанию).
     * Людей, живущих в одном доме, выводить через запятую по алфавиту (вначале по фамилии, потом по имени). Пример:
     * <p>
     * Железнодорожная 3 - Петров Иван
     * Железнодорожная 7 - Иванов Алексей, Иванов Михаил
     * Садовая 5 - Сидоров Петр, Сидорова Мария
     * <p>
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static public void sortAddresses(String inputName, String outputName) throws IOException {
        Scanner input = new Scanner(new File(inputName));
        List<List<String>> list = new ArrayList<>();
        while (input.hasNextLine()) {
            String line = input.nextLine();
            if (line.matches("^[А-Я][а-я] + [А-Я][а-я] - [А-Я][а-я]+ [0-9]")) {
                throw new IllegalArgumentException();
            }
            String[] adres = line.split(" - ");
            list.add(Arrays.asList(adres[0], adres[1]));
        }
        input.close();

        Map<String, Set<String>> nList = new TreeMap<>();

    FileWriter output = new FileWriter(new File(outputName));

}


    /**
     * Сортировка температур
     * <p>
     * Средняя
     * (Модифицированная задача с сайта acmp.ru)
     * <p>
     * Во входном файле заданы температуры различных участков абстрактной планеты с точностью до десятых градуса.
     * Температуры могут изменяться в диапазоне от -273.0 до +500.0.
     * Например:
     * <p>
     * 24.7
     * -12.6
     * 121.3
     * -98.4
     * 99.5
     * -12.6
     * 11.0
     * <p>
     * Количество строк в файле может достигать ста миллионов.
     * Вывести строки в выходной файл, отсортировав их по возрастанию температуры.
     * Повторяющиеся строки сохранить. Например:
     * <p>
     * -98.4
     * -12.6
     * -12.6
     * 11.0
     * 24.7
     * 99.5
     * 121.3
     */
    static public void sortTemperatures(String inputName, String outputName) throws IOException {
        Scanner input = new Scanner(new File(inputName));
        ArrayList<Double> temper = new ArrayList<>();
        while (input.hasNextLine()) {
            String line = input.nextLine();
            double temp = Double.parseDouble(line);
            temper.add(temp);
        }
        input.close();

        Collections.sort(temper);

        FileWriter output = new FileWriter(new File(outputName));
        for (double t : temper) {
            output.write(String.valueOf(t));
        }
        output.close();
    }

    /**
     * Сортировка последовательности0..0.
     * <p>
     * Средняя
     * (Задача взята с сайта acmp.ru)
     * <p>
     * В файле задана последовательность из n целых положительных чисел, каждое в своей строке, например:
     * <p>
     * 1
     * 2
     * 3
     * 2
     * 3
     * 1
     * 2
     * <p>
     * Необходимо найти число, которое встречается в этой последовательности наибольшее количество раз,
     * а если таких чисел несколько, то найти минимальное из них,
     * и после этого переместить все такие числа в конец заданной последовательности.
     * Порядок расположения остальных чисел должен остаться без изменения.
     * <p>
     * 1
     * 3
     * 3
     * 1
     * 2
     * 2
     * 2
     */
    static public void sortSequence(String inputName, String outputName) {
        throw new NotImplementedError();
    }

    /**
     * Соединить два отсортированных массива в один
     * <p>
     * Простая
     * <p>
     * Задан отсортированный массив first и второй массив second,
     * первые first.size ячеек которого содержат null, а остальные ячейки также отсортированы.
     * Соединить оба массива в массиве second так, чтобы он оказался отсортирован. Пример:
     * <p>
     * first = [4 9 15 20 28]
     * second = [null null null null null 1 3 9 13 18 23]
     * <p>
     * Результат: second = [1 3 4 9 9 13 15 20 23 28]
     */
    static <T extends Comparable<T>> void mergeArrays(T[] first, T[] second) {
        throw new NotImplementedError();
    }

}