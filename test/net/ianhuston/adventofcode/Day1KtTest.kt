package net.ianhuston.adventofcode

import org.junit.Assert.*
import org.junit.Test

class Day1KtTest {

    @Test
    fun testSumNext() {
        assertEquals(6, sumNext("1212"))
        assertEquals(0, sumNext("1221"))
        assertEquals(4, sumNext("123425"))
        assertEquals(12, sumNext("123123"))
    }

    @Test
    fun testStringToInts() {
        assertEquals(listOf(1), stringToInts("1"))
        assertEquals(listOf(1,2,3), stringToInts("123"))
    }

    @Test
    fun testAppendToEnd() {
        assertEquals(listOf(1), appendToEnd(listOf(1)))
        assertEquals(listOf(1,2,1,2), appendToEnd(listOf(1,2)))
        assertEquals(listOf(1,2,3,1,2,3), appendToEnd(listOf(1,2,3)))
    }

    @Test
    fun testMatchNextAccumulator() {
        assertEquals(6, matchNextAccumulator(listOf(1,2,1,2,1,2,1,2)))
        assertEquals(0, matchNextAccumulator(listOf(1,2,2,1,1,2,2,1)))
        assertEquals(4, matchNextAccumulator(listOf(1,2,3,4,2,5,1,2,3,4,2,5)))
        assertEquals(12, matchNextAccumulator(listOf(1,2,3,1,2,3,1,2,3,1,2,3)))
    }
}