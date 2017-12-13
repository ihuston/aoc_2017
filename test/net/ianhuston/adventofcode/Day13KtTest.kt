package net.ianhuston.adventofcode

import org.junit.Assert.*
import org.junit.Test

class Day13KtTest {

    @Test
    fun testFindSeverity() {
        val input = "0: 3\n" +
                "1: 2\n" +
                "4: 4\n" +
                "6: 4"

        assertEquals(24, findSeverity(input))

    }

    @Test
    fun testCaught() {
        assertTrue(caught(0, 3, 0))
        assertTrue(caught(0, 10, 0))
        assertFalse(caught(0, 0, 0))
        assertTrue(caught(1, 1, 0))
        assertTrue(caught(6, 4, 0))
    }

    @Test
    fun testCaughtWithDelay() {
        assertFalse(caught(0, 3, 1))
        assertTrue(caught(0, 2, 2))
        assertFalse(caught(0, 0, 15))
        assertTrue(caught(1, 1, 3))
        assertFalse(caught(6, 4, 10))
        assertTrue(caught(6, 4, 12))
    }

    @Test
    fun testFindMinDelay() {
        val input = "0: 3\n" +
                "1: 2\n" +
                "4: 4\n" +
                "6: 4"

        assertEquals(10, findMinDelay(input))

    }
}