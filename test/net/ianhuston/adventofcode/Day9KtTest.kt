package net.ianhuston.adventofcode

import org.junit.Assert.*
import org.junit.Test

class Day9KtTest {

    @Test
    fun testDiscardUntilGarbageEnd() {
        assertEquals(Pair("{}",11), discardUntilGarbageEnd("{<aaaaassdflj>}"))
        assertEquals(Pair("{}", 11), discardUntilGarbageEnd("{<aaaa!>assdflj>}"))
        assertEquals(Pair("{}", 16), discardUntilGarbageEnd("{<aaaaas<<<<<sdflj>}"))
        assertEquals(Pair("{}", 0), discardUntilGarbageEnd("{<!!>}"))
        assertEquals(Pair("{}", 14), discardUntilGarbageEnd("{<asdasd{assdfs}>}"))
    }

    @Test
    fun testGroupCounter() {
        assertEquals(1, groupCounter("{}"))
        assertEquals(3, groupCounter("{{}}"))
        assertEquals(5, groupCounter("{{}{}}"))
        assertEquals(6, groupCounter("{{{}}}"))
        assertEquals(16, groupCounter("{{{},{},{{}}}}"))
    }
}