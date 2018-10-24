package lesson2;

import kotlin.NotImplementedError;
import kotlin.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

@SuppressWarnings("unused")
public class JavaAlgorithms {
    /**
     * Получение наибольшей прибыли (она же -- поиск максимального подмассива)
     * Простая
     * <p>
     * Во входном файле с именем inputName перечислены цены на акции компании в различные (возрастающие) моменты времени
     * (каждая цена идёт с новой строки). Цена -- это целое положительное число. Пример:
     * <p>
     * 201
     * 196
     * 190
     * 198
     * 187
     * 194
     * 193
     * 185
     * <p>
     * Выбрать два момента времени, первый из них для покупки акций, а второй для продажи, с тем, чтобы разница
     * между ценой продажи и ценой покупки была максимально большой. Второй момент должен быть раньше первого.
     * Вернуть пару из двух моментов.
     * Каждый момент обозначается целым числом -- номер строки во входном файле, нумерация с единицы.
     * Например, для приведённого выше файла результат должен быть Pair(3, 4)
     * <p>
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static public Pair<Integer, Integer> optimizeBuyAndSell(String inputName) {
        throw new NotImplementedError();
    }

    /**
     * Задача Иосифа Флафия.
     * Простая
     * <p>
     * Образовав круг, стоят menNumber человек, пронумерованных от 1 до menNumber.
     * <p>
     * 1 2 3
     * 8   4
     * 7 6 5
     * <p>
     * Мы считаем от 1 до choiceInterval (например, до 5), начиная с 1-го человека по кругу.
     * Человек, на котором остановился счёт, выбывает.
     * <p>
     * 1 2 3
     * 8   4
     * 7 6 х
     * <p>
     * Далее счёт продолжается со следующего человека, также от 1 до choiceInterval.
     * Выбывшие при счёте пропускаются, и человек, на котором остановился счёт, выбывает.
     * <p>
     * 1 х 3
     * 8   4
     * 7 6 Х
     * <p>
     * Процедура повторяется, пока не останется один человек. Требуется вернуть его номер (в данном случае 3).
     * <p>
     * 1 Х 3
     * х   4
     * 7 6 Х
     * <p>
     * 1 Х 3
     * Х   4
     * х 6 Х
     * <p>
     * х Х 3
     * Х   4
     * Х 6 Х
     * <p>
     * Х Х 3
     * Х   х
     * Х 6 Х
     * <p>
     * Х Х 3
     * Х   Х
     * Х х Х
     */

    static public int josephTask(int menNumber, int choiceInterval) {
        int res = 0;
        if (menNumber == 0) {
            throw new NotImplementedError();
        }
        for (int i = 1; i <= menNumber; i++) {
            res = (res + choiceInterval) % i;
        }
        res++;
        return res;
    }

    /**
     * Наибольшая общая подстрока.
     * Средняя
     * <p>
     * Дано две строки, например ОБСЕРВАТОРИЯ и КОНСЕРВАТОРЫ.
     * Найти их самую длинную общую подстроку -- в примере это СЕРВАТОР.
     * Если общих подстрок нет, вернуть пустую строку.
     * При сравнении подстрок, регистр символов *имеет* значение.
     * Если имеется несколько самых длинных общих подстрок одной длины,
     * вернуть ту из них, которая встречается раньше в строке first.
     */
    static public String longestCommonSubstring(String first, String second) {
        int maxLeng = 0;
        int max = 0;
        int i,j;
        int k = first.length();
        int[][] leng = new int[k][];
        if (first.length() == 0 || second.length() == 0) {
            return "";
        }
        for (i = 0; i < leng.length; i++) {
            leng[i] = new int[second.length()];
            for (j = 0; j < leng[i].length; j++) {
                if (first.charAt(i) == second.charAt(j)) {
                    if (i != 0 && j != 0) {
                        leng[i][j] = leng[i - 1][j - 1] + 1;
                    } else {
                        leng[i][j] = 1;
                    }
                    if (leng[i][j] > maxLeng) {
                        maxLeng = leng[i][j];
                        max = i;
                    }
                }
            }
        }
        return first.substring(max - maxLeng + 1, max + 1);
    }

    /**
     * Число простых чисел в интервале
     * Простая
     * <p>
     * Рассчитать количество простых чисел в интервале от 1 до limit (включительно).
     * Если limit <= 1, вернуть результат 0.
     * <p>
     * Справка: простым считается число, которое делится нацело только на 1 и на себя.
     * Единица простым числом не считается.
     */
    static public int calcPrimesNumber(int limit) {
        int res = 0;
        for (int i = 1; i < limit; i++) {
            if (isPrime(i))
                res++;
        }
        return res;
    }

    public static boolean isPrime(int num) {
        if (num % 2 == 0 ) return false;
        if (num == 2) return true;
        for (int i = 3; i*i <= num; i=i+2) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Балда
     * Сложная
     * <p>
     * В файле с именем inputName задана матрица из букв в следующем формате
     * (отдельные буквы в ряду разделены пробелами):
     * <p>
     * И Т Ы Н
     * К Р А Н
     * А К В А
     * <p>
     * В аргументе words содержится множество слов для поиска, например,
     * ТРАВА, КРАН, АКВА, НАРТЫ, РАК.
     * <p>
     * Попытаться найти каждое из слов в матрице букв, используя правила игры БАЛДА,
     * и вернуть множество найденных слов. В данном случае:
     * ТРАВА, КРАН, АКВА, НАРТЫ
     * <p>
     * И т Ы Н     И т ы Н
     * К р а Н     К р а н
     * А К в а     А К В А
     * <p>
     * Все слова и буквы -- русские или английские, прописные.
     * В файле буквы разделены пробелами, строки -- переносами строк.
     * Остальные символы ни в файле, ни в словах не допускаются.
     */
    static public Set<String> baldaSearcher(String inputName, Set<String> words) throws FileNotFoundException {
        Scanner read = new Scanner(new File(inputName));
        ArrayList<char[]> list = new ArrayList<>();
        int j =0;
        while (read.hasNextLine()){
            String[] mz = read.nextLine().split(" ");
            list.add(new char[mz.length]);
            for (int i =0; i < mz.length; i++) {
                list.get(j)[i]=mz[i].charAt(0);
            }
        }
        TreeSet<String> res = new TreeSet<>();
        for (int i = 0; i < list.size(); i++){
            for (j = 0; j < list.get(i).length; j++){
                for (String word: words) {
                    if (balda(i,j,word.charAt(0),word.substring(1),list))
                        res.add(word);
                }
                words.removeAll(res);
            }
        }
        return res;
    }

    private static boolean balda(int i, int j, char charAt, String substring, ArrayList<char[]> deck) {
        if (charAt != deck.get(i)[j]) return false;
        int[][] move = {{1,0},{0,1},{-1,0},{0,-1}};
        for (int[] aMove : move) {
            int x = i + aMove[0];
            int y = j + aMove[1];
            if (y > 0 && y < deck.size() && x > 0 && x < deck.get(i).length) {
                if (balda(x, y, substring.charAt(0), substring.substring(1), deck))
                    return true;
            }
        }
        return false;
    }
}
