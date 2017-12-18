package net.ianhuston.adventofcode

import org.junit.Assert.*
import org.junit.Test

class Day5KtTest {

    @Test
    fun testExitCount() {
        val program = Day5Program(arrayListOf(0,3,0,1,-3), 0)
        assertEquals(10, exitCount(program))
    }

    @Test
    fun testIncAfterStep() {
        val start = Day5Program(arrayListOf(0,3,0,1,-3), 0)
        val end = Day5Program(arrayListOf(1,3,0,1,-3), 0)
        assertEquals(end, takeStep(start))
    }

    @Test
    fun testDecAfterStep() {
        val start = Day5Program(arrayListOf(0,3,0,1,-3), 1)
        val end = Day5Program(arrayListOf(0,2,0,1,-3), 4)
        assertEquals(end, takeStep(start))
    }

    @Test
    fun testTwoSteps() {
        val start = Day5Program(arrayListOf(0,3,0,1,-3), 0)
        val end = Day5Program(arrayListOf(2,3,0,1,-3), 1)
        assertEquals(end, takeStep(takeStep(start)))
    }

    @Test
    fun testExitList() {
        assertFalse(exitedList(Day5Program(arrayListOf(0,1,2), 0)))
        assertFalse(exitedList(Day5Program(arrayListOf(0,1,2), 2)))
        assertTrue(exitedList(Day5Program(arrayListOf(0,1,2), 3)))
        assertTrue(exitedList(Day5Program(arrayListOf(0,1,2), -1)))

    }
}