package net.ianhuston.adventofcode

import org.junit.Assert.*
import org.junit.Test
import kotlin.math.exp

class Day12KtTest {

    @Test
    fun testCountConnections() {
        val input = mapOf(0 to listOf(2),
                1 to listOf(1),
                2 to listOf(0, 3, 4),
                3 to listOf(2, 4),
                4 to listOf(2, 3, 6),
                5 to listOf(6),
                6 to listOf(4, 5))

        assertEquals(6, countConnectionsSeen(input, 0))
    }

    @Test
    fun testParseDay12Input() {
        val input = "0 <-> 2\n" +
                "1 <-> 1\n" +
                "2 <-> 0, 3, 4\n" +
                "3 <-> 2, 4\n" +
                "4 <-> 2, 3, 6\n" +
                "5 <-> 6\n" +
                "6 <-> 4, 5"

        val expected = mapOf(0 to listOf(2),
                1 to listOf(1),
                2 to listOf(0, 3, 4),
                3 to listOf(2, 4),
                4 to listOf(2, 3, 6),
                5 to listOf(6),
                6 to listOf(4, 5))

        assertEquals(expected, parseDay12Input(input))
    }

    @Test
    fun testParseSingleLine() {
        assertEquals(Pair(0, listOf(2)), parseSingleLine("0 <-> 2"))
    }

    @Test
    fun testCountTwoGroups() {
        val input = mapOf(0 to listOf(2),
                1 to listOf(1),
                2 to listOf(0, 3, 4),
                3 to listOf(2, 4),
                4 to listOf(2, 3, 6),
                5 to listOf(6),
                6 to listOf(4, 5))

        assertEquals(2, countGroups(input))
    }

    @Test
    fun testCountThreeGroups() {
        val input = mapOf(0 to listOf(2),
                1 to listOf(1),
                2 to listOf(0, 3, 4),
                3 to listOf(2, 4),
                4 to listOf(2, 3, 6),
                5 to listOf(6),
                6 to listOf(4, 5),
                7 to listOf(8,9),
                8 to listOf(7),
                9 to listOf(7))

        assertEquals(3, countGroups(input))
    }
}