package net.ianhuston.advent

import org.junit.Assert.*
import org.junit.Test

class Day7KtTest {

    @Test
    fun testBottomProgram() {
        val input = "pbga (66)\n" +
                "xhth (57)\n" +
                "ebii (61)\n" +
                "havc (66)\n" +
                "ktlj (57)\n" +
                "fwft (72) -> ktlj, cntj, xhth\n" +
                "qoyq (66)\n" +
                "padx (45) -> pbga, havc, qoyq\n" +
                "tknk (41) -> ugml, padx, fwft\n" +
                "jptl (61)\n" +
                "ugml (68) -> gyxo, ebii, jptl\n" +
                "gyxo (61)\n" +
                "cntj (57)"

        assertEquals("tknk", bottomProgram(input))
    }

    @Test
    fun testFindBottom() {
        val input = mapOf("padx" to Tower("padx", emptyList<String>(), 0),
                "ugml" to Tower("ugml", emptyList<String>(), 0),
                "fwft" to Tower("fwft", emptyList<String>(), 0),
                "tknk" to Tower("tknk", listOf("ugml", "padx", "fwft"), 0))

        assertEquals(Tower("tknk", listOf("ugml", "padx", "fwft"), 0),
                findBottom(input))
    }

    @Test
    fun testParseInput() {
        val input = "padx (66)\n" +
                "ugml (57)\n" +
                "fwft (61)\n" +
                "tknk (72) -> ugml, padx, fwft"
        val expected = mapOf("padx" to Tower("padx", emptyList<String>(), 66),
                "ugml" to Tower("ugml", emptyList<String>(), 57),
                "fwft" to Tower("fwft", emptyList<String>(), 61),
                "tknk" to Tower("tknk", listOf("ugml", "padx", "fwft"), 72))

        assertEquals(expected, parseInput(input))
    }

    @Test
    fun testSplitLine() {
        assertEquals(Tower("padx", emptyList<String>(), 66), splitLine("padx (66)"))
        assertEquals(Tower("padxa", emptyList<String>(), 66), splitLine("padxa (66)"))
        assertEquals(Tower("padxa", listOf("ugml"), 66), splitLine("padxa (66) -> ugml"))
        assertEquals(Tower("padxa", listOf("ugml", "aaa"), 66), splitLine("padxa (66) -> ugml, aaa"))
    }

    @Test
    fun testListSubWeights() {
        assertEquals(emptyList<Int>(), listSubWeights("a", mapOf("a" to Tower("a", emptyList(), 10))))
        assertEquals(listOf(10), listSubWeights("a", mapOf("a" to Tower("a", listOf("b"), 10),
                                                        "b" to Tower("b", emptyList(), 10))))
        assertEquals(listOf(10, 10), listSubWeights("a", mapOf("a" to Tower("a", listOf("b", "c"), 10),
                                                        "b" to Tower("b", emptyList(), 10),
                                                        "c" to Tower("c", emptyList(), 10))))
    }
}