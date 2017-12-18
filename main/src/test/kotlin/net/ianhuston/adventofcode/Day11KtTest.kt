package net.ianhuston.adventofcode

import org.junit.Assert.*
import org.junit.Test

class Day11KtTest {

    @Test
    fun testFindNumberOfSteps() {
        assertEquals(Pair(3,3), findNumberOfSteps("ne,ne,ne"))
        assertEquals(Pair(0,2), findNumberOfSteps("ne,ne,sw,sw"))
        assertEquals(Pair(2,2), findNumberOfSteps("ne,ne,s,s"))
        assertEquals(Pair(3,3), findNumberOfSteps("se,sw,se,sw,sw"))
    }

    @Test
    fun testParseInput() {
        assertEquals(listOf(Triple(1,0,-1), Triple(1,0,-1),
                Triple(1,0,-1)), parseInput("ne,ne,ne"))
        assertEquals(listOf(Triple(1,0,-1),
                Triple(1,0,-1),
                Triple(-1,0,1),
                Triple(-1,0,1)), parseInput("ne,ne,sw,sw"))
        assertEquals(listOf(Triple(1,0,-1),
                Triple(1,0,-1), Triple(0,-1,1),
                Triple(0,-1,1)), parseInput("ne,ne,s,s"))
        assertEquals(listOf(Triple(1,-1,0),
                Triple(-1,0,1),
                Triple(1,-1,0),
                Triple(-1,0,1),
                Triple(-1,0,1)), parseInput("se,sw,se,sw,sw"))
    }


}