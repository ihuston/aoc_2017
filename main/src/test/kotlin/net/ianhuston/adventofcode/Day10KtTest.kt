package net.ianhuston.adventofcode

import org.junit.Assert.*
import org.junit.Test

class Day10KtTest {

    @Test
    fun testReverseSubList() {
        assertEquals(KnotList(listOf(2, 1, 0, 3, 4), 3, 1),
                KnotList(listOf(0, 1, 2, 3, 4), 0, 0).applyLength(3))
        assertEquals(KnotList(listOf(4, 3, 0, 1, 2), 3, 2),
                KnotList(listOf(2, 1, 0, 3, 4), 3, 1).applyLength(4))
        assertEquals(KnotList(listOf(4, 3, 0, 1, 2), 1, 3),
                KnotList(listOf(4, 3, 0, 1, 2), 3, 2).applyLength(1))
        assertEquals(KnotList(listOf(3, 4, 2, 1, 0), 4, 4),
                KnotList(listOf(4, 3, 0, 1, 2), 1, 3).applyLength(5))
    }

    @Test
    fun testWrappedSubList() {
        assertEquals(listOf(0, 1, 2), KnotList(listOf(0, 1, 2, 3, 4), 0, 0).wrappedSubList(3))
        assertEquals(listOf(3, 4, 0, 1), KnotList(listOf(0, 1, 2, 3, 4), 3, 0).wrappedSubList(4))
        assertEquals(listOf(4), KnotList(listOf(0, 1, 2, 3, 4), 4, 0).wrappedSubList(1))
    }

    @Test
    fun testKnotHash() {
        assertEquals("a2582a3a0e66e6e86e3812dcb672a272", knotHash(""))
        assertEquals("33efeb34ea91902bb2f59c9920caa6cd", knotHash("AoC 2017"))
        assertEquals("3efbe78a8d82f29979031a4aa0b16a9d", knotHash("1,2,3"))
        assertEquals("63960835bcdc130f0b66d7ff4f6a5a8e", knotHash("1,2,4"))
    }

    @Test
    fun testDenseHash() {
        assertEquals(listOf(64), denseHash(listOf(65, 27, 9, 1, 4, 3, 40, 50, 91, 7, 6, 0, 2, 5, 68, 22)))
        assertEquals(listOf(64, 64), denseHash(listOf(65, 27, 9, 1, 4, 3, 40, 50, 91, 7, 6, 0, 2, 5, 68, 22,
                                                      65, 27, 9, 1, 4, 3, 40, 50, 91, 7, 6, 0, 2, 5, 68, 22)))
    }

    @Test
    fun testConvertToHex() {
        assertEquals("4007ff", convertToHex(listOf(64, 7, 255)))
    }
}