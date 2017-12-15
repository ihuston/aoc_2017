package net.ianhuston.adventofcode

import org.junit.Assert.*
import org.junit.Test

class Day15KtTest {

    @Test
    fun testFindPairs() {
        assertEquals(588, findPairs(starterA=65, starterB=8921))
    }

    @Test
    fun testNextValues() {
        assertEquals(Pair(1092455L, 430625591L), nextValues(Pair(65, 8921)))
        assertEquals(Pair(1181022009L, 1233683848L), nextValues(Pair(1092455L, 430625591L)))
        assertEquals(Pair(245556042L, 1431495498L), nextValues(Pair(1181022009L, 1233683848L)))
        assertEquals(Pair(1744312007L, 137874439L), nextValues(Pair(245556042L, 1431495498L)))
        assertEquals(Pair(1352636452L, 285222916L), nextValues(Pair(1744312007L, 137874439L)))
    }

    @Test
    fun testIsMatch() {
        assertFalse(isMatch(Pair(1092455, 430625591)))
        assertFalse(isMatch(Pair(1181022009, 1233683848)))
        assertTrue(isMatch(Pair(245556042, 1431495498)))
        assertFalse(isMatch(Pair(1744312007, 137874439)))
        assertFalse(isMatch(Pair(1352636452, 285222916)))
    }


}