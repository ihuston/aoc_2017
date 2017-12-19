package net.ianhuston.adventofcode

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class Day19KtTest {

    lateinit var grid: List<List<Char>>

    @Before
    fun setup() {
        grid = listOf(
                listOf(' ', ' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '),
                listOf(' ', ' ', ' ', ' ', ' ', '|', ' ', ' ', '+', '-', '-', '+', ' ', ' ', ' ', ' '),
                listOf(' ', ' ', ' ', ' ', ' ', 'A', ' ', ' ', '|', ' ', ' ', 'C', ' ', ' ', ' ', ' '),
                listOf(' ', 'F', '-', '-', '-', '|', '-', '-', '-', '-', 'E', '|', '-', '-', '+', ' '),
                listOf(' ', ' ', ' ', ' ', ' ', '|', ' ', ' ', '|', ' ', ' ', '|', ' ', ' ', 'D', ' '),
                listOf(' ', ' ', ' ', ' ', ' ', '+', 'B', '-', '+', ' ', ' ', '+', '-', '-', '+', ' ')
        )
    }

    @Test
    fun testParseDay19Input() {
        val input = "     |          \n" +
                    "     |  +--+    \n" +
                    "     A  |  C    \n" +
                    " F---|----E|--+ \n" +
                    "     |  |  |  D \n" +
                    "     +B-+  +--+ \n" +
                    "\n"

        assertTrue(grid == parseDay19Input(input))
    }

    @Test
    fun testFollowPath() {
        assertEquals("ABCDEF", followPathFindingLetters(grid))
    }

    @Test
    fun testFollowPathCounting() {
        assertEquals(38, followPathCounting(grid))
    }

    @Test
    fun findStartingPoint() {
        assertEquals(Pair(5, 0), startingPoint(grid))
    }

    @Test
    fun testTakeStep() {
        val travellerAtStart = Traveller(location = Pair(5,0),
                                  direction = Pair(0, +1),
                                  lettersSeen = mutableListOf<Char>(),
                grid = grid)
        val travellerAtEnd = Traveller(location = Pair(5,1),
                direction = Pair(0, +1),
                lettersSeen = mutableListOf<Char>(),
                grid = grid, stepsTaken = 1)

        assertEquals(travellerAtEnd, travellerAtStart.takeStep())

    }

    @Test
    fun testCollectLetter() {
        val travellerAtStart = Traveller(location = Pair(5,2),
                direction = Pair(0, +1),
                lettersSeen = mutableListOf<Char>(),
                grid = grid)
        val travellerAtEnd = Traveller(location = Pair(5,3),
                direction = Pair(0, +1),
                lettersSeen = mutableListOf('A'),
                grid = grid, stepsTaken = 1)

        assertEquals(travellerAtEnd, travellerAtStart.takeStep())

    }

    @Test
    fun testMakeTurn() {
        val travellerAtStart = Traveller(location = Pair(5,5),
                direction = Pair(0, +1),
                lettersSeen = mutableListOf<Char>(),
                grid = grid)
        val travellerAtEnd = Traveller(location = Pair(6,5),
                direction = Pair(+1, 0),
                lettersSeen = mutableListOf<Char>(),
                grid = grid, stepsTaken = 1)

        assertEquals(travellerAtEnd, travellerAtStart.takeStep())

    }

    @Test
    fun testPassOver() {
        val travellerAtStart = Traveller(location = Pair(5,3),
                direction = Pair(0, +1),
                lettersSeen = mutableListOf<Char>(),
                grid = grid)
        val travellerAtEnd = Traveller(location = Pair(5,4),
                direction = Pair(0, +1),
                lettersSeen = mutableListOf<Char>(),
                grid = grid, stepsTaken = 1)

        assertEquals(travellerAtEnd, travellerAtStart.takeStep())

    }

    @Test
    fun testPassUnder() {
        val travellerAtStart = Traveller(location = Pair(6,3),
                direction = Pair(-1, 0),
                lettersSeen = mutableListOf<Char>(),
                grid = grid)
        val travellerAtEnd = Traveller(location = Pair(5,3),
                direction = Pair(-1, 0),
                lettersSeen = mutableListOf<Char>(),
                grid = grid, stepsTaken = 1)

        assertEquals(travellerAtEnd, travellerAtStart.takeStep())

    }

    @Test
    fun testEndOfPath() {
        val traveller = Traveller(location = Pair(0,3),
                direction = Pair(-1, 0),
                lettersSeen = mutableListOf<Char>(),
                grid = grid)
        assertTrue(traveller.endOfPath())
        traveller.location = Pair(1, 3)
        assertFalse(traveller.endOfPath())
    }
}