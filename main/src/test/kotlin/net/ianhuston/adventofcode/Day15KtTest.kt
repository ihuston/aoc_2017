package net.ianhuston.adventofcode

import org.junit.Assert.*
import org.junit.Test

class Day15KtTest {

    @Test
    fun testFindPairs() {
        assertEquals(309, findPairs(starterA=65, starterB=8921))
    }

    @Test
    fun testNextValues() {
        assertEquals(1352636452L, nextValue(65, generator=1))
        assertEquals(1992081072L, nextValue(1352636452L, generator=1))
        assertEquals(530830436L, nextValue(1992081072L, generator=1))
        assertEquals(1980017072L, nextValue(530830436L, generator=1))
        assertEquals(740335192L, nextValue(1980017072L, generator=1))

        assertEquals(1233683848L, nextValue(8921, generator=2))
        assertEquals(862516352L, nextValue(1233683848L, generator=2))
        assertEquals(1159784568L, nextValue(862516352L, generator=2))
        assertEquals(1616057672L, nextValue(1159784568L, generator=2))
        assertEquals(412269392L, nextValue(1616057672L, generator=2))

    }

    @Test
    fun testIsMatch() {
        assertFalse(isMatch(Pair(1092455, 430625591)))
        assertFalse(isMatch(Pair(1181022009, 1233683848)))
        assertTrue(isMatch(Pair(245556042, 1431495498)))
        assertFalse(isMatch(Pair(1744312007, 137874439)))
        assertFalse(isMatch(Pair(1352636452, 285222916)))
        assertTrue(isMatch(Pair(1023762912L, 896885216L)))
    }


}