package net.ianhuston.advent

import org.junit.Assert.*
import org.junit.Test
import kotlin.test.assertEquals

class Day5KtTest {

    @Test
    fun testExitCount() {
        val program = Program(arrayListOf(0,3,0,1,-3), 0)
        assertEquals(10, exitCount(program))
    }

    @Test
    fun testIncAfterStep() {
        val start = Program(arrayListOf(0,3,0,1,-3), 0)
        val end = Program(arrayListOf(1,3,0,1,-3), 0)
        assertEquals(end, takeStep(start))
    }

    @Test
    fun testDecAfterStep() {
        val start = Program(arrayListOf(0,3,0,1,-3), 1)
        val end = Program(arrayListOf(0,2,0,1,-3), 4)
        assertEquals(end, takeStep(start))
    }

    @Test
    fun testTwoSteps() {
        val start = Program(arrayListOf(0,3,0,1,-3), 0)
        val end = Program(arrayListOf(2,3,0,1,-3), 1)
        assertEquals(end, takeStep(takeStep(start)))
    }

    @Test
    fun testExitList() {
        assertFalse(exitedList(Program(arrayListOf(0,1,2), 0)))
        assertFalse(exitedList(Program(arrayListOf(0,1,2), 2)))
        assertTrue(exitedList(Program(arrayListOf(0,1,2), 3)))
        assertTrue(exitedList(Program(arrayListOf(0,1,2), -1)))

    }
}