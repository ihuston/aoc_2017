package net.ianhuston.adventofcode

import org.junit.Assert.*
import org.junit.Test

class Day3KtTest {
    @Test
    fun testSpiralCount() {
        assertEquals(0, spiralCount(1))
        assertEquals(3, spiralCount(12))
        assertEquals(2, spiralCount(23))
        assertEquals(31, spiralCount(1024))
    }
}