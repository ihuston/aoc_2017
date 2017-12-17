package net.ianhuston.adventofcode

import org.junit.Assert.*
import org.junit.Test

class Day17KtTest {

    @Test
    fun testSpinlock() {
        assertEquals(638, spinlock(3))
    }

    @Test
    fun testForwardAndInsert() {
        assertEquals(Pair(listOf(0,1), 1),
                forwardAndInsert(Pair(listOf(0), 0), 3))
        assertEquals(Pair(listOf(0,2,1), 1),
                forwardAndInsert(Pair(listOf(0, 1), 1), 3))
        assertEquals(Pair(listOf(0, 2, 3, 1), 2),
                forwardAndInsert(Pair(listOf(0, 2, 1), 1), 3))
        assertEquals(Pair(listOf(0, 5, 7, 2, 4, 3, 8, 6, 1), 6),
                forwardAndInsert(Pair(listOf(0, 5, 7, 2, 4, 3, 6, 1), 2), 3))
    }
}