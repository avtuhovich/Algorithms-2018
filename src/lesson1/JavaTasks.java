package lesson1;

import kotlin.NotImplementedError;
import org.jetbrains.annotations.NotNull;

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
        List<Time> list = new ArrayList<>();
        Scanner input = new Scanner(new File(inputName));
        while (input.hasNextLine()) {
            String line = input.nextLine();
            if (!line.matches("(([01]\\d)|2[0-3]):[0-5]\\d:[0-5]\\d")) {
                throw new IllegalAccessError();
            } else {
                String[] tmp = line.split(":");
                list.add(new Time(tmp[0],tmp[1],tmp[2]));
            }
        }
        Collections.sort(list);
        FileWriter fw = new FileWriter(new File(outputName));
        for (Time t: list) {
            fw.write(String.format("%s:%s:%s\n",t.hh,t.mm,t.ss));
            fw.flush();
        }
    }

    static class Time implements Comparable<Time>{
        String hh, mm, ss;

        @Override
        public String toString() {
            return "Time{" +
                    "hh='" + hh + '\'' +
                    ", mm='" + mm + '\'' +
                    ", ss='" + ss + '\'' +
                    '}';
        }

        public Time(String hh, String mm, String ss) {
            this.hh = hh;
            this.mm = mm;
            this.ss = ss;
        }

        @Override
        public int compareTo(@NotNull Time o) {
            if (hh.compareTo(o.hh) != 0)
                return hh.compareTo(o.hh);
            if (mm.compareTo(o.mm) != 0)
                return mm.compareTo(o.mm);
            return ss.compareTo(o.ss);
        }
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
        SortedMap<String, TreeSet<String>> list = new TreeMap<>();
        while (input.hasNextLine()) {
            String line = input.nextLine();
            if (!line.matches("^[А-Я][а-я]+ [А-Я][а-я]+ - [А-Я][а-я]+ \\d+")) {
                throw new IllegalArgumentException();
            }
            String[] adres = line.split(" - ");
            if (list.containsKey(adres[1]))
                list.get(adres[1]).add(adres[0]);
            else {
                list.put(adres[1], new TreeSet<>());
                list.get(adres[1]).add(adres[0]);
            }
        }
        input.close();
        FileWriter output = new FileWriter(new File(outputName));
        for (String str: list.keySet()) {
            StringBuilder res = new StringBuilder();
            res.append(str).append(" - ");
            boolean fl = true;
            for (String nm: list.get(str)) {
                if (fl) fl = !fl;
                else res.append(", ");
                res.append(nm);
            }
            output.write(res.append("\n").toString());
            output.flush();
        }
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
        DataInputStream input = new DataInputStream(new FileInputStream(inputName));
        int[] cnt = new int[7730];
        while (input.available()>0) {
            StringBuilder str = new StringBuilder();
            byte t = input.readByte();
            while (t != '\n'){
                if(t != '.' && t != '\r')
                    str.append((char)t);
                t = input.readByte();
            }
            cnt[Integer.parseInt(str.toString())+2730]++;
        }
        input.close();
        FileWriter fw = new FileWriter(new File(outputName));
        int i = 0;
        while (i <cnt.length){
            for (int j = 0; j < cnt[i]; j++) {
                fw.write(String.format(Locale.US,"%.1f\n", (double) i /10 - 273.0));
                fw.flush();
            }
            i++;
        }
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