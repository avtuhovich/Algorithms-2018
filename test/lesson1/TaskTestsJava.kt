package lesson1

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Assertions.fail
import org.junit.jupiter.api.Tag
import java.io.File
import java.io.FileWriter
import kotlin.test.Test

class TaskTestsJava : AbstractTaskTests() {

    @Test
    @Tag("Easy")
    fun testSortTimes() {
        sortTimes { inputName, outputName -> JavaTasks.sortTimes(inputName, outputName) }
    }

    @Test
    @Tag("Easy")
    fun testSortTimes2() {
        val wr = File("tmpIn").writeText("""
            00:00:00
            27:00:00
        """.trimIndent())
        try {
            JavaTasks.sortTimes("tmpIn", "tmpOut")
            fail<IllegalAccessError>()
        } catch (ex: IllegalAccessError) {
            File("tmpIn").delete()
        }
    }

    @Test
    @Tag("Normal")
    fun testSortAddresses() {
        sortAddresses { inputName, outputName -> JavaTasks.sortAddresses(inputName, outputName) }
    }

    @Test
    @Tag("Normal")
    fun testSortAddresses2() {
        val wr = File("tmpIn").writeText(""""
            Садовая 5 - Сидоров Петр, Сидорова Мария
            ЖЕлезноДОрожная 3 - ПEтров ИВАН
            """.trimIndent())
        try {
            JavaTasks.sortAddresses("tmpIn", "tmpOut")
            fail<IllegalArgumentException>();
        } catch (ex: IllegalArgumentException) {
            File("tmpIn").delete()
        }
    }

    @Test
    @Tag("Normal")
    fun testSortTemperatures() {
        sortTemperatures { inputName, outputName -> JavaTasks.sortTemperatures(inputName, outputName) }
    }

    @Test
    @Tag("Normal")
    fun testSortSequence() {
        sortSequence { inputName, outputName -> JavaTasks.sortSequence(inputName, outputName) }
    }

    @Test
    @Tag("Easy")
    fun testMergeArrays() {
        val result = arrayOf(null, null, null, null, null, 1, 3, 9, 13, 18, 23)
        JavaTasks.mergeArrays<Int>(arrayOf(4, 9, 15, 20, 23), result)
        assertArrayEquals(arrayOf(1, 3, 4, 9, 9, 13, 15, 18, 20, 23, 23), result)

        run {
            val (first, second, expectedResult) = generateArrays(20000, 20000)
            JavaTasks.mergeArrays<Int>(first, second)
            assertArrayEquals(expectedResult, second)
        }

        run {
            val (first, second, expectedResult) = generateArrays(500000, 500000)
            JavaTasks.mergeArrays<Int>(first, second)
            assertArrayEquals(expectedResult, second)
        }
    }
}