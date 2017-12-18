package net.ianhuston.adventofcode

import org.junit.Assert.*
import org.junit.Test

class Day6KtTest {

    @Test
    fun testRedistribute() {
        assertEquals(listOf(2,4,1,2), redistribute(listOf(0,2,7,0)))
        assertEquals(listOf(3,1,2,3), redistribute(listOf(2,4,1,2)))
        assertEquals(listOf(0,2,3,4), redistribute(listOf(3,1,2,3)))
        assertEquals(listOf(1,3,4,1), redistribute(listOf(0,2,3,4)))
        assertEquals(listOf(2,4,1,2), redistribute(listOf(1,3,4,1)))
    }

    @Test
    fun testCountCycle() {
        assertEquals(4, countCycle(listOf(0,2,7,0)))
    }

    @Test
    fun testArgMax() {
        assertEquals(2, argMax(listOf(1,2,3,1)))
        assertEquals(3, argMax(listOf(1,2,3,4)))
        assertEquals(2, argMax(listOf(1,2,3,3)))
        assertNull(argMax(emptyList<Int>()))
    }
}