package lesson2

import org.junit.jupiter.api.Tag
import kotlin.test.Test
import kotlin.test.assertEquals

class AlgorithmsTestsJava : AbstractAlgorithmsTests() {
    @Test
    @Tag("Easy")
    fun testOptimizeBuyAndSell() {
        optimizeBuyAndSell { JavaAlgorithms.optimizeBuyAndSell(it) }
    }

    @Test
    @Tag("Easy")
    fun testJosephTask() {
        josephTask { menNumber, choiceInterval -> JavaAlgorithms.josephTask(menNumber, choiceInterval) }
    }

    @Test
    @Tag("Easy")
    fun testJosephTask2() {
        assertEquals(367, JavaAlgorithms.josephTask(488, 7))
    }

    @Test
    @Tag("Normal")
    fun testLongestCommonSubstring() {
        longestCommonSubstring { first, second -> JavaAlgorithms.longestCommonSubstring(first, second) }
    }

    @Test
    @Tag("Normal")
    fun testLongString() {
        assertEquals("\nСкрипит деревянная нога, блестят оловянные глаза,\nЗабытого неба ожидает рука",
                JavaAlgorithms.longestCommonSubstring("""
                    Фига лежит в кармане последним оружием дурака, последним оружием дураков.
                    Город пропал в тумане, мелькнул огнями и был таков.
                    Но долго ль им собираться - компас, планшетка да борода -
                    Лишь детям да рудознатцам нужны изумрудные города
                    Скрипит деревянная нога, блестят оловянные глаза,
                    Забытого неба ожидает рука
                """.trimIndent(), """
                    Эот ль не "казус белли" - летучий домик, уваший вниз.
                    Совсем одрехлела Элли, у ней подагра и ревматизм.
                    Сгрызли до основания крысы волшебные башмачки,
                    Но Морган идет к Гаване, глядит на небо из-под руки.
                    Скрипит деревянная нога, блестят оловянные глаза,
                    Забытого неба ожидает рука
                """.trimIndent()))
    }

    @Test
    @Tag("Easy")
    fun testCalcPrimesNumber() {
        calcPrimesNumber { JavaAlgorithms.calcPrimesNumber(it) }
    }

    @Test
    @Tag("Easy")
    fun testCalcPrimesNumbers() {
        assertEquals(7837, JavaAlgorithms.calcPrimesNumber(80000))
    }

    @Test
    @Tag("Hard")
    fun testBaldaSearcher() {
        baldaSearcher { inputName, words -> JavaAlgorithms.baldaSearcher(inputName, words) }
    }

    @Test
    @Tag("Hard")
    fun testBaldaSearcher2() {
        assertEquals(setOf("КРОТ", "КУСТ", "РОТА", "ЭРА", "ЛОБ", "УСТА"),
                JavaAlgorithms.baldaSearcher("input/balda_in2.txt", setOf("КРОТ", "КУСТ", "РОТА", "ЭРА", "ЛОБ", "УСТА", "БУРЯ", "ЗЛО")))
    }
}