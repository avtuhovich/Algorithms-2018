package lesson5

import org.junit.jupiter.api.Tag
import kotlin.test.Test

class GraphTestsJava : AbstractGraphTests() {
    @Test
    @Tag("Normal")
    fun testFindEulerLoop() {
        findEulerLoop { let { JavaGraphTasks.findEulerLoop(it) } }
    }

    @Test
    @Tag("Normal")
    fun testFindEulerLoop2() {
        findEulerLoop2 { let { JavaGraphTasks.findEulerLoop(it) } }
    }

    @Test
    @Tag("Normal")
    fun testMinimumSpanningTree() {
        minimumSpanningTree { let { JavaGraphTasks.minimumSpanningTree(it) } }
    }

    @Test
    @Tag("Normal")
    fun testMinimumSpanningTree2() {
        minimumSpanningTree2 { let { JavaGraphTasks.minimumSpanningTree(it) } }
    }

    @Test
    @Tag("Hard")
    fun testLargestIndependentVertexSet() {
        largestIndependentVertexSet { let { JavaGraphTasks.largestIndependentVertexSet(it) } }
    }

    @Test
    @Tag("Hard")
    fun testLongestSimplePath() {
        longestSimplePath { let { JavaGraphTasks.longestSimplePath(it) } }
    }

}